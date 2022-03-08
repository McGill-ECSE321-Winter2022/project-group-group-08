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
import mcgill.ecse321.grocerystore.model.Customer.TierClass;
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
		
	//USERROLE
	@Transactional
	public UserRole createManager() {
		Manager manager = new Manager();
		return manager;
	}
	
	@Transactional
	public UserRole createEmployee() {
		Employee employee = new Employee();
		return employee;
	}
	
	@Transactional
	public UserRole createCustomer(TierClass tierClass, boolean ban) {
		Customer customer = new Customer();
		return customer;
	}
	
	@Transactional
	public UserRole getUserRole(int id) {
		UserRole userRole = userRoleRepository.findUserRoleById(id);
		return userRole;
	}
	
	//ACCOUNT
	@Transactional
	public Account createAccount(String username, String password, boolean inTown, int totalPoints) {
		String error = "";
		if (username == null || username.trim().length() == 0) {
		    error = error + "Account username cannot be empty! ";
		}
		if (password == null || password.trim().length() == 0) {
		    error = error + "Account password cannot be empty! ";
		}
		if (totalPoints < 0) {
		    error = error + "Account Total points cannot be negative ";
		}
		error = error.trim();
		if (error.length() > 0) {
		    throw new IllegalArgumentException(error);
		}
		Account account = new Account();
		account.setUsername(username);
		account.setPassword(password);
		account.setInTown(inTown);
		account.setTotalPoints(totalPoints);
		accountRepository.save(account);
		return account;
	}
	
	@Transactional
	public Account getAccount(String username) {
		Account account = accountRepository.findAccountByUsername(username);
		return account;
	}
	
	//PERSON
	@Transactional
	public Person createPerson(String email, String firstName, String lastName, String phoneNumber,
			String address, UserRole userRole, Account account) {
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
		if (phoneNumber == null || phoneNumber.trim().length() == 0) {
		    error = error + "Person phone number cannot be empty! ";
		}
		if(phoneNumber.length() < 10) {
			error = error + "Person phone number cannot be less than 10 digits";
		}
		if (address == null || address.trim().length() == 0) {
		    error = error + "Person last name cannot be empty! ";
		}
		
		error = error.trim();
		if (error.length() > 0) {
		    throw new IllegalArgumentException(error);
		}
		Person person = new Person();
		person.setEmail(email);
		person.setFirstName(firstName);
		person.setLastName(lastName);
		person.setPhoneNumber(phoneNumber);
		person.setAddress(address);
		person.setUserRole(userRole);
		person.setAccount(account);
		personRepository.save(person);
		return person;
	}
		
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
