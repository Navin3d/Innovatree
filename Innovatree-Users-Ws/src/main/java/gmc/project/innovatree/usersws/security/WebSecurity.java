package gmc.project.innovatree.usersws.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import gmc.project.innovatree.usersws.service.UsersService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
	
	private final UsersService userService;
	private final Environment env;
	private final BCryptPasswordEncoder passwordEncoder;
	
	public WebSecurity(UsersService userService, Environment env, BCryptPasswordEncoder passwordEncoder) {
		super();
		this.userService = userService;
		this.env = env;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/users/**").permitAll()
			.anyRequest().authenticated()
		.and()
			.headers().frameOptions().disable()
		.and()
			.csrf().disable();
		http.addFilter(getAuthenticationFilter());
	}
	
	private AuthenticationFilter getAuthenticationFilter() throws Exception {
		AuthenticationFilter authFilter = new AuthenticationFilter(userService, env, authenticationManager());
//		authFilter.setFilterProcessesUrl(env.getProperty("login.path.url"));
		authFilter.setFilterProcessesUrl("/users/login");
		log.debug("Filter process Url: "+env.getProperty("login.path.url"));
		return authFilter;
	}	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
	}

}
