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
import mcgill.ecse321.grocerystore.dao.PersonRepository;
import mcgill.ecse321.grocerystore.dao.UserRoleRepository;
import mcgill.ecse321.grocerystore.model.Customer.TierClass;
import mcgill.ecse321.grocerystore.model.Person;
import mcgill.ecse321.grocerystore.model.UserRole;

@ExtendWith(MockitoExtension.class)
 public class TestUserRoleService {
	

	@Mock
	private UserRoleRepository roleDao;
	@Mock 
	private PersonRepository personDao;
	@Mock 
	private CustomerRepository customerDao;
	
	@InjectMocks
	private UserRoleService service;
	@InjectMocks
	private CustomerService customerService;
	@InjectMocks
	private PersonService personService;
	
	private static final int ID_KEY = 1234567;

	@BeforeEach
	public void setMockOutput() {
		lenient().when(roleDao.findUserRoleById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(ID_KEY)) {
				Person person = personService.createPerson("m","m","m","5555555555","m");
				UserRole customer = customerService.createCustomer(person, TierClass.Gold, false);
				customer.setId(ID_KEY);
				return customer;
			} else {
				return null;
			}
		});
		lenient().when(personDao.existsById(anyString())).thenReturn(true);
	}
	
	@Test
	public void testFindUserRoleById() {
		UserRole temp = null;
		try {
			temp = service.findUserRoleById(ID_KEY);
		}catch(IllegalArgumentException e) {
			fail();
		}
		assertEquals(temp.getId(),ID_KEY);
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