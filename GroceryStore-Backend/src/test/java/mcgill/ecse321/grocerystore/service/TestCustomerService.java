package mcgill.ecse321.grocerystore.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import mcgill.ecse321.grocerystore.dao.CustomerRepository;
import mcgill.ecse321.grocerystore.dao.PersonRepository;
import mcgill.ecse321.grocerystore.dao.UserRoleRepository;
import mcgill.ecse321.grocerystore.model.Customer;
import mcgill.ecse321.grocerystore.model.Person;
import mcgill.ecse321.grocerystore.model.UserRole;
import mcgill.ecse321.grocerystore.model.Customer.TierClass;

@ExtendWith(MockitoExtension.class)
public class TestCustomerService {

	@Mock
	private CustomerRepository customerDao;
	@Mock
	private PersonRepository personDao;
	@Mock
	private UserRoleRepository userRoleDao;

	@InjectMocks
	private CustomerService service;
	@InjectMocks
	private PersonService personService;

	private static final int ID_KEY = 1234567;
	private static final int FAKE_ID_KEY = 6666666;
	private static final TierClass TIER_KEY = TierClass.Bronze;
	private static final TierClass NEW_TIER_KEY = TierClass.Silver;
	private static final TierClass INVALID_TIER_KEY = null;
	private static final boolean BAN_KEY = false;
	private static final boolean NEW_BAN_KEY = true;
	private static final String EMAIL_KEY = "email@gmail.com";
	private static final String FIRSTNAME_KEY = "Bob";
	private static final String LASTNAME_KEY = "The Builder";
	private static final String PHONE_KEY = "111-222-3333";
	private static final String ADDR_KEY = "123 street";

	@BeforeEach
	public void setMockOutput() {
		lenient().when(customerDao.findCustomerById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(ID_KEY)) {
				Customer customer = new Customer();
				customer.setId(ID_KEY);
				customer.setTierclass(TIER_KEY);
				customer.setBan(BAN_KEY);
				return customer;
			} else {
				return null;
			}
		});
		lenient().when(customerDao.findCustomerByTierclass(TIER_KEY)).thenAnswer((InvocationOnMock invocation) -> {
			List<Customer> customerByTierList = new ArrayList<Customer>();
			Customer customer = new Customer();
			customer.setTierclass(TIER_KEY);
			customer.setBan(BAN_KEY);
			customerByTierList.add(customer);
			return customerByTierList;
		});
		lenient().when(customerDao.findCustomerByBan(BAN_KEY)).thenAnswer((InvocationOnMock invocation) -> {
			List<Customer> customerByBanList = new ArrayList<Customer>();
			Customer customer = new Customer();
			customer.setBan(BAN_KEY);
			customer.setTierclass(TIER_KEY);
			customerByBanList.add(customer);
			return customerByBanList;
		});
		lenient().when(customerDao.existsById(ID_KEY)).thenReturn(true);
		lenient().when(personDao.existsById(anyString())).thenReturn(true);
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(customerDao.save(any(Customer.class))).thenAnswer(returnParameterAsAnswer);
	}

	@Test
	public void testCreateCustomerSimple() {
		assertEquals(0, service.getAllCustomers().size());
		TierClass defaultTier = TierClass.Bronze;
		boolean defaultBan = false;
		Customer customer = null;
		Person person = personService.createPerson(EMAIL_KEY, FIRSTNAME_KEY, LASTNAME_KEY, PHONE_KEY,
				ADDR_KEY);
		try {
			customer = service.createCustomer(person);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}
		assertNotNull(customer);
		assertEquals(defaultTier, customer.getTierclass());
		assertEquals(defaultBan, customer.getBan());
	}

	@Test
	public void testCreateCustomerSimpleInvalidPerson() {
		String error = null;
		Customer customer = null;
		Person person = null;
		try {
			customer = service.createCustomer(person, TIER_KEY, BAN_KEY);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertNull(customer);
		assertNull(person);
		assertEquals("Invalid person", error);
	}
	
	@Test
	public void testCreateCustomer() {
		Customer customer = null;
		Person person = personService.createPerson(EMAIL_KEY, FIRSTNAME_KEY, LASTNAME_KEY, PHONE_KEY,
				ADDR_KEY);
		try {
			customer = service.createCustomer(person, TIER_KEY, BAN_KEY);
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertNotNull(customer);
		assertEquals(TIER_KEY, customer.getTierclass());
		assertEquals(BAN_KEY, customer.getBan());
	}
	
	@Test
	public void testCreateCustomerInvalidPerson() {
		String error = null;
		Customer customer = null;
		Person person = null;
		try {
			customer = service.createCustomer(person, TIER_KEY, BAN_KEY);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertNull(customer);
		assertNull(person);
		assertEquals("Invalid person", error);
	}
	
	@SuppressWarnings("unused")
	@Test
	public void testCreateCustomerInvalidPersonWithRole() {
		String error = null;
		Customer customer = null;
		Person person = personService.createPerson(EMAIL_KEY, FIRSTNAME_KEY, LASTNAME_KEY, PHONE_KEY,
				ADDR_KEY);
		UserRole oldCustomer = service.createCustomer(person, TIER_KEY, BAN_KEY);
		try {
			customer = service.createCustomer(person, TIER_KEY, BAN_KEY);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertNull(customer);
		assertEquals("Person has already been assigned a role", error);
	}
	
	@Test
	public void testCreateCustomerInvalidTier() {
		String error = null;
		Customer customer = null;
		Person person = personService.createPerson(EMAIL_KEY, FIRSTNAME_KEY, LASTNAME_KEY, PHONE_KEY,
				ADDR_KEY);
		try {
			customer = service.createCustomer(person, INVALID_TIER_KEY, BAN_KEY);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertNull(customer);
		assertEquals("Invalid tier Class", error);
	}
	
//	@Test
//	public void testUpdateCustomer() {
//		Person person = personService.createPerson(EMAIL_KEY, FIRSTNAME_KEY, LASTNAME_KEY, PHONE_KEY,
//				ADDR_KEY);
//		Customer customer = service.createCustomer(person, TIER_KEY, BAN_KEY);
//		int id = customer.getId();
//		try {
//			customer = service.updateCustomer(id, person, NEW_TIER_KEY, NEW_BAN_KEY);
//		} catch (IllegalArgumentException e) {
//			fail();
//		}
//		assertNotNull(customer);
//		assertEquals(NEW_TIER_KEY, customer.getTierclass());
//		assertEquals(NEW_BAN_KEY, customer.getBan());
//	}

	@Test
	public void testGetExistingCustomer() {
		Customer customer  = null;
		try {
			customer = service.getCustomer(ID_KEY);
		}catch(InvalidInputException e) {
			fail();
		}
		assertNotNull(customer);
		assertEquals(TIER_KEY, customer.getTierclass());
		assertEquals(BAN_KEY, customer.getBan());
	}

	@Test
	public void testGetNonExistingCustomer() {
		Customer customer  = null;
		String error = "";
		try {
			customer = service.getCustomer(FAKE_ID_KEY);
		}catch(InvalidInputException e) {
			error = e.getMessage();
		}
		assertNull(customer);
		assertEquals("Customer does not exist",error);
	}

	@Test
	public void testGetAllCustomerByTier() {
		List<Customer> customers = new ArrayList<Customer>();
		try {
			customers = service.getAllCustomerByTier(TIER_KEY);
		}catch(InvalidInputException e){
			fail();
		}
		Customer customer = customers.get(0);
		assertNotNull(customer);
		assertEquals(TIER_KEY, customer.getTierclass());
		assertEquals(BAN_KEY, customer.getBan());
	}

	@Test
	public void testGetAllCustomerByBan() {
		List<Customer> customers = new ArrayList<Customer>();
		try {
			customers = service.getAllCustomerByBan(BAN_KEY);
		}catch(InvalidInputException e){
			fail();
		}
		Customer customer = customers.get(0);
		System.out.println(customer.getTierclass());
		assertNotNull(customer);
		assertEquals(TIER_KEY, customer.getTierclass());
		assertEquals(BAN_KEY, customer.getBan());
	}

	@Test
	public void testDeleteCustomer() {
		Customer customer = null;
		try {
			customer = service.deleteCustomer(ID_KEY);
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertNotNull(customer);
	}
}
