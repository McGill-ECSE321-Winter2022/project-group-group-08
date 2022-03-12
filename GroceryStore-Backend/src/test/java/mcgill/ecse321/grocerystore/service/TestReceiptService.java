package mcgill.ecse321.grocerystore.service;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import mcgill.ecse321.grocerystore.dao.AccountRepository;
import mcgill.ecse321.grocerystore.dao.CartRepository;
import mcgill.ecse321.grocerystore.dao.PersonRepository;
import mcgill.ecse321.grocerystore.model.Account;
import mcgill.ecse321.grocerystore.model.Cart;
import mcgill.ecse321.grocerystore.model.Person;

@ExtendWith(MockitoExtension.class)
 public class TestReceiptService1 {

 	@Mock
 	private ItemRepository itemDao;


 	@InjectMocks
 	private ItemService service;

 	private static int ID = 0;
 	private static final String NAME = "Carrot";
 	private static final int PRICE = 3;
 	private static final int POINT = 100;
 	private static final int RETURN_POLICY = 2;
 	private static final boolean PICKUP = true;
 	private static final int IN_STORE_QUANTITY = 52;


 	@BeforeEach
 	public void setMockOutput() {
 		lenient().when(itemDao.findItemById(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
 			if(invocation.getArgument(0).equals(ID)) {
 				Item item = new Item();
 				item.setName(NAME);
 				item.setPrice(PRICE);
 				item.setPoint(POINT);
 				item.setReturnPolicy(RETURN_POLICY);
 				item.setPickup(PICKUP);
 				item.setInStoreQuantity(IN_STORE_QUANTITY);
 				return item;
 			} else {
 				return null;
 			}
 		});
 		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
 			return invocation.getArgument(0);
 		};
 		lenient().when(itemDao.save(any(Item.class))).thenAnswer(returnParameterAsAnswer);
 	}

 	@Test
 	public void testCreateReceipt() {
 		assertEquals(0, service.getAllItems().size());

 		String name = "Carrot";
 		int price = 2;
 		int point = 10;
 		int returnPolicy = 2;
 		boolean pickup = true;
 		int inStoreQuantity = 58;
 		Item item = null;
 		try {
 			item = service.createItem(name, price, point, returnPolicy, pickup, inStoreQuantity);
 		} catch (IllegalArgumentException e) {
 			fail();
 		}
 		int id = item.getId();
 		assertNotNull(item);

 		assertEquals(id,item.getId());
 		assertEquals(name,item.getName());
 		assertEquals(price,item.getPrice());
 		assertEquals(point,item.getPoint());
 		assertEquals(returnPolicy,item.getReturnPolicy());
 		assertEquals(pickup,item.getPickup());
 		assertEquals(inStoreQuantity,item.getInStoreQuantity());
 	}

 	@Test
 	public void testCreateItemNameNull() {		
 		String name = null;
 		int price = 2;
 		int point = 10;
 		int returnPolicy = 2;
 		boolean pickup = true;
 		int inStoreQuantity = 58;
 		Item item = null;
 		String error = null;
 		try {
 			item = service.createItem(name, price, point, returnPolicy, pickup, inStoreQuantity);
 		} catch (IllegalArgumentException e) {
 			error = e.getMessage();
 		}
 		assertNull(item);
 		assertEquals(error,"Item name cannot be null or empty");
 	}

 	@Test
 	public void testCreateItemNameEmpty() {		
 		String name = "";
 		int price = -1;
 		int point = -1;
 		int returnPolicy = -1;
 		boolean pickup = true;
 		int inStoreQuantity = -1;
 		Item item = null;
 		String error = null;
 		try {
 			item = service.createItem(name, price, point, returnPolicy, pickup, inStoreQuantity);
 		} catch (IllegalArgumentException e) {
 			error = e.getMessage();
 		}
 		assertNull(item);
 		assertEquals(error,"Item name cannot be null or empty");
 	}

 	@Test
 	public void testCreateItemNameSpaces() {		
 		String name = " ";
 		int price = -1;
 		int point = -1;
 		int returnPolicy = -1;
 		boolean pickup = true;
 		int inStoreQuantity = -1;
 		Item item = null;
 		String error = null;
 		try {
 			item = service.createItem(name, price, point, returnPolicy, pickup, inStoreQuantity);
 		} catch (IllegalArgumentException e) {
 			error = e.getMessage();
 		}
 		assertNull(item);
 		assertEquals(error,"Item name cannot be null or empty");
 	}

 	@Test
 	public void testCreateItemPriceNegative() {		
 		String name = "Carrot";
 		int price = -2;
 		int point = 10;
 		int returnPolicy = 2;
 		boolean pickup = true;
 		int inStoreQuantity = 58;
 		Item item = null;
 		String error = null;
 		try {
 			item = service.createItem(name, price, point, returnPolicy, pickup, inStoreQuantity);
 		} catch (IllegalArgumentException e) {
 			error = e.getMessage();
 		}
 		assertNull(item);
 		assertEquals(error,"The price cannot be a negative number");
 	}

 	@Test
 	public void testCreateItemPointNegative() {		
 		String name = "Carrot";
 		int price = 2;
 		int point = -10;
 		int returnPolicy = 2;
 		boolean pickup = true;
 		int inStoreQuantity = 58;
 		Item item = null;
 		String error = null;
 		try {
 			item = service.createItem(name, price, point, returnPolicy, pickup, inStoreQuantity);
 		} catch (IllegalArgumentException e) {
 			error = e.getMessage();
 		}
 		assertNull(item);
 		assertEquals(error,"The point cannot be a negative number");
 	}

 	@Test
 	public void testCreateItemReturnPolicyNegative() {		
 		String name = "Carrot";
 		int price = 2;
 		int point = 10;
 		int returnPolicy = -2;
 		boolean pickup = true;
 		int inStoreQuantity = 58;
 		Item item = null;
 		String error = null;
 		try {
 			item = service.createItem(name, price, point, returnPolicy, pickup, inStoreQuantity);
 		} catch (IllegalArgumentException e) {
 			error = e.getMessage();
 		}
 		assertNull(item);
 		assertEquals(error,"The returnPolicy cannot be a negative number");
 	}

 	@Test
 	public void testCreateItemInStoreQuantityyNegative() {		
 		String name = "Carrot";
 		int price = 2;
 		int point = 10;
 		int returnPolicy = 2;
 		boolean pickup = true;
 		int inStoreQuantity = -58;
 		Item item = null;
 		String error = null;
 		try {
 			item = service.createItem(name, price, point, returnPolicy, pickup, inStoreQuantity);
 		} catch (IllegalArgumentException e) {
 			error = e.getMessage();
 		}
 		assertNull(item);
 		assertEquals(error,"The inStoreQuantity cannot be a negative number");
 	}

 //	@Test
 //	public void testGetAllItems() {
 //		try {
 //			System.out.println(service.createItem(NAME, PRICE, POINT, RETURN_POLICY, PICKUP, IN_STORE_QUANTITY).getId());
 //			System.out.println(service.createItem(NAME, PRICE, POINT, RETURN_POLICY, PICKUP, IN_STORE_QUANTITY).getId());
 //		} catch (IllegalArgumentException e) {
 //			fail();
 //		}
 //		try {
 //			System.out.println(service.createItem(NAME, PRICE, POINT, RETURN_POLICY, PICKUP, IN_STORE_QUANTITY).getId());
 //		} catch (IllegalArgumentException e) {
 //			fail();
 //		}
 //		
 //		List<Item> items = null;
 //		try {
 //			items = service.getAllItems();
 //		} catch (IllegalArgumentException e) {
 //			fail();
 //		}
 //		System.out.println(items);
 //		assertNotNull(items);
 //		assertEquals(2, items.size());
 //	}

 	@Test
 	public void testGetItemById() {
 		Item item = null;
 		String error = "";
 		try {
 			item = service.getItemById(ID);
 		} catch (IllegalArgumentException e) {
 			fail();
 		}
 		assertNotNull(item);
 		assertEquals(ID,item.getId());
 		assertEquals(NAME,item.getName());
 		assertEquals(PRICE,item.getPrice());
 		assertEquals(POINT,item.getPoint());
 		assertEquals(RETURN_POLICY,item.getReturnPolicy());
 		assertEquals(PICKUP,item.getPickup());
 		assertEquals(IN_STORE_QUANTITY,item.getInStoreQuantity());
 	}

 	@Test
 	public void testGetItemByIdNegative() {
 		Item item = null;
 		String error = "";
 		int id = -1;
 		try {
 			item = service.getItemById(id);
 		} catch (IllegalArgumentException e) {
 			error = e.getMessage();
 		}
 		assertNull(item);
 		assertEquals(error,"The id cannot be a negative number");
 	}

 	@Test
 	public void testGetItemByIdNull() {
 		Item item = null;
 		String error = "";
 		int id = 3;
 		try {
 			item = service.getItemById(id);
 		} catch (IllegalArgumentException e) {
 			error = e.getMessage();
 		}
 		assertNull(item);
 		assertEquals(error,"No item with id " + id + " exists");
 	}

 //	@Test
 //	public void testGetItemByName() {
 //		List<Item> item = null;
 //		String error = "";
 //		try {
 //			item = service.getItemByName(NAME);
 //		} catch (IllegalArgumentException e) {
 //			fail();
 //		}
 //		assertNotNull(item);
 //		assertEquals(ID,item.getId());
 //		assertEquals(NAME,item.getName());
 //		assertEquals(PRICE,item.getPrice());
 //		assertEquals(POINT,item.getPoint());
 //		assertEquals(RETURN_POLICY,item.getReturnPolicy());
 //		assertEquals(PICKUP,item.getPickup());
 //		assertEquals(IN_STORE_QUANTITY,item.getInStoreQuantity());
 //	}

 } 