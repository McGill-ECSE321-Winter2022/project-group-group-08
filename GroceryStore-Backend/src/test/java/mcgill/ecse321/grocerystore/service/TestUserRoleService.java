package mcgill.ecse321.grocerystore.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;

import java.util.ArrayList;
import java.util.Collection;
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
import mcgill.ecse321.grocerystore.dao.EmployeeRepository;
import mcgill.ecse321.grocerystore.dao.ManagerRepository;
import mcgill.ecse321.grocerystore.dao.PersonRepository;
import mcgill.ecse321.grocerystore.dao.UserRoleRepository;
import mcgill.ecse321.grocerystore.model.Customer;
import mcgill.ecse321.grocerystore.model.Customer.TierClass;
import mcgill.ecse321.grocerystore.model.Employee;
import mcgill.ecse321.grocerystore.model.Manager;
import mcgill.ecse321.grocerystore.model.Person;
import mcgill.ecse321.grocerystore.model.Receipt;
import mcgill.ecse321.grocerystore.model.UserRole;

@ExtendWith(MockitoExtension.class)
 public class TestUserRoleService {
	
	@Mock
	private CustomerRepository customerDao;
	
	@Mock
	private EmployeeRepository employeeDao;
	@Mock
	private UserRoleRepository roleDao;
	
	@InjectMocks
	private UserRoleService service;

	@Mock
	private ManagerRepository managerDao;
	
	@Mock
	private PersonRepository personDao;

	@InjectMocks
	private ManagerService managerService;
	
	@InjectMocks
	private PersonService personService;
	
	private static final int ID_KEY = 1234567;
	private static final int FAKE_ID_KEY = 6666666;
	private static final TierClass TIER_KEY = TierClass.Gold;
	private static final boolean BAN_KEY = true;

	@BeforeEach
	public void setMockOutput() {
		lenient().when(roleDao.findUserRoleById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(ID_KEY)) {
				UserRole customer = service.createCustomer(TierClass.Gold, false);
				customer.setId(ID_KEY);
				return customer;
			} else {
				return null;
			}
		});
		
		lenient().when(customerDao.findCustomerById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(ID_KEY)) {
				Customer customer = new Customer();
				customer.setId(ID_KEY);
				return customer;
			} else {
				return null;
			}
		});
		
		lenient().when(customerDao.findCustomerByBan(anyBoolean())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(ID_KEY)) {
				Customer customer = new Customer();
				customer.setId(ID_KEY);
				customer.setBan(true);
				return customer;
			} else {
				return null;
			}
		});

		lenient().when(customerDao.findCustomerByTierclass(anyTier())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(TIER_KEY)) {
				Customer customer = new Customer();
				customer.setTierclass(TIER_KEY);
				return customer;
			} else {
				return null;
			}
		});

		lenient().when(customerDao.findCustomerByBan(anyBoolean())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(BAN_KEY)) {
				Customer customer = new Customer();
				customer.setBan(BAN_KEY);
				return customer;
			} else {
				return null;
			}
		});
		
		lenient().when(employeeDao.findEmployeeById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(ID_KEY)) {
				Employee employee = new Employee();
				employee.setId(ID_KEY);
				return employee;
			} else {
				return null;
			}
		});
		lenient().when(managerDao.findManagerById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(0)) {
				Person person = personService.createPerson("email@gmail.com", "Bob", 
						"The Builder", "111-222-3333", "123 street");
				Manager manager = new Manager();
				manager.setPerson(person);
				return manager;
			} else {
				return null;
			}
		});
		lenient().when(managerDao.findAll()).thenAnswer((InvocationOnMock invocation) -> {
			Person person = personService.createPerson("email@gmail.com", "Bob", 
					"The Builder", "111-222-3333", "123 street");
			Manager manager = new Manager();
			manager.setPerson(person);
			List<Manager> managers = new ArrayList<Manager>();
			managers.add(manager);
			return managers;
		});
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(managerDao.save(any(Manager.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(customerDao.save(any(Customer.class))).thenAnswer(returnParameterAsAnswer);
	}

	private TierClass anyTier() {
		return getRandomTier();
	}

	public static TierClass getRandomTier() {
		return TierClass.values()[(int) (Math.random() * TierClass.values().length)];
	}

	
	@Test
	public void testFindUserRoleById() {
		UserRole temp = service.findUserRoleById(ID_KEY);
		assertEquals(temp.getId(),ID_KEY);
	}
	@Test
	public void testFindUserRoleByBadId() {
		String error = "";
		UserRole temp;
		try {
			temp = service.findUserRoleById(-1);
		}
		catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals(error, "No user with that id");
	}
	
	public void testGetAllCustomerByBan() {
		String error = "";
		List<Customer> customers = service.getAllCustomerByBan(true);
		Customer curr = customers.get(0);
		assertNotNull(curr);
	}
	@Test
	public void testGetAllCustomerBanWithNoBans() {
		String error = "";
		List<Customer> customers = new ArrayList<Customer>();
		
		try {
			customers  = service.getAllCustomerByBan(false);
		}
		
		catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals(error, "No customers with ban");
	}
	
	@Test
	public void testCreateCustomerSimple() {
		assertEquals(0, service.getAllCustomers().size());
		TierClass defaultTier = TierClass.Bronze;
		boolean defaultBan = false;
		Customer customer = null;
		try {
			customer = service.createCustomer();
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}
		assertNotNull(customer);
		assertEquals(defaultTier, customer.getTierclass());
		assertEquals(defaultBan, customer.getBan());
	}

	@Test
	public void testCreateCustomer() {
		assertEquals(0, service.getAllCustomers().size());
		TierClass tier = TierClass.Silver;
		boolean ban = true;
		Customer customer = null;
		try {
			customer = service.createCustomer(tier, ban);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}
		assertNotNull(customer);
		assertEquals(tier, customer.getTierclass());
		assertEquals(ban, customer.getBan());
	}
	
	
	@Test
	public void testCreateEmployee() {
		assertEquals(0, service.getAllEmployees().size());
		Employee employee = null;
		try {
			employee = service.createEmployee();
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}
		assertNotNull(employee);
	}
	
	@Test
	public void testGetExistingCustomer() {
		assertEquals(ID_KEY, service.getEmployee(ID_KEY).getId());
	}
	
	@Test
	public void testGetNonExistingCustomer() {
		assertNull(service.getEmployee(FAKE_ID_KEY));
	}
	
	@Test
	public void testGetCustomer() {
		assertNotNull(service.getCustomer(ID_KEY));
	}
	
	//manager tests
	
	@Test
	public void testCreateManager() {
		
		Manager manager = null;
		try {
			manager = service.createManager();
		} catch (IllegalArgumentException e) {
			fail();
		}
		int id = manager.getId();
		assertNotNull(manager);
		
		assertEquals(id,manager.getId());
	}
	
	@Test
	public void testGetManagerById() {
		Manager manager = null;
		try {
			manager = service.getManager(0);
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertNotNull(manager);
		assertEquals(0,manager.getId());
		
	}
	

	@Test
	public void testGetManagerByIdNegative() {
		Manager manager = null;
		String error = "";
		int id = -1;
		try {
			manager = service.getManager(id);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(manager);
		assertEquals(error,"The id cannot be a negative number");
	}
	
	@Test
	public void testGetManagerByIdNull() {
		Manager manager = null;
		String error = "";
		int id = 3;
		try {
			manager = service.getManager(id);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(manager);
		assertEquals(error,"No manager with id " + id + " exists");
	}
	
	
	
	
	@Test
	public void testDeleteManager() {
		Manager manager = service.createManager();
		assertTrue(service.deleteManager(manager));
	}
	
	@Test
    public void testDeleteManagerById() {
		boolean managerDeleted = false;
        try {
        	managerDeleted = service.deleteManagerById(0);
        } catch (IllegalArgumentException e) {
        	fail();
        }
        assertTrue(managerDeleted);
    }
	
	@Test
    public void testDeleteManagerByIdNegative() {
		boolean managerDeleted = true;
        try {
        	managerDeleted = service.deleteManagerById(-1);
        } catch (IllegalArgumentException e) {
        	fail();
        }
        assertFalse(managerDeleted);
    }
	
	@Test
    public void testDeleteManagerByIdNotExists() {
		boolean managerDeleted = true;
        try {
        	managerDeleted = service.deleteManagerById(4);
        } catch (IllegalArgumentException e) {
        	fail();
        }
        assertFalse(managerDeleted);
    }
	
	@Test
	public void testGetAllManagers() {
		List<Manager> managers = new ArrayList<Manager>();
		managers = service.getAllManagers();
		Manager manager = managers.get(0);
		assertNotNull(manager);
	}
	
	
}