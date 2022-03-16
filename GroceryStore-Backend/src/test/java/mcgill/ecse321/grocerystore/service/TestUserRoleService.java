package mcgill.ecse321.grocerystore.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;

import mcgill.ecse321.grocerystore.dao.CustomerRepository;
import mcgill.ecse321.grocerystore.dao.EmployeeRepository;
import mcgill.ecse321.grocerystore.dao.PersonRepository;
import mcgill.ecse321.grocerystore.dao.UserRoleRepository;
import mcgill.ecse321.grocerystore.model.Customer.TierClass;
import mcgill.ecse321.grocerystore.model.Person;
import mcgill.ecse321.grocerystore.model.UserRole;

@ExtendWith(MockitoExtension.class)
 public class TestUserRoleService {
		
	@Mock
	private EmployeeRepository employeeDao;
	@Mock
	private UserRoleRepository roleDao;

	@Mock 
	private CustomerRepository customerDao;
	
	@Mock
	private PersonRepository personDao;
	
	@InjectMocks
	private UserRoleService service;
	@InjectMocks
	private CustomerService customerService;
	@InjectMocks
	private PersonService personService;
	
	private static final int FAKE_ID_KEY = 6666666;
	private static final TierClass TIER_KEY = TierClass.Gold;
	private static final boolean BAN_KEY = true;

	private static final String EMAIL = "abc@gmail.com";
	private static final String FIRSTNAME = "Bob";
	private static final String LASTNAME = "Smith";
	private static final String PHONENUMBER = "1112223333";
	private static final String ADDRESS = "845 Sherbrooke St W, Montreal, Quebec H3A 0G4";
	
	@BeforeEach
	public void setMockOutput() {
		lenient().when(roleDao.findUserRoleById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(0)) {
				Person person = personService.createPerson(EMAIL, FIRSTNAME, LASTNAME, PHONENUMBER, ADDRESS);
				UserRole customer = customerService.createCustomer(person, TierClass.Gold, false);
				return customer;
			} else {
				return null;
			}
		});
		
		
		lenient().when(roleDao.findAll()).thenAnswer((InvocationOnMock invocation) -> {
			
			Person person = personService.createPerson(EMAIL, FIRSTNAME, LASTNAME, PHONENUMBER, ADDRESS);
			UserRole customer = customerService.createCustomer(person, TierClass.Gold, false);
			ArrayList<UserRole> temp = new ArrayList<UserRole>();
			temp.add(customer);
			return temp;
	
		});
		
		lenient().when(roleDao.findUserRoleByPerson(any())).thenAnswer((InvocationOnMock invocation) -> {
			
			Person person = personService.createPerson(EMAIL, FIRSTNAME, LASTNAME, PHONENUMBER, ADDRESS);
			UserRole customer = customerService.createCustomer(person, TierClass.Gold, false);
			return customer;
	
		});

		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(roleDao.save(any(UserRole.class))).thenAnswer(returnParameterAsAnswer);
	
	}
	
	@Test
	public void testFindUserRoleById() {
		UserRole temp = service.findUserRoleById(0);
		assertEquals(temp.getId(),0);
	}
	
	@Test
	public void testGetAllUserRoles() {
		List<UserRole> temp = service.getAllUserRoles();
		UserRole curr = temp.get(0);
		assertNotNull(curr);
	}
	
	@Test
	public void testFindUserRoleByBadId() {
		String error = "";
		UserRole temp = null;
		try {
			temp = service.findUserRoleById(-1);
		}
		catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(temp);
		assertEquals("No user with that id",error);
	}
}