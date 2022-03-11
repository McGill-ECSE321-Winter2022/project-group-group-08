package mcgill.ecse321.grocerystore.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import mcgill.ecse321.grocerystore.dto.EmployeeDto;
import mcgill.ecse321.grocerystore.model.Employee;
import mcgill.ecse321.grocerystore.service.EmployeeService;

@CrossOrigin(origins = "*")
@RestController
public class EmployeeRestController {

	@Autowired
	private EmployeeService service;
	
	@GetMapping(value = { "/employees", "/employees/" })
	public List<EmployeeDto> getAllEmployees() {
		return service.getAllEmployees().stream().map(p -> convertToDto(p)).collect(Collectors.toList());
	}
	
	@PostMapping(value = { "/employee", "/employee/" })
	public EmployeeDto createCustomer() throws IllegalArgumentException {
		Employee employee = service.createEmployee();
		return convertToDto(employee);
	}

	private EmployeeDto convertToDto(Employee employee) {
		if (employee == null) {
			throw new IllegalArgumentException("There is no such employee!");
		}
		EmployeeDto employeeDto = new EmployeeDto();
		return employeeDto;
	}
}
