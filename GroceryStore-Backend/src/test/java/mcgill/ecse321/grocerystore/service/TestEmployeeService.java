package mcgill.ecse321.grocerystore.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
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
import org.mockito.stubbing.Answer;

import mcgill.ecse321.grocerystore.dao.BusinessHourRepository;
import mcgill.ecse321.grocerystore.dao.EmployeeRepository;
import mcgill.ecse321.grocerystore.dao.PersonRepository;
import mcgill.ecse321.grocerystore.dao.UserRoleRepository;
import mcgill.ecse321.grocerystore.model.Customer;
import mcgill.ecse321.grocerystore.model.Employee;
import mcgill.ecse321.grocerystore.model.Person;

@ExtendWith(MockitoExtension.class)
public class TestEmployeeService {

	@Mock
	private EmployeeRepository employeeDao;
	@Mock
	private PersonRepository personDao;
	@Mock
	private UserRoleRepository userRoleDao;
	@Mock
	private BusinessHourRepository businessHourDao;

	@InjectMocks
	private EmployeeService service;
	@InjectMocks
	private PersonService personService;

	private static final int ID_KEY = 1;
	private static final int FAKE_ID_KEY = 2;

	@BeforeEach
	public void setMockOutput() {
		lenient().when(employeeDao.findEmployeeById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(ID_KEY)) {
				Employee employee = new Employee();
				employee.setId(ID_KEY);
				return employee;
			} else {
				return null;
			}
		});
		lenient().when(personDao.existsById(anyString())).thenReturn(true);
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(employeeDao.save(any(Employee.class))).thenAnswer(returnParameterAsAnswer);
	}

	@Test
	public void testCreateEmployee() {
		assertEquals(0, service.getAllEmployees().size());
		Employee employee = null;
		Person person = personService.createPerson("email@gmail.com", "Bob", "The Builder", "111-222-3333", "123 street");
		try {
			employee = service.createEmployee(person);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}
		assertNotNull(employee);
	}
	
	@Test
	public void testUpdateEmployee() {
		Person person = personService.createPerson("email@gmail.com", "Bob", "The Builder", "111-222-3333", "123 street");
		Employee employee = null;
		try {
			employee = service.updateEmployee(ID_KEY, person);
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertNotNull(employee); 
		assertEquals(employee.getPerson(), person);
	}
	
	@Test
	public void testUpdateEmployeeIdNegative() {
		Person person = personService.createPerson("email@gmail.com", "Bob", "The Builder", "111-222-3333", "123 street");
		Employee employee = null;
		String error = "";
		try {
			employee = service.updateEmployee(-1, person);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(employee);
		assertEquals("Invalid id", error);
	}

	@Test
	public void testGetExistingCustomer() {
		assertEquals(ID_KEY, service.getEmployee(ID_KEY).getId());
	}
	
	@Test
	public void testGetExistingEmployeeIdNegative() {
		int tempId = -1;
		Employee employee = null;
		String error = "";
		try {
			employee = service.getEmployee(tempId);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
//			fail();
			error = e.getMessage();
		}
		assertNull(employee);
		assertEquals("Invalid id", error);
	}

	@Test
	public void testGetNonExistingCustomer() {
		Employee employee = null;
		String error = "";
		try {
			employee = service.getEmployee(FAKE_ID_KEY);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
//			fail();
			error = e.getMessage();
		}
		assertEquals("No employee found", error);
		assertNull(employee);
	}

	@Test
	public void testDeleteEmployee() {
		assertEquals(0, service.getAllEmployees().size());
		Employee employee = null;
		try {
			employee = service.deleteEmployee(ID_KEY);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}
		assertNotNull(employee);
	}
	
	@Test
	public void testDeleteEmployeeIdNegative() {
		assertEquals(0, service.getAllEmployees().size());
		Employee employee = null;
		String error = "";
		try {
			employee = service.deleteEmployee(-1);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}
		assertEquals("Invalid id", error);
		assertNull(employee);
	}
	
	@Test
	public void testDeleteEmployeeEmployeeNotExists() {
		assertEquals(0, service.getAllEmployees().size());
		Employee employee = null;
		String error = "";
		try {
			employee = service.deleteEmployee(10);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}
		assertEquals("Employee with id 10 does not exists.", error);
		assertNull(employee);
	}

}
