package mcgill.ecse321.grocerystore.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mcgill.ecse321.grocerystore.dao.AccountRepository;
import mcgill.ecse321.grocerystore.dao.PersonRepository;
import mcgill.ecse321.grocerystore.dao.UserRoleRepository;
import mcgill.ecse321.grocerystore.model.Account;
import mcgill.ecse321.grocerystore.model.Customer;
import mcgill.ecse321.grocerystore.model.Employee;
import mcgill.ecse321.grocerystore.model.Manager;
import mcgill.ecse321.grocerystore.model.Person;
import mcgill.ecse321.grocerystore.model.UserRole;

@Service
public class PersonService {
	
	@Autowired
	PersonRepository personRepository;
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	UserRoleRepository userRoleRepository;
	@Autowired
	AccountService accountService;
	@Autowired 
	ManagerService managerService;
	@Autowired 
	EmployeeService employeeService;
	@Autowired 
	CustomerService customerService;
	
	/**
	 * Creates a person object
	 * @param email primary key of person
	 * @param firstName first name of the person
	 * @param lastName last name of the person
	 * @param phoneNumber phone number fo person
	 * @param address address of person
	 * @return created person
	 */
	@Transactional
	public Person createPerson(String email, String firstName, String lastName, String phoneNumber,
			String address) {
		String error = "";
		if (email == null || email.trim().length() == 0) {
		    error = error + "Person email cannot be empty! ";
		}
		if (firstName == null || firstName.trim().length() == 0) {
		    error = error + "Person first name cannot be empty! ";
		}
		if (lastName == null || lastName.trim().length() == 0) {
		    error = error + "Person last name cannot be empty! ";
		}
		if (phoneNumber == null || phoneNumber.trim().length() == 0 || phoneNumber.length() < 10) {
		    error = error + "Person phone number is invalid! ";
		}
		if (address == null || address.trim().length() == 0) {
		    error = error + "Person address cannot be empty! ";
		}
		error = error.trim();
		if (error.length() > 0) {
		    throw new InvalidInputException(error);
		}
		Person person = new Person();
		person.setEmail(email);
		person.setFirstName(firstName);
		person.setLastName(lastName);
		person.setPhoneNumber(phoneNumber);
		person.setAddress(address);
		personRepository.save(person);
		return person;
	}
	
	@Transactional
	/**
	 * Updates person
	 * @param email primary key of person
	 * @param firstName new first name of person
	 * @param lastName new last name of person
	 * @param phoneNumber new phone number
	 * @param address new address
	 * @return updated person
	 */
	public Person updatePerson(String email,
			String firstName, String lastName, String phoneNumber,
			String address) {
		String error = "";
		if (email == null || email.trim().length() == 0) {
			throw new InvalidInputException("Person email cannot be empty!");
		}
		if (firstName == null || firstName.trim().length() == 0) {
		    error = error + "Person first name cannot be empty! ";
		}
		if (lastName == null || lastName.trim().length() == 0) {
		    error = error + "Person last name cannot be empty! ";
		}
		if (phoneNumber == null || phoneNumber.trim().length() == 0 || phoneNumber.length() < 10) {
		    error = error + "Person phone number is invalid! ";
		}
		if (address == null || address.trim().length() == 0) {
		    error = error + "Person address name cannot be empty! ";
		}
		Person person = personRepository.findPersonByEmail(email);
		if(person == null) {
			throw new InvalidInputException("Person with email " + email + " does not exists");
		}
		error = error.trim();
		if (error.length() > 0) {
		    throw new InvalidInputException(error);
		}
		person.setFirstName(firstName);
		person.setLastName(lastName);
		person.setPhoneNumber(phoneNumber);
		person.setAddress(address);
		personRepository.save(person);
		return person;
	}
	
	/**
	 * Finds a person using email as primary key identifier
	 * @param email primary key of person
	 * @return person with this email
	 */
	@Transactional 
	public Person findPersonByEmail(String email){
		if (email == null || email.trim().length() == 0) {
		    throw new InvalidInputException("Person email cannot be empty!");
		}else {
			Person person = personRepository.findPersonByEmail(email);
			return person;
		}
	}
	
	/**
	 * Returns a list of person with their firstName containing a specific string
	 * @param firstName the string that we are searching for
	 * @return a list of person with their first name containing the string
	 */
	@Transactional 
	public List<Person> findPersonByFirstNameContainingIgnoreCase(String firstName){
		if(firstName == null || firstName.trim().length() == 0){
			throw new InvalidInputException("Cannot search for empty firstname!");
		}else{
			List<Person> personList = new ArrayList<Person>();
			for(Person p: personRepository.findPersonByFirstNameContainingIgnoreCase(firstName)) {
				personList.add(p);
			}
			return personList;
		}
	}

	/**
	 * Returns a list of person with their lastName containing a specific string
	 * @param lastName the string that we are searching for
	 * @return a list of person with their last name containing the string
	 */
	@Transactional 
	public List<Person> findPersonByLastNameContainingIgnoreCase(String lastName){
		if(lastName== null || lastName.trim().length() == 0){
			throw new InvalidInputException("Cannot search for empty lastname!");
		}else{
			List<Person> personList = new ArrayList<Person>();
			for(Person p: personRepository.findPersonByLastNameContainingIgnoreCase(lastName)) {
				personList.add(p);
			}
			return personList;
		}
	}

	/**
	 * Returns a list of person with their address containing a specific string
	 * @param address the string that we are searching for
	 * @return a list of person with their address containing the string
	 */
	@Transactional 
	public List<Person> findPersonByAddressContainingIgnoreCase(String address){
		if(address == null || address.trim().length() == 0){
			throw new InvalidInputException("Cannot search for empty address!");
		}else{
			List<Person> personList = new ArrayList<Person>();
			for(Person p: personRepository.findPersonByAddressContainingIgnoreCase(address)) {
				personList.add(p);
			}
			return personList;
		}
	}
	
	/**
	 * Deletes a person and all the classes which depends on this person
	 * @param person the person we want to delete
	 * @return deleted person
	 */
	@Transactional
	public Person deletePerson(Person person) {
		if (person == null) {
			throw new InvalidInputException("Person does not exist.");
		}else {
			Account account = accountRepository.findAccountByPerson(person);
			if(account != null) {
				accountService.deleteAccount(account);
			}			
			UserRole userRole = userRoleRepository.findUserRoleByPerson(person);
			if(userRole != null) {
				if (userRole instanceof Manager) {
		 			managerService.deleteManagerById(userRole.getId());
		 		}
		 		if (userRole instanceof Employee) {
		 			employeeService.deleteEmployee(userRole.getId());
		 		}
		 		if (userRole instanceof Customer) {
		 			customerService.deleteCustomer(userRole.getId());
		 		}
			}
			personRepository.delete(person);
			return person;
		}
	}
	
	/**
	 * Deletes a person associated with this email and all the classes which depends on this person
	 * @param email primary key of person object
	 * @return deleted person
	 */
	@Transactional
	public Person deletePersonByEmail(String email) {
		if (email == null || email.trim().length() == 0 || !personRepository.existsById(email)) {
			throw new InvalidInputException("Person with provided email does not exist.");
		}else {
			Person person = personRepository.findPersonByEmail(email);		
			
			Account account = accountRepository.findAccountByPerson(person);
			if(account != null) {
				accountService.deleteAccount(account);
			}
			UserRole userRole = userRoleRepository.findUserRoleByPerson(person);
			if(userRole != null) {
				if(userRole != null) {
					if (userRole instanceof Manager) {
			 			managerService.deleteManagerById(userRole.getId());
			 		}
			 		if (userRole instanceof Employee) {
			 			employeeService.deleteEmployee(userRole.getId());
			 		}
			 		if (userRole instanceof Customer) {
			 			customerService.deleteCustomer(userRole.getId());
			 		}
				}
			}
			personRepository.delete(person);
			return person;
		}
	}
	
	/**
	 * @return returns a list of person present in the database
	 */
	@Transactional
	public List<Person> getAllPerson(){
		return toList(personRepository.findAll());
	}
	
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}	
}
