package app.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.EnableScheduling;

import app.core.entities.Company;
import app.core.entities.Coupon;
import app.core.entities.Customer;
import app.core.entities.User;


@EnableScheduling
@Configuration
@ComponentScan
public class Config {

	@Bean
	@Scope("prototype")
	public Company company() {
		Company company = new Company();
		return company;
	}

	@Bean
	@Scope("prototype")
	public Customer customer() {
		Customer customer = new Customer();
		return customer;

	}
	
	@Bean
	@Scope("prototype")
	public Coupon coupon() {
		Coupon coupon = new Coupon();
		return coupon;
	}
	@Bean
	@Scope("prototype")
	public User user() {
		User user = new User();
		return user;
	}

}
