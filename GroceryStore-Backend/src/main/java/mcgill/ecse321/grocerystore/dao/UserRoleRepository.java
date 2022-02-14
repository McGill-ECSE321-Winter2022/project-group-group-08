package mcgill.ecse321.grocerystore.dao;

import org.springframework.data.repository.CrudRepository;

import mcgill.ecse321.model.UserRole;

public interface UserRoleRepository extends CrudRepository<UserRole, Integer>{

	UserRole findAccountByUsername(String username);
	
	void deleteByAccountUsername(String username);
}