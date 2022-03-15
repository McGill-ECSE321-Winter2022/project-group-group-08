package mcgill.ecse321.grocerystore.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mcgill.ecse321.grocerystore.dao.CustomerRepository;
import mcgill.ecse321.grocerystore.dao.UserRoleRepository;
import mcgill.ecse321.grocerystore.model.Customer;
import mcgill.ecse321.grocerystore.model.Employee;
import mcgill.ecse321.grocerystore.model.Customer.TierClass;

@Service
public class CustomerService {
	
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	UserRoleRepository userRoleRepository;
	
	/**
	 * Method to create a customer role
	 * @param tierClass of customer
	 * @param ban status of customer
	 * @return Customer object
	 */
	@Transactional
	public Customer createCustomer(TierClass tierClass, boolean ban) {
		Customer customer = new Customer();
		customer.setTierclass(tierClass);
		customer.setBan(ban);
		return customer;
	}
	
	
	/**
	 * Method to create a customer role with no parameters
	 * @return Customer object
	 */
	@Transactional
	public Customer createCustomer() {
		TierClass defaultTier = TierClass.Bronze;
		boolean defaultBan = false;
		Customer customer = new Customer();
		customer.setTierclass(defaultTier);
		customer.setBan(defaultBan);
		return customer;
	}

	/**
	 * Method to get a customer by their role id
	 * @param role id
	 * @return customer with that id
	 */
	@Transactional
	public Customer getCustomer(int id) {
		Customer customer = customerRepository.findCustomerById(id);
	    return customer;
	}
	
	/**
	 * Method to get all customers
	 * @return list of all customers
	 */
	@Transactional
	public List<Customer> getAllCustomers() {
		return toList(customerRepository.findAll());
	}

	/**
	 * Method to get all customers of a certain tier
	 * @param tier
	 * @return list of all customers of the given tier
	 */
	@Transactional
	public List<Customer> getAllCustomerByTier(TierClass tier) {
		return toList(customerRepository.findCustomerByTierclass(tier));
	}
	
	/**
	 * Method to get all customers of a certain ban
	 * @param ban
	 * @return list of all customers of the given ban
	 */
	@Transactional
	public List<Customer> getAllCustomerByBan(boolean ban) {
		return toList(customerRepository.findCustomerByBan(ban));
	}
	
	/**
	 * Method to delete a customer by their role id
	 * @param role id
	 * @return employee with that id
	 */
	@Transactional
	public Customer deleteCustomer(int id) {
		Customer customer = customerRepository.findCustomerById(id);
		customerRepository.delete(customer);
	    return customer;
	}
	
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
	
}
