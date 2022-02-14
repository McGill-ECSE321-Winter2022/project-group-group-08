package mcgill.ecse321.grocerystore.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import mcgill.ecse321.model.Account;
import mcgill.ecse321.model.Cart;
import mcgill.ecse321.model.Item;

public interface CartRepository extends CrudRepository<Cart, Integer>{

	Cart findCartById(int id);
	
	Cart findByAccount(Account account);
	
	List<Cart> findByItem(Item item);
}
