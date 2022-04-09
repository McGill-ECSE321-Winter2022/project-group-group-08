package mcgill.ecse321.grocerystore.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import mcgill.ecse321.grocerystore.model.Account;
import mcgill.ecse321.grocerystore.model.Cart;

public interface CartRepository extends CrudRepository<Cart, Integer>{

	Cart findCartById(int id);
	
	Cart findCartByAccount(Account account);

	List<Cart> findCartByDate(Date date);

	List<Cart> findCartByDateAfter(Date date);
	
	List<Cart> findCartByDateBefore(Date date);
	
	List<Cart> findCartByDateAfterAndDateBefore(Date minDate, Date maxDate);
}