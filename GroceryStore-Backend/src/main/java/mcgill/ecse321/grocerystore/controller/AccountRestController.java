package mcgill.ecse321.grocerystore.controller;
import java.util.ArrayList;
import java.util.List;
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
import mcgill.ecse321.grocerystore.model.Person;
import mcgill.ecse321.grocerystore.dto.AccountDto;
import mcgill.ecse321.grocerystore.dto.PersonDto;
import mcgill.ecse321.grocerystore.service.AccountService;
import mcgill.ecse321.grocerystore.service.PersonService;

@CrossOrigin(origins = "*")
@RestController
public class AccountRestController {

	@Autowired
	private AccountService accountService;
	@Autowired 
	private PersonService personService;
	
	@PostMapping(value = { "/createAccount/{username}", "/createAccount/{username}/" })
	public AccountDto createAccount(@PathVariable("username") String username, @RequestParam String password,
			@RequestParam boolean inTown, @RequestParam int totalPoints, 
			@RequestParam String personEmail) throws IllegalArgumentException {
		Person p = personService.findPersonByEmail(personEmail);
		
		Account a = accountService.createAccount(username, password, inTown, totalPoints, p);
		return convertToDto(a);
	}
	
	@PutMapping(value = { "/updateAccount/{username}", "/updateAccount/{currentUsername}/" })
	public AccountDto updateAccount(@PathVariable("username") String username,
			@RequestParam String password, @RequestParam boolean inTown,
			@RequestParam int totalPoints, @RequestParam String personEmail) throws IllegalArgumentException {
		Person p = personService.findPersonByEmail(personEmail);

		Account a = accountService.updateAccount(username, password, inTown, totalPoints, p);
		return convertToDto(a);
	}
	
	@GetMapping(value = { "/getAccountByUsername/{username}", "/getAccountByUsername/{username}/" })
	public AccountDto getAccountByUsername(@PathVariable("username") String username) {
		return convertToDto(accountService.findAccountByUsername(username));
	}
	
	@GetMapping(value = { "/getAllAccounts", "/getAllAccounts/" })
	public List<AccountDto> getAllAccounts() {
		List<AccountDto> accountDtos = new ArrayList<>();
		for (Account account: accountService.getAllAccounts()) {
			accountDtos.add(convertToDto(account));
		}
		return accountDtos;
	}
	
	@GetMapping(value = { "/getAccountByUsernameContaining/{word}", "/getAccountByUsernameContaining/{word}/" })
	public List<AccountDto> getAccountByUsernameContainingIgnoreCase (@PathVariable("word") String word) {
		List<Account> accounts = accountService.findAccountByUsernameContainingIgnoreCase(word);
		List<AccountDto> accountDtos = new ArrayList<AccountDto>();
		for(Account account: accounts) {
			accountDtos.add(convertToDto(account));
		}
		return accountDtos;
	}
	
	@GetMapping(value = { "/getAccountsByTotalPointsBetween/{minPoints}/{maxPoints}",
			"/getAccountsByTotalPointsBetween/{minPoints}/{maxPoints}/" })
	public List<AccountDto> getAccountsByTotalPointsBetween(@PathVariable("minPoints") int minPoints,
			@PathVariable("maxPoints") int maxPoints) {
		List<AccountDto> accountDtos = new ArrayList<>();
		for (Account account: accountService.getAllAccounts()) {
			if(account.getTotalPoints() < maxPoints && account.getTotalPoints() > minPoints) {
				accountDtos.add(convertToDto(account));
			}
		}
		return accountDtos;
	}
	
	@GetMapping(value = { "/getAccountsInTown/{inTown}", "/getAccountsInTown/{inTown}/" })
	public List<AccountDto> getAccountsInTown(@PathVariable("inTown") boolean inTown) {
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
	
	@DeleteMapping(value = { "/deleteAllAccounts", "/deleteAllAccounts/" })
	public List<AccountDto> deleteAllPersons() throws IllegalArgumentException {
		List <Account> accountList = accountService.getAllAccounts();
		List <AccountDto> accountDtos = new ArrayList<AccountDto>();
		
		for(Account account: accountList) {
			accountService.deleteAccount(account);
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
				p.getAddress());
		return personDto;
	}
	
	private AccountDto convertToDto(Account a) {
		if (a == null) {
			throw new IllegalArgumentException("There is no such Account!");
		}
		AccountDto accountDto = new AccountDto(a.getUsername(),a.getPassword(),a.getInTown(),a.getTotalPoints(),
				convertToDto(a.getPerson()));
		return accountDto;
	}
}