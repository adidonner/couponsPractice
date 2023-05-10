package app.core.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import app.core.entities.Company;
import app.core.entities.Coupon;
import app.core.entities.Coupon.Category;
import app.core.entities.User;
import app.core.exceptions.CouponsException;
import app.core.services.CompanyService;

@RestController
@RequestMapping("api/company")
@CrossOrigin
public class CompanyController {

	@Autowired
	private CompanyService companyService;

	@GetMapping(path = "/details", headers = { HttpHeaders.AUTHORIZATION })
	public Company getCompanyDetails(HttpServletRequest req) {
		User user = (User) req.getAttribute("user");
		return companyService.getCompanyDetails(user.getId());
	}

	@PostMapping(path = "/add-coupon", headers = { HttpHeaders.AUTHORIZATION })
	@ResponseStatus(HttpStatus.CREATED)
	public Coupon addCouponToCompany(HttpServletRequest req, @ModelAttribute Coupon coupon) {
		try {
			User user = (User) req.getAttribute("user");
			System.out.println(coupon);
			return this.companyService.addCouponToCompany(user.getId(), coupon);
		} catch (CouponsException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping(path = "/coupon-id/{couponId}", headers = { HttpHeaders.AUTHORIZATION })
	public Coupon getCouponById(HttpServletRequest req, @PathVariable int couponId) {
		try {
			User user = (User) req.getAttribute("user");
			return this.companyService.getCouponById(user.getId(), couponId);
		} catch (CouponsException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@GetMapping(path = "/all-coupons", headers = { HttpHeaders.AUTHORIZATION })
	public List<Coupon> getCouponsForCompany(HttpServletRequest req) {
		User user = (User) req.getAttribute("user");
		return this.companyService.getCouponsForCompany(user.getId());
	}

	@GetMapping(path = "/category-coupons", headers = { HttpHeaders.AUTHORIZATION })
	public List<Coupon> getAllCompanyCouponsBy(HttpServletRequest req, @RequestParam Category category) {
		User user = (User) req.getAttribute("user");
		return this.companyService.getAllCompanyCouponsByCategory(user.getId(), category);
	}

	@GetMapping(path = "/max-price-coupons", headers = { HttpHeaders.AUTHORIZATION })
	public List<Coupon> getAllCompanyCouponsLessThan(HttpServletRequest req, @RequestParam double maxPrice) {
		User user = (User) req.getAttribute("user");
		return this.companyService.getAllCompanyCouponsLessThanMaxPrice(user.getId(), maxPrice);
	}

	@PutMapping(path = "/update-coupon", headers = { HttpHeaders.AUTHORIZATION })
	public Coupon updateCoupon(HttpServletRequest req, @ModelAttribute Coupon coupon) {
		try {
			User user = (User) req.getAttribute("user");
			return this.companyService.updateCoupon(user.getId(), coupon);
		} catch (CouponsException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@DeleteMapping(path = "/delete-coupon-id/{couponId}", headers = { HttpHeaders.AUTHORIZATION })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCouponById(HttpServletRequest req, @PathVariable int couponId) {
		try {
			User user = (User) req.getAttribute("user");
			this.companyService.deleteCouponById(user.getId(), couponId);
		} catch (CouponsException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

}
