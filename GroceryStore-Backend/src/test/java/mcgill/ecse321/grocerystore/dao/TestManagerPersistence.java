package mcgill.ecse321.grocerystore.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import mcgill.ecse321.grocerystore.model.Manager;
import mcgill.ecse321.grocerystore.model.Person;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestManagerPersistence {

	@Autowired
	private ManagerRepository managerRepository;
	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	@AfterEach
	public void clearDatabase() {
		// First, we clear the repositories to avoid exceptions due to inconsistencies
		managerRepository.deleteAll();
		personRepository.deleteAll();
		userRoleRepository.deleteAll();
	}
	
	//creates a manager
	public Manager createManager(Person person) {
		Manager manager = new Manager();
		manager.setPerson(person);
		managerRepository.save(manager);
		return manager;
	}
	
	//creates a person
	public Person createPerson(String email, String firstName, String lastName, String phoneNumber, String address) {
		Person person = new Person();
		person.setEmail(email);
		person.setFirstName(firstName);
		person.setLastName(lastName);
		person.setPhoneNumber(phoneNumber);
		person.setAddress(address);
		personRepository.save(person);
		return person;
	}
	
	@Test
	public void testPersistAndLoadManager() {
		String email = "abc@gmail.com";
		String phoneNumber = "1112223333";
		String address = "845 Sherbrooke St W, Montreal, Quebec H3A 0G4";
		String firstName = "Bob";
		String lastName = "Smith";
		
		Person person = createPerson(email, firstName, lastName, phoneNumber, address);
		
		Manager manager = createManager(person);
		int id = manager.getId();
		
		manager = null;
		manager = managerRepository.findManagerById(id);
		
		//testing
		assertNotNull(manager);
		
		assertEquals(id,manager.getId());
	}
}
