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
	 * Creates Person
	 * @param email primary key of person
	 * @param firstName first name of person
	 * @param lastName last name of person 
	 * @param phoneNumber phone number of person
	 * @param address address of person
	 * @return person
	 */
	@Transactional
	public Person createPerson(String image, String email, String firstName, String lastName, String phoneNumber,
			String address) {
		String error = "";
		if(personRepository.findPersonByEmail(email) != null && email.equals("marwan.kanaan@mcgill.ca")){
			return personRepository.findPersonByEmail("marwan.kanaan@mcgill.ca");
		}
		if(personRepository.existsById(email)){
			error = error + "Person with this email has already been registered";
		}
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
		person.setImage(image);
		person.setEmail(email);
		person.setFirstName(firstName);
		person.setLastName(lastName);
		person.setPhoneNumber(phoneNumber);
		person.setAddress(address);
		personRepository.save(person);
		return person;
	}
	
	/**
	 * Updates Person
	 * @param email primary key of person
	 * @param firstName new first name of person
	 * @param lastName new last name of person 
	 * @param phoneNumber new phone number of person
	 * @param address new address of person
	 * @return person
	 */
	@Transactional
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
	 * Gets Person by email
	 * @param email primary identifier of person
	 * @return person
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
	 * Return list of person with first name containing string first name
	 * @param firstName searching for this string in first name of person
	 * @return list of person
	 */
	@Transactional 
	public List<Person> findPersonByFirstNameContainingIgnoreCase(String firstName){
		List<Person> personList = new ArrayList<Person>();
		for(Person p: personRepository.findPersonByFirstNameContainingIgnoreCase(firstName)) {
			personList.add(p);
		}
		return personList;
	}

	/**
	 * Returns list of person with last name containing string lastName
	 * @param lastName searching for this string in last name of person
	 * @return list of person
	 */
	@Transactional 
	public List<Person> findPersonByLastNameContainingIgnoreCase(String lastName){
		List<Person> personList = new ArrayList<Person>();
		for(Person p: personRepository.findPersonByLastNameContainingIgnoreCase(lastName)) {
			personList.add(p);
		}
		return personList;
	}

	/**
	 * Returns list of person with address containing string address
	 * @param address searching for this string in address of person
	 * @return list of person
	 */
	@Transactional 
	public List<Person> findPersonByAddressContainingIgnoreCase(String address){
		List<Person> personList = new ArrayList<Person>();
		for(Person p: personRepository.findPersonByAddressContainingIgnoreCase(address)) {
			personList.add(p);
		}
		return personList;
	}
	
	/**
	 * Delete person
	 * @param person person object 
	 * @return person
	 */
	@Transactional
	public Person deletePerson(Person person) {
		if (person == null) {
			throw new InvalidInputException("Person does not exist.");
		}else {
			//deletes objects associated with person
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
	 * Deletes person by email
	 * @param email primary key 
	 * @return person
	 */
	@Transactional
	public Person deletePersonByEmail(String email) {
		if (email == null || email.trim().length() == 0 || !personRepository.existsById(email)) {
			throw new InvalidInputException("Person with provided email does not exist.");
		}else {
			Person person = personRepository.findPersonByEmail(email);		
			
			//deletes object associated to person
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
	 * @return list of person
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
