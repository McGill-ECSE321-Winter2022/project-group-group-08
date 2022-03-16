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
import mcgill.ecse321.grocerystore.model.Customer.TierClass;
import mcgill.ecse321.grocerystore.model.Person;
import mcgill.ecse321.grocerystore.model.UserRole;

@ExtendWith(MockitoExtension.class)
 public class TestUserRoleService {
	
	@Mock
	private CustomerRepository customerDao;
	@Mock
	private UserRoleRepository roleDao;
	@Mock
	private PersonRepository personDao;
	
	@InjectMocks
	private UserRoleService service;
	@InjectMocks
	private CustomerService customerService;
	@InjectMocks
	private PersonService personService;

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
		lenient().when(personDao.existsById(anyString())).thenReturn(true);
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(roleDao.save(any(UserRole.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(personDao.save(any(Person.class))).thenAnswer(returnParameterAsAnswer);
	}

	
	@Test
	public void testFindUserRoleById() {
		UserRole temp = null;
		try {
			temp = service.findUserRoleById(0);
		}
		catch(IllegalArgumentException e) {
			fail();
		}
		assertEquals(0,temp.getId());
	}
	
	@Test
	public void testGetAllUserRoles() {
		List<UserRole> temp = service.getAllUserRoles();
		UserRole curr = null;
		try {
			curr = temp.get(0);
		}
		catch(IllegalArgumentException e) {
			fail();
		}
		assertNotNull(curr);
	}
	
	@Test
	public void testFindUserRoleByBadId() {
		String error = "";
		UserRole temp = null;
		try {
			temp = service.findUserRoleById(3);
		}
		catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(temp);
		assertEquals("No user with that id",error);
	}
	
	@Test
	public void testFindUserRoleByIdNegative() {
		String error = "";
		UserRole temp = null;
		try {
			temp = service.findUserRoleById(-1);
		}
		catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(temp);
		assertEquals("Id can't be negative",error);
	}
	
	
	
	@Test
	public void testFindUserRoleByPerson() {
		UserRole temp = null;
		String error = "";
		try {
			temp = service.findUserRoleByPerson(null);
		}catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(temp);
		assertEquals("Person cannot be null",error);
	}
}