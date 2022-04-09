package mcgill.ecse321.grocerystore.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import mcgill.ecse321.grocerystore.model.Person;

public interface PersonRepository extends CrudRepository<Person, String>{

	Person findPersonByEmail(String email);
	
	List<Person> findPersonByFirstNameContainingIgnoreCase(String firstName);
	
	List<Person> findPersonByLastNameContainingIgnoreCase(String lastName);
	
	List<Person> findPersonByFirstNameAndLastNameContainingIgnoreCase(String firstName, String lastName);
	
	List<Person> findPersonByPhoneNumber(String phoneNumber);
	
	List<Person> findPersonByAddressContainingIgnoreCase(String address);
}
