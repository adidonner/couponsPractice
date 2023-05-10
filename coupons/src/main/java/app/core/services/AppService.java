package app.core.services;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.core.entities.Coupon;
import app.core.entities.Customer;
import app.core.exceptions.CouponsException;
import app.core.exceptions.CustomersException;
import app.core.repositories.CouponRepo;
import app.core.repositories.CustomerRepo;

@Service
@Transactional
public class AppService {
	
	@Autowired
	private CustomerRepo customerRepo;
	@Autowired
	private CouponRepo couponRepo;

	public Coupon purchaseCoupon(int customerId, int couponId) throws Exception {
		Optional<Customer> optCustomer = Optional.of(customerRepo.findById(customerId)
				.orElseThrow(() -> new CustomersException(
						"purchaseCoupon faild - customerId: " + customerId + "not found")));
		Optional<Coupon> optCoupon = Optional.of(couponRepo.findById(couponId)
				.orElseThrow(() -> new CouponsException(
						"purchaseCoupon faild - couponId: " + couponId + "not found")));
		if (optCoupon.isPresent() && optCustomer.isPresent()) {
			Customer customer = optCustomer.get();
			Coupon coupon = optCoupon.get();
			customer.addCoupon(coupon);
			return coupon;
		} else {
			throw new CouponsException("purchaseCoupon faild - customer already has this coupon or coupon amount zero");
		}
	}

}
