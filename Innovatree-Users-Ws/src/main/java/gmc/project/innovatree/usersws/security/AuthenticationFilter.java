package gmc.project.innovatree.usersws.security;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import gmc.project.innovatree.usersws.model.LoginRequestModel;
import gmc.project.innovatree.usersws.service.UsersService;
import gmc.project.innovatree.usersws.shared.UserDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final UsersService usersService;
	private final Environment env;
	
	public AuthenticationFilter(UsersService usersService, Environment env, AuthenticationManager authenticationManager) {
		super.setAuthenticationManager(authenticationManager);;
		this.usersService = usersService;
		this.env = env;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		
		try {
			LoginRequestModel creds = new ObjectMapper().readValue(request.getInputStream(), LoginRequestModel.class);
			
			return getAuthenticationManager().authenticate(
					new UsernamePasswordAuthenticationToken(
							creds.getUserName(), 
							creds.getPassword(), 
							new ArrayList<>()
							)
					);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest req, 
											HttpServletResponse res, 
											FilterChain chain, 
											Authentication auth) throws IOException, ServletException {
		
		String secret = Base64.getEncoder().encodeToString(env.getProperty("jwt.token.secret").getBytes(StandardCharsets.UTF_8));
		
		String userName = ((User)auth.getPrincipal()).getUsername();
		
		UserDto foundUser = usersService.findUserByUserName(userName);
		
		String token = Jwts.builder()
				.setIssuer(env.getProperty("jwt.token.issuer"))
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(env.getProperty("jwt.token.expiration_time"))))
				.setSubject(foundUser.getUserId())
				.signWith(SignatureAlgorithm.HS512, secret)
				.compact();
		
		res.addHeader("Authorization", "Bearer " + token);
	}
	
}
