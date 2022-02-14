package mcgill.ecse321.grocerystore.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import mcgill.ecse321.model.Account;
import mcgill.ecse321.model.User;
import mcgill.ecse321.model.UserRole;

public interface UserRepository extends CrudRepository<User, String>{
	
	User findUserByEmail(String email);
	
	User findByAccount(Account account);
	
	User findByUserRole(UserRole role);	
	
	User findByFirstNameAndLastName(String firstName, String lastName);
	
	User findByPhoneNumber(int phoneNumber);
	
	List<User> findAll();
}
