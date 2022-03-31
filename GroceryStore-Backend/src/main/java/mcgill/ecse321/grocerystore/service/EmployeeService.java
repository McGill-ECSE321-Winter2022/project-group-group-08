package mcgill.ecse321.grocerystore.service;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import mcgill.ecse321.grocerystore.dao.BusinessHourRepository;
import mcgill.ecse321.grocerystore.dao.EmployeeRepository;
import mcgill.ecse321.grocerystore.dao.PersonRepository;
import mcgill.ecse321.grocerystore.dao.UserRoleRepository;
import mcgill.ecse321.grocerystore.model.BusinessHour;
import mcgill.ecse321.grocerystore.model.Employee;
import mcgill.ecse321.grocerystore.model.Person;
import mcgill.ecse321.grocerystore.model.BusinessHour.WeekDay;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	BusinessHourRepository businessHourRepository;
	@Autowired
	PersonRepository personRepository;
	@Autowired
	UserRoleRepository userRoleRepository;
	@Autowired
	BusinessHourService businessHourService;

	/**
	 * Method to create a employee role
	 * 
	 * @param person
	 * @return Employee object
	 */
	@Transactional
	public Employee createEmployee(Person person) {
		List<BusinessHour.WeekDay> weekdays = Arrays.asList(BusinessHour.WeekDay.Sunday, BusinessHour.WeekDay.Monday, BusinessHour.WeekDay.Tuesday, BusinessHour.WeekDay.Wednesday, BusinessHour.WeekDay.Thursday, BusinessHour.WeekDay.Friday, BusinessHour.WeekDay.Saturday);
		if (person == null || !personRepository.existsById(person.getEmail())) {
			throw new IllegalArgumentException("Invalid person");
		}
		if (userRoleRepository.findUserRoleByPerson(person) != null) {
			throw new IllegalArgumentException("Person has already been assigned a role");
		}
		Employee employee = new Employee();
		employee.setPerson(person);
		employeeRepository.save(employee);
		for (int i = 0; i < weekdays.size(); i++) {
			businessHourService.createBusinessHourforEmployee(weekdays.get(i), Time.valueOf("00:01:00"), Time.valueOf("23:59:00"), false, employee);
		}
		return employee;
	}

	/**
	 * Method to update an employee role
	 * 
	 * @param role   id
	 * @param person
	 * @return Employee object
	 */
	@Transactional
	public Employee updateEmployee(int id, Person person) {
		if (id <= 0) {
			throw new IllegalArgumentException("Invalid id");
		}
		if (person == null || !personRepository.existsById(person.getEmail())) {
			throw new IllegalArgumentException("Invalid person");
		}
		Employee employee = employeeRepository.findEmployeeById(id);
		employee.setPerson(person);
		employeeRepository.save(employee);
		return employee;
	}

	/**
	 * Method to get a employee by their role id
	 * 
	 * @param role id
	 * @return employee with that id
	 */
	@Transactional
	public Employee getEmployee(int id) {
		if (id <= 0) {
			throw new IllegalArgumentException("Invalid id");
		}
		Employee employee = employeeRepository.findEmployeeById(id);
		if (employee == null) {
			throw new IllegalArgumentException("No employee found");
		}
		return employee;
	}
	
	/**
	 * Method to get a employee by their role id
	 * 
	 * @param role id
	 * @return employee with that id
	 */
	@Transactional
	public Employee getEmployeeByPerson(String email) {
		if (email.strip().length() == 0) {
			throw new IllegalArgumentException("Email cannot be empty");
		}
		Person person = personRepository.findPersonByEmail(email);
		if (person == null) {
			throw new IllegalArgumentException("No person found");
		}
		Employee employee = employeeRepository.findEmployeeByPerson(person);
		if (employee == null) {
			throw new IllegalArgumentException("No employee found");
		}
		return employee;
	}

	/**
	 * Method to delete a employee by their role id
	 * 
	 * @param role id
	 * @return employee with that id
	 */
	@Transactional
	public Employee deleteEmployee(int id) {
		if (id <= 0) {
			throw new IllegalArgumentException("Invalid id");
		}
		Employee employee = employeeRepository.findEmployeeById(id);
		if (employee == null) {
			throw new IllegalArgumentException("Employee with id " + id + " does not exists.");
		}
		List<BusinessHour> businessHours = businessHourRepository.findBusinessHoursByEmployee(employee);
		for (int i = 0; i < businessHours.size(); i++) {
			businessHourService.deleteBusinessHourbyID(businessHours.get(i).getId());
		}
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
