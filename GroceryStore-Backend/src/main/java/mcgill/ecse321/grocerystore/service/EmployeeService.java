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
import mcgill.ecse321.grocerystore.model.Person;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	/**
	 * Method to create a employee role
	 * 
	 * @return Employee object
	 */
	@Transactional
	public Employee createEmployee(Person person) {
		Employee employee = new Employee();
		employee.setPerson(person);
		employeeRepository.save(employee);
		return employee;
	}
	
	/**
	 * Method to update an employee role
	 * 
	 * @return Employee object
	 */
	@Transactional
	public Employee updateEmployee(int id, Person person) {
		Employee employee = employeeRepository.findEmployeeById(id);
		employee.setPerson(person);
		employeeRepository.save(employee);
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
	 * Method to delete a employee by their role id
	 * @param role id
	 * @return employee with that id
	 */
	@Transactional
	public Employee deleteEmployee(int id) {
		Employee employee = employeeRepository.findEmployeeById(id);
		employeeRepository.delete(employee);
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
