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
	@InjectMocks
	private CustomerService customerService;

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
				UserRole customer = customerService.createCustomer(TierClass.Gold, false);
				customer.setId(ID_KEY);
				return customer;
			} else {
				return null;
			}
		});
			
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		
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
	
	
	
	
}