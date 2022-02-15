package mcgill.ecse321.grocerystore.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import mcgill.ecse321.model.Account;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestAccountPersistence {
	@Autowired
	private AccountRepository accountRepository;

	@AfterEach
	public void clearDatabase() {
		accountRepository.deleteAll();
	}
	
//	@Test
//	public void testPersistAndLoadAccount() {
//		String username = "Joey";
//		String password = "050807";
//		boolean inTown = false;
//		
//		Account account = new Account();
//		account.setCart(null);
//		account.setInTown(inTown);
//		account.setPassword(password);
//		account.setUsername(username);
//		accountRepository.save(account);
//		
//		account = null;
//		account = accountRepository.findAccountByUsername(username);
//		
//		assertNotNull(account);
//		assertEquals(username,account.getUsername());
//		assertEquals(password,account.getPassword());
//		assertEquals(inTown,account.getInTown());
//		assertNull(account.getCart());
//	}
//
//	@Test
//	public void testPersistAndLoadCustomerByFirstNameAndLastName() {
//		
//	}
}
