package mcgill.ecse321.grocerystore.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Time;
import java.time.LocalTime;
import java.util.HashSet;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import mcgill.ecse321.grocerystore.model.BusinessHour;
import mcgill.ecse321.grocerystore.model.GroceryStoreSystem;
import mcgill.ecse321.grocerystore.model.Item;
import mcgill.ecse321.grocerystore.model.Quantity;
import mcgill.ecse321.grocerystore.model.Receipt;
import mcgill.ecse321.grocerystore.model.BusinessHour.WeekDay;
import mcgill.ecse321.grocerystore.model.Receipt.ReceiptStatus;
import mcgill.ecse321.grocerystore.model.Receipt.ReceiptType;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestItemPersistence {

	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private GroceryStoreSystemRepository groceryStoreSystemRepository;
	@Autowired
	private QuantityRepository quantityRepository;

	@AfterEach
	public void clearDatabase() {
		// First, we clear registrations to avoid exceptions due to inconsistencies
		itemRepository.deleteAll();
	}
	
	public GroceryStoreSystem createGroceryStoreSystem(String storeName, String address, int employeeDiscount) {
		GroceryStoreSystem groceryStoreSystem = new GroceryStoreSystem();
		groceryStoreSystem.setStoreName(storeName);
		groceryStoreSystem.setAddress(address);
		groceryStoreSystem.setEmployeeDiscount(employeeDiscount);
		groceryStoreSystemRepository.save(groceryStoreSystem);
		return groceryStoreSystem;
	}
	
	public Quantity createQuantity(int count) {
		Quantity quantity = new Quantity();
		quantity.setCount(count);
		quantityRepository.save(quantity);
		return quantity;
	}
	
	public Item createItem(String name, int price, int point, int returnPolicy, boolean pickup, int inStoreQuantity) {
		Item item = new Item();
		item.setName(name);
		item.setPrice(price);
		item.setPoint(point);
		item.setReturnPolicy(returnPolicy);
		item.setPickup(pickup);
		item.setInStoreQuantity(inStoreQuantity);
		itemRepository.save(item);
		return item;
	}
	
	@Test
	public void testPersistAndLoadItem() {		
		String name = "Carrot";
		int price = 2;
		int point = 10;
		int returnPolicy = 2;
		boolean pickup = true;
		int inStoreQuantity = 58;
		Item item = createItem(name, price, point, returnPolicy, pickup, inStoreQuantity);
		int id = item.getId();
		
		item = null;
		item = itemRepository.findItemById(id);
		
		assertNotNull(item);
		
		assertEquals(id,item.getId());
		assertEquals(name,item.getName());
		assertEquals(price,item.getPrice());
		assertEquals(point,item.getPoint());
		assertEquals(returnPolicy,item.getReturnPolicy());
		assertEquals(pickup,item.getPickup());
		assertEquals(inStoreQuantity,item.getInStoreQuantity());
	} 
	
}