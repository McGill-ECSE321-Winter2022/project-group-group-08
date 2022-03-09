package mcgill.ecse321.grocerystore.service;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mcgill.ecse321.grocerystore.dao.ReceiptRepository;
import mcgill.ecse321.grocerystore.dao.UserRoleRepository;
import mcgill.ecse321.grocerystore.model.UserRole;

@Service
public class UserRoleService {
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	
	@Transactional
	public UserRole findUserRoleById (int id) {
		UserRole curr = userRoleRepository.findUserRoleById(id);
		if (curr == null) {
			throw new IllegalArgumentException("No user with that Id");
		}
		return curr;
	}
	
	
}