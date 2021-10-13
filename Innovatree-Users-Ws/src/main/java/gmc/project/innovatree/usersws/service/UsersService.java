package gmc.project.innovatree.usersws.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import gmc.project.innovatree.usersws.model.SignUpRequestModel;
import gmc.project.innovatree.usersws.model.SignUpResponseModel;
import gmc.project.innovatree.usersws.shared.UserDto;

public interface UsersService extends UserDetailsService {
	UserDto findUserByUserName(String userName);
	SignUpResponseModel createUser(SignUpRequestModel signUpRequestModel);
}
