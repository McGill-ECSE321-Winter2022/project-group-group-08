package mcgill.ecse321.grocerystore.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import mcgill.ecse321.grocerystore.model.Account;
import mcgill.ecse321.grocerystore.model.Cart;
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
		// First, we clear registrations to avoid exceptions due to inconsistencies
		accountRepository.deleteAll();
		personRepository.deleteAll();
	}
	
	public Account createAccount(String username, String password, boolean inTown) {
		Account account = new Account();
		account.setUsername(username);
		account.setPassword(password);
		account.setInTown(inTown);
		accountRepository.save(account);
		return account;
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
	public void testPersistAndLoadAccount() {
		String username = "Bob";
		String password = "101";
		boolean inTown = true;
		
		Account account = createAccount(username, password, inTown);
		
		account = null;
		account = accountRepository.findAccountByUsername(username);
		
		assertNotNull(account);
		
		assertEquals(username,account.getUsername());
		assertEquals(password,account.getPassword());
		assertEquals(inTown,account.getInTown());
	}
	
	
	@Test
	public void testPersistAndLoadAccountByPerson() {
		//create an instance of a person 
		String email = "abc@gmail.com";
		int phoneNumber = 1112223333;
		String address = "845 Sherbrooke St W, Montreal, Quebec H3A 0G4";
		String firstName = "Bob";
		String lastName = "Smith";
		Person person = createPerson(email, firstName, lastName, phoneNumber, address);
		
		//create an instance of an account
		String username = "Bob";
		String password = "101";
		boolean inTown = true;
		Account account = createAccount(username, password, inTown);
		
		//link both together
		person.setAccount(account);
		account.setPerson(person);
		
		//save repositories
		personRepository.save(person);
		accountRepository.save(account);
		
		person = null;
		account = null;
		
		//find person
		person = personRepository.findPersonByEmail(email);
		assertNotNull(person);
		
		//find account through person
		account = person.getAccount();
		assertNotNull(account);
		
		//test attributes
		assertEquals(username,account.getUsername());
		assertEquals(password,account.getPassword());
		assertEquals(inTown,account.getInTown());
	}
	
}
