package app.core.services;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.core.auth.JwtUtil;
import app.core.auth.UserCredentials;
import app.core.entities.Company;
import app.core.entities.Customer;
import app.core.entities.User;
import app.core.entities.User.ClientType;
import app.core.repositories.CompanyRepo;
import app.core.repositories.CustomerRepo;

@Service
@Transactional
public class AuthService {

	@Value("${admin.email}")
	private String adminEmail;
	
	@Value("${admin.password}")
	private String adminPassword;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private CompanyRepo companyRepo;

	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private ApplicationContext ctx;


	public String login(UserCredentials userCredentials) throws LoginException {
		User user = ctx.getBean(User.class);
		switch (userCredentials.getClientType()) {
		case ADMINISTRATION:
			if (!userCredentials.getEmail().equals(adminEmail)) {
				throw new LoginException(
						"loging failed - admin with email " + userCredentials.getEmail() + " not found");
			} else if (!userCredentials.getPassword().equals(adminPassword)) {
				throw new LoginException(
						"loging failed - admin with password " + userCredentials.getEmail() + " not found");
			} else {
				user.setClientType(ClientType.ADMINISTRATION);
				user.setEmail(userCredentials.getEmail());
				user.setPassword(userCredentials.getPassword());
			}
			break;
		case COMPANY:
			Company company = companyRepo
					.findByEmailAndPassword(userCredentials.getEmail(), userCredentials.getPassword())
					.orElseThrow(() -> new LoginException("loging failed - user with email "
							+ userCredentials.getEmail() + " not found or password incoerrect"));
			user.setClientType(ClientType.COMPANY);
			user.setId(company.getId());
			user.setName(company.getName());
			user.setEmail(company.getEmail());
			user.setLogoImage(company.getLogoImage());

			break;
		case CUSTOMER:
			Customer customer = customerRepo
					.findByEmailAndPassword(userCredentials.getEmail(), userCredentials.getPassword())
					.orElseThrow(() -> new LoginException("loging failed - user with email "
							+ userCredentials.getEmail() + " not found or password incoerrect"));
			user.setClientType(ClientType.CUSTOMER);
			user.setId(customer.getId());
			user.setFirstName(customer.getFirstName());
			user.setLastName(customer.getLastName());
			user.setUserName(customer.getUserName());
			break;
		}
		return this.jwtUtil.generateToken(user);
	}

}