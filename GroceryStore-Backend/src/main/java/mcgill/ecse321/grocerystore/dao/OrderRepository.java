package mcgill.ecse321.grocerystore.dao;

import org.springframework.data.repository.CrudRepository;

import mcgill.ecse321.grocerystore.model.Order;

public interface OrderRepository extends CrudRepository<Receipt, Integer>{
	
	Receipt findOrderByOrderNum(int orderNum);
}
