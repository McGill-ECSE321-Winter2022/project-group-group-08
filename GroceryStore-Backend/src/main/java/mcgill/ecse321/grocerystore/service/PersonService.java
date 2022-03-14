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
import mcgill.ecse321.grocerystore.model.Person;
import mcgill.ecse321.grocerystore.model.UserRole;

@Service
public class PersonService {
	
	@Autowired
	PersonRepository personRepository;
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	AccountService accountService;
//	@Autowired 
//	UserRoleService userRoleService;
	
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
		if (phoneNumber == null || phoneNumber.trim().length() == 0) {
		    error = error + "Person phone number cannot be empty! ";
		}
		if(phoneNumber.length() < 10) {
			error = error + "Person phone number cannot be less than 10 digits";
		}
		if (address == null || address.trim().length() == 0) {
		    error = error + "Person address name cannot be empty! ";
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
		personRepository.save(person);
		return person;
	}
	
	@Transactional
	public Person updatePerson(String email,
			String firstName, String lastName, String phoneNumber,
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
		    error = error + "Person address name cannot be empty! ";
		}
		Person person = personRepository.findPersonByEmail(email);
		if(person == null) {
			throw new IllegalArgumentException("Person with email " + email + " does not exists");
		}
		error = error.trim();
		if (error.length() > 0) {
		    throw new IllegalArgumentException(error);
		}
		person.setFirstName(firstName);
		person.setLastName(lastName);
		person.setPhoneNumber(phoneNumber);
		person.setAddress(address);
		personRepository.save(person);
		return person;
	}
	
	@Transactional 
	public Person findPersonByEmail(String email){
		if (email == null || email.trim().length() == 0) {
		    throw new IllegalArgumentException("Person email cannot be empty! ");
		}else {
			Person person = personRepository.findPersonByEmail(email);
			return person;
		}
	}

	@Transactional 
	public List<Person> findPersonByAddressContainingIgnoreCase(String address){
		List<Person> personList = new ArrayList<Person>();
		for(Person p: personRepository.findPersonByAddressContainingIgnoreCase(address)) {
			personList.add(p);
		}
		return personList;
	}
	
	@Transactional 
	public List<Person> findPersonByLastNameContainingIgnoreCase(String lastName){
		List<Person> personList = new ArrayList<Person>();
		for(Person p: personRepository.findPersonByLastNameContainingIgnoreCase(lastName)) {
			personList.add(p);
		}
		return personList;
	}
	
	@Transactional 
	public List<Person> findPersonByFirstNameContainingIgnoreCase(String firstName){
		List<Person> personList = new ArrayList<Person>();
		for(Person p: personRepository.findPersonByFirstNameContainingIgnoreCase(firstName)) {
			personList.add(p);
		}
		return personList;
	}
	
	@Transactional
	public Person deletePerson(Person person) {
		if (person == null) {
			throw new IllegalArgumentException("Person does not exist.");
		}else {
			Account account = accountRepository.findAccountByPerson(person);
			if(account != null) {
//				accountRepository.delete(account);
				accountService.deleteAccount(account);
			}			
//			UserRole userRole = userRoleRepository.findUserRoleByPerson(person);
//			if(userRole != null) {
//				userRoleService.deleteUserRole(userRole);
//			}
			personRepository.delete(person);
			return person;
		}
	}
	
	@Transactional
	public Person deletePersonByEmail(String email) {
		if (email == null || email.trim().length() == 0 || !personRepository.existsById(email)) {
			throw new IllegalArgumentException("Person with provided email does not exist.");
		}else {
			Person person = personRepository.findPersonByEmail(email);		
			
			Account account = accountRepository.findAccountByPerson(person);
			if(account != null) {
//				accountRepository.delete(account);
				accountService.deleteAccount(account);
			}
//			UserRole userRole = userRoleRepository.findUserRoleByPerson(person);
//			if(userRole != null) {
//				userRoleService.deleteUserRole(userRole);
//			}
					
			personRepository.delete(person);
			return person;
		}
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
