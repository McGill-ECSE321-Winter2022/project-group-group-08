package mcgill.ecse321.grocerystore.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import mcgill.ecse321.grocerystore.model.Customer;
import mcgill.ecse321.grocerystore.model.Customer.TierClass;
import mcgill.ecse321.grocerystore.model.Person;

public interface CustomerRepository extends CrudRepository<Customer, Integer>{
	Customer findCustomerById(int id);
	
	Customer findCustomerByPerson(Person person);
	
	List<Customer> findCustomerByTierclass(TierClass tierClass);
	
	List<Customer> findCustomerByBan(boolean ban);
}