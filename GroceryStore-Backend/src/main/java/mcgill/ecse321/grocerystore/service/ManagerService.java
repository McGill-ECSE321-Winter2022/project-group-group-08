package mcgill.ecse321.grocerystore.service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mcgill.ecse321.grocerystore.dao.UserRoleRepository;
import mcgill.ecse321.grocerystore.model.Manager;
import mcgill.ecse321.grocerystore.model.UserRole;

@Service
public class ManagerService {
	
	@Autowired
	UserRoleRepository userRoleRepository;
	
	@Transactional 
	public UserRole createManager() {
		Manager manager = new Manager();
		return manager;
	}
	
	public UserRole getManager(int id) {
		UserRole managerID = userRoleRepository.findUserRoleById(id);
		return managerID;
	}
	 	

}