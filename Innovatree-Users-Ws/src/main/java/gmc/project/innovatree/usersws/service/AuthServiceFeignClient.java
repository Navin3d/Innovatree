package gmc.project.innovatree.usersws.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import gmc.project.innovatree.usersws.model.LoginRequestModel;
import gmc.project.innovatree.usersws.model.SignUpRequestModel;
import gmc.project.innovatree.usersws.model.SignUpResponseModel;

@FeignClient("Innovatree-Auth-Ws")
public interface AuthServiceFeignClient {
	
	@PostMapping("/auth/signup")
	SignUpResponseModel createUser(@RequestBody SignUpRequestModel signUpRequestModel);
	
	@PostMapping("/auth/login")
	void loginUser(@RequestBody LoginRequestModel loginRequestModel);
}
