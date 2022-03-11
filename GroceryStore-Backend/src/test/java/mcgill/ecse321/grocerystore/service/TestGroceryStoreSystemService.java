package mcgill.ecse321.grocerystore.service;

import static org.mockito.Mockito.lenient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import mcgill.ecse321.grocerystore.dao.GroceryStoreSystemRepository;
import mcgill.ecse321.grocerystore.dao.ManagerRepository;
import mcgill.ecse321.grocerystore.model.GroceryStoreSystem;
import mcgill.ecse321.grocerystore.model.Manager;

import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class TestGroceryStoreSystemService {

	@Mock
	private GroceryStoreSystemRepository groceryStoreSystemDao;

	@InjectMocks
	private GroceryStoreSystemService service;
	
	private static final String storeName = "GStore";
	private static final String address = "173 Wellington St";
	private static final int employeeDiscount = 20;
	
	@BeforeEach
	public void setMockOutput() {
		lenient().when(groceryStoreSystemDao.findGroceryStoreSystemByStoreName(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(storeName)) {
				GroceryStoreSystem groceryStoreSystem = new GroceryStoreSystem();
				groceryStoreSystem.setStoreName(storeName);
				groceryStoreSystem.setAddress(address);
				groceryStoreSystem.setEmployeeDiscount(employeeDiscount);
				return groceryStoreSystem;
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
		assertEquals(error,"Grocery store system's name cannot be null or empty");
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
		assertEquals(error,"Grocery store system's name cannot be null or empty");
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
		assertEquals(error,"Grocery store system's address cannot be null or empty");
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
		assertEquals(error,"Grocery store system's address cannot be null or empty");
	}
	
	@Test
	public void testCreateGroceryStoreSystemEmployeeDiscountNegative() {		
		String storeName = "GStore";
		String address = "";
		int employeeDiscount = -1;
		GroceryStoreSystem groceryStoreSystem = null;
		String error = null;
		try {
			groceryStoreSystem = service.createGroceryStoreSystem(storeName, address, employeeDiscount);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(groceryStoreSystem);
		assertEquals(error,"Grocery store system's employee discount cannot be null or empty");
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
		assertEquals(error,"Grocery store name cannot be null");
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
		assertEquals(error,"Grocery store name cannot be empty");
	}
	
	
	
	
	
}
