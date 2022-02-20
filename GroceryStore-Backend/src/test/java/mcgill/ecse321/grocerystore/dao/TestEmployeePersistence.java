package mcgill.ecse321.grocerystore.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Time;
import java.time.LocalTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import mcgill.ecse321.grocerystore.model.BusinessHour;
import mcgill.ecse321.grocerystore.model.Employee;
import mcgill.ecse321.grocerystore.model.BusinessHour.WeekDay;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestEmployeePersistence {

	@Autowired
	private EmployeeRepository employeeRepository;

	@AfterEach
	public void clearDatabase() {
		// First, we clear registrations to avoid exceptions due to inconsistencies
		employeeRepository.deleteAll();
	}
	

	public Employee createEmployee() {
		Employee employee = new Employee();
		employeeRepository.save(employee);
		return employee;
	}
	
	@Test
	public void testPersistAndLoadEmployee() {
		Employee employee = createEmployee();
		int id= employee.getId();
		
		employee = null;
		employee = employeeRepository.findEmployeeById(id);
		
		assertNotNull(employee);
		
		assertEquals(id,employee.getId());
	}
	
}
