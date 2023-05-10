package app.core.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString(exclude = {"coupons"})
@Entity
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(unique = true, nullable = false)
	private String name;
	private String email;
	@Column(length = 4)
	private String password;
	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
 	private List<Coupon> coupons;

	private String logoImage; 

	// CTOR - bind coupons to this company
//	public Company(int id, String name, String email, String password, List<Coupon> coupons) {
//		super();
//		this.id = id;
//		this.name = name;
//		this.email = email;
//		this.password = password;
//		if (coupons != null) {
//			setCoupons(coupons); // use the setter that binds
//		}
//	}
	public Company(int id, String name, String email, String password, String logoImage, List<Coupon> coupons) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.logoImage = logoImage;
		
		if (coupons != null) {
			setCoupons(coupons); // use the setter that binds
		}
	}
	
	
	public void addCoupon(Coupon coupon) {
		if (this.coupons == null) {
			this.coupons = new ArrayList<>();
		}
		coupon.setCompany(this);
		this.coupons.add(coupon);
	}

	// this setter is instead of the Lombok setter which doesn't work
	public void setCoupons(List<Coupon> coupons) {
		for (Coupon coupon : coupons) {
			coupon.setCompany(this);
		}
		this.coupons = coupons;
	}


}

