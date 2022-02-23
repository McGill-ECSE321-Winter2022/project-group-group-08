package mcgill.ecse321.grocerystore.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import mcgill.ecse321.grocerystore.model.Account;
import mcgill.ecse321.grocerystore.model.Person;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestPersonPersistence {

	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private AccountRepository accountRepository;
	

	@AfterEach
	public void clearDatabase() {
		// First, we clear the repositories to avoid exceptions due to inconsistencies
		personRepository.deleteAll();
		accountRepository.deleteAll();
	}
	
	//creates an account
	public Account createAccount(String username, String password, boolean inTown) {
		Account account = new Account();
		account.setUsername(username);
		account.setPassword(password);
		account.setInTown(inTown);
		accountRepository.save(account);
		return account;
	}
	
	//creates a person
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
		//person attributes
		String email = "abc@gmail.com";
		int phoneNumber = 1112223333;
		String address = "845 Sherbrooke St W, Montreal, Quebec H3A 0G4";
		String firstName = "Bob";
		String lastName = "Smith";
		
		Person person = createPerson(email, firstName, lastName, phoneNumber, address);
		
		person = null;
		person = personRepository.findPersonByEmail(email);
		
		//testing
		assertNotNull(person);
		
		assertEquals(email,person.getEmail());
		assertEquals(phoneNumber,person.getPhoneNumber());
		assertEquals(address,person.getAddress());
		assertEquals(firstName,person.getFirstName());
		assertEquals(lastName,person.getLastName());
	}
	
	@Test
	public void testPersistAndLoadPersonByAccount() {
		//create an instance of person
		String email = "abc@gmail.com";
		int phoneNumber = 1112223333;
		String address = "845 Sherbrooke St W, Montreal, Quebec H3A 0G4";
		String firstName = "Bob";
		String lastName = "Smith";
		Person person = createPerson(email, firstName, lastName, phoneNumber, address);
		
		//create an instance of account
		String username = "Bob";
		String password = "101";
		boolean inTown = true;
		Account account = createAccount(username, password, inTown);
		
		//reference them
		person.setAccount(account);
		account.setPerson(person);
		
		//save repositories
		personRepository.save(person);
		accountRepository.save(account);
		
		person = null;
		account = null;
		
		account = accountRepository.findAccountByUsername(username);
		
		//get person through account
		person = account.getPerson();
		
		//testing
		assertNotNull(account);
		
		assertNotNull(person);
		
		assertEquals(email,person.getEmail());
		assertEquals(phoneNumber,person.getPhoneNumber());
		assertEquals(address,person.getAddress());
		assertEquals(firstName,person.getFirstName());
		assertEquals(lastName,person.getLastName());
	}
}