package app.core.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.core.entities.Company;
import app.core.entities.Coupon;
import app.core.entities.Coupon.Category;
import app.core.exceptions.CompaniesException;
import app.core.exceptions.CouponsException;
import app.core.repositories.CompanyRepo;
import app.core.repositories.CouponRepo;

/**
 * @author adido
 *
 */
@Service
@Transactional
public class CompanyService extends ClientService {

	@Autowired
	private CompanyRepo companyRepo;
	@Autowired
	private CouponRepo couponRepo;

	
	public Coupon addCouponToCompany(int companyId, Coupon coupon) throws CouponsException {
		if (!this.couponRepo.existsById(coupon.getId())) {
			companyRepo.getOne(companyId).addCoupon(coupon);
			return coupon;
		} else {
			throw new CouponsException(
					"addCouponToCompany failed - a coupon with this id already exists" + coupon.getId());
		}
	}

	/**
	 * @param couponId
	 * @return
	 * @throws CouponsException if the specified coupon not exists
	 */
	public Coupon getCouponById(int companyId, int couponId) throws CouponsException {
		Coupon coupon = couponRepo.findById(couponId)
				.orElseThrow(() -> new CouponsException("Coupon Id: " + couponId + "not found "));
		if (coupon.getCompany().getId() != companyId) {
			throw new CouponsException("coupon not from this company");
		} else {
			return coupon;
		}
	}

	public List<Coupon> getCouponsForCompany(int companyId) {
		return companyRepo.findById(companyId).get().getCoupons();
	}

	public List<Coupon> getAllCompanyCouponsByCategory(int companyId, Category category) {
		return couponRepo.findAllByCompanyIdAndCategory(companyId, category);
	}

	public List<Coupon> getAllCompanyCouponsLessThanMaxPrice(int companyId, double maxPrice) {
		List<Coupon> coupons = couponRepo.findAllByCompanyIdAndPriceLessThan(companyId, maxPrice);
		return coupons;
	}

	/**
	 * @param coupon
	 * @return
	 * @throws CouponsException if the specified coupon not exists
	 */
	public Coupon updateCoupon(int companyId, Coupon coupon) throws CouponsException {
		if (this.couponRepo.existsById(coupon.getId())) {
			coupon.setCompany(companyRepo.getOne(companyId));
			return this.couponRepo.save(coupon);
		} else {
			throw new CouponsException("updateCoupon failed - not found" + coupon.getId());
		}
	}

	/**
	 * @param couponId
	 * @throws CouponsException if the specified coupon not exists
	 */
	public void deleteCouponById(int companyId, int couponId) throws CouponsException {
		if (couponRepo.existsById(couponId)) {
			couponRepo.deleteById(couponId);
			System.out.println("coupon id: " + couponId + " deleted");
		} else {
			throw new CouponsException("deleteCouponById failed - not found: " + couponId);
		}
	}

	public Company getCompanyDetails(int companyId) throws CompaniesException {
		return companyRepo.findById(companyId)
				.orElseThrow(() -> new CompaniesException("getCompanyDetails failed - not found"));
	}

}
