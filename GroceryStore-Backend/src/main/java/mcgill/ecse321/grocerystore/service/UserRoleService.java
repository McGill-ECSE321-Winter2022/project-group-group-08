package mcgill.ecse321.grocerystore.service;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mcgill.ecse321.grocerystore.dao.UserRoleRepository;
import mcgill.ecse321.grocerystore.model.Customer;
import mcgill.ecse321.grocerystore.model.Employee;
import mcgill.ecse321.grocerystore.model.Manager;
import mcgill.ecse321.grocerystore.model.UserRole;

@Service
public class UserRoleService {
	
	@Autowired
	private UserRoleRepository userRoleRepository;	
	@Autowired
	private ManagerService managerService;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private CustomerService customerService;
	
	@Transactional
	public UserRole findUserRoleById (int id) {
		UserRole curr = userRoleRepository.findUserRoleById(id);
		if (curr == null) {
			throw new IllegalArgumentException("No user with that id");
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
	
	@Transactional
 	public UserRole deleteUserRoleById (int id) {
 		UserRole curr = userRoleRepository.findUserRoleById(id);

 		if (curr == null) { throw new IllegalArgumentException("No user with that id");}

 		if (curr instanceof Manager) {
 			Manager temp = managerService.deleteManagerById(id);
 			return temp;
 		}
 		if (curr instanceof Employee) {
 			Employee temp = employeeService.deleteEmployee(id);
 			return temp;
 		}
 		if (curr instanceof Customer) {
 			Customer temp = customerService.deleteCustomer(id);
 			return temp;
 		}
 		return null;
 	}

	
}