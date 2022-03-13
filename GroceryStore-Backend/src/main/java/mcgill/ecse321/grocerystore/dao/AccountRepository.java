package mcgill.ecse321.grocerystore.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import mcgill.ecse321.grocerystore.model.Account;
import mcgill.ecse321.grocerystore.model.Cart;
import mcgill.ecse321.grocerystore.model.Person;

public interface AccountRepository extends CrudRepository<Account, String>{

	Account findAccountByUsername(String username);
	
	List<Account> findAccountByUsernameContainingIgnoreCase(String username);
	
	List<Account> findAccountByInTown(boolean inTown);
	
	List<Account> findAccountByTotalPointsBetween(int minPoint, int maxPoint);
	
	List<Account> findAccountByInTownAndTotalPointsBetween(boolean inTown, int minPoint, int maxPoint);	
}
