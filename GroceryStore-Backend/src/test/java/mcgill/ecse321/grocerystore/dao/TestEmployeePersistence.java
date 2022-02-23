package mcgill.ecse321.grocerystore.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Time;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import mcgill.ecse321.grocerystore.model.BusinessHour;
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
	@Autowired
	private BusinessHourRepository businessHourRepository;

	@AfterEach
	public void clearDatabase() {
		// First, we clear the repositories to avoid exceptions due to inconsistencies
		employeeRepository.deleteAll();
		personRepository.deleteAll();
		userRoleRepository.deleteAll();
		businessHourRepository.deleteAll();
	}
	
	//creates an employee
	public Employee createEmployee() {
		Employee employee = new Employee();
		employeeRepository.save(employee);
		return employee;
	}
	
	//creates a person
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
	
	//creates a business hour
	public BusinessHour createBusinessHour(WeekDay day, Time startTime, Time endTime, boolean working) {
		BusinessHour bH = new BusinessHour();
		bH.setDay(day);
		bH.setStartTime(startTime);
		bH.setEndTime(endTime);
		bH.setWorking(working);
		businessHourRepository.save(bH);
		return bH;
	}
	
	@Test
	public void testPersistAndLoadEmployee() {
		Employee employee = createEmployee();
		int id= employee.getId();
		
		employee = null;
		employee = employeeRepository.findEmployeeById(id);
		
		//testing
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
		
		//get employee from person
		employee = (Employee) person.getUserRole();
			
		//testing
		assertNotNull(person);
				
		assertNotNull(employee);
		
		assertEquals(id,employee.getId());
	}
	
	@Test
	public void testPersistAndLoadWorkingHoursByEmployee() {
		Employee employee = createEmployee();
		int id= employee.getId();
		
		//creating an instance of working hour
		WeekDay dayOfWeek = WeekDay.Monday;
		Time startTime = java.sql.Time.valueOf(LocalTime.of(9, 30));
		Time endTime = java.sql.Time.valueOf(LocalTime.of(17, 00));
		boolean working = true;
		
		BusinessHour bH = createBusinessHour(dayOfWeek,startTime,endTime,working);
		
		int idBH = bH.getId();
		
		//setting employee's working hours
		Set<BusinessHour> workingHours = new HashSet<BusinessHour>();
		workingHours.add(bH);
		employee.setWorkingHours(workingHours);
		employeeRepository.save(employee);
		
		bH = null;
		workingHours = null;
		employee = null;
		
		employee = employeeRepository.findEmployeeById(id);
		bH = employee.getWorkingHours().iterator().next();
		
		//testing
		assertNotNull(employee);
		assertEquals(idBH,bH.getId());
		assertEquals(dayOfWeek, bH.getDay());
		assertEquals(startTime, bH.getStartTime());
		assertEquals(endTime, bH.getEndTime());
		assertEquals(working,bH.getWorking());
	}
}
