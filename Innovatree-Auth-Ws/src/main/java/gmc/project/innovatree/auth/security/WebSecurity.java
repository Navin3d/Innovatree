package gmc.project.innovatree.auth.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import gmc.project.innovatree.auth.service.AuthService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
	
	private final AuthService authService;
	private final Environment environment;
	private final BCryptPasswordEncoder passwordEncoder;
	
	public WebSecurity(AuthService authService, Environment environment, BCryptPasswordEncoder passwordEncoder) {
		super();
		this.authService = authService;
		this.environment = environment;
		this.passwordEncoder = passwordEncoder;
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/**").permitAll()
		.and()
			.headers().frameOptions().disable()
		.and()
			.csrf().disable();
		http.addFilter(getAuthenticationFilter());
	}
	
	
	private AuthenticationFilter getAuthenticationFilter() throws Exception {
		AuthenticationFilter authFilter = new AuthenticationFilter(authService, environment, authenticationManager());
		authFilter.setFilterProcessesUrl(environment.getProperty("login.url.path"));
//		authFilter.setFilterProcessesUrl("/users/login");
		log.debug("Filter process Url: "+environment.getProperty("login.url.path"));
		return authFilter;
	}	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(authService).passwordEncoder(passwordEncoder);
	}

}
