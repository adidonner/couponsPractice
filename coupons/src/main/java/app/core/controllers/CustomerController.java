package app.core.controllers;

import java.util.List;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import app.core.entities.Coupon;
import app.core.entities.Coupon.Category;
import app.core.entities.Customer;
import app.core.entities.User;
import app.core.exceptions.CouponsException;
import app.core.services.CustomerService;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping(path = "/purchase-coupon/{couponId}", headers = { HttpHeaders.AUTHORIZATION })
	@ResponseStatus(HttpStatus.CREATED)
	public Coupon purchaseCoupon(HttpServletRequest req, @PathVariable int couponId) {
		try {
			User user = (User) req.getAttribute("user");
			return this.customerService.purchaseCoupon(user.getId(), couponId);
		} catch (CouponsException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping(path = "/get-customer-coupons", headers = { HttpHeaders.AUTHORIZATION })
	public List<Coupon> getCustomerCoupons(HttpServletRequest req) {
		User user = (User) req.getAttribute("user");
		return this.customerService.getCustomerCoupons(user.getId());
	}

	@GetMapping(path = "/get-coupon/{couponId}", headers = { HttpHeaders.AUTHORIZATION })
	public Coupon getCouponById(HttpServletRequest req, @PathVariable int couponId) {
		try {
			User user = (User) req.getAttribute("user");
			return this.customerService.getCouponById(user.getId(), couponId);
		} catch (CouponsException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
	
	@GetMapping(path = "/details", headers = { HttpHeaders.AUTHORIZATION })
	public Customer getCustomerDetails(HttpServletRequest req) {
		User user = (User) req.getAttribute("user");
		return this.customerService.customerDetails(user.getId());
	}
	
	@GetMapping(path = "/all-companies-coupons", headers = { HttpHeaders.AUTHORIZATION })
	public List<Coupon> getAllCompanies() {
		return customerService.getAllCoupons();
	}




	@GetMapping(path = "/category-coupons", headers = { HttpHeaders.AUTHORIZATION })
	public List<Coupon> getCustomerCouponsByCategory(HttpServletRequest req, Category category) {
		User user = (User) req.getAttribute("user");
		return this.customerService.getCustomerCouponsByCategory(user.getId(), category);
				
	}

	@GetMapping(path = "/max-price-coupons", headers = {HttpHeaders.AUTHORIZATION})
	public List<Coupon> getCustomrCouponsLessThanMaxPrice(HttpServletRequest req, double maxPrice) {
		User user = (User) req.getAttribute("user");
		return this.customerService.getCustomrCouponsLessThanMaxPrice(user.getId(), maxPrice);
	}

}
