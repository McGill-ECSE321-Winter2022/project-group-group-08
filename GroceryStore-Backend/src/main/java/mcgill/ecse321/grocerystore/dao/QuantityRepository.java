package mcgill.ecse321.grocerystore.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import mcgill.ecse321.grocerystore.model.Quantity;

public interface QuantityRepository extends CrudRepository<Quantity, Integer>{

	Quantity findQuantityById(int id);
	
	List<Quantity> findQuantityByCount(int count);

}
