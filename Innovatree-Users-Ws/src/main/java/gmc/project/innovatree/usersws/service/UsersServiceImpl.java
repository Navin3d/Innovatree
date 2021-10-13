package gmc.project.innovatree.usersws.service;

import java.util.ArrayList;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import gmc.project.innovatree.usersws.data.UsersEntity;
import gmc.project.innovatree.usersws.data.UsersRepository;
import gmc.project.innovatree.usersws.model.Role;
import gmc.project.innovatree.usersws.model.SignUpRequestModel;
import gmc.project.innovatree.usersws.model.SignUpResponseModel;
import gmc.project.innovatree.usersws.model.UserDao;
import gmc.project.innovatree.usersws.shared.UserDto;

@Service
public class UsersServiceImpl implements UsersService {
	
	private final UsersRepository usersRepository;
	private final BCryptPasswordEncoder passwordEncoder;

	public UsersServiceImpl(UsersRepository usersRepository, BCryptPasswordEncoder passwordEncoder) {
		super();
		this.usersRepository = usersRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				
		if(username.contains("@")) {
			UsersEntity foundUser = usersRepository.findByEmail(username);
			
			if(foundUser == null) throw new UsernameNotFoundException(username + " Not FOund...");
			
			return new User(foundUser.getEmail(), foundUser.getEncryptedPassword(), foundUser.isEnable(), foundUser.isActive(), true, true, new ArrayList<>());
		
		} else {
			UsersEntity foundUser = usersRepository.findByPhoneNumber(username);
			
			if(foundUser == null) throw new UsernameNotFoundException(username + " Not FOund...");
			
			return new User(foundUser.getPhoneNumber(), foundUser.getEncryptedPassword(), foundUser.isEnable(), foundUser.isActive(), true, true, new ArrayList<>());
		}
					
	}

	@Override
	public UserDto findUserByUserName(String userName) {
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		UsersEntity foundUser = null;
		
		if(userName.contains("@")) {
			foundUser = usersRepository.findByEmail(userName);
		} else {
			foundUser = usersRepository.findByPhoneNumber(userName);
		}
		
		if(foundUser == null) throw new UsernameNotFoundException(userName);
		
		UserDto returnValue = modelMapper.map(foundUser, UserDto.class);
		
		return returnValue;
		
	}

	@Override
	public SignUpResponseModel createUser(SignUpRequestModel signUpRequestModel) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		UserDao user = modelMapper.map(signUpRequestModel, UserDao.class);
		user.setUserId(UUID.randomUUID().toString());
		user.setEncryptedPassword(passwordEncoder.encode(signUpRequestModel.getPassword()));
		user.setRole(Role.USER);
		user.setActive(true);
		user.setEnable(true);
		
		UsersEntity detachedUser = modelMapper.map(user, UsersEntity.class);
		UsersEntity savedUser = usersRepository.save(detachedUser);
		
		SignUpResponseModel returnValue = modelMapper.map(savedUser, SignUpResponseModel.class);
		return returnValue;
	}

}
