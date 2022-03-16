//package mcgill.ecse321.grocerystore.dao;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//import java.sql.Time;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import mcgill.ecse321.grocerystore.model.BusinessHour;
//import mcgill.ecse321.grocerystore.model.BusinessHour.WeekDay;
//import mcgill.ecse321.grocerystore.model.GroceryStoreSystem;
//import mcgill.ecse321.grocerystore.model.Item;
//
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest
//public class TestGroceryStoreSystemPersistence {
//
//	@Autowired
//	private GroceryStoreSystemRepository groceryStoreSystemRepository;
//	@Autowired
//	private ItemRepository itemRepository;
//	@Autowired
//	private BusinessHourRepository bHRepository;
//
//	@AfterEach
//	public void clearDatabase() {
//		// First, we clear the repositories to avoid exceptions due to inconsistencies
//		groceryStoreSystemRepository.deleteAll();
//		itemRepository.deleteAll();
//		bHRepository.deleteAll();
//	}
//	
//	//creates a grocery store system
//	public GroceryStoreSystem createGroceryStoreSystem(String storeName, String address, int employeeDiscount) {
//		GroceryStoreSystem groceryStoreSystem = new GroceryStoreSystem();
//		groceryStoreSystem.setStoreName(storeName);
//		groceryStoreSystem.setAddress(address);
//		groceryStoreSystem.setEmployeeDiscount(employeeDiscount);
//		groceryStoreSystemRepository.save(groceryStoreSystem);
//		return groceryStoreSystem;
//	}
//	
//	//creates an item
//	public Item createItem(String name, int price, int point, int returnPolicy, boolean pickup, int inStoreQuantity) {
//		Item item = new Item();
//		item.setName(name);
//		item.setPrice(price);
//		item.setPoint(point);
//		item.setReturnPolicy(returnPolicy);
//		item.setPickup(pickup);
//		item.setInStoreQuantity(inStoreQuantity);
//		itemRepository.save(item);
//		return item;
//	}
//	
//	//creates a business hour
//	public BusinessHour createBusinessHour(WeekDay day, Time startTime, Time endTime, boolean working) {
//		BusinessHour bH = new BusinessHour();
//		bH.setDay(day);
//		bH.setStartTime(startTime);
//		bH.setEndTime(endTime);
//		bH.setWorking(working);
//		bHRepository.save(bH);
//		return bH;
//	}
//	
//	@Test
//	public void testPersistAndLoadGroceryStoreSystem() {
//		//grocery store system attributes
//		String storeName = "My Market";
//		String address = "845 Sherbrooke St W, Montreal, Quebec H3A 0G4";
//		int employeeDiscount = 20;
//		
//		GroceryStoreSystem groceryStoreSystem = createGroceryStoreSystem(storeName, address, employeeDiscount);
//		
//		groceryStoreSystem = null;
//		groceryStoreSystem = groceryStoreSystemRepository.findGroceryStoreSystemByStoreName(storeName);
//		
//		//testing
//		assertNotNull(groceryStoreSystem);
//		
//		assertEquals(storeName,groceryStoreSystem.getStoreName());
//		assertEquals(address,groceryStoreSystem.getAddress());
//		assertEquals(employeeDiscount,groceryStoreSystem.getEmployeeDiscount());
//	}
//}
