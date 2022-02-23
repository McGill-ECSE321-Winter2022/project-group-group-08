package mcgill.ecse321.grocerystore.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Time;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import mcgill.ecse321.grocerystore.model.BusinessHour;
import mcgill.ecse321.grocerystore.model.BusinessHour.WeekDay;
import mcgill.ecse321.grocerystore.model.GroceryStoreSystem;
import mcgill.ecse321.grocerystore.model.Item;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestGroceryStoreSystemPersistence {

	@Autowired
	private GroceryStoreSystemRepository groceryStoreSystemRepository;
	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private BusinessHourRepository bHRepository;

	@AfterEach
	public void clearDatabase() {
		// First, we clear the repositories to avoid exceptions due to inconsistencies
		groceryStoreSystemRepository.deleteAll();
		itemRepository.deleteAll();
		bHRepository.deleteAll();
	}
	
	//creates a grocery store system
	public GroceryStoreSystem createGroceryStoreSystem(String storeName, String address, int employeeDiscount) {
		GroceryStoreSystem groceryStoreSystem = new GroceryStoreSystem();
		groceryStoreSystem.setStoreName(storeName);
		groceryStoreSystem.setAddress(address);
		groceryStoreSystem.setEmployeeDiscount(employeeDiscount);
		groceryStoreSystemRepository.save(groceryStoreSystem);
		return groceryStoreSystem;
	}
	
	//creates an item
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
	
	//creates a business hour
	public BusinessHour createBusinessHour(WeekDay day, Time startTime, Time endTime, boolean working) {
		BusinessHour bH = new BusinessHour();
		bH.setDay(day);
		bH.setStartTime(startTime);
		bH.setEndTime(endTime);
		bH.setWorking(working);
		bHRepository.save(bH);
		return bH;
	}
	
	@Test
	public void testPersistAndLoadGroceryStoreSystem() {
		//grocery store system attributes
		String storeName = "My Market";
		String address = "845 Sherbrooke St W, Montreal, Quebec H3A 0G4";
		int employeeDiscount = 20;
		
		GroceryStoreSystem groceryStoreSystem = createGroceryStoreSystem(storeName, address, employeeDiscount);
		
		groceryStoreSystem = null;
		groceryStoreSystem = groceryStoreSystemRepository.findGroceryStoreSystemByStoreName(storeName);
		
		//testing
		assertNotNull(groceryStoreSystem);
		
		assertEquals(storeName,groceryStoreSystem.getStoreName());
		assertEquals(address,groceryStoreSystem.getAddress());
		assertEquals(employeeDiscount,groceryStoreSystem.getEmployeeDiscount());
	}
	
	@Test
	public void testPersistAndLoadItemByGroceryStoreSystem() {
		//create grocery store system instance
		String storeName = "My Market";
		String address = "845 Sherbrooke St W, Montreal, Quebec H3A 0G4";
		int employeeDiscount = 20;
		
		GroceryStoreSystem groceryStoreSystem = createGroceryStoreSystem(storeName, address, employeeDiscount);
		
		//create item instance
		String name = "Carrot";
		int price = 2;
		int point = 10;
		int returnPolicy = 2;
		boolean pickup = true;
		int inStoreQuantity = 58;
		
		Item item = createItem(name, price, point, returnPolicy, pickup, inStoreQuantity);
		int id = item.getId();
		
		//add the list of items to grocerySystem list
		Set<Item> items = new HashSet<Item>();
		items.add(item);
		groceryStoreSystem.setItems(items);
		itemRepository.save(item);
		groceryStoreSystemRepository.save(groceryStoreSystem);
		
		items = null;
		item  = null;
		groceryStoreSystem = null;
		
		groceryStoreSystem = groceryStoreSystemRepository.findGroceryStoreSystemByStoreName(storeName);
		item = groceryStoreSystem.getItems().iterator().next();
		
		//testing
		assertNotNull(groceryStoreSystem);
		assertEquals(id,item.getId());
		assertEquals(name,item.getName());
		assertEquals(price,item.getPrice());
		assertEquals(point,item.getPoint());
		assertEquals(returnPolicy,item.getReturnPolicy());
		assertEquals(pickup,item.getPickup());
		assertEquals(inStoreQuantity,item.getInStoreQuantity());
	}
	
	@Test
	public void testPersistandLoadOpeningHoursByGroceryStoreSystem() {
		//create grocery store system instance
		String storeName = "My Market";
		String address = "845 Sherbrooke St W, Montreal, Quebec H3A 0G4";
		int employeeDiscount = 20;
		
		GroceryStoreSystem groceryStoreSystem = createGroceryStoreSystem(storeName, address, employeeDiscount);
		
		//creates an instance of opening hours
		WeekDay dayOfWeek = WeekDay.Monday;
		Time startTime = java.sql.Time.valueOf(LocalTime.of(9, 30));
		Time endTime = java.sql.Time.valueOf(LocalTime.of(17, 00));
		boolean working = true;
		
		BusinessHour bH = createBusinessHour(dayOfWeek,startTime,endTime,working);
		
		int id = bH.getId();
		
		//adding opening hours to the system
		Set<BusinessHour> openingHours = new HashSet<BusinessHour>();
		openingHours.add(bH);
		groceryStoreSystem.setOpeningHours(openingHours);
		groceryStoreSystemRepository.save(groceryStoreSystem);
		
		bH = null;
		openingHours = null;
		groceryStoreSystem = null;
		
		groceryStoreSystem = groceryStoreSystemRepository.findGroceryStoreSystemByStoreName(storeName);
		bH = groceryStoreSystem.getOpeningHours().iterator().next();
		
		//testing
		assertNotNull(groceryStoreSystem);
		assertEquals(id,bH.getId());
		assertEquals(dayOfWeek, bH.getDay());
		assertEquals(startTime, bH.getStartTime());
		assertEquals(endTime, bH.getEndTime());
		assertEquals(working,bH.getWorking());
	}
}
