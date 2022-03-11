package mcgill.ecse321.grocerystore.service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mcgill.ecse321.grocerystore.dao.ManagerRepository;
import mcgill.ecse321.grocerystore.dao.UserRoleRepository;
import mcgill.ecse321.grocerystore.model.Manager;
import mcgill.ecse321.grocerystore.model.UserRole;

@Service
public class ManagerService {
	
	@Autowired
	UserRoleRepository userRoleRepository;
	@Autowired
	ManagerRepository managerRepository;
	
	@Transactional 
	public Manager createManager(int id) {
		if(id < 0) {
			throw new IllegalArgumentException("The manager's id cannot be negative");
		}
		Manager manager = new Manager();
		return manager;
	}
	@Transactional
	public List<Manager> getAllManagers() {
		return toList(managerRepository.findAll());
	}
	
	@Transactional
	public Manager getManager(int id) {
		Manager managerID = managerRepository.findManagerById(id);
		return managerID;
	}
	
	@Transactional
	public boolean deleteManager(Manager manager) {
		if (manager == null) {
			return false;
		}else {
			managerRepository.delete(manager);
			return true;
		}
	}
	
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
	
	 	

}