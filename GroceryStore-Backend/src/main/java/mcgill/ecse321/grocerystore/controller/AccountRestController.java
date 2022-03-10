package mcgill.ecse321.grocerystore.controller;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
import mcgill.ecse321.grocerystore.service.PersonService;

@CrossOrigin(origins = "*")
@RestController
public class AccountRestController {

	@Autowired
	private AccountService accountService;
	@Autowired 
	private PersonService personService;
	@Autowired
	private CartService cartService;
	
//	public void deleteAccount(Account account) {

//	public void deleteAccountByUsername(String username) {
	
	@PostMapping(value = { "/account/create/{username}", "/account/create/{username}/" })
	public AccountDto createAccount(@PathVariable("username") String username, @RequestParam String password,
			@RequestParam boolean inTown, @RequestParam int totalPoints, @RequestParam(name = "person") PersonDto pDto,
			@RequestParam(name = "cart") Cart cartDto) throws IllegalArgumentException {
		Person p = personService.getPerson(pDto.getEmail());
		Cart c = cartService.getCart(cartDto.getId());

		Account a = accountService.createAccount(username, password, inTown, totalPoints, p, c);
		return convertToDto(a);
	}
	
	@PutMapping(value = { "/account/update/{currentUsername}", "/account/update/{currentUsername}/" })
	public AccountDto updateAccount(@PathVariable("currentUsername") String currentUsername,
			@RequestParam String newUsername, @RequestParam String password, @RequestParam boolean inTown,
			@RequestParam int totalPoints, @RequestParam(name = "person") PersonDto pDto,
			@RequestParam(name = "cart") Cart cartDto) throws IllegalArgumentException {
		Person p = personService.getPerson(pDto.getEmail());
		Cart c = cartService.getCart(cartDto.getId());

		Account a = accountService.updateAccount(currentUsername, newUsername, password, inTown, totalPoints, p, c);
		return convertToDto(a);
	}
	
	@GetMapping(value = { "/getAllAccounts/{word}", "/getAllAccounts/{word}/" })
	public List<AccountDto> getAccountByUsernameContaining(@PathVariable("word") String word) {
		List<AccountDto> accountDtos = new ArrayList<>();
		for (Account account: accountService.getAllAccounts()) {
			if(account.getUsername().contains(word)) {
				accountDtos.add(convertToDto(account));
			}
		}
		return accountDtos;
	}
	
	@GetMapping(value = { "/getAllAccounts/{minPoints}/{maxPoints}", "/getAllAccounts/{minPoints}/{maxPoints}/" })
	public List<AccountDto> getAccountByTotalPointsBetween(@PathVariable("minPoints") int minPoints,
			@PathVariable("maxPoints") int maxPoints) {
		List<AccountDto> accountDtos = new ArrayList<>();
		for (Account account: accountService.getAllAccounts()) {
			if(account.getTotalPoints() < maxPoints && account.getTotalPoints() > minPoints) {
				accountDtos.add(convertToDto(account));
			}
		}
		return accountDtos;
	}
	
	@GetMapping(value = { "/getAllAccounts/{inTown}", "/getAllAccounts/{inTown}/" })
	public List<AccountDto> getAccountInTown(@PathVariable("inTown") boolean inTown) {
		List<AccountDto> accountDtos = new ArrayList<>();
		for (Account account: accountService.getAllAccounts()) {
			if(account.getInTown() == inTown) {
				accountDtos.add(convertToDto(account));
			}
		}
		return accountDtos;
	}
	
	@DeleteMapping(value = { "/deleteAccount/{username}", "/deleteAccount/{username}/" }) 
	public AccountDto deleteAccountByUsername(@PathVariable("username") String username) {
		return convertToDto(accountService.deleteAccountByUsername(username));
	}

	@GetMapping(value = { "/account/{username}", "/account/{username}/" })
	public AccountDto getAccount(@PathVariable("username") String username) throws IllegalArgumentException {
		return convertToDto(accountService.getAccount(username));
	}
	
	@GetMapping(value = { "/getAllAccounts", "/getAllAccounts/" })
	public List<AccountDto> getAllLibrarians() {
		List<AccountDto> accountDtos = new ArrayList<>();
		for (Account account: accountService.getAllAccounts()) {
			accountDtos.add(convertToDto(account));
		}
		return accountDtos;
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