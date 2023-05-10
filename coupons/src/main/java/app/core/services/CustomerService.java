package app.core.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.core.entities.Coupon;
import app.core.entities.Coupon.Category;
import app.core.entities.Customer;
import app.core.exceptions.CouponsException;
import app.core.exceptions.CustomersException;
import app.core.repositories.CouponRepo;
import app.core.repositories.CustomerRepo;

@Service
@Scope("prototype")
@Transactional
public class CustomerService extends ClientService {

	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private CouponRepo couponRepo;


	public List<Coupon> getAllCoupons(){
		return couponRepo.findAll();
	}
	
	public Coupon purchaseCoupon(int customerId, int couponId) {
		Customer customer = customerRepo.findById(customerId)
				.orElseThrow(() -> new CustomersException("purchase failed - customer not found"));
		Coupon coupon = couponRepo.findById(couponId)
				.orElseThrow(() -> new CouponsException("purchase failed -coupon not found"));
		if (coupon.getAmount() > 0 && !customer.getCoupons().contains(coupon)) {
			customer.addCoupon(coupon);
			int x = coupon.getAmount() - 1;
			coupon.setAmount(x);
			couponRepo.flush();
			return coupon;
		} else {
			throw new CouponsException("purchaseCoupon faild - customer already has this coupon or coupon amount zero");
		}
	}

	/**
	 * @param couponId
	 * @return
	 * @throws CouponsException if the specified coupon not exists
	 */
	public Coupon getCouponById(int customerId, int couponId) throws CouponsException {
		return couponRepo.findById(couponId)
				.orElseThrow(() -> new CouponsException("getCouponById failed - not found" + couponId));
	}

	public List<Coupon> getCustomerCoupons(int customerId) {
		List<Coupon> coupons = customerRepo.getById(customerId).getCoupons();
		return coupons;
	}

	 public List<Coupon>getCustomerCouponsByCategory(int customerId, Category category){
	       
		 return couponRepo.findAllByCustomersAndCategory(customerRepo.getById(customerId), category);
    }
		

	public List<Coupon> getCustomrCouponsLessThanMaxPrice(int customerId, double maxPrice) {
		return couponRepo.findAllByCustomersAndPriceLessThan(customerRepo.getById(customerId), maxPrice);
	}

	public Customer customerDetails(int customerId) {
		return customerRepo.findById(customerId)
				.orElseThrow(() -> new CustomersException("getCustomerDetails failed - not found"));
	}

}
