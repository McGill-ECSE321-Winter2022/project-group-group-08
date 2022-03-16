package mcgill.ecse321.grocerystore.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mcgill.ecse321.grocerystore.dto.EmployeeDto;
import mcgill.ecse321.grocerystore.dto.PersonDto;
import mcgill.ecse321.grocerystore.model.Employee;
import mcgill.ecse321.grocerystore.model.Person;
import mcgill.ecse321.grocerystore.service.EmployeeService;
import mcgill.ecse321.grocerystore.service.PersonService;

@CrossOrigin(origins = "*")
@RestController
public class EmployeeRestController {

	@Autowired
	private EmployeeService service;
	@Autowired
	private PersonService personService;
	
	@GetMapping(value = { "/employees", "/employees/" })
	public List<EmployeeDto> getAllEmployees() {
		return service.getAllEmployees().stream().map(p -> convertToDto(p)).collect(Collectors.toList());
	}
	
	@GetMapping(value = { "/employee/{id}", "/employee/{id}/" })
	public EmployeeDto getEmployee(@PathVariable("id") int id) throws IllegalArgumentException {
		return convertToDto(service.getEmployee(id));
	}
	
	@PostMapping(value = { "/employee", "/employee/" })
	public EmployeeDto createCustomer(@RequestParam(name = "personEmail") String personEmail) {
		Person person = personService.findPersonByEmail(personEmail);
		Employee employee = service.createEmployee(person);
		return convertToDto(employee);
	}
	
	@PatchMapping(value = {"/employee/update/{id}", "/employee/update/{id}/"})
	public EmployeeDto updateEmployee(
			@PathVariable(name = "id") int id,
			@RequestParam(name = "personEmail") String personEmail
			){
		Employee employee = service.updateEmployee(id, personService.findPersonByEmail(personEmail));
		return convertToDto(employee);
	}
	
	@DeleteMapping(value = {"/employee/delete/{id}", "/employee/delete/{id}/"})
	public EmployeeDto deleteEmployee(@PathVariable("id") int id) {
		Employee employee = service.getEmployee(id);
		service.deleteEmployee(id);
		return convertToDto(employee);
	}

	private EmployeeDto convertToDto(Employee employee) {
		if (employee == null) {
			throw new IllegalArgumentException("There is no such employee!");
		}
		EmployeeDto employeeDto = new EmployeeDto(employee.getId(), PersonDto.convertToDto(employee.getPerson()));
		return employeeDto;
	}
}
