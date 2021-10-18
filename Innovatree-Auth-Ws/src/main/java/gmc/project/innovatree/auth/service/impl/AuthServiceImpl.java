package gmc.project.innovatree.auth.service.impl;

import java.util.ArrayList;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import gmc.project.innovatree.auth.service.AuthService;
import gmc.project.innovatree.auth.shared.UsersDto;
import gmc.project.innovatree.auth.entity.UsersEntity;
import gmc.project.innovatree.auth.dao.UserDao;
import gmc.project.innovatree.auth.model.Role;
import gmc.project.innovatree.auth.model.SignUpRequestModel;
import gmc.project.innovatree.auth.model.SignUpResponseModel;

@Service
public class AuthServiceImpl implements AuthService {
	
	private final UserDao userDao;
	private final BCryptPasswordEncoder passwordEncoder;

	public AuthServiceImpl(UserDao userDao, BCryptPasswordEncoder passwordEncoder) {
		super();
		this.userDao = userDao;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				
		if(username.contains("@")) {
			UsersEntity foundUser = userDao.findByEmail(username);
			
			if(foundUser == null) throw new UsernameNotFoundException(username + " Not FOund...");
			
			return new User(foundUser.getEmail(), foundUser.getEncryptedPassword(), foundUser.isEnable(), foundUser.isActive(), true, true, new ArrayList<>());
		
		} else {
			UsersEntity foundUser = userDao.findByPhoneNumber(username);
			
			if(foundUser == null) throw new UsernameNotFoundException(username + " Not FOund...");
			
			return new User(foundUser.getPhoneNumber(), foundUser.getEncryptedPassword(), foundUser.isEnable(), foundUser.isActive(), true, true, new ArrayList<>());
		}
					
	}

	@Override
	public UsersDto findUserByUserName(String userName) {
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		UsersEntity foundUser = null;
		
		if(userName.contains("@")) {
			foundUser = userDao.findByEmail(userName);
		} else {
			foundUser = userDao.findByPhoneNumber(userName);
		}
		
		if(foundUser == null) throw new UsernameNotFoundException(userName);
		
		UsersDto returnValue = modelMapper.map(foundUser, UsersDto.class);
		
		return returnValue;
		
	}

	@Override
	public SignUpResponseModel createUser(SignUpRequestModel signUpRequestModel) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		UsersEntity detachedUser = modelMapper.map(signUpRequestModel, UsersEntity.class);
		detachedUser.setUserId(UUID.randomUUID().toString());
		detachedUser.setEncryptedPassword(passwordEncoder.encode(signUpRequestModel.getPassword()));
		detachedUser.setRole(Role.USER);
		
		UsersEntity savedUser = userDao.save(detachedUser);
		
		SignUpResponseModel returnValue = modelMapper.map(savedUser, SignUpResponseModel.class);
		return returnValue;
	}

}
