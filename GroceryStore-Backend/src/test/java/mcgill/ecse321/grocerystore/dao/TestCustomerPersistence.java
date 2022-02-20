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
import mcgill.ecse321.grocerystore.model.Person;
import mcgill.ecse321.grocerystore.model.BusinessHour.WeekDay;
import mcgill.ecse321.grocerystore.model.Customer.TierClass;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestCustomerPersistence {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private UserRoleRepository userRoleRepository;

	@AfterEach
	public void clearDatabase() {
		// First, we clear registrations to avoid exceptions due to inconsistencies
		customerRepository.deleteAll();
		personRepository.deleteAll();
		userRoleRepository.deleteAll();
	}
	
	public Customer createCustomer(TierClass tierClass, boolean ban) {
		Customer customer = new Customer();
		customer.setTierclass(tierClass);
		customer.setBan(ban);
		customerRepository.save(customer);
		return customer;
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
	public void testPersistAndLoadCustomer() {
		TierClass tierClass = TierClass.Bronze;
		boolean ban = false;
		Customer customer = createCustomer(tierClass, ban);
		int id= customer.getId();
		
		customer = null;
		customer = customerRepository.findCustomerById(id);
		
		assertNotNull(customer);
		
		assertEquals(id,customer.getId());
		assertEquals(tierClass, customer.getTierclass());
		assertEquals(ban, customer.getBan());
	}
	
	@Test
	public void testPersistAndLoadCustomerByPerson() {
		//create instance of customer
		TierClass tierClass = TierClass.Bronze;
		boolean ban = false;
		Customer customer = createCustomer(tierClass, ban);
		int id= customer.getId();
		
		//create instance of person
		String email = "abc@gmail.com";
		int phoneNumber = 1112223333;
		String address = "845 Sherbrooke St W, Montreal, Quebec H3A 0G4";
		String firstName = "Bob";
		String lastName = "Smith";
		
		Person person = createPerson(email, firstName, lastName, phoneNumber, address);
		
		//reference objects
		person.setUserRole(customer);
		customer.setPerson(person);
		
		personRepository.save(person);
		customerRepository.save(customer);
		
		person = null;
		customer = null;
		
		//get instance of person
		person = personRepository.findPersonByEmail(email);
		
		assertNotNull(person);
		
		//get customer from person
		customer = (Customer) person.getUserRole();
		
		assertNotNull(customer);
		assertEquals(id,customer.getId());
		assertEquals(tierClass, customer.getTierclass());
		assertEquals(ban, customer.getBan());
	} 
}
