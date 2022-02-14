package mcgill.ecse321.grocerystore.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import mcgill.ecse321.model.Cart;
import mcgill.ecse321.model.Item;

public interface ItemRepository extends CrudRepository<Item, Integer>{
	
	Item findItemById(int id);
	
	List<Item> findByCart(Cart cart);
}