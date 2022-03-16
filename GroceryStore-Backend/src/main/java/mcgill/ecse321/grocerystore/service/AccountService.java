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
	 * Finds person by accounts
	 * @param account object
	 * @return unique person linked to account
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
	 * Creates an account with its primary key username
	 * @param username unique identifier of account
	 * @param password password of the account
	 * @param inTown specify whether the person is inTown or out
	 * @param totalPoints totalPoints accummulated by account
	 * @param person the person object which will be linked to that account
	 * @return unique account linked to person 
	 */
	@Transactional
	public Account createAccount(String username, String password, boolean inTown,
			int totalPoints, Person person) {
		String error = "";
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
	 * Updates an account with its primary key username
	 * @param username unique identifier of account
	 * @param password new password of the account
	 * @param inTown specify whether the person is inTown or out
	 * @param totalPoints new totalPoints accummulated by account
	 * @param person if ever it should be linked to another person but will rarely or not be used at all
	 * @return updated account
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
	 * Deletes an account
	 * @param account existing account in database
	 * @return account
	 */
	@Transactional
	public Account deleteAccount(Account account) {
	    if(account == null) {
			throw new InvalidInputException("Account with username " + account + " does not exists");
		}else {
			Cart cart = cartRepository.findCartByAccount(account);
			if(cart != null) {
				cartService.deleteCart(cart);
			}
			accountRepository.delete(account);
			return account;
		}
	}
	
	/**
	 * Deletes an account using primary key
	 * @param username unique identifier of account
	 * @return account
	 */
	@Transactional
	public Account deleteAccountByUsername(String username) {
	    if(!accountRepository.existsById(username)) {
			throw new InvalidInputException("Account with username " + username + " does not exists");
		}else {
			Account account = accountRepository.findAccountByUsername(username);
			Cart cart = cartRepository.findCartByAccount(account);
			if(cart != null) {
				cartService.deleteCart(cart);
			}
			accountRepository.delete(account);
			return account;
		}
	}
	
	/**
	 * Log into an account
	 * @param username primary key of account
	 * @param password password associated with that account
	 * @return account
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
	 * Returns a list of accounts with points between min and max points
	 * @param minPoint lower bound of search
	 * @param maxPoint upper bound of search
	 * @return list of account
	 */
	@Transactional 
	public List<Account> findAccountByTotalPointsBetween(int minPoint, int maxPoint){
		if(minPoint > maxPoint){
			throw new InvalidInputException("minPoint cannot be smaller than maxPoint");
		}else{
			List<Account> accountList = new ArrayList<Account>();
			for(Account a: accountRepository.findAccountByTotalPointsBetween(minPoint,maxPoint)) {
				accountList.add(a);
			}
			return accountList;
		}
		
	}
	
	/**
	 * Returns a list of account with the username containing a word
	 * @param word string which is being searched
	 * @return list of account
	 */
	@Transactional 
	public List<Account> findAccountByUsernameContainingIgnoreCase(String word){
		if (word == null || word.trim().length() == 0) {
			throw new InvalidInputException("Search word cannot be empty!");
		}else {
			List<Account> accountList = new ArrayList<Account>();
			for(Account a: accountRepository.findAccountByUsernameContainingIgnoreCase(word)) {
				accountList.add(a);
			}
			return accountList;
		}	
	}
	
	/**
	 * Returns a list of account which have their inTown variable set to the boolean passed in function
	 * @param inTown variable to know if account is inTown or not
	 * @return list of account
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
	 * Returns account with this primary key username
	 * @param username primary identifier of account
	 * @return an account with this username
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
	 * @return Returns a list of accounts in the database
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
