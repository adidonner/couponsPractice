package app.core.repositories;

import java.time.LocalDate;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import app.core.entities.Coupon;
import app.core.entities.Customer;
import app.core.entities.Coupon.Category;

public interface CouponRepo extends JpaRepository<Coupon, Integer> {

	boolean existsByTitle(String title);

	List<Coupon> findAllByCompanyIdAndCategory(int id, Category category);

	List<Coupon> findAllByCompanyIdAndPriceLessThan(int id, double maxPrice);

	Optional<Coupon> findByIdAndCompanyId(int couponId, int id);

	List<Coupon> findByEndDateBefore(LocalDate now);

	List<Coupon> findAllByCustomersAndCategory(Customer customers, Category category);
	
	List<Coupon> findAllByCustomersAndPriceLessThan(Customer customers, double maxPrice);
}
