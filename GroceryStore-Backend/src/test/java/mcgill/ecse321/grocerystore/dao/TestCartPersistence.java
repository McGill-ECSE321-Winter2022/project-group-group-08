package mcgill.ecse321.grocerystore.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import mcgill.ecse321.grocerystore.model.Account;
import mcgill.ecse321.grocerystore.model.BusinessHour;
import mcgill.ecse321.grocerystore.model.Cart;
import mcgill.ecse321.grocerystore.model.BusinessHour.WeekDay;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestCartPersistence {

	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private AccountRepository accountRepository;

	@AfterEach
	public void clearDatabase() {
		// First, we clear registrations to avoid exceptions due to inconsistencies
		cartRepository.deleteAll();
	}
	
	public Cart createCart(Date date) {
		Cart cart = new Cart();
		cart.setDate(date);
		cartRepository.save(cart);
		return cart;
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
	public void testPersistAndLoadCart() {
		//create instance of cart
		Date date = java.sql.Date.valueOf(LocalDate.of(2021, Month.DECEMBER, 2));
		
		Cart cart = createCart(date);
		int id= cart.getId();
		
		cart = null;
		cart = cartRepository.findCartById(id);
		
		assertNotNull(cart);
		assertEquals(id,cart.getId());
		assertEquals(date,cart.getDate());
	}
	
	@Test
	public void testPersistAndLoadCartByAccount() {
		//create instance of cart
		Date date = java.sql.Date.valueOf(LocalDate.of(2021, Month.DECEMBER, 2));
		Cart cart = createCart(date);
		int id= cart.getId();
		
		//create an instance of an account
		String username = "Bob";
		String password = "101";
		boolean inTown = true;
		Account account = createAccount(username, password, inTown);
		
		//set reference
		cart.setAccount(account);
		account.setCart(cart);
		
		//save repositories
		cartRepository.save(cart);
		account.setCart(cart);
		
		cart = null;
		account = null;
		
		account = accountRepository.findAccountByUsername(username);
		assertNotNull(account);
		
		//get cart through account
		cart = account.getCart();
		assertNotNull(cart);
		
		//test attributes
		assertEquals(id,cart.getId());
		assertEquals(date,cart.getDate());
	}
	
}
