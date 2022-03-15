package mcgill.ecse321.grocerystore.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import mcgill.ecse321.grocerystore.dto.AccountDto;
import mcgill.ecse321.grocerystore.dto.CartDto;
import mcgill.ecse321.grocerystore.dto.CustomerDto;
import mcgill.ecse321.grocerystore.dto.PersonDto;
import mcgill.ecse321.grocerystore.dto.UserRoleDto;
import mcgill.ecse321.grocerystore.model.Account;
import mcgill.ecse321.grocerystore.model.Cart;
import mcgill.ecse321.grocerystore.model.Customer;
import mcgill.ecse321.grocerystore.model.Person;
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

	private PersonDto convertToDto(Person person) {
		if (person == null) {
			throw new IllegalArgumentException("There is no such Person!");
		}
		PersonDto personDto = new PersonDto(person.getEmail(),person.getFirstName(),person.getLastName(),
				person.getPhoneNumber(), person.getAddress());
		return personDto;
	}

	@SuppressWarnings("unused")
	private AccountDto convertToDto(Account a) {
		if (a == null) {
			throw new IllegalArgumentException("There is no such Account!");
		}
		AccountDto accountDto = new AccountDto(a.getUsername(),a.getPassword(),a.getInTown(),a.getTotalPoints(),
				convertToDto(a.getPerson()));
		return accountDto;
	}

	@SuppressWarnings("unused")
	private CartDto convertToDto(Cart c) {
		if (c == null) {
			throw new IllegalArgumentException("There is no such Cart!");
		}
		CartDto cartDto = new CartDto();
		return cartDto;
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
