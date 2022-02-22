package mcgill.ecse321.grocerystore.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import mcgill.ecse321.grocerystore.model.Account;
import mcgill.ecse321.grocerystore.model.Cart;
import mcgill.ecse321.grocerystore.model.Quantity;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestCartPersistence {

	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private QuantityRepository quantityRepository;

	@AfterEach
	public void clearDatabase() {
		// First, we clear registrations to avoid exceptions due to inconsistencies
		cartRepository.deleteAll();
		accountRepository.deleteAll();
		quantityRepository.deleteAll();
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
	
	public Quantity createQuantity(int count) {
		Quantity quantity = new Quantity();
		quantity.setCount(count);
		quantityRepository.save(quantity);
		return quantity;
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
	
	@Test
	public void testPersistAndLoadQuantityByCart() {
		//create instance of cart
		Date date = java.sql.Date.valueOf(LocalDate.of(2021, Month.DECEMBER, 2));
		
		Cart cart = createCart(date);
		int id= cart.getId();
		
		int count = 7;
		
		Quantity quantity = createQuantity(count);
		int id2 = quantity.getId();
		
		Set<Quantity> itemQuantities = new HashSet<Quantity>();
		itemQuantities.add(quantity);
		cart.setItemQuantities(itemQuantities);
		cartRepository.save(cart);
		
		itemQuantities = null;
		quantity = null;
		cart = null;
		
		cart = cartRepository.findCartById(id);
		quantity = cart.getItemQuantities().iterator().next();		
		
		assertEquals(id2,quantity.getId());
		assertEquals(count,quantity.getCount());
	}
}
