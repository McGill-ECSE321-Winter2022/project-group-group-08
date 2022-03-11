package mcgill.ecse321.grocerystore.service;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mcgill.ecse321.grocerystore.dao.BusinessHourRepository;
import mcgill.ecse321.grocerystore.dao.EmployeeRepository;
import mcgill.ecse321.grocerystore.dao.UserRoleRepository;
import mcgill.ecse321.grocerystore.model.Employee;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	UserRoleRepository userRoleRepository;
	@Autowired
	BusinessHourRepository businessHourRepository;

	/**
	 * Method to create a employee role
	 * 
	 * @return Employee object
	 */
	@Transactional
	public Employee createEmployee() {
		Employee employee = new Employee();
		return employee;
	}
	
	/**
	 * Method to get a employee by their role id
	 * @param role id
	 * @return employee with that id
	 */
	@Transactional
	public Employee getEmployee(int id) {
		Employee employee = employeeRepository.findEmployeeById(id);
	    return employee;
	}

	/**
	 * Method to get all employee
	 * 
	 * @return list of all employee
	 */
	@Transactional
	public List<Employee> getAllEmployees() {
		return toList(employeeRepository.findAll());
	}

	private <T> List<T> toList(Iterable<T> iterable) {
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
	
}
