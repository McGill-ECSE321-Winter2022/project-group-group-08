package mcgill.ecse321.grocerystore.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import mcgill.ecse321.grocerystore.model.Cart;
import mcgill.ecse321.grocerystore.model.Item;
import mcgill.ecse321.grocerystore.model.Quantity;

public interface QuantityRepository extends CrudRepository<Quantity, Integer>{

	Quantity findQuantityById(int id);
		
	List<Quantity> findQuantityByItem(Item item);
	
	List<Quantity> findQuantityByCart(Cart cart);
}
