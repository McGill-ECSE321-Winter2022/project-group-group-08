package mcgill.ecse321.grocerystore.dao;

import org.springframework.data.repository.CrudRepository;

import mcgill.ecse321.grocerystore.model.Person;
import mcgill.ecse321.grocerystore.model.UserRole;

public interface UserRoleRepository extends CrudRepository<UserRole, Integer>{
	
	UserRole findUserRoleById(int id);
	
	UserRole findUserRoleByPerson(Person person);
}
