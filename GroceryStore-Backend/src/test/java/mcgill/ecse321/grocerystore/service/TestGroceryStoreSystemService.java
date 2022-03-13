package mcgill.ecse321.grocerystore.service;
import java.sql.Time;
import java.time.LocalTime;
import static org.mockito.Mockito.lenient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

import org.apache.tomcat.jni.Time;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import mcgill.ecse321.grocerystore.dao.GroceryStoreSystemRepository;
import mcgill.ecse321.grocerystore.dao.ItemRepository;
import mcgill.ecse321.grocerystore.dto.GroceryStoreSystemDto;
import mcgill.ecse321.grocerystore.model.BusinessHour;
import mcgill.ecse321.grocerystore.model.BusinessHour.WeekDay;
import mcgill.ecse321.grocerystore.model.GroceryStoreSystem;
import mcgill.ecse321.grocerystore.model.Item;

import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;


@ExtendWith(MockitoExtension.class)
public class TestGroceryStoreSystemService {

	@Mock
	private GroceryStoreSystemRepository groceryStoreSystemDao;


	@InjectMocks
	private GroceryStoreSystemService service;
	
	@InjectMocks
	private GroceryStoreSystemService groceryService;

	
	private static final String storeName = "GStore";
	private static final String address = "173 Wellington St";
	private static final int employeeDiscount = 20;
	
	@BeforeEach
	public void setMockOutput() {
		lenient().when(groceryStoreSystemDao.findGroceryStoreSystemByStoreName(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(storeName)) {
				GroceryStoreSystem g  = new GroceryStoreSystem();
				g.setStoreName(storeName);
				g.setAddress(address);
				g.setEmployeeDiscount(employeeDiscount);
				
				return g;
			} else {
				return null;
			}
		});
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(groceryStoreSystemDao.save(any(GroceryStoreSystem.class))).thenAnswer(returnParameterAsAnswer);
	}
	@Test
	public void testCreateGroceryStoreSystem() {
		
		GroceryStoreSystem groceryStoreSystem = null;
		try {
			groceryStoreSystem = service.createGroceryStoreSystem(storeName, address, employeeDiscount);
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertNotNull(groceryStoreSystem);
		
		assertEquals(storeName,groceryStoreSystem.getStoreName());
		assertEquals(address,groceryStoreSystem.getAddress());
		assertEquals(employeeDiscount,groceryStoreSystem.getEmployeeDiscount());
	}
	
	
	@Test
	public void testCreateGroceryStoreSystemStoreNameNull() {		
		String storeName = null;
		String address = "173 Wellington St";
		int employeeDiscount = 20;
		GroceryStoreSystem groceryStoreSystem = null;
		String error = null;
		try {
			groceryStoreSystem = service.createGroceryStoreSystem(storeName, address, employeeDiscount);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(groceryStoreSystem);
		assertEquals("The store name cannot be empty.", error);
	}
	
	@Test
	public void testCreateGroceryStoreSystemStoreNameEmpty() {		
		String storeName = "";
		String address = "173 Wellington St";
		int employeeDiscount = 20;
		GroceryStoreSystem groceryStoreSystem = null;
		String error = null;
		try {
			groceryStoreSystem = service.createGroceryStoreSystem(storeName, address, employeeDiscount);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(groceryStoreSystem);
		assertEquals("The store name cannot be empty.", error);
	}
	
	@Test
	public void testCreateGroceryStoreSystemAddressNull() {		
		String storeName = "GStore";
		String address = null;
		int employeeDiscount = 20;
		GroceryStoreSystem groceryStoreSystem = null;
		String error = null;
		try {
			groceryStoreSystem = service.createGroceryStoreSystem(storeName, address, employeeDiscount);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(groceryStoreSystem);
		assertEquals("The address cannot be empty.", error);
	}
	
	@Test
	public void testCreateGroceryStoreSystemAddressEmpty() {		
		String storeName = "GStore";
		String address = "";
		int employeeDiscount = 20;
		GroceryStoreSystem groceryStoreSystem = null;
		String error = null;
		try {
			groceryStoreSystem = service.createGroceryStoreSystem(storeName, address, employeeDiscount);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(groceryStoreSystem);
		assertEquals("The address cannot be empty.", error);
	}
	
	@Test
	public void testCreateGroceryStoreSystemEmployeeDiscountNegative() {		
		String storeName = "GStore";
		String address = "173 Wellington St";
		int employeeDiscount = -1;
		GroceryStoreSystem groceryStoreSystem = null;
		String error = null;
		try {
			groceryStoreSystem = service.createGroceryStoreSystem(storeName, address, employeeDiscount);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(groceryStoreSystem);
		assertEquals("The employee discount cannot be less than 0.", error);
	}
	
	@Test
	public void testGetGroceryStoreSystemByStoreName() {
		GroceryStoreSystem groceryStoreSystem = null;
		try {
			groceryStoreSystem = service.getGroceryStoreSystem(storeName);
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertNotNull(groceryStoreSystem);
		assertEquals(storeName,groceryStoreSystem.getStoreName());
		assertEquals(address,groceryStoreSystem.getAddress());
		assertEquals(employeeDiscount,groceryStoreSystem.getEmployeeDiscount());
	}
	
	@Test
	public void testGetGroceryStoreSystemByStoreNameNull() {
		GroceryStoreSystem groceryStoreSystem = null;		
		String error = "";
		String storeName = null;
		try {
			groceryStoreSystem = service.getGroceryStoreSystem(storeName);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(groceryStoreSystem);
		assertEquals("The store name cannot be empty.", error);
	}
	
	@Test
	public void testGetGroceryStoreSystemByStoreNameEmpty() {
		GroceryStoreSystem groceryStoreSystem = null;		
		String error = "";
		String storeName = "";
		try {
			groceryStoreSystem = service.getGroceryStoreSystem(storeName);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(groceryStoreSystem);
		assertEquals("The store name cannot be empty.", error);
	}
	
	@Test
	public void testCreateBusinessHourWithEmptyID() {
		
		String error = "";
		BusinessHour curr = null;
		try {
			curr = service.createBusinessHour(0, WeekDay.Monday, Time.valueOf("18:45:20"),Time.valueOf("18:45:21"), true);
		}
		catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertNull(curr);
		assertEquals("Business Hour id cannot be empty", error);
		
	
	}
	
	
	@Test
	public void testCreateBusinessHourWithDayEmpty() {
		
		String error = "";
		BusinessHour curr = null;
		try {
			curr = service.createBusinessHour(2, null, Time.valueOf("18:45:20"),Time.valueOf("18:45:21"), true);
		}
		catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertNull(curr);
		assertEquals("Week day cannot be empty", error);
		
	
	}
	
	@Test
	public void testCreateBusinessHourWithStartTimeEmpty() {
		
		String error = "";
		BusinessHour curr = null;
		try {
			curr = service.createBusinessHour(2, WeekDay.Monday, null ,Time.valueOf("18:45:21"), true);
		}
		catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertNull(curr);
		assertEquals("Start time cannot be empty", error);
		
	
	}
	
	@Test
	public void testCreateBusinessHourWithEndTimeEmpty() {
		
		String error = "";
		BusinessHour curr = null;
		try {
			curr = service.createBusinessHour(2, WeekDay.Monday, Time.valueOf("18:45:20"),null, true);
		}
		catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertNull(curr);
		assertEquals("End time cannot be empty", error);
		
	
	}
	
	@Test
	public void testCreateBusinessHourWithBadOrderTimes() {
		
		String error = "";
		BusinessHour curr = null;
		try {
			curr = service.createBusinessHour(2, WeekDay.Monday, Time.valueOf("18:45:21"),Time.valueOf("18:45:20"), true);
		}
		catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		assertNull(curr);
		assertEquals("End time cannot be earlier than Start time", error);
		
	
	}
	
	@Test
	public void createBusinessHourGood() {
		
		
		BusinessHour curr = service.createBusinessHour(2, WeekDay.Monday, Time.valueOf("18:45:20"),Time.valueOf("18:45:21"), true);
		
		assertEquals(curr.getId(),2);
		assertEquals(curr.getDay(), WeekDay.Monday);
		assertEquals(curr.getStartTime(),Time.valueOf("18:45:20"));
		assertEquals(curr.getEndTime(),Time.valueOf("18:45:21"));
		
		
	
	}
	
	//updating
	
	@Test
	public void testUpdateBusinessHourWithGroceryEmpty() {
		
		String error = "";
		BusinessHour curr = service.createBusinessHour(2, WeekDay.Monday, Time.valueOf("18:45:20"),Time.valueOf("18:45:21"), true);

		try {
			service.updateBusinessHour(null, WeekDay.Monday, Time.valueOf("18:45:20"),Time.valueOf("18:45:21"), true);
		}
		catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		
		assertEquals("Grocery store is null", error);
		
	
	}
	
	@Test
	public void testUpdateBusinessHourWithDayEmpty() {
		
		String error = "";
		BusinessHour curr = service.createBusinessHour(2, WeekDay.Monday, Time.valueOf("18:45:20"),Time.valueOf("18:45:21"), true);
		GroceryStoreSystem currSystem = groceryService.createGroceryStoreSystem("a", "a", 0);
		try {
			service.updateBusinessHour(currSystem, null, Time.valueOf("18:45:20"),Time.valueOf("18:45:21"), true);
		}
		catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		
		assertEquals("Week day cannot be empty", error);
		
	
	}
	
	@Test
	public void testUpdateeBusinessHourWithStartTimeEmpty() {
		
		String error = "";
		BusinessHour curr = service.createBusinessHour(2, WeekDay.Monday, Time.valueOf("18:45:20"),Time.valueOf("18:45:21"), true);
		GroceryStoreSystem currSystem = groceryService.createGroceryStoreSystem("a", "a", 0);
		try {
			service.updateBusinessHour(currSystem, WeekDay.Monday, null,Time.valueOf("18:45:21"), true);
		}
		catch (IllegalArgumentException e){
			error = e.getMessage();
		}
	
		assertEquals("Start time cannot be empty", error);
		
	
	}
	
	@Test
	public void testUpdateBusinessHourWithEndTimeEmpty() {
		
		String error = "";
		BusinessHour curr = service.createBusinessHour(2, WeekDay.Monday, Time.valueOf("18:45:20"),Time.valueOf("18:45:21"), true);
		GroceryStoreSystem currSystem = groceryService.createGroceryStoreSystem("a", "a", 0);
		try {
			service.updateBusinessHour(currSystem, WeekDay.Monday, Time.valueOf("18:45:20"),null, true);
		}
		catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		
		assertEquals("End time cannot be empty", error);
		
	
	}
	
	@Test
	public void testUpdateBusinessHourWithBadOrderTimes() {
		
		String error = "";
		BusinessHour curr = service.createBusinessHour(2, WeekDay.Monday, Time.valueOf("18:45:20"),Time.valueOf("18:45:21"), true);
		GroceryStoreSystem currSystem = groceryService.createGroceryStoreSystem("a", "a", 0);
		try {
			service.updateBusinessHour(currSystem, WeekDay.Monday, Time.valueOf("18:45:21"),Time.valueOf("18:45:20"), true);
		}
		catch (IllegalArgumentException e){
			error = e.getMessage();
		}

		assertEquals("End time cannot be earlier than Start time", error);
		
	
	}
	
	@Test
	public void testUpdateBusinessHourGood() {
		String error = "";
		BusinessHour curr = service.createBusinessHour(2, WeekDay.Monday, Time.valueOf("18:45:20"),Time.valueOf("18:45:21"), true);
		GroceryStoreSystem currSystem = groceryService.createGroceryStoreSystem("a", "a", 0);
		service.updateBusinessHour(currSystem, WeekDay.Monday, Time.valueOf("18:45:20"),Time.valueOf("18:45:21"), true);
		
		assertEquals(curr.getDay(), WeekDay.Monday);
		assertEquals(curr.getStartTime(),Time.valueOf("18:45:20"));
		assertEquals(curr.getEndTime(),Time.valueOf("18:45:21"));
		
		
	
	}
	@Test
	public void testDeleteGrocery() {
		GroceryStoreSystem currSystem = groceryService.createGroceryStoreSystem("a", "a", 0);
		
		assertTrue(groceryService.deleteGroceryStoreSystem(currSystem));
	}
	@Test
	public void testDeleteGroceryWithNull() {
		
		assertFalse(groceryService.deleteGroceryStoreSystem(null));
	}
	
	@Test
	public void testDeleteGroceryByStoreName() {
		assertTrue(groceryService.deleteGroceryStoreSystemByStoreName("GStore"));
	}
	
	@Test
	public void testDeleteGroceryByStoreNameWithNull() {
		assertFalse(groceryService.deleteGroceryStoreSystemByStoreName(null));
	}
	
	//updategrocery
	
	@Test
	public void testUpdateGroceryStoreSystemStoreWithEmptyNull() {		
		String storeName = null;
		String address = "173 Wellington St";
		int employeeDiscount = 20;
		GroceryStoreSystem groceryStoreSystem = null;
		String error = null;
		try {
			groceryStoreSystem = service.updateGroceryStoreSystem(storeName, address, employeeDiscount);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(groceryStoreSystem);
		assertEquals("Store name cannot be empty", error);
	}
	
	@Test
	public void testUpdateGroceryStoreSystemStoreWithNullAddress() {		
		String storeName = "GG";
		String address = null;
		int employeeDiscount = 20;
		GroceryStoreSystem groceryStoreSystem = null;
		String error = null;
		try {
			groceryStoreSystem = service.updateGroceryStoreSystem(storeName, address, employeeDiscount);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(groceryStoreSystem);
		assertEquals("Address cannot be empty", error);
	}
	
	@Test
	public void testUpdateGroceryStoreSystemStoreWithEmptyName() {		
		String storeName = "";
		String address = "173 Wellington St";
		int employeeDiscount = 20;
		GroceryStoreSystem groceryStoreSystem = null;
		String error = null;
		try {
			groceryStoreSystem = service.updateGroceryStoreSystem(null, address, employeeDiscount);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(groceryStoreSystem);
		assertEquals("Store name cannot be empty", error);
	}
	
	@Test
	public void testUpdateGroceryStoreSystemStoreWithEmptyAddress() {		
		String storeName = "GStore";
		String address = "";
		int employeeDiscount = 20;
		GroceryStoreSystem groceryStoreSystem = null;
		String error = null;
		try {
			groceryStoreSystem = service.updateGroceryStoreSystem(storeName, address, employeeDiscount);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(groceryStoreSystem);
		assertEquals("Address cannot be empty", error);
	}
	
	
	@Test
	public void testUpdateGroceryStoreSystemStoreWithNegativeDiscount() {		
		String storeName = "GStore";
		String address = "173 Wellington St";
		int employeeDiscount = -20;
		GroceryStoreSystem groceryStoreSystem = null;
		String error = null;
		try {
			groceryStoreSystem = service.updateGroceryStoreSystem(storeName, address, employeeDiscount);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(groceryStoreSystem);
		assertEquals("Employee discount cannot be negative", error);
	}
	
	@Test
	public void testUpdateGroceryStoreSystemStoreWithNoSystem() {		
		String storeName = "Not a store";
		String address = "173 Wellington St";
		int employeeDiscount = 20;
		GroceryStoreSystem groceryStoreSystem = null;
		String error = null;
		try {
			groceryStoreSystem = service.updateGroceryStoreSystem(storeName, address, employeeDiscount);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(groceryStoreSystem);
		assertEquals("The grocery store system does not exist", error);
	}
	@Test
	public void testUpdateGroceryStoreSystemStore() {		
		String storeName = "GStore";
		String address = "173 Wellington St";
		int employeeDiscount = 20;
		GroceryStoreSystem groceryStoreSystem = null;
		String error = null;
		try {
			groceryStoreSystem = service.updateGroceryStoreSystem(storeName, address, employeeDiscount);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals(groceryStoreSystem.getStoreName(), storeName);
		assertEquals(groceryStoreSystem.getAddress(), address);
		assertEquals(groceryStoreSystem.getEmployeeDiscount(), employeeDiscount);
	}
	
	
	
	
	
	
	
	
}
