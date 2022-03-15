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
import mcgill.ecse321.grocerystore.service.AccountService;
import mcgill.ecse321.grocerystore.service.PersonService;

@CrossOrigin(origins = "*")
@RestController
public class AccountRestController {

	@Autowired
	private AccountService accountService;
	@Autowired 
	private PersonService personService;
	
	/**
	 * Creates an accountDto 
	 * @param username account username
	 * @param password account password
	 * @param inTown specify whether in or out of town
	 * @param totalPoints total Points accumulated by account
	 * @param personEmail email of person object which will be link to account
	 * @return AccountDto 
	 */
	@PostMapping(value = { "/createAccount/{username}", "/createAccount/{username}/" })
	public AccountDto createAccount(@PathVariable("username") String username, @RequestParam String password,
			@RequestParam boolean inTown, @RequestParam int totalPoints, 
			@RequestParam String personEmail) {
		Person p = personService.findPersonByEmail(personEmail);
		
		Account a = accountService.createAccount(username, password, inTown, totalPoints, p);
		return AccountDto.convertToDto(a);
	}
	
	/**
	 * Updates an AccountDto
	 * @param username account username
	 * @param password new account password
	 * @param inTown specify whether in or out of town
	 * @param totalPoints new total Points accumulated by account
	 * @param personEmail new email of person object which will be link to account
	 * @return AccountDto
	 */
	@PutMapping(value = { "/updateAccount/{username}", "/updateAccount/{username}/" })
	public AccountDto updateAccount(@PathVariable("username") String username,
			@RequestParam String password, @RequestParam boolean inTown,
			@RequestParam int totalPoints, @RequestParam String personEmail) {
		Person p = personService.findPersonByEmail(personEmail);

		Account a = accountService.updateAccount(username, password, inTown, totalPoints, p);
		return AccountDto.convertToDto(a);
	}
	
	/**
	 * login AccountDto
	 * @param username account username
	 * @param password new account password
	 * @return AccountDto
	 */
	@PutMapping(value = { "/loginAccount/{username}", "/loginAccount/{username}/" })
	public AccountDto loginAccount(@PathVariable("username") String username,
			@RequestParam String password) {
		return AccountDto.convertToDto(accountService.loginAccount(username, password));
	}
	
	/**
	 * Gets an AccountDto with this username
	 * @param username account username
	 * @return AccountDto
	 */
	@GetMapping(value = { "/getAccountByUsername/{username}", "/getAccountByUsername/{username}/" })
	public AccountDto getAccountByUsername(@PathVariable("username") String username) {
		return AccountDto.convertToDto(accountService.findAccountByUsername(username));
	}
	
	/**
	 * Gets the list of existing accounts in the database
	 * @return List<AccountDto>
	 */
	@GetMapping(value = { "/getAllAccounts", "/getAllAccounts/" })
	public List<AccountDto> getAllAccounts() {
		List<AccountDto> accountDtos = new ArrayList<>();
		for (Account account: accountService.getAllAccounts()) {
			accountDtos.add(AccountDto.convertToDto(account));
		}
		return accountDtos;
	}
	
	/**
	 * Gets the list of AccountDto with username containing the word
	 * @param word string found in account username
	 * @return List<AccountDto>
	 */
	@GetMapping(value = { "/getAccountByUsernameContaining/{word}", "/getAccountByUsernameContaining/{word}/" })
	public List<AccountDto> getAccountByUsernameContainingIgnoreCase (@PathVariable("word") String word) {
		List<Account> accounts = accountService.findAccountByUsernameContainingIgnoreCase(word);
		List<AccountDto> accountDtos = new ArrayList<AccountDto>();
		for(Account account: accounts) {
			accountDtos.add(AccountDto.convertToDto(account));
		}
		return accountDtos;
	}
	
	/**
	 * Gets the list of AccountDto with Total Points between minPoint and maxPoint
	 * @param minPoints lower bound of points
	 * @param maxPoints upper bound of points
	 * @return List<AccountDto>
	 */
	@GetMapping(value = { "/getAccountsByTotalPointsBetween/{minPoints}/{maxPoints}",
			"/getAccountsByTotalPointsBetween/{minPoints}/{maxPoints}/" })
	public List<AccountDto> getAccountsByTotalPointsBetween(@PathVariable("minPoints") int minPoints,
			@PathVariable("maxPoints") int maxPoints) {
		List<AccountDto> accountDtos = new ArrayList<>();
		for (Account account: accountService.getAllAccounts()) {
			if(account.getTotalPoints() < maxPoints && account.getTotalPoints() > minPoints) {
				accountDtos.add(AccountDto.convertToDto(account));
			}
		}
		return accountDtos;
	}
	
	/**
	 * Gets the list of AccountDtos with the value of inTown
	 * @param inTown boolean to know whether account user is in town or out
	 * @return List<AccountDto>
	 */
	@GetMapping(value = { "/getAccountsInTown/{inTown}", "/getAccountsInTown/{inTown}/" })
	public List<AccountDto> getAccountsInTown(@PathVariable("inTown") boolean inTown) {
		List<AccountDto> accountDtos = new ArrayList<>();
		for (Account account: accountService.getAllAccounts()) {
			if(account.getInTown() == inTown) {
				accountDtos.add(AccountDto.convertToDto(account));
			}
		}
		return accountDtos;
	}
	
	/**
	 * Delete AccountDto with this username
	 * @param username the account username
	 * @return AccountDto
	 */
	@DeleteMapping(value = { "/deleteAccount/{username}", "/deleteAccount/{username}/" }) 
	public AccountDto deleteAccountByUsername(@PathVariable("username") String username) {
		return AccountDto.convertToDto(accountService.deleteAccountByUsername(username));
	}
	
	/**
	 * Deletes all existing AccountDtos
	 * @return List<AccountDto> 
	 */
	@DeleteMapping(value = { "/deleteAllAccounts", "/deleteAllAccounts/" })
	public List<AccountDto> deleteAllPersons() {
		List <Account> accountList = accountService.getAllAccounts();
		List <AccountDto> accountDtos = new ArrayList<AccountDto>();
		
		for(Account account: accountList) {
			accountService.deleteAccount(account);
			accountDtos.add(AccountDto.convertToDto(account));
		}
		return accountDtos;
	}
}