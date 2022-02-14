package mcgill.ecse321.grocerystore.dao;

import java.util.List;

import javax.persistence.criteria.Order;

import org.springframework.data.repository.CrudRepository;

import mcgill.ecse321.model.Cart;

public interface OrderRepository extends CrudRepository<Order, Integer>{

	Order findOrderByOrderNum(int order);
	
	List<Order> findByCart(Cart cart);
}