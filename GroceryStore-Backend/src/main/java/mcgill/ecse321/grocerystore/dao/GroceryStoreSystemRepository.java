package mcgill.ecse321.grocerystore.dao;

import org.springframework.data.repository.CrudRepository;

import mcgill.ecse321.grocerystore.model.GroceryStoreSystem;

public interface GroceryStoreSystemRepository extends CrudRepository<GroceryStoreSystem, Integer>{

}