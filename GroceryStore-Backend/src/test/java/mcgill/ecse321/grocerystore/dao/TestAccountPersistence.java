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
public class TestAccountPersistence {

	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private PersonRepository personRepository;

	@AfterEach
	public void clearDatabase() {
		// First, we clear the repositories to avoid exceptions due to inconsistencies
		accountRepository.deleteAll();
		personRepository.deleteAll();
	}
	
	//creates an account
	public Account createAccount(String username, String password, boolean inTown, Person person) {
		Account account = new Account();
		account.setUsername(username);
		account.setPassword(password);
		account.setInTown(inTown);
		account.setPerson(person);
		accountRepository.save(account);
		return account;
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
	public void testPersistAndLoadAccount() {
		String email = "abc@gmail.com";
		String phoneNumber = "1112223333";
		String address = "845 Sherbrooke St W, Montreal, Quebec H3A 0G4";
		String firstName = "Bob";
		String lastName = "Smith";
		
		Person person = createPerson(email, firstName, lastName, phoneNumber, address);
		
		//person details
		String username = "Bob";
		String password = "101";
		boolean inTown = true;
		
		Account account = createAccount(username, password, inTown, person);
		
		account = null;
		account = accountRepository.findAccountByUsername(username);
		
		//testing
		assertNotNull(account);
		
		assertEquals(username,account.getUsername());
		assertEquals(password,account.getPassword());
		assertEquals(inTown,account.getInTown());
	}
}
