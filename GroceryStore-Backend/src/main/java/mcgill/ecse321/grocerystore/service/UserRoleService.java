package mcgill.ecse321.grocerystore.service;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mcgill.ecse321.grocerystore.dao.CustomerRepository;
import mcgill.ecse321.grocerystore.dao.EmployeeRepository;
import mcgill.ecse321.grocerystore.dao.ManagerRepository;
import mcgill.ecse321.grocerystore.dao.ReceiptRepository;
import mcgill.ecse321.grocerystore.dao.UserRoleRepository;
import mcgill.ecse321.grocerystore.model.Customer;
import mcgill.ecse321.grocerystore.model.Customer.TierClass;
import mcgill.ecse321.grocerystore.model.Employee;
import mcgill.ecse321.grocerystore.model.Manager;
import mcgill.ecse321.grocerystore.model.Receipt;
import mcgill.ecse321.grocerystore.model.UserRole;

@Service
public class UserRoleService {
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	ManagerRepository managerRepository;
	
	// User Role Part
	
	@Transactional
	public UserRole findUserRoleById (int id) {
		UserRole curr = userRoleRepository.findUserRoleById(id);
		if (curr == null) {
			throw new IllegalArgumentException("No user with that id");
		}
		return curr;
	}
	
	@Transactional
	public Boolean deleteUserRoleById (int id) {
		UserRole curr = userRoleRepository.findUserRoleById(id);
		
		if (curr == null) { return false; }
		
		if (curr instanceof Manager) {
			managerRepository.deleteById(id);
		}
		if (curr instanceof Employee) {
			employeeRepository.deleteById(id);
		}
		if (curr instanceof Customer) {
			customerRepository.deleteById(id);
		}

		return true;
	}
	
	@Transactional
	public List<UserRole>  getAllUserRoles() {
		
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