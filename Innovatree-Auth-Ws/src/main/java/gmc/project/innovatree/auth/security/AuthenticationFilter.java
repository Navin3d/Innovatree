package gmc.project.innovatree.auth.security;

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

import gmc.project.innovatree.auth.service.AuthService;
import gmc.project.innovatree.auth.shared.UsersDto;
import gmc.project.innovatree.auth.model.LoginRequestModel;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthService authService;
	private final Environment env;
	
	public AuthenticationFilter(AuthService authService, Environment env, AuthenticationManager authenticationManager) {
		this.authService = authService;
		this.env = env;
		super.setAuthenticationManager(authenticationManager);
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
		
		UsersDto foundUser = authService.findUserByUserName(userName);
		
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
