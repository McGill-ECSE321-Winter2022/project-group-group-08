package mcgill.ecse321.grocerystore.dao;

import org.springframework.data.repository.CrudRepository;

import mcgill.ecse321.grocerystore.model.Cart;

public interface CartRepository extends CrudRepository<Cart, Integer>{

	Cart findCartById(int id);
}