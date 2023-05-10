package app.core.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import app.core.entities.User;
import app.core.entities.User.ClientType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;


@Component
	public class JwtUtil extends JwtUtilAbstract<User, String> {

	@Override
	public String generateToken(User user) {
		// create a map of all needed claims
		System.out.println(user);
		Map<String, Object> claims = new HashMap<>();
		claims.put("clientType", user.getClientType());
		claims.put("email", user.getEmail());
		claims.put("id", user.getId());
		claims.put("name", user.getName());
		claims.put("firstName", user.getFirstName());
		claims.put("lastName", user.getLastName());
		claims.put("userName", user.getUserName());
		claims.put("logoImage", user.getLogoImage());
		String token = this.createToken(claims, user.getEmail());
		return token;
	}

	@Override
	public User extractUser(String token) throws JwtException {
		Claims claims = this.extractAllClaims(token);
		String email = claims.getSubject();
		int id = claims.get("id", Integer.class);
		String name = claims.get("name", String.class);
		String firstName = claims.get("firstName", String.class);
		String lastName = claims.get("lastName", String.class);
		String userName = claims.get("username", String.class);
		String logoImage = claims.get("logoImage", String.class);
		ClientType clientType = ClientType.valueOf(claims.get("clientType", String.class));
		User user = new User(id, email, null, name, firstName, lastName, userName, logoImage, clientType);
		return user;
	}

}