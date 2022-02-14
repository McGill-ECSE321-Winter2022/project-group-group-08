package mcgill.ecse321.grocerystore.dao;

import org.springframework.data.repository.CrudRepository;

import mcgill.ecse321.model.Account;
import mcgill.ecse321.model.Cart;
import mcgill.ecse321.model.User;

public interface AccountRepository extends CrudRepository<Account, String>{

	Account findAccountByUsername(String username);
	
	void deleteByAccountUsername(String username);	
	
	Account findByCart(Cart cart);
	
	Account findByUser(User user);
	
	boolean existsByAccountUsername(String username);
}