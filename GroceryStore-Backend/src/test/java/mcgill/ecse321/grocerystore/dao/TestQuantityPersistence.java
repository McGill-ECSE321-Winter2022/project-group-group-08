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
import mcgill.ecse321.grocerystore.model.Quantity;
import mcgill.ecse321.grocerystore.model.BusinessHour.WeekDay;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestQuantityPersistence {

	@Autowired
	private QuantityRepository quantityRepository;

	@AfterEach
	public void clearDatabase() {
		// First, we clear registrations to avoid exceptions due to inconsistencies
		quantityRepository.deleteAll();
	}
	
	public Quantity createQuantity(int count) {
		Quantity quantity = new Quantity();
		quantity.setCount(count);
		quantityRepository.save(quantity);
		return quantity;
	}
	
	@Test
	public void testPersistAndLoadQuantity() {
		int count = 7;
		
		Quantity quantity = createQuantity(count);
		int id = quantity.getId();
		
		quantity = null;
		quantity = quantityRepository.findQuantityById(id);
		
		assertNotNull(quantity);
		
		assertEquals(id,quantity.getId());
		assertEquals(count,quantity.getCount());
	}
}
