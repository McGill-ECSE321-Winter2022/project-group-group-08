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

import mcgill.ecse321.grocerystore.model.GroceryStoreSystem;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestGroceryStoreSystemPersistence {

	@Autowired
	private GroceryStoreSystemRepository groceryStoreSystemRepository;

	@AfterEach
	public void clearDatabase() {
		// First, we clear registrations to avoid exceptions due to inconsistencies
		groceryStoreSystemRepository.deleteAll();
	}
	
	public GroceryStoreSystem createGroceryStoreSystem(String storeName, String address, int employeeDiscount) {
		GroceryStoreSystem groceryStoreSystem = new GroceryStoreSystem();
		groceryStoreSystem.setStoreName(storeName);
		groceryStoreSystem.setAddress(address);
		groceryStoreSystem.setEmployeeDiscount(employeeDiscount);
		groceryStoreSystemRepository.save(groceryStoreSystem);
		return groceryStoreSystem;
	}
	
	@Test
	public void testPersistAndLoadGroceryStoreSystem() {
		String storeName = "My Market";
		String address = "845 Sherbrooke St W, Montreal, Quebec H3A 0G4";
		int employeeDiscount = 20;
		
		GroceryStoreSystem groceryStoreSystem = createGroceryStoreSystem(storeName, address, employeeDiscount);
		
		groceryStoreSystem = null;
		groceryStoreSystem = groceryStoreSystemRepository.findGroceryStoreSystemByStoreName(storeName);
		
		assertNotNull(groceryStoreSystem);
		
		assertEquals(storeName,groceryStoreSystem.getStoreName());
		assertEquals(address,groceryStoreSystem.getAddress());
		assertEquals(employeeDiscount,groceryStoreSystem.getEmployeeDiscount());
	} 	
}
