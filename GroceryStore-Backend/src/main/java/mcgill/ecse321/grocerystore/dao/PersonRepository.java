package mcgill.ecse321.grocerystore.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import mcgill.ecse321.grocerystore.model.Person;

public interface PersonRepository extends CrudRepository<Person, String>{

	Person findPersonByEmail(String email);
	
	List<Person> findPersonByFirstName(String firstName);
	
	List<Person> findPersonByLastName(String lastName);
	
	List<Person> findPersonByFirstNameAndLastName(String firstName, String lastName);
	
	List<Person> findPersonByPhoneNumber(int phoneNumber);
	
	List<Person> findPersonByAddress(String address);
}