package mcgill.ecse321.grocerystore.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Time;
import java.time.LocalTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import mcgill.ecse321.grocerystore.model.BusinessHour;
import mcgill.ecse321.grocerystore.model.Person;
import mcgill.ecse321.grocerystore.model.BusinessHour.WeekDay;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestPersonPersistence {

	@Autowired
	private PersonRepository personRepository;

	@AfterEach
	public void clearDatabase() {
		// First, we clear registrations to avoid exceptions due to inconsistencies
		personRepository.deleteAll();
	}
	
	public Person createPerson(String email, String firstName, String lastName, int phoneNumber, String address) {
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
	public void testPersistAndLoadPerson() {
		String email = "abc@gmail.com";
		int phoneNumber = 1112223333;
		String address = "845 Sherbrooke St W, Montreal, Quebec H3A 0G4";
		String firstName = "Bob";
		String lastName = "Smith";
		
		Person person = createPerson(email, firstName, lastName, phoneNumber, address);
		
		person = null;
		person = personRepository.findPersonByEmail(email);
		
		assertNotNull(person);
		
		assertEquals(email,person.getEmail());
		assertEquals(phoneNumber,person.getPhoneNumber());
		assertEquals(address,person.getAddress());
		assertEquals(firstName,person.getFirstName());
		assertEquals(lastName,person.getLastName());
	}
}