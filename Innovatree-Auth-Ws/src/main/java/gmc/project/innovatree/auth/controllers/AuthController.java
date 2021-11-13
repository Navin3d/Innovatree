package gmc.project.innovatree.auth.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gmc.project.innovatree.auth.config.AuthConstants;
import gmc.project.innovatree.auth.model.SignUpRequestModel;
import gmc.project.innovatree.auth.model.SignUpResponseModel;
import gmc.project.innovatree.auth.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	private final AuthService authService;

	public AuthController(AuthService authService) {
		super();
		this.authService = authService;
	}
	
	@PostMapping("/signup")
	@CrossOrigin(origins = AuthConstants.corsUrl)
	public SignUpResponseModel signUp(@RequestBody SignUpRequestModel signUpRequestModel) {
		SignUpResponseModel returnValue = authService.createUser(signUpRequestModel);
		return returnValue;
	}
	
}
