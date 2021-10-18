package gmc.project.innovatree.auth.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import gmc.project.innovatree.auth.model.SignUpRequestModel;
import gmc.project.innovatree.auth.model.SignUpResponseModel;
import gmc.project.innovatree.auth.shared.UsersDto;

public interface AuthService extends UserDetailsService {
	UsersDto findUserByUserName(String userName);
	SignUpResponseModel createUser(SignUpRequestModel signUpRequestModel);
}
