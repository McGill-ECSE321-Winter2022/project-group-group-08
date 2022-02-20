package mcgill.ecse321.grocerystore.dao;

import org.springframework.data.repository.CrudRepository;

import mcgill.ecse321.grocerystore.model.Manager;

public interface ManagerRepository extends CrudRepository<Manager, Integer>{
	Manager findManagerById(int id);
}