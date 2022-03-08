package mcgill.ecse321.grocerystore.controller;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mcgill.ecse321.grocerystore.model.Account;
import mcgill.ecse321.grocerystore.model.Cart;
import mcgill.ecse321.grocerystore.model.Person;
import mcgill.ecse321.grocerystore.model.UserRole;
import mcgill.ecse321.grocerystore.dto.AccountDto;
import mcgill.ecse321.grocerystore.dto.CartDto;
import mcgill.ecse321.grocerystore.dto.PersonDto;
import mcgill.ecse321.grocerystore.dto.UserRoleDto;
import mcgill.ecse321.grocerystore.service.AccountService;

@CrossOrigin(origins = "*")
@RestController
public class AccountRestController {

	@Autowired
	private AccountService service;
	
	//PERSONDTO
	@GetMapping(value = { "/person/{email}", "/person/{email}/" })
	public PersonDto getPerson(@PathVariable("email") String email) throws IllegalArgumentException {
		return convertToDto(service.getPerson(email));
	}
	
//	@PostMapping(value = { "/person/{email}", "/person/{email}/" })
//	public PersonDto createPerson(@PathVariable("email") String email, @RequestParam String firstName,
//			@RequestParam String lastName,@RequestParam String phoneNumber,
//			@RequestParam String address) throws IllegalArgumentException {
//		Person person = service.createPerson(email, firstName, lastName, phoneNumber, address);
//		return convertToDto(person);
//	}
	
	//CARTDTO
	@GetMapping(value = { "/cart/{id}", "/cart/{id}/" })
	public CartDto getCart(@PathVariable("id") int id) throws IllegalArgumentException {
		return convertToDto(service.getCart(id));
	}
	
	@PostMapping(value = { "/cart/{id}", "/cart/{id}/" })
	public CartDto createCart(@PathVariable("id") int id, @RequestParam Date date) throws IllegalArgumentException {
		Cart cart = service.createCart(date);
		return convertToDto(cart);
	}
	
	//ACCOUNT
	@PostMapping(value = { "/account", "/account/" })
	public AccountDto createAccount(@RequestParam String username, @RequestParam String password, @RequestParam boolean inTown,
			@RequestParam int totalPoints, @RequestParam(name = "person") PersonDto pDto,
			@RequestParam(name = "cart") Cart cartDto) throws IllegalArgumentException {
		Person p = service.getPerson(pDto.getEmail());
		Cart c = service.getCart(cartDto.getId());

		Account a = service.createAccount(username, password, inTown, totalPoints, p, c);
		return convertToDto(a);
	}
	
	//UTILITIES
	private PersonDto convertToDto(Person p) {
		if (p == null) {
			throw new IllegalArgumentException("There is no such Person!");
		}
		PersonDto personDto = new PersonDto(p.getEmail(),p.getFirstName(),p.getLastName(),p.getPhoneNumber(),
				p.getAddress(),convertToDto(p.getUserRole()),convertToDto(p.getAccount()));
		return personDto;
	}
	
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
	
	private CartDto convertToDto(Cart c) {
		if (c == null) {
			throw new IllegalArgumentException("There is no such Cart!");
		}
		CartDto cartDto = new CartDto();
		return cartDto;
	}
}