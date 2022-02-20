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

import mcgill.ecse321.grocerystore.model.BusinessHour;
import mcgill.ecse321.grocerystore.model.Cart;
import mcgill.ecse321.grocerystore.model.BusinessHour.WeekDay;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestCartPersistence {

	@Autowired
	private CartRepository cartRepository;

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
	
	@Test
	public void testPersistAndLoadCart() {
		Date date = java.sql.Date.valueOf(LocalDate.of(2021, Month.DECEMBER, 2));
		
		Cart cart = createCart(date);
		int id= cart.getId();
		
		cart = null;
		cart = cartRepository.findCartById(id);
		
		assertNotNull(cart);
		assertEquals(id,cart.getId());
		assertEquals(date,cart.getDate());
	}
	
}
