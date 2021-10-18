package gmc.project.innovatree.usersws.service.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import gmc.project.innovatree.usersws.entity.UsersEntity;
import gmc.project.innovatree.usersws.exceptions.PasswordMisMatchException;
import gmc.project.innovatree.usersws.exceptions.UserNotFoundException;
import gmc.project.innovatree.usersws.model.DeleteUserModel;
import gmc.project.innovatree.usersws.model.UsersEditModel;
import gmc.project.innovatree.usersws.model.UsersInfoModel;
import gmc.project.innovatree.usersws.service.UsersService;
import gmc.project.innovatree.usersws.dao.UserDao;
import gmc.project.innovatree.usersws.shared.UsersDto;

@Service
public class UsersServiceImpl implements UsersService {
	
	private final UserDao userDao;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	public UsersServiceImpl(UserDao userDao, BCryptPasswordEncoder bCryptPasswordEncoder) {
		super();
		this.userDao = userDao;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
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
		
		if(foundUser == null) throw new UserNotFoundException(userName);
		
		UsersDto returnValue = modelMapper.map(foundUser, UsersDto.class);
		
		return returnValue;
	}

	@Override
	public UsersDto findOne(String userId) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		UsersEntity foundUser = userDao.findByUserId(userId);
		UsersDto returnValue = modelMapper.map(foundUser, UsersDto.class);
		
		return returnValue;
	}

	@Override
	public void deleteUserById(String userId) {
		UsersEntity foundUser = userDao.findByUserId(userId);
		userDao.delete(foundUser);
	}

	@Override
	public UsersInfoModel logedInUser(String userId) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
				
		UsersEntity foundUser = userDao.findByUserId(userId);
		
		if(foundUser == null)
			throw new UserNotFoundException("The user with userId: " + userId + " Not FOund...");

		UsersInfoModel returnValue = modelMapper.map(foundUser, UsersInfoModel.class);
		
		return returnValue;
	}

	@Override
	public UsersEditModel logedInUserEdit(String userId) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		UsersInfoModel logedInUser = logedInUser(userId);
		UsersEditModel returnValue = modelMapper.map(logedInUser, UsersEditModel.class);
		
		return returnValue;
	}

	@Override
	public void deleteLogedInUser(DeleteUserModel deleteUserModel) {
		String userId = deleteUserModel.getUserId();
		
		UsersEntity foundUser = null;
		
		try {
			foundUser = userDao.findByUserId(userId);
		} catch (Exception e) {
			throw new UserNotFoundException("User ID : "+userId+" Not found...");
		}
		
		if(foundUser == null)
			throw new UserNotFoundException("User ID : "+userId+" Not found...");
		
		boolean passwordMatches = bCryptPasswordEncoder.matches(deleteUserModel.getPassword(), foundUser.getEncryptedPassword());

		if(deleteUserModel.getEmail() == foundUser.getEmail() && passwordMatches)
			deleteUserById(userId);
		else
			throw new PasswordMisMatchException("");
	}

	@Override
	public UsersInfoModel logedInUserSave(UsersEditModel usersEditModel) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		UsersEntity foundUser = null;
		
		foundUser = userDao.findByUserId(usersEditModel.getUserId());
		
		if(foundUser == null)
			throw new UserNotFoundException();
		
		foundUser.setFirstName(usersEditModel.getFirstName());
		foundUser.setLastName(usersEditModel.getLastName());
		foundUser.setAadharNumber(usersEditModel.getAadharNumber());
		
		if(foundUser.getEmail() != usersEditModel.getEmail())
			foundUser.setEmailVerified(false);
		
		foundUser.setEmail(usersEditModel.getEmail());
		foundUser.setPhoneNumber(usersEditModel.getPhoneNumber());
		
		UsersEntity savedUser = userDao.save(foundUser);
		UsersInfoModel returnValue = modelMapper.map(savedUser, UsersInfoModel.class);
		
		return returnValue;
	}

}
