package app.core.services;

import java.time.LocalDate;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.core.entities.Coupon;
import app.core.exceptions.CustomersException;
import app.core.repositories.CouponRepo;

@Service
@Transactional
public class DailyJobService {

	@Autowired
	private CouponRepo couponRepo;

	public void checkCouponExpiration() {
		try {
			if(!couponRepo.findByEndDateBefore(LocalDate.now()).isEmpty()) {
				List<Coupon> couponsToDelete = couponRepo.findByEndDateBefore(LocalDate.now());
				System.out.println("coupons are expired and be deleted:\n" + couponsToDelete);
				couponRepo.deleteAll(couponsToDelete);	
			}
		} catch (Exception e) {
			throw new CustomersException("coupon check expiration error", e);
		}
	}

}
