package mcgill.ecse321.grocerystore.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;

import mcgill.ecse321.grocerystore.dao.EmployeeRepository;
import mcgill.ecse321.grocerystore.model.Employee;
public class TestEmployeeService {

	@Mock
	private EmployeeRepository employeeDao;

	@InjectMocks
	private EmployeeService service;
	
	private static final int ID_KEY = 1234567;
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
}
