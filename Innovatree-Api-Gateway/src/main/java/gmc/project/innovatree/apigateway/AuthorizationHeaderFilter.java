package gmc.project.innovatree.apigateway;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.google.common.net.HttpHeaders;

import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class AuthorizationHeaderFilter extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter> {

	private final Environment environment;
	
	public AuthorizationHeaderFilter(Environment environment) {
		super(AuthorizationHeaderFilter.class);
		this.environment = environment;
	}
	
	public static class Config {
		
	}

	@Override
	public GatewayFilter apply(AuthorizationHeaderFilter config) {
		return (exchange, chain) -> {
			ServerHttpRequest request = exchange.getRequest();
			
			if(!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION))
				return onError(exchange, "No Authrization Header", HttpStatus.UNAUTHORIZED);
			
			String authorizationHeader = request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
			String jwt = authorizationHeader.replace("Bearer", "");
			
			log.debug("isJwt: "+jwt);

			if(!isJwtValid(jwt)) {
				return onError(exchange, "The JWT is Invalid.", HttpStatus.UNAUTHORIZED);
			}
			
			return chain.filter(exchange);
		};
	}
	
	private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus status) {
		log.error("error"+err);
		ServerHttpResponse response = exchange.getResponse();
		response.setStatusCode(status);
		return response.setComplete();
	}
	
	private boolean isJwtValid(String jwt) {
		boolean isValid = true;
		
		String subject = null;
		String secretKey = Base64.getEncoder().encodeToString(environment.getProperty("jwt.token.secret").getBytes(StandardCharsets.UTF_8));
		
		log.debug("Environment: " + environment.getProperty("jwt.token.secret"));
		try {
			
			subject = Jwts.parser()
					.setSigningKey(secretKey)
					.parseClaimsJws(jwt)
					.getBody().getSubject();
			
		} catch (Exception e) {
			isValid = false;
			e.printStackTrace();
		}
		
		log.debug("The Subject is: "+subject);
		if(subject == null || subject.isBlank()) {
			isValid = false;
		}
		
		return isValid;
	}

}
