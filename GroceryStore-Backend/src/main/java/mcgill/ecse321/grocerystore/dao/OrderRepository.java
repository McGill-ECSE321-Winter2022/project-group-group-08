package mcgill.ecse321.grocerystore.dao;

import org.springframework.data.repository.CrudRepository;

import mcgill.ecse321.grocerystore.model.Order;

public interface OrderRepository extends CrudRepository<Order, Integer>{
	
	Order findOrderByOrderNum(int orderNum);
}