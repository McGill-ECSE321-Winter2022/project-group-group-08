package mcgill.ecse321.grocerystore.service;

import static org.mockito.Mockito.lenient;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
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

import mcgill.ecse321.grocerystore.dao.AccountRepository;
import mcgill.ecse321.grocerystore.dao.CartRepository;
import mcgill.ecse321.grocerystore.dao.ItemRepository;
import mcgill.ecse321.grocerystore.dao.PersonRepository;
import mcgill.ecse321.grocerystore.dao.QuantityRepository;
import mcgill.ecse321.grocerystore.model.Account;
import mcgill.ecse321.grocerystore.model.Cart;
import mcgill.ecse321.grocerystore.model.Item;
import mcgill.ecse321.grocerystore.model.Person;
import mcgill.ecse321.grocerystore.model.Quantity;

import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;


@ExtendWith(MockitoExtension.class)
public class TestQuantityService {

	@Mock
	private QuantityRepository quantityDao;
	
	@Mock
	private ItemRepository itemDao;
	
	@Mock
	private CartRepository cartDao;
	
	@Mock
	private PersonRepository personDao;
	
	@Mock
	private AccountRepository accountDao;


	@InjectMocks
	private QuantityService service;
	
	@InjectMocks
	private ItemService itemService;
	
	@InjectMocks
	private CartService cartService;
	
	@InjectMocks
	private PersonService personService;
	
	@InjectMocks
	private AccountService accountService;

	private static int ID = 0;
	private static int COUNT = 10;
	private static final Date DATE = java.sql.Date.valueOf(LocalDate.of(2022, Month.DECEMBER, 31));
	
	@BeforeEach
	public void setMockOutput() {
		lenient().when(quantityDao.findQuantityById(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(ID)) {
				Person person = personService.createPerson("email@gmail.com", "Bob", "The Builder", "111-222-3333", "123 street");
				Account account = accountService.createAccount("username123", "password123", false, 123, person);
				Quantity quantity = new Quantity();
				quantity.setCount(COUNT);
				quantity.setCart(cartService.createCart(DATE, account));
				quantity.setItem(itemService.createItem("Carrot", 2, 10, 2, true, 58));
				return quantity;
			} else {
				return null;
			}
		});
		lenient().when(quantityDao.findQuantityByItem(any())).thenAnswer((InvocationOnMock invocation) -> {
			Person person = personService.createPerson("email@gmail.com", "Bob", "The Builder", "111-222-3333", "123 street");
			Account account = accountService.createAccount("username123", "password123", false, 123, person);
			Quantity quantity = new Quantity();
			quantity.setCount(COUNT);
			quantity.setCart(cartService.createCart(DATE, account));
			quantity.setItem(itemService.createItem("Carrot", 2, 10, 2, true, 58));
			ArrayList<Quantity> list = new ArrayList<Quantity>();
			list.add(quantity);
			return list;
		});
		lenient().when(quantityDao.findQuantityByCart(any())).thenAnswer((InvocationOnMock invocation) -> {
			Person person = personService.createPerson("email@gmail.com", "Bob", "The Builder", "111-222-3333", "123 street");
			Account account = accountService.createAccount("username123", "password123", false, 123, person);
			Quantity quantity = new Quantity();
			quantity.setCount(COUNT);
			quantity.setCart(cartService.createCart(DATE, account));
			quantity.setItem(itemService.createItem("Carrot", 2, 10, 2, true, 58));
			ArrayList<Quantity> list = new ArrayList<Quantity>();
			list.add(quantity);
			return list;
		});
		lenient().when(quantityDao.findAll()).thenAnswer((InvocationOnMock invocation) -> {
			Person person = personService.createPerson("email@gmail.com", "Bob", "The Builder", "111-222-3333", "123 street");
			Account account = accountService.createAccount("username123", "password123", false, 123, person);
			Quantity quantity = new Quantity();
			quantity.setCount(COUNT);
			quantity.setCart(cartService.createCart(DATE, account));
			quantity.setItem(itemService.createItem("Carrot", 2, 10, 2, true, 58));
			ArrayList<Quantity> list = new ArrayList<Quantity>();
			list.add(quantity);
			return list;
		});
		lenient().when(itemDao.findItemById(ID)).thenAnswer((InvocationOnMock invocation) -> {
			Item item = new Item();
			item.setName("Carrot");
			item.setPrice(2);
			item.setPoint(1);
			item.setReturnPolicy(3);
			item.setPickup(true);
			item.setInStoreQuantity(7);
			return item;
		});
		lenient().when(cartDao.findCartById(ID)).thenAnswer((InvocationOnMock invocation) -> {
			Person person = personService.createPerson("email@gmail.com", "Bob", "The Builder", "111-222-3333", "123 street");
			Account account = accountService.createAccount("username123", "password123", false, 123, person);
			Cart cart = cartService.createCart(DATE, account);
			return cart;
		});
		lenient().when(personDao.existsById(anyString())).thenReturn(true);
		
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(quantityDao.save(any(Quantity.class))).thenAnswer(returnParameterAsAnswer);
	}
	
	@Test
	public void testCreateQuantity() {
		assertEquals(1, service.getAllQuantities().size());
		Person person = personService.createPerson("email@gmail.com", "Bob", "The Builder", "111-222-3333", "123 street");
		Account account = accountService.createAccount("username123", "password123", false, 123, person);
		Item item = itemService.createItem("Carrot", 2, 10, 2, true, 58);
		Cart cart = cartService.createCart(DATE, account);
		
		int count = 2;
		Quantity quantity = null;
		try {
			quantity = service.createQuantity(count, item, cart);
		} catch (IllegalArgumentException e) {
			fail();
		}
		int id = quantity.getId();
		assertNotNull(quantity);
		assertEquals(id,quantity.getId());
		assertEquals(count,quantity.getCount());
		assertEquals(item,quantity.getItem());
		assertEquals(cart,quantity.getCart());
	}
	
	@Test
	public void testCreateQuantityItemNull() {
		assertEquals(1, service.getAllQuantities().size());
		Person person = personService.createPerson("email@gmail.com", "Bob", "The Builder", "111-222-3333", "123 street");
		Account account = accountService.createAccount("username123", "password123", false, 123, person);
		Cart cart = cartService.createCart(DATE, account);
		String error = "";
		
		int count = 2;
		Quantity quantity = null;
		try {
			quantity = service.createQuantity(count, null, cart);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(quantity);
		assertEquals(error, "Item cannot be null or empty");
	}
	
	@Test
	public void testCreateQuantityCartNull() {
		assertEquals(1, service.getAllQuantities().size());
		Item item = itemService.createItem("Carrot", 2, 10, 2, true, 58);
		String error = "";
		
		int count = 2;
		Quantity quantity = null;
		try {
			quantity = service.createQuantity(count, item, null);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(quantity);
		assertEquals(error, "Cart cannot be null or empty");
	}
	
	@Test
	public void testGetAllQuantities() {
		List<Quantity> quantities = new ArrayList<Quantity>();
		quantities = service.getAllQuantities();
		Quantity quantity = quantities.get(0);
		assertNotNull(quantity);
	}
	
	@Test
	public void testGetQuantityById() {
		Quantity quantity = null;
		try {
			quantity = service.getQuantityById(ID);
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertNotNull(quantity);
		assertEquals(ID,quantity.getId());
		assertEquals(COUNT,quantity.getCount());
		assertNotNull(quantity.getItem());
		assertNotNull(quantity.getCart());
	}
	
	@Test
	public void testGetQuantityByIdNegative() {
		Quantity quantity = null;
		String error = "";
		try {
			quantity = service.getQuantityById(-1);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(quantity);
		assertEquals(error,"The id cannot be a negative number");
	}
	
	@Test
	public void testGetQuantityByIdNotExist() {
		int tempId = 5;
		Quantity quantity = null;
		String error = "";
		try {
			quantity = service.getQuantityById(tempId);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(quantity);
		assertEquals(error,"No quantity with  id " + tempId + " exists");
	
	}
	
	@Test
	public void testGetQuantityByItemId() {
		int itemId = 0;
		List<Quantity> quantities = new ArrayList<Quantity>();
		quantities = service.getQuantityByItemId(itemId);
		Quantity quantity = quantities.get(0);
		assertNotNull(quantity);
	}
	
	@Test
	public void testGetQuantityByItemIdNegative() {
		int itemId = -1;
		List<Quantity> quantities = new ArrayList<Quantity>();
		String error = "";
		try {
			quantities = service.getQuantityByItemId(itemId);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals(quantities.size(), 0);
		assertEquals(error, "The itemId cannot be a negative number");
		
	}
	
	@Test
	public void testGetQuantityByItemIdNoItem() {
		int itemId = 100;
		List<Quantity> quantities = new ArrayList<Quantity>();
		String error = "";
		try {
			quantities = service.getQuantityByItemId(itemId);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals(quantities.size(), 0);
		assertEquals(error, "No item found");
		
	}
	
	@Test
	public void testGetQuantityByCartId() {
		int cartId = 0;
		List<Quantity> quantities = new ArrayList<Quantity>();
		quantities = service.getQuantityByCartId(cartId);
		Quantity quantity = quantities.get(0);
		assertNotNull(quantity);
	}
	
	@Test
	public void testGetQuantityByCartIdNegative() {
		int cartId = -1;
		List<Quantity> quantities = new ArrayList<Quantity>();
		String error = "";
		try {
			quantities = service.getQuantityByCartId(cartId);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals(quantities.size(), 0);
		assertEquals(error, "The cartId cannot be a negative number");
		
	}
	
	@Test
	public void testGetQuantityByCartIdNoCart() {
		int cartId = 100;
		List<Quantity> quantities = new ArrayList<Quantity>();
		String error = "";
		try {
			quantities = service.getQuantityByCartId(cartId);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals(quantities.size(), 0);
		assertEquals(error, "No cart found");
		
	}
	
	@Test
    public void testUpdateQuantity() {
		Person person = personService.createPerson("email@gmail.com", "Bob", "The Builder", "111-222-3333", "123 street");
		Account account = accountService.createAccount("username123", "password123", false, 123, person);
		int count = 20;
		Item item = itemService.createItem("Onion", 3, 20, 3, false, 60);
		Cart cart = cartService.createCart(java.sql.Date.valueOf(LocalDate.of(2022, Month.DECEMBER, 30)), account);
		Quantity quantity = null;
		
        try {
        	quantity = service.updateQuantity(ID, count, item, cart);
        } catch (IllegalArgumentException e) {
        	fail();
        }
        assertNotNull(quantity);
        assertEquals(count, quantity.getCount());
        assertEquals(item, quantity.getItem());
        assertEquals(cart, quantity.getCart());
    }
	
	@Test
    public void testUpdateQuantityId() {
		Person person = personService.createPerson("email@gmail.com", "Bob", "The Builder", "111-222-3333", "123 street");
		Account account = accountService.createAccount("username123", "password123", false, 123, person);
		int count = 20;
		Item item = itemService.createItem("Onion", 3, 20, 3, false, 60);
		Cart cart = cartService.createCart(java.sql.Date.valueOf(LocalDate.of(2022, Month.DECEMBER, 30)), account);
		Quantity quantity = null;
		String error = "";
        try {
        	quantity = service.updateQuantity(-1, count, item, cart);
        } catch (IllegalArgumentException e) {
        	error = e.getMessage();
        }
        assertNull(quantity);
        assertEquals(error, "The id cannot be a negative number");
    }
	
	@Test
    public void testUpdateQuantityCount() {
		Person person = personService.createPerson("email@gmail.com", "Bob", "The Builder", "111-222-3333", "123 street");
		Account account = accountService.createAccount("username123", "password123", false, 123, person);
		int count = -20;
		Item item = itemService.createItem("Onion", 3, 20, 3, false, 60);
		Cart cart = cartService.createCart(java.sql.Date.valueOf(LocalDate.of(2022, Month.DECEMBER, 30)), account);
		Quantity quantity = null;
		String error = "";
        try {
        	quantity = service.updateQuantity(ID, count, item, cart);
        } catch (IllegalArgumentException e) {
        	error = e.getMessage();
        }
        assertNull(quantity);
        assertEquals(error, "Count cannot be a negative number");
    }
	
	@Test
    public void testUpdateQuantityItem() {
		Person person = personService.createPerson("email@gmail.com", "Bob", "The Builder", "111-222-3333", "123 street");
		Account account = accountService.createAccount("username123", "password123", false, 123, person);
		int count = 20;
		Cart cart = cartService.createCart(java.sql.Date.valueOf(LocalDate.of(2022, Month.DECEMBER, 30)), account);
		Quantity quantity = null;
		String error = "";
        try {
        	quantity = service.updateQuantity(ID, count, null, cart);
        } catch (IllegalArgumentException e) {
        	error = e.getMessage();
        }
        assertNull(quantity);
        assertEquals(error, "Item cannot be null");
    }
	
	@Test
    public void testUpdateQuantityCart() {
		int count = 20;
		Item item = itemService.createItem("Onion", 3, 20, 3, false, 60);
		Quantity quantity = null;
		String error = "";
        try {
        	quantity = service.updateQuantity(ID, count, item, null);
        } catch (IllegalArgumentException e) {
        	error = e.getMessage();
        }
        assertNull(quantity);
        assertEquals(error, "Cart cannot be null");
    }
	
	@Test
    public void testUpdateQuantityNotExist() {
		Person person = personService.createPerson("email@gmail.com", "Bob", "The Builder", "111-222-3333", "123 street");
		Account account = accountService.createAccount("username123", "password123", false, 123, person);
		int tempId = 4;
		int count = 20;
		Item item = itemService.createItem("Onion", 3, 20, 3, false, 60);
		Cart cart = cartService.createCart(java.sql.Date.valueOf(LocalDate.of(2022, Month.DECEMBER, 30)), account);
		Quantity quantity = null;
		String error = "";
        try {
        	quantity = service.updateQuantity(tempId, count, item, cart);
        } catch (IllegalArgumentException e) {
        	error = e.getMessage();
        }
        assertNull(quantity);
        assertEquals(error, "Quantity with id " + tempId + " does not exists");
    }
	
	@Test
    public void testDeleteQuantityById() {
		Quantity quantity = null;
        try {
        	quantity = service.deleteQuantityById(ID);
        } catch (IllegalArgumentException e) {
        	fail();
        }
        assertNotNull(quantity);
    }
	
	@Test
    public void testDeleteQuantityByIdNegative() {
		Quantity quantity = null;
        try {
        	quantity = service.deleteQuantityById(-1);
        } catch (IllegalArgumentException e) {
        	fail();
        }
        assertNull(quantity);
    }
	
	@Test
    public void testDeleteQuantityByIdNotExist() {
		Quantity quantity = null;
        try {
        	quantity = service.deleteQuantityById(3);
        } catch (IllegalArgumentException e) {
        	fail();
        }
        assertNull(quantity);
    }

}