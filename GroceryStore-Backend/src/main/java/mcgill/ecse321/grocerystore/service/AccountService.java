package mcgill.ecse321.grocerystore.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mcgill.ecse321.grocerystore.dao.AccountRepository;
import mcgill.ecse321.grocerystore.dao.CartRepository;
import mcgill.ecse321.grocerystore.dao.PersonRepository;
import mcgill.ecse321.grocerystore.model.Account;
import mcgill.ecse321.grocerystore.model.Cart;
import mcgill.ecse321.grocerystore.model.Person;

@Service
public class AccountService {
	
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	PersonRepository personRepository;
	@Autowired 
	CartRepository cartRepository;
	@Autowired
	CartService cartService;

	/**
	 * Get person by account
	 * @param account the account which is associated to the person
	 * @return Person 
	 */
	@Transactional 
	public Person getPersonByAccount(Account account) {
		if(account == null) {
			throw new InvalidInputException("Account does not exists");
		}else {
			return accountRepository.findAccountByUsername(account.getUsername()).getPerson();
		}
	}

	/**
	 * creating an account
	 * @param username the desired ussername
	 * @param password the password for the account
	 * @param inTown whether they are in town or not. This is for delivery fees
	 * @param totalPoints the total amount of points they have
	 * @param person the person who is creating this account
	 * @return Account
	 */
	@Transactional
	public Account createAccount(String username, String password, boolean inTown,
			int totalPoints, Person person) {
		String error = "";
		if(accountRepository.findAccountByUsername("BigBoss") != null){
			return accountRepository.findAccountByUsername("BigBoss");
		}
		if(accountRepository.existsById(username)){
			error = error + "Username already taken";
		}
		if (username == null || username.trim().length() == 0) {
		    error = error + "Account username cannot be empty! ";
		}
		if (password == null || password.trim().length() == 0) {
		    error = error + "Account password cannot be empty! ";
		}
		if (totalPoints < 0) {
		    error = error + "Account Total points cannot be negative! ";
		}
	    if (person == null) {
	        error = error + "Person needs to be selected for account! ";
	    } else if (!personRepository.existsById(person.getEmail())) {
	        error = error + "Person does not exist! ";
	    }
	    error = error.trim();
	    if (error.length() > 0) {
	        throw new InvalidInputException(error);
	    }
		Account account = new Account();
		account.setUsername(username);
		account.setPassword(password);
		account.setInTown(inTown);
		account.setTotalPoints(totalPoints);
		account.setPerson(person);
		accountRepository.save(account);
		return account;
	}
	
	/**
	 * updating an existing account. Could update one or all attributes
	 * @param username the username that is associated with it
	 * @param password the new desired password
	 * @param inTown the new boolean for inTown
	 * @param totalPoints the new total points
	 * @param person the new person who is associated with this account
	 * @return Account
	 */
	@Transactional
	public Account updateAccount(String username, String password, boolean inTown,
			int totalPoints, Person person) {
		String error = "";
		if (username == null || username.trim().length() == 0) {
		    error = error + "Account username cannot be empty! ";
		}
		if (password == null || password.trim().length() == 0) {
		    error = error + "Account password cannot be empty! ";
		}
		if (totalPoints < 0) {
		    error = error + "Account Total points cannot be negative ";
		}
	    if (person == null) {
	        error = error + "Person needs to be selected for account! ";
	    } else if (!personRepository.existsById(person.getEmail())) {
	        error = error + "Person does not exist! ";
	    }
	    Account account = accountRepository.findAccountByUsername(username);
	    if(account == null) {
			throw new InvalidInputException("Account with username " + account + " does not exists");
		}
	    error = error.trim();
	    if (error.length() > 0) {
	        throw new InvalidInputException(error);
	    }
		account.setPassword(password);
		account.setInTown(inTown);
		account.setTotalPoints(totalPoints);
		account.setPerson(person);
		accountRepository.save(account);
		return account;
	}
	
	/**
	 * deleting an account using account
	 * @param account the account that we want to delete
	 * @return Acccount
	 */
	@Transactional
	public Account deleteAccount(Account account) {
	    if(account == null) {
			throw new InvalidInputException("Account with username " + account + " does not exists");
		}else {
			Cart cart = cartRepository.findCartByAccount(account);
			if(cart != null) {
				cartService.deleteCartbyID(cart.getId());
			}
			accountRepository.delete(account);
			return account;
		}
	}
	
	/**
	 * deleting an account using username
	 * @param username the username of the account we want to delete
	 * @return Account
	 */
	@Transactional
	public Account deleteAccountByUsername(String username) {
	    if(!accountRepository.existsById(username)) {
			throw new InvalidInputException("Account with username " + username + " does not exists");
		}else {
			Account account = accountRepository.findAccountByUsername(username);
			Cart cart = cartRepository.findCartByAccount(account);
			if(cart != null) {
				cartService.deleteCartbyID(cart.getId());
			}
			accountRepository.delete(account);
			return account;
		}
	}
	
	/**
	 * logging into the account
	 * @param username the username of the account
	 * @param password the password of the account
	 * @return Account
	 */
	@Transactional
	public Account loginAccount(String username, String password) {
		Account account = accountRepository.findAccountByUsername(username);
		if(account == null){
		     throw new InvalidInputException("no account exists with this username "+ username);
		}
		if(!account.getPassword().equals(password)){
		      throw new InvalidInputException("incorrect password : " + password);
		}
		return account;
	}
	
	/**
	 * finding all accounts within the given range of total points
	 * @param minPoint the min points the accounts have
	 * @param maxPoint the max point the accounts have
	 * @return List<Account>
	 */
	@Transactional 
	public List<Account> findAccountByTotalPointsBetween(int minPoint, int maxPoint){
		List<Account> accountList = new ArrayList<Account>();
		for(Account a: accountRepository.findAccountByTotalPointsBetween(minPoint,maxPoint)) {
			accountList.add(a);
		}
		return accountList;
	}
	
	/**
	 * finding all account who's username contains ____
	 * @param username the name the username should contains
	 * @return List<Account>
	 */
	@Transactional 
	public List<Account> findAccountByUsernameContainingIgnoreCase(String username){
		List<Account> accountList = new ArrayList<Account>();
		for(Account a: accountRepository.findAccountByUsernameContainingIgnoreCase(username)) {
			accountList.add(a);
		}
		return accountList;
	}
	
	/**
	 * finding all accounts who are either in town or out of town
	 * @param inTown determine which type of accounts we want
	 * @return List<Account>
	 */
	@Transactional 
	public List<Account> findAccountByInTown(boolean inTown){
		List<Account> accountList = new ArrayList<Account>();
		for(Account a: accountRepository.findAccountByInTown(inTown)) {
			accountList.add(a);
		}
		return accountList;
	}
	
	/**
	 * finidng an account using username
	 * @param username the username associated with the account
	 * @return Account
	 */
	@Transactional
	public Account findAccountByUsername(String username){
		if (username == null || username.trim().length() == 0) {
			throw new InvalidInputException("Username cannot be empty!");
		}else {
			return accountRepository.findAccountByUsername(username);
		}
	}
	
	/**
	 * getting all accounts
	 * @return List<Account>
	 */
	@Transactional
	public List<Account> getAllAccounts(){
		return toList(accountRepository.findAll());
	}
	
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
}
