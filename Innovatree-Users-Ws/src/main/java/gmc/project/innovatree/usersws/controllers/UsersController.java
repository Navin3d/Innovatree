package gmc.project.innovatree.usersws.controllers;

import java.util.List;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gmc.project.innovatree.usersws.model.DeleteUserModel;
import gmc.project.innovatree.usersws.model.UsersEditModel;
import gmc.project.innovatree.usersws.model.UsersInfoModel;
import gmc.project.innovatree.usersws.service.UsersService;
import gmc.project.innovatree.usersws.shared.UsersDto;

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
	
	@GetMapping
	public ResponseEntity<List<UsersDto>> getAvailUsers() {
		List<UsersDto> returnValue = usersService.getAllUsers();
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(returnValue);
	}

	@GetMapping("/email")
	public ResponseEntity<List<String>> getAvailUsersEmail() {
		List<String> returnValue = usersService.getUsersEmail();
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(returnValue);
	}
	
	@GetMapping("/phonenumber")
	public ResponseEntity<List<String>> getAvailUsersPno() {
		List<String> returnValue = usersService.getUsersPno();
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(returnValue);
	}
	
	@GetMapping("/{userId}")
	public UsersDto getUser(@PathVariable String userId) {
		UsersDto returnValue = usersService.findOne(userId);
		return returnValue;
	}
	
	@GetMapping("/profile/{userId}")
	public ResponseEntity<UsersInfoModel> userProfile(@PathVariable String userId) {
		UsersInfoModel returnValue = usersService.logedInUser(userId);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(returnValue);
	}
	
	@GetMapping("/profile/{userId}/edit")
	public ResponseEntity<UsersEditModel> userProfileEdit(@PathVariable String userId) {
		UsersEditModel returnValue = usersService.logedInUserEdit(userId);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(returnValue);
	}
	
	@PostMapping("/delete")
	public String userProfileDelete(@RequestBody DeleteUserModel deleteUserModel) {
		usersService.deleteLogedInUser(deleteUserModel);
		return "redirect:/";
	}
	
	@PostMapping("/edit")
	public String updateUser(@RequestBody UsersEditModel usersEditModel) {
		usersService.logedInUserSave(usersEditModel);
		return "redirect:/users/profile";
	}
	
	@GetMapping("/status/check")
	public String serverStatus() {
		return "The server is Up and running in port: " + env.getProperty("local.server.port");
	}

}
