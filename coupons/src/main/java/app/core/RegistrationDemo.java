package app.core;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import app.core.entities.Company;
import app.core.entities.Coupon;
import app.core.entities.Coupon.Category;
import app.core.entities.Customer;
import app.core.services.AdminService;
import app.core.services.CompanyService;

@Component
@Order(1)
public class RegistrationDemo implements CommandLineRunner {

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private CompanyService companyService;

//	@Autowired
//	private CouponRepo couponRepo;
	@Autowired
	private ApplicationContext ctx;

	@Override
	public void run(String... args) throws Exception {

		System.out.println("\n\t\t\t\t==================================== ");
		System.out.println("\t\t\t\t============ ADMIN DEMO ============ ");
		System.out.println("\t\t\t\t==================================== ");

//		adminService.adminLogin(); // Administrator documentation as user
		System.out.println("\n========== CREATE COMPANIES & USERS ==========");
		Company company1 = ctx.getBean(Company.class);
		company1.setName("Osem");
		company1.setEmail("osem@mail");
		company1.setPassword("osem");
		company1.setLogoImage("https://upload.wikimedia.org/wikipedia/he/2/22/Osem_Logo.svg");
		adminService.addCompany(company1);
		System.out.println("The company: " + adminService.getCompanyById(company1.getId()) + " added successfully");
		
		Company company2 = ctx.getBean(Company.class);
		company2.setName("Teva");
		company2.setEmail("teva@mail");
		company2.setPassword("teva");
		company2.setLogoImage("https://findlogovector.com/wp-content/uploads/2018/12/teva-pharmaceutical-industries-logo-vector.png");
		adminService.addCompany(company2);
		
		Company company3 = ctx.getBean(Company.class);
		company3.setName("Gali");
		company3.setEmail("gali@mail");
		company3.setPassword("gali");
		company3.setLogoImage("https://res.cloudinary.com/crunchbase-production/image/upload/c_lpad,h_170,w_170,f_auto,b_white,q_auto:eco,dpr_1/51f7127620af4ed2707f");
		adminService.addCompany(company3);
		System.out.println("The company: " + adminService.getCompanyById(company3.getId()) + " added successfully");
		
		System.out.println("\n========== CREATE CUSTOMERS ==========");
		Customer customer1 = ctx.getBean(Customer.class);
		customer1.setFirstName("Dina");
		customer1.setLastName("Levi");
		customer1.setEmail("dina@gmail.com");
		customer1.setPassword("dina");
		customer1.setUserName("Din Din");
		adminService.addCustomer(customer1);
		System.out.println("The customer: " + adminService.getCustomerById(customer1.getId()) + " added successfully");
		
		Customer customer2 = ctx.getBean(Customer.class);
		customer2.setFirstName("Dan");
		customer2.setLastName("Ramon");
		customer2.setUserName("Darmon");
		customer2.setEmail("ramon@mail");
		customer2.setPassword("ramo");
		adminService.addCustomer(customer2);
		System.out.println("The customer: " + adminService.getCustomerById(customer2.getId()) + " added successfully");
	
		Customer customer3 = ctx.getBean(Customer.class);
		customer3.setFirstName("Richard");
		customer3.setLastName("Badash");
		customer3.setUserName("Richie");
		customer3.setEmail("richard@gmail.com");
		customer3.setPassword("rich");
		adminService.addCustomer(customer3);
		System.out.println("The customer: " + adminService.getCustomerById(customer3.getId()) + " added successfully");
		
		System.out.println("\n\t\t\t\t==================================== ");
		System.out.println("\t\t\t\t========== COMPANY DEMO ============ ");
		System.out.println("\t\t\t\t==================================== ");
		
		System.out.println("\n========== COPMANIES CREATE COUPONS ==========");
		Coupon coupon1 = ctx.getBean(Coupon.class);
		company1.addCoupon(coupon1);
		coupon1.setCategory(Category.CAMPING);
		coupon1.setTitle("Tent");
		coupon1.setDescription("Tent for 3 people");
		coupon1.setStartDate(LocalDate.of(2023, 1, 1));
		coupon1.setEndDate(LocalDate.of(2023, 12, 31));
		coupon1.setAmount(18);
		coupon1.setPrice(180);
		coupon1.setImage("https://contents.mediadecathlon.com/p1801201/k$0a8edcb82a4529014a350391aff69c8b/trekking-3-seasons-freestanding-3-person-tent-trek-500-grey-orange.jpg?format=auto&quality=40&f=800x800");		
		companyService.addCouponToCompany(company1.getId(), coupon1);
		
		Coupon coupon2 = ctx.getBean(Coupon.class);
		coupon2.setCompany(company2);
		coupon2.setCategory(Category.FOOD);
		coupon2.setTitle("White bread");
		coupon2.setDescription("10% discount");
		coupon2.setStartDate(LocalDate.of(2023, 1, 1));
		coupon2.setEndDate(LocalDate.of(2023, 12, 31));
		coupon2.setAmount(10);
		coupon2.setPrice(7);
		coupon2.setImage("https://www.goldmedalbakery.com/content/uploads/2019/12/Sandwich-White.jpg");
		companyService.addCouponToCompany(company2.getId(), coupon2);

		Coupon coupon3 = ctx.getBean(Coupon.class);
		coupon3.setCompany(company3);
		coupon3.setCategory(Category.SPORTS);
		coupon3.setTitle("Sports Shoes");
		coupon3.setDescription("8% discount");
		coupon3.setStartDate(LocalDate.of(2023, 1, 1));
		coupon3.setEndDate(LocalDate.of(2023, 12, 10));
		coupon3.setAmount(15);
		coupon3.setPrice(180);
		coupon3.setImage("https://s3.eu-west-2.amazonaws.com/media.getit.co.il/Products/Huge/121820.jpg");
		companyService.addCouponToCompany(company3.getId(), coupon3);

		Coupon coupon4 = ctx.getBean(Coupon.class);
		coupon4.setCompany(company3);
		coupon4.setCategory(Category.CLOTHINGS);
		coupon4.setTitle("Cool Jeans");
		coupon4.setDescription("buy 2 get 1 free");
		coupon4.setStartDate(LocalDate.of(2023, 1, 1));
		coupon4.setEndDate(LocalDate.of(2023, 12, 31));
		coupon4.setAmount(30);
		coupon4.setPrice(220);
		coupon4.setImage("https://cdn.shopify.com/s/files/1/0299/8563/6396/products/jeans_dsquared2_blu_222431uje000006-470n-3_4000x@2x.progressive.jpg?v=1673309803");
		companyService.addCouponToCompany(company3.getId(), coupon4);

		Coupon coupon5 = ctx.getBean(Coupon.class);
		coupon5.setCompany(company3);
		coupon5.setCategory(Category.CLOTHINGS);
		coupon5.setTitle("OLD Jeans");
		coupon5.setDescription("buy 2 get 1 free");
		coupon5.setStartDate(LocalDate.of(2023, 1, 1));
		coupon5.setEndDate(LocalDate.of(2023, 4, 1));
		coupon5.setAmount(30);
		coupon5.setPrice(220);
		coupon5.setImage("https://cdn.shopify.com/s/files/1/0299/8563/6396/products/jeans_dsquared2_blu_222431uje000006-470n-3_4000x@2x.progressive.jpg?v=1673309803");
		companyService.addCouponToCompany(company3.getId(), coupon5);
				
	}
}