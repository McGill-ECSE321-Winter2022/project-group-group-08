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
	@Transactional
	public UserRole findUserRoleById (int id) {
		UserRole curr = userRoleRepository.findUserRoleById(id);
		if (curr == null) {
			throw new IllegalArgumentException("No user with that id");
		}
		return curr;
	}
	
	@Transactional
	public UserRole findUserRoleByPerson (Person input) {
		UserRole curr = userRoleRepository.findUserRoleByPerson(input);
		if (curr == null) {
			throw new IllegalArgumentException("No role to that person");
		}
		return curr;
	}
	 
	@Transactional
	public List<UserRole> getAllUserRoles() {
		
		return toList(userRoleRepository.findAll());
		
	};
	
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
}