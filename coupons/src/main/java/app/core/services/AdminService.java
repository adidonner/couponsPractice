package app.core.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import app.core.entities.Company;
import app.core.entities.Customer;
import app.core.exceptions.CompaniesException;
import app.core.exceptions.CouponsException;
import app.core.exceptions.CustomersException;
import app.core.repositories.CompanyRepo;
import app.core.repositories.CustomerRepo;

/**
 * @author adido
 *
 */
@Service
@Transactional
public class AdminService extends ClientService {

	@Value("${admin.email}")
	private String adminEmail;
	@Value("${admin.password}")
	private String adminPassword;
	@Autowired
	private CompanyRepo companyRepo;
	@Autowired
	private CustomerRepo customerRepo;


	// company name must be unique
	public Company addCompany(Company company) throws CompaniesException {
		if (!this.companyRepo.existsByName(company.getName())) {
			this.companyRepo.save(company);
			return company;
		} else {
			throw new CompaniesException(
					"addCompany failed - a company with this name already exists: " + company.getName());
		}
	}

	// email must be unique
	public Customer addCustomer(Customer customer) throws CustomersException {
		if (!this.customerRepo.existsByEmail(customer.getEmail())) {
			this.customerRepo.save(customer);;
			return customer;
		} else {
			throw new CustomersException("addCustamer failed - a custmer with this email already exists: "
					+ customer.getFirstName() + " " + customer.getLastName());
		}
	}

	/**
	 * @param companyId
	 * @return
	 * @throws CompaniesException if specified company not found
	 */
	public Company getCompanyById(int companyId) throws CompaniesException {
		return this.companyRepo.findById(companyId)
				.orElseThrow(() -> new CompaniesException("get companyById failed - not found" + companyId));
	}

	public List<Company> getAllCompanies() {
		return companyRepo.findAll();
	}

	/**
	 * @param company
	 * @return
	 * @throws CompaniesException if specified company not found
	 */
	public Company updateCompany(Company company) throws CompaniesException {
		if (this.companyRepo.existsById(company.getId())) {
			return this.companyRepo.save(company);
		} else {
			throw new CompaniesException("updateCompany failed - not found" + company.getId());
		}
	}

	/**
	 * @param companyId
	 * @throws CompaniesException if specified company not found
	 */
	public void deleteCompanyById(int companyId) throws CompaniesException {
		if (this.companyRepo.existsById(companyId)) {
			this.companyRepo.deleteById(companyId);
		} else {
			throw new CompaniesException("deleteCompanyById failed - not found: " + companyId);
		}
	}

	/**
	 * @param customerId
	 * @return
	 * @throws CustomersException if specified customer not found
	 */
	public Customer getCustomerById(int customerId) throws CustomersException {
		return this.customerRepo.findById(customerId)
				.orElseThrow(() -> new CouponsException("getCustomerById failed - not found " + customerId));
	}

	public List<Customer> getAllCustomers() {
//		List<Customer> customers = customerRepo.findAll();
		return customerRepo.findAll();
	}

	/**
	 * @param customer
	 * @return
	 * @throws CustomersException if specified customer not found
	 */
	public Customer updateCustomer(Customer customer) throws CustomersException {
		if (this.customerRepo.existsById(customer.getId())) {
			return this.customerRepo.save(customer);
		}
		throw new CustomersException("updateCustomer failed - not found" + customer.getId());
	}

	/**
	 * @param customerId
	 * @throws CustomersException if specified customer not found
	 */
	public void deleteCustomerById(int customerId) throws CustomersException {
		if (this.customerRepo.existsById(customerId)) {
			this.customerRepo.deleteById(customerId);
		} else {
			throw new CustomersException("deleteCustomer failed - not found: " + customerId);
		}
	}

}
