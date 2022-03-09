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
import mcgill.ecse321.grocerystore.service.AccountService;
import mcgill.ecse321.grocerystore.service.PersonService;

@CrossOrigin(origins = "*")
@RestController
public class PersonRestController {

	@Autowired
	private PersonService personService;
	@Autowired
	private AccountService accountService;
	
	//ACCOUNT DTO	
//	@PostMapping(value = { "/account/{username}", "/account/{username}/" })
//	public AccountDto createAccount(@PathVariable("username") String username, @RequestParam String password, 
//			@RequestParam boolean inTown,@RequestParam int totalPoints) throws IllegalArgumentException {
//		Account account = service.createAccount(username, password, inTown, totalPoints);
//		return convertToDto(account);
//	}
//	
//	//USERROLE DTO
//	
//	@GetMapping(value = { "/userrole/{id}", "/userrole/{id}/" })
//	public UserRoleDto getUserRole(@PathVariable("id") int id) throws IllegalArgumentException {
//		return convertToDto(service.getUserRole(id));
//	}
//	
//	@PostMapping(value = { "/manager/{id}", "/manager/{id}/" })
//	public UserRoleDto createManager(@PathVariable("id") int id) throws IllegalArgumentException {
//		Manager manager = (Manager) service.createManager();
//		return convertToDto(manager);
//		
//	}
//	
//	@PostMapping(value = { "/employee/{id}", "/employee/{id}/" })
//	public UserRoleDto createEmployee(@PathVariable("id") int id) throws IllegalArgumentException {
//		Employee employee = (Employee) service.createEmployee();
//		return convertToDto(employee);
//	}
//	
//	@PostMapping(value = { "/customer/{id}", "/customer/{id}/" })
//	public UserRoleDto createCustomer(@PathVariable("id") int id, @RequestParam TierClass tierClass, 
//			@RequestParam boolean ban) throws IllegalArgumentException {
//		Customer customer = (Customer) service.createCustomer(tierClass, ban);
//		return convertToDto(customer);
//	}
}