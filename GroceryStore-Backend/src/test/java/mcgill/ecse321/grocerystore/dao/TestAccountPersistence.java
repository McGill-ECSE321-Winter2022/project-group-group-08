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

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestAccountPersistence {

	@Autowired
	private AccountRepository accountRepository;

	@AfterEach
	public void clearDatabase() {
		// First, we clear registrations to avoid exceptions due to inconsistencies
		accountRepository.deleteAll();
	}
	
	public Account createAccount(String username, String password, boolean inTown) {
		Account account = new Account();
		account.setUsername(username);
		account.setPassword(password);
		account.setInTown(inTown);
		accountRepository.save(account);
		return account;
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
}
