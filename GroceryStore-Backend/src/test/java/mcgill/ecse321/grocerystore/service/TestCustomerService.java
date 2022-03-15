//package mcgill.ecse321.grocerystore.service;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertNull;
//import static org.junit.jupiter.api.Assertions.fail;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyBoolean;
//import static org.mockito.ArgumentMatchers.anyInt;
//import static org.mockito.Mockito.lenient;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.invocation.InvocationOnMock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.mockito.stubbing.Answer;
//
//import mcgill.ecse321.grocerystore.dao.CustomerRepository;
//import mcgill.ecse321.grocerystore.model.Customer;
//import mcgill.ecse321.grocerystore.model.Employee;
//import mcgill.ecse321.grocerystore.model.Customer.TierClass;
//
//@ExtendWith(MockitoExtension.class)
//public class TestCustomerService {
//
//	@Mock
//	private CustomerRepository customerDao;
//
//	@InjectMocks
//	private CustomerService service;
//
//	private static final int ID_KEY = 1234567;
//	private static final int FAKE_ID_KEY = 6666666;
//	private static final TierClass TIER_KEY = TierClass.Bronze;
//	private static final boolean BAN_KEY = false;
//
//	@BeforeEach
//	public void setMockOutput() {
//		lenient().when(customerDao.findCustomerById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
//			if (invocation.getArgument(0).equals(ID_KEY)) {
//				Customer customer = new Customer();
//				customer.setId(ID_KEY);
//				return customer;
//			} else {
//				return null;
//			}
//		});
//
//		lenient().when(customerDao.findCustomerByTierclass(anyTier())).thenAnswer((InvocationOnMock invocation) -> {
//				List<Customer> customerByTierList = new ArrayList<Customer>();
//				Customer customer = new Customer();
//				customer.setTierclass(TIER_KEY);
//				customerByTierList.add(customer);
//				return customerByTierList;
//		});
//
//		lenient().when(customerDao.findCustomerByBan(anyBoolean())).thenAnswer((InvocationOnMock invocation) -> {
//				List<Customer> customerByBanList = new ArrayList<Customer>();
//				Customer customer = new Customer();
//				customer.setBan(BAN_KEY);
//				customerByBanList.add(customer);
//				return customerByBanList;
//		});
//		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
//			return invocation.getArgument(0); 
//		};
//		lenient().when(customerDao.save(any(Customer.class))).thenAnswer(returnParameterAsAnswer);
//	}
//
//	private TierClass anyTier() {
//		return getRandomTier();
//	}
//
//	public static TierClass getRandomTier() {
//		return TierClass.values()[(int) (Math.random() * TierClass.values().length)];
//	}
//
//	@Test
//	public void testCreateCustomerSimple() {
//		assertEquals(0, service.getAllCustomers().size());
//		TierClass defaultTier = TierClass.Bronze;
//		boolean defaultBan = false;
//		Customer customer = null;
//		try {
//			customer = service.createCustomer();
//		} catch (IllegalArgumentException e) {
//			// Check that no error occurred
//			fail();
//		}
//		assertNotNull(customer);
//		assertEquals(defaultTier, customer.getTierclass());
//		assertEquals(defaultBan, customer.getBan());
//	}
//
//	@Test
//	public void testCreateCustomer() {
//		assertEquals(0, service.getAllCustomers().size());
//		TierClass tier = TierClass.Silver;
//		boolean ban = true;
//		Customer customer = null;
//		try {
//			customer = service.createCustomer(tier, ban);
//		} catch (IllegalArgumentException e) {
//			// Check that no error occurred
//			fail();
//		}
//		assertNotNull(customer);
//		assertEquals(tier, customer.getTierclass());
//		assertEquals(ban, customer.getBan());
//	}
//	
//	@Test
//	public void testGetExistingCustomer() {
//		assertEquals(ID_KEY, service.getCustomer(ID_KEY).getId());
//	}
//
//	@Test
//	public void testGetNonExistingCustomer() {
//		assertNull(service.getCustomer(FAKE_ID_KEY));
//	}
//	
//	@Test
//	public void testGetAllCustomerByTier() {
//		List<Customer> customers = new ArrayList<Customer>();
//		customers = service.getAllCustomerByTier(TIER_KEY);
//		Customer customer = customers.get(0);
//		assertNotNull(customer);
//	}
//	
//	@Test
//	public void testGetAllCustomerByBan() {
//		List<Customer> customers = new ArrayList<Customer>();
//		customers = service.getAllCustomerByBan(BAN_KEY);
//		Customer customer = customers.get(0);
//		assertNotNull(customer);
//	}
//	
//	@Test
//	public void testDeleteCustomer() {
//		assertEquals(0, service.getAllCustomers().size());
//		Customer customer = null;
//		try {
//			customer = service.deleteCustomer(ID_KEY);
//		} catch (IllegalArgumentException e) {
//			// Check that no error occurred
//			fail();
//		}
//		assertNotNull(customer);
//	}
//}
