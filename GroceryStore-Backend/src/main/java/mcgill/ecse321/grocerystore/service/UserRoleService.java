package mcgill.ecse321.grocerystore.service;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mcgill.ecse321.grocerystore.dao.UserRoleRepository;
import mcgill.ecse321.grocerystore.model.Person;
import mcgill.ecse321.grocerystore.model.UserRole;

@Service
public class UserRoleService {
	
	@Autowired
	private UserRoleRepository userRoleRepository;	
	
	//find an existing UserRole via the id
	//if id is negative OR no UserRole exists with that id, throw errors
	@Transactional
	public UserRole findUserRoleById (int id) {
		if(id<0) {
			throw new IllegalArgumentException("Id can't be negative");	
		}
		UserRole curr = userRoleRepository.findUserRoleById(id);
		if (curr == null) {
			throw new IllegalArgumentException("No user with that id");
		}
		return curr;
	}
	//find an existing UserRole via a Person
	@Transactional
	public UserRole findUserRoleByPerson (Person input) {
		if(input == null) {
			throw new IllegalArgumentException("Person cannot be null");	
		}
		UserRole curr = userRoleRepository.findUserRoleByPerson(input);
		if (curr == null) {
			throw new IllegalArgumentException("No role to that person");
		}
		return curr;
	}
	 
	//returns all the existing UserRoles
	@Transactional
	public List<UserRole> getAllUserRoles() {	
		return toList(userRoleRepository.findAll());
		
	};
	//iterates over iterable data and returns a list
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
	

	
}