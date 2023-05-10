package app.core.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import app.core.entities.Company;
import app.core.entities.Customer;
import app.core.exceptions.CompaniesException;
import app.core.exceptions.CustomersException;
import app.core.services.AdminService;

@RestController
@RequestMapping("api/admin")
@CrossOrigin
public class AdminController {

	@Autowired
	private AdminService adminService;

	@PostMapping("/add-company")
	@ResponseStatus(HttpStatus.CREATED)
	public Company addCompany(@ModelAttribute Company company) { // solves response error 415
		try {
			return this.adminService.addCompany(company);
		} catch (CompaniesException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}

	}

	@GetMapping("/get-company-id/{companyId}")
	public Company getCompanyById(@PathVariable int companyId) {
		try {
			return this.adminService.getCompanyById(companyId);
		} catch (CompaniesException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}

	}

	@GetMapping("/all-companies")
	public List<Company> getAllCompanies() {
		return this.adminService.getAllCompanies();
	}

	@PutMapping("/update-company")
	public Company updateCompany(@ModelAttribute Company company) {
		try {
			return this.adminService.updateCompany(company);
		} catch (CompaniesException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@DeleteMapping("/delete-company-id/{companyId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCompanyById(@PathVariable int companyId) {
		try {
			this.adminService.deleteCompanyById(companyId);
		} catch (CompaniesException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@PostMapping("/add-customer")
	@ResponseStatus(HttpStatus.CREATED)
	public Customer addCustomer(@ModelAttribute Customer Customer) {
		try {
			return this.adminService.addCustomer(Customer);
		} catch (CustomersException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping("/get-customer-id/{customerId}")
	public Customer getCustomerById(@PathVariable int customerId) {
		try {
			return this.adminService.getCustomerById(customerId);
		} catch (CustomersException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}

	}

	@GetMapping("/all-customers")
	public List<Customer> getAllCustomers() {
		return this.adminService.getAllCustomers();
	}

	@PutMapping("/update-customer")
	public Customer updateCustomer(@ModelAttribute Customer customer) {
		try {
			return this.adminService.updateCustomer(customer);
		} catch (CustomersException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@DeleteMapping("/delete-customer-id/{customerId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCustomerById(@PathVariable int customerId) {
		try {
			this.adminService.deleteCustomerById(customerId);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
}
