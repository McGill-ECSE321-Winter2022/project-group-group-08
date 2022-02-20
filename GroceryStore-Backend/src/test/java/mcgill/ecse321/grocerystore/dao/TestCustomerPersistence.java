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
import mcgill.ecse321.grocerystore.model.BusinessHour.WeekDay;
import mcgill.ecse321.grocerystore.model.Customer.TierClass;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestCustomerPersistence {

	@Autowired
	private CustomerRepository customerRepository;

	@AfterEach
	public void clearDatabase() {
		// First, we clear registrations to avoid exceptions due to inconsistencies
		customerRepository.deleteAll();
	}
	
	public Customer createCustomer(TierClass tierClass, boolean ban) {
		Customer customer = new Customer();
		customer.setTierclass(tierClass);
		customer.setBan(ban);
		customerRepository.save(customer);
		return customer;
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
}
