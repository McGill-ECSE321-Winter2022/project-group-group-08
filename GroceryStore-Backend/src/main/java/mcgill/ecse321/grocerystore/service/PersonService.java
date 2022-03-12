package mcgill.ecse321.grocerystore.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mcgill.ecse321.grocerystore.dao.PersonRepository;
import mcgill.ecse321.grocerystore.model.Person;

@Service
public class PersonService {
	
	@Autowired
	PersonRepository personRepository;
		
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
	public Person updatePerson(String currentEmail, String newEmail,
			String firstName, String lastName, String phoneNumber,
			String address) {
		String error = "";
		if (currentEmail == null || currentEmail.trim().length() == 0) {
		    error = error + "Person current email cannot be empty! ";
		}
		if (newEmail == null || newEmail.trim().length() == 0) {
		    error = error + "Person new email cannot be empty! ";
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
		Person person = personRepository.findPersonByEmail(currentEmail);
		if(person == null) {
			throw new IllegalArgumentException("Person with email " + currentEmail + " does not exists");
		}
		error = error.trim();
		if (error.length() > 0) {
		    throw new IllegalArgumentException(error);
		}
		person.setEmail(newEmail);
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
	public List<Person> findPersonByAddress(String address){
		List<Person> personList = new ArrayList<Person>();
		for(Person p: personRepository.findPersonByAddress(address)) {
			personList.add(p);
		}
		return personList;
	}
	
	@Transactional 
	public List<Person> findPersonByLastName(String lastName){
		List<Person> personList = new ArrayList<Person>();
		for(Person p: personRepository.findPersonByLastName(lastName)) {
			personList.add(p);
		}
		return personList;
	}
	
	@Transactional 
	public List<Person> findPersonByFirstName(String firstName){
		List<Person> personList = new ArrayList<Person>();
		for(Person p: personRepository.findPersonByFirstName(firstName)) {
			personList.add(p);
		}
		return personList;
	}
	
	@Transactional
	public boolean deletePerson(Person person) {
		if (person == null) {
			return false;
		}else {
			personRepository.delete(person);
			return true;
		}
	}
	
	@Transactional
	public boolean deletePersonByEmail(String email) {
		if (email == null || email.trim().length() == 0) {
		    return false;
		}else {
			Person person = personRepository.findPersonByEmail(email);
			personRepository.delete(person);
			return true;
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
