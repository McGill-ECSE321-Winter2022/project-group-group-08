package mcgill.ecse321.grocerystore.dao;

import org.springframework.data.repository.CrudRepository;

import mcgill.ecse321.grocerystore.model.Employee;
import mcgill.ecse321.grocerystore.model.Person;

public interface EmployeeRepository extends CrudRepository<Employee, Integer>{
	Employee findEmployeeById(int id);
	
	Employee findEmployeeByPerson(Person person);
	
	
}
