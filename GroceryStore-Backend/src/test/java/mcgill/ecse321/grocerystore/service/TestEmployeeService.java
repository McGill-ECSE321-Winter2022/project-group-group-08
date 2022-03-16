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

	private static final int ID_KEY = 1234567;
	private static final int INVALID_ID_KEY = -2;
	private static final int FAKE_ID_KEY = 6666666;

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
	public void testCreateEmployeeInvalidPerson() {
		String error = null;
		Employee employee = null;
		Person person = null;
		try {
			employee = service.createEmployee(person);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertNull(employee);
		assertNull(person);
		assertEquals("Invalid person", error);
	}
	
	@Test
	public void testCreateEmployeeInvalidPersonWithRole() {
		String error = null;
		Employee employee = null;
		Person person = personService.createPerson("email@gmail.com", "Bob", "The Builder", "111-222-3333", "123 street");
		try {
			employee = service.createEmployee(person);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertNull(employee);
		assertEquals("Person has already been assigned a role", error);
	}
	
	@Test
	public void testUpdateEmployee() {
		Employee employee = null;
		Person person = personService.createPerson("email@gmail.com", "Bob", "The Builder", "111-222-3333", "123 street");;
		try {
			employee = service.updateEmployee(ID_KEY, person);
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertNotNull(employee);
	}
	
	@Test
	public void testUpdateEmployeeInvalidID() {
		String error = null;
		Employee employee = null;
		Person person = personService.createPerson("email@gmail.com", "Bob", "The Builder", "111-222-3333", "123 street");;
		try {
			employee = service.updateEmployee(INVALID_ID_KEY, person);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertNull(employee);
		assertEquals("Invalid id", error);
	}
	
	@Test
	public void testUpdateEmployeeInvalidPerson() {
		String error = null;
		Employee employee = null;
		Person person = null;
		try {
			employee = service.updateEmployee(ID_KEY, person);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertNull(employee);
		assertEquals("Invalid person", error);
	}

	@Test
	public void testGetExistingCustomer() {
		assertEquals(ID_KEY, service.getEmployee(ID_KEY).getId());
	}
	
	@Test
	public void testGetExistingCustomerInvalidID() {
		String error = null;
		Employee employee = null;
		try {
			employee = service.getEmployee(INVALID_ID_KEY);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertNull(employee);
		assertEquals("Invalid id", error);
	}

	@Test
	public void testGetNonExistingCustomer() {
		assertNull(service.getEmployee(FAKE_ID_KEY));
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
	public void testDeleteEmployeeInvalidID() {
		assertEquals(0, service.getAllEmployees().size());
		String error = null;
		Employee employee = null;
		try {
			employee = service.deleteEmployee(INVALID_ID_KEY);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertNull(employee);
		assertEquals("Invalid id", error);
	}
	
	@Test
	public void testDeleteEmployeeFakeID() {
		assertEquals(0, service.getAllEmployees().size());
		Employee employee = null;
		try {
			employee = service.deleteEmployee(FAKE_ID_KEY);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}
		assertNull(employee);
	}


}
