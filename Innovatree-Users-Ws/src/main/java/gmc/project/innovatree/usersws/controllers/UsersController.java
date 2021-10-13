package gmc.project.innovatree.usersws.controllers;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gmc.project.innovatree.usersws.model.SignUpRequestModel;
import gmc.project.innovatree.usersws.model.SignUpResponseModel;
import gmc.project.innovatree.usersws.service.UsersService;

@RestController
@RequestMapping("/users")
public class UsersController {
	
	private final UsersService usersService;
	private final Environment env;

	public UsersController(UsersService usersService, Environment env) {
		super();
		this.usersService = usersService;
		this.env = env;
	}
	
	@PostMapping("/signup")
	public ResponseEntity<SignUpResponseModel> userSignUp(@RequestBody SignUpRequestModel signUpRequestModel) {
		SignUpResponseModel returnValue = usersService.createUser(signUpRequestModel);
		return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
	}
	
	@GetMapping("/status/check")
	public String serverStatus() {
		return "The server is Up and running in port: " + env.getProperty("local.server.port");
	}

}
