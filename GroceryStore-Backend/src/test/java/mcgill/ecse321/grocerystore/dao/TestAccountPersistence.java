//package mcgill.ecse321.grocerystore.dao;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertNull;
//
//import java.sql.Date;
//import java.time.LocalDate;
//import java.time.Month;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import mcgill.ecse321.grocerystore.model.Account;
//import mcgill.ecse321.grocerystore.model.Cart;
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest
//public class TestAccountPersistence {
//
//	@Autowired
//	private AccountRepository accountRepository;
//	@Autowired
//	private CartRepository cartRepository;
//
//	@AfterEach
//	public void clearDatabase() {
//		// First, we clear registrations to avoid exceptions due to inconsistencies
//		accountRepository.deleteAll();
//	}
//	
//	@Test
//	public void testPersistAndLoadAccount() {
//		String username = "Joey";
//		String password = "Koay";
//		boolean inTown = true;
//		Cart cart = new Cart();
//		Date date = java.sql.Date.valueOf(LocalDate.of(2021, Month.DECEMBER, 2));
//		cart.setDate(date);
//		cartRepository.save(cart);
//		
//		Account account = new Account();
//		account.setCart(cart);
//		account.setInTown(inTown);
//		account.setPassword(password);
//		account.setUsername(username);
//		accountRepository.save(account);
//		
//		account = null;
//		account = accountRepository.findAccountByUsername(username);
//		
//		assertNotNull(account);
//		
//		assertEquals(username,account.getUsername());
//		assertEquals(password,account.getPassword());
//		assertEquals(inTown,account.getInTown());
//		assertEquals(cart,account.getCart());
//	}
//}
