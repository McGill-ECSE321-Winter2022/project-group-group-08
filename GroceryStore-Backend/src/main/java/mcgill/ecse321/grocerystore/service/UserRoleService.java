package mcgill.ecse321.grocerystore.service;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mcgill.ecse321.grocerystore.dao.CustomerRepository;
import mcgill.ecse321.grocerystore.dao.EmployeeRepository;
import mcgill.ecse321.grocerystore.dao.ManagerRepository;
import mcgill.ecse321.grocerystore.dao.ReceiptRepository;
import mcgill.ecse321.grocerystore.dao.UserRoleRepository;
import mcgill.ecse321.grocerystore.model.Customer;
import mcgill.ecse321.grocerystore.model.Customer.TierClass;
import mcgill.ecse321.grocerystore.model.Employee;
import mcgill.ecse321.grocerystore.model.Manager;
import mcgill.ecse321.grocerystore.model.Receipt;
import mcgill.ecse321.grocerystore.model.UserRole;

@Service
public class UserRoleService {
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	ManagerRepository managerRepository;
	
	// User Role Part
	
	@Transactional
	public UserRole findUserRoleById (int id) {
		UserRole curr = userRoleRepository.findUserRoleById(id);
		if (curr == null) {
			throw new IllegalArgumentException("No user with that id");
		}
		return curr;
	}
	
	@Transactional
	public List<UserRole>  getAllUserRoles() {
		
		return toList(userRoleRepository.findAll());
		
	};
	
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
	
	/// Customer Part
	
	
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
		List<Customer> customers = customerRepository.findCustomerByBan(ban);
		if (customers == null) {throw new IllegalArgumentException( "No customers with ban");}
		
		return customers;
	}
	
	// Customer Part
	
	/**
	 * Method to create a employee role
	 * 
	 * @return Employee object
	 */
	@Transactional
	public Employee createEmployee() {
		Employee employee = new Employee();
		return employee;
	}
	
	/**
	 * Method to get a employee by their role id
	 * @param role id
	 * @return employee with that id
	 */
	@Transactional
	public Employee getEmployee(int id) {
		Employee employee = employeeRepository.findEmployeeById(id);
	    return employee;
	}

	/**
	 * Method to get all employee
	 * 
	 * @return list of all employee
	 */
	@Transactional
	public List<Employee> getAllEmployees() {
		return toList(employeeRepository.findAll());
	}
	//manager part
	
	@Transactional 
	public Manager createManager() {
		Manager manager = new Manager();
		return manager;
	}
	@Transactional
	public List<Manager> getAllManagers() {
		return toList(managerRepository.findAll());
	}
	
	@Transactional
	public Manager getManager(int id) {
		if(id < 0) {
			throw new IllegalArgumentException ("The id cannot be a negative number");
		}
		Manager manager = managerRepository.findManagerById(id);
		if(manager == null) {
			throw new IllegalArgumentException("No manager with id " + id + " exists");
		}
		return manager;
	}
	
	@Transactional
	public boolean deleteManager(Manager manager) {
		if (manager == null) {
			return false;
		}else {
			managerRepository.delete(manager);
			return true;
		}
	}
	
	@Transactional
	public boolean deleteManagerById(int id) {
		if (id < 0) {
			return false;
		}
		else {
			Manager manager = managerRepository.findManagerById(id);
			if(manager == null) {
				return false;
			}
			managerRepository.delete(manager);
			return true;
		}
	}
	
	
	

	
}