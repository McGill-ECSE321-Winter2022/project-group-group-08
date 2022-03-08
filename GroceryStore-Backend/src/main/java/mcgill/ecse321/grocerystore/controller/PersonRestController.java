package mcgill.ecse321.grocerystore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mcgill.ecse321.grocerystore.dto.AccountDto;
import mcgill.ecse321.grocerystore.dto.CartDto;
import mcgill.ecse321.grocerystore.dto.PersonDto;
import mcgill.ecse321.grocerystore.dto.UserRoleDto;
import mcgill.ecse321.grocerystore.model.Account;
import mcgill.ecse321.grocerystore.model.Cart;
import mcgill.ecse321.grocerystore.model.Customer;
import mcgill.ecse321.grocerystore.model.Customer.TierClass;
import mcgill.ecse321.grocerystore.model.Employee;
import mcgill.ecse321.grocerystore.model.Manager;
import mcgill.ecse321.grocerystore.model.Person;
import mcgill.ecse321.grocerystore.model.UserRole;
import mcgill.ecse321.grocerystore.service.PersonService;

@CrossOrigin(origins = "*")
@RestController
public class PersonRestController {

	@Autowired
	private PersonService service;
	
	//ACCOUNT DTO
	
	@GetMapping(value = { "/account/{username}", "/person/{username}/" })
	public AccountDto getAccount(@PathVariable("username") String username) throws IllegalArgumentException {
		return convertToDto(service.getAccount(username));
	}
	
	@PostMapping(value = { "/account/{username}", "/account/{username}/" })
	public AccountDto createAccount(@PathVariable("username") String username, @RequestParam String password, 
			@RequestParam boolean inTown,@RequestParam int totalPoints) throws IllegalArgumentException {
		Account account = service.createAccount(username, password, inTown, totalPoints);
		return convertToDto(account);
	}
	
	//USERROLE DTO
	
	@GetMapping(value = { "/userrole/{id}", "/userrole/{id}/" })
	public UserRoleDto getUserRole(@PathVariable("id") int id) throws IllegalArgumentException {
		return convertToDto(service.getUserRole(id));
	}
	
	@PostMapping(value = { "/manager/{id}", "/manager/{id}/" })
	public UserRoleDto createManager(@PathVariable("id") int id) throws IllegalArgumentException {
		Manager manager = (Manager) service.createManager();
		return convertToDto(manager);
		
	}
	
	@PostMapping(value = { "/employee/{id}", "/employee/{id}/" })
	public UserRoleDto createEmployee(@PathVariable("id") int id) throws IllegalArgumentException {
		Employee employee = (Employee) service.createEmployee();
		return convertToDto(employee);
	}
	
	@PostMapping(value = { "/customer/{id}", "/customer/{id}/" })
	public UserRoleDto createCustomer(@PathVariable("id") int id, @RequestParam TierClass tierClass, 
			@RequestParam boolean ban) throws IllegalArgumentException {
		Customer customer = (Customer) service.createCustomer(tierClass, ban);
		return convertToDto(customer);
	}
	
	//PERSON DTO
	
	@PostMapping(value = { "/person/{email}", "/person/{email}/" })
	public PersonDto createPerson(@PathVariable("email") String email, @RequestParam String firstName, @RequestParam String lastName,
			@RequestParam String phoneNumber, @RequestParam String address, @RequestParam UserRoleDto uDto,
			@RequestParam AccountDto aDto) throws IllegalArgumentException {
		UserRole u = service.getUserRole(uDto.getId());
		Account a = service.getAccount(aDto.getUsername());
		
		Person person = service.createPerson(email, firstName, lastName, phoneNumber, address, u, a);
		return convertToDto(person);
	}
	
	//UTILITIES
	
	private UserRoleDto convertToDto(UserRole r) {
		if (r == null) {
			throw new IllegalArgumentException("There is no such UserRole!");
		}
		UserRoleDto userRoleDto = new UserRoleDto();
		return userRoleDto;
	}
	
	private AccountDto convertToDto(Account a) {
		if (a == null) {
			throw new IllegalArgumentException("There is no such Account!");
		}
		AccountDto accountDto = new AccountDto(a.getUsername(),a.getPassword(),a.getInTown(),a.getTotalPoints(),
				convertToDto(a.getPerson()),convertToDto(a.getCart()));
		return accountDto;
	}
	
	private PersonDto convertToDto(Person p) {
		if (p == null) {
			throw new IllegalArgumentException("There is no such Person!");
		}
		PersonDto personDto = new PersonDto(p.getEmail(),p.getFirstName(),p.getLastName(),p.getPhoneNumber(),
				p.getAddress(),convertToDto(p.getUserRole()),convertToDto(p.getAccount()));
		return personDto;
	}
	
	private CartDto convertToDto(Cart c) {
		if (c == null) {
			throw new IllegalArgumentException("There is no such Cart!");
		}
		CartDto cartDto = new CartDto();
		return cartDto;
	}
}