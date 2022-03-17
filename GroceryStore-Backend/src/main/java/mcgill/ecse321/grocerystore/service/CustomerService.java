package mcgill.ecse321.grocerystore.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mcgill.ecse321.grocerystore.dao.CustomerRepository;
import mcgill.ecse321.grocerystore.dao.PersonRepository;
import mcgill.ecse321.grocerystore.dao.UserRoleRepository;
import mcgill.ecse321.grocerystore.model.Customer;
import mcgill.ecse321.grocerystore.model.Person;
import mcgill.ecse321.grocerystore.model.Customer.TierClass;

@Service
public class CustomerService {

	@Autowired
	PersonRepository personRepository;
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	UserRoleRepository userRoleRepository;

	/**
	 * Method to create a customer role
	 * 
	 * @param person
	 * @param tierClass of customer
	 * @param ban       status of customer
	 * @return Customer object
	 */
	@Transactional
	public Customer createCustomer(Person person, TierClass tierClass, boolean ban) {
		if (person == null || !personRepository.existsById(person.getEmail())) {
			throw new InvalidInputException("Invalid person");
		}
		if (userRoleRepository.findUserRoleByPerson(person) != null) {
			throw new InvalidInputException("Person has already been assigned a role");
		}
		if (tierClass == null || !(tierClass instanceof TierClass)) {
			throw new InvalidInputException("Invalid tier Class");
		}
		Customer customer = new Customer();
		customer.setPerson(person);
		customer.setTierclass(tierClass);
		customer.setBan(ban);
		customerRepository.save(customer);
		return customer;
	}

	/**
	 * Method to create a customer role with no parameters
	 * 
	 * @param person
	 * @return Customer object
	 */
	@Transactional
	public Customer createCustomer(Person person) {
		if (person == null || !personRepository.existsById(person.getEmail())) {
			throw new InvalidInputException("Invalid person");
		}
		TierClass defaultTier = TierClass.Bronze;
		boolean defaultBan = false;
		Customer customer = new Customer();
		customer.setPerson(person);
		customer.setTierclass(defaultTier);
		customer.setBan(defaultBan);
		customerRepository.save(customer);
		return customer;
	}

	/**
	 * Method to update a customer's information
	 * 
	 * @param role   id
	 * @param person
	 * @param new    tierClass
	 * @param new    ban
	 * @return Customer object
	 */
	@Transactional
	public Customer updateCustomer(int id, Person person, TierClass tierClass, boolean ban) {
		if (!customerRepository.existsById(id)) {
			throw new InvalidInputException("Customer does not exists");
		}
		if (id <= 0) {
			throw new InvalidInputException("Invalid id");
		}
		if (person == null || !personRepository.existsById(person.getEmail())) {
			throw new InvalidInputException("Invalid person");
		}
		if (tierClass == null || !(tierClass instanceof TierClass)) {
			throw new InvalidInputException("Invalid tier Class");
		}
		Customer customer = customerRepository.findCustomerById(id);
		customer.setPerson(person);
		customer.setTierclass(tierClass);
		customer.setBan(ban);
		customerRepository.save(customer);
		return customer;
	}

	/**
	 * Method to get a customer by their role id
	 * 
	 * @param role id
	 * @return customer with that id
	 */
	@Transactional
	public Customer getCustomer(int id) {
		if (id <= 0) {
			throw new InvalidInputException("Invalid id");
		}
		if (!customerRepository.existsById(id)) {
			throw new InvalidInputException("Customer does not exist");
		}
		Customer customer = customerRepository.findCustomerById(id);
		return customer;
	}

	/**
	 * Method to get all customers
	 * 
	 * @return list of all customers
	 */
	@Transactional
	public List<Customer> getAllCustomers() {
		return toList(customerRepository.findAll());
	}

	/**
	 * Method to get all customers of a certain tier
	 * 
	 * @param tier
	 * @return list of all customers of the given tier
	 */
	@Transactional
	public List<Customer> getAllCustomerByTier(TierClass tierClass) {
		if (tierClass == null || !(tierClass instanceof TierClass)) {
			throw new InvalidInputException("Invalid tier Class");
		}
		return toList(customerRepository.findCustomerByTierclass(tierClass));
	}

	/**
	 * Method to get all customers of a certain ban
	 * 
	 * @param ban
	 * @return list of all customers of the given ban
	 */
	@Transactional
	public List<Customer> getAllCustomerByBan(boolean ban) {
		return toList(customerRepository.findCustomerByBan(ban));
	}

	/**
	 * Method to delete a customer by their role id
	 * 
	 * @param role id
	 * @return employee with that id
	 */
	@Transactional
	public Customer deleteCustomer(int id) {
		if (id <= 0) {
			throw new InvalidInputException("Invalid id");
		}
		if (!customerRepository.existsById(id)) {
			throw new InvalidInputException("Customer does not exist");
		}
		Customer customer = customerRepository.findCustomerById(id);
		customerRepository.delete(customer);
		return customer;
	}

	private <T> List<T> toList(Iterable<T> iterable) {
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}

}
