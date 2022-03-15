package mcgill.ecse321.grocerystore.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import mcgill.ecse321.grocerystore.dto.CustomerDto;
import mcgill.ecse321.grocerystore.dto.UserRoleDto;
import mcgill.ecse321.grocerystore.model.Customer;
import mcgill.ecse321.grocerystore.model.UserRole;
import mcgill.ecse321.grocerystore.service.CustomerService;

@CrossOrigin(origins = "*")
@RestController
public class CustomerRestController {
	
	@Autowired
	private CustomerService service;
	
	@GetMapping(value = { "/customers", "/customers/" })
	public List<CustomerDto> getAllCustomers() {
		return service.getAllCustomers().stream().map(p -> convertToDto(p)).collect(Collectors.toList());
	}
	
	@GetMapping(value = { "/customer/{id}", "/customer/{id}/" })
	public CustomerDto getCustomer(@PathVariable("id") int id) throws IllegalArgumentException {
		return convertToDto(service.getCustomer(id));
	}
	
	@PostMapping(value = { "/customer", "/customer/" })
	public CustomerDto createCustomer() throws IllegalArgumentException {
		Customer customer = service.createCustomer();
		return convertToDto(customer);
	}

	private CustomerDto convertToDto(Customer customer) {
		if (customer == null) {
			throw new IllegalArgumentException("There is no such customer!");
		}
		CustomerDto customerDto = new CustomerDto();
		return customerDto;
	}

	@SuppressWarnings("unused")
	private UserRoleDto convertToDto(UserRole role) {
		if (role == null) {
			throw new IllegalArgumentException("There is no such UserRole!");
		}
		UserRoleDto userRoleDto = new UserRoleDto();
		return userRoleDto;
	}
	
}
