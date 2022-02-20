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
import mcgill.ecse321.grocerystore.model.Customer;
import mcgill.ecse321.grocerystore.model.Employee;
import mcgill.ecse321.grocerystore.model.Person;
import mcgill.ecse321.grocerystore.model.BusinessHour.WeekDay;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestEmployeePersistence {

	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private UserRoleRepository userRoleRepository;

	@AfterEach
	public void clearDatabase() {
		// First, we clear registrations to avoid exceptions due to inconsistencies
		employeeRepository.deleteAll();
		personRepository.deleteAll();
		userRoleRepository.deleteAll();
	}
	

	public Employee createEmployee() {
		Employee employee = new Employee();
		employeeRepository.save(employee);
		return employee;
	}
	
	public Person createPerson(String email, String firstName, String lastName, int phoneNumber, String address) {
		Person person = new Person();
		person.setEmail(email);
		person.setFirstName(firstName);
		person.setLastName(lastName);
		person.setPhoneNumber(phoneNumber);
		person.setAddress(address);
		personRepository.save(person);
		return person;
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
	
	@Test
	public void testPersistAndLoadEmployeeByPerson() {
		Employee employee = createEmployee();
		int id= employee.getId();
		
		//create instance of person
		String email = "abc@gmail.com";
		int phoneNumber = 1112223333;
		String address = "845 Sherbrooke St W, Montreal, Quebec H3A 0G4";
		String firstName = "Bob";
		String lastName = "Smith";
				
		Person person = createPerson(email, firstName, lastName, phoneNumber, address);
		
		//reference objects
		person.setUserRole(employee);
		employee.setPerson(person);
				
		personRepository.save(person);
		employeeRepository.save(employee);
		
		person = null;
		employee = null;
		
		//get instance of person
		person = personRepository.findPersonByEmail(email);
			
		assertNotNull(person);
			
		//get employee from person
		employee = (Employee) person.getUserRole();
				
		assertNotNull(employee);
		
		assertEquals(id,employee.getId());
	}
	
}
