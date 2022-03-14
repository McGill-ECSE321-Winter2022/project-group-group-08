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
//	@Autowired
//	CartService cartService;
	
	@Transactional 
	public Person getPersonByAccount(Account account) {
		if(account == null) {
			throw new IllegalArgumentException("Account does not exists");
		}else {
			return accountRepository.findAccountByUsername(account.getUsername()).getPerson();
		}
	}

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
	        throw new IllegalArgumentException(error);
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
			throw new IllegalArgumentException("Account with username " + account + " does not exists");
		}
	    error = error.trim();
	    if (error.length() > 0) {
	        throw new IllegalArgumentException(error);
	    }
		account.setPassword(password);
		account.setInTown(inTown);
		account.setTotalPoints(totalPoints);
		account.setPerson(person);
		accountRepository.save(account);
		return account;
	}
	
	@Transactional
	public Account deleteAccount(Account account) {
	    if(account == null) {
			throw new IllegalArgumentException("Account with username " + account + " does not exists");
		}else {
//			Cart cart = cartRepository.findCartByAccount(account);
//			if(cart != null) {
//				cartService.deleteCart(cart);
//			}
			accountRepository.delete(account);
			return account;
		}
	}
	
	@Transactional
	public Account deleteAccountByUsername(String username) {
	    if(!accountRepository.existsById(username)) {
			throw new IllegalArgumentException("Account with username " + username + " does not exists");
		}else {
			Account account = accountRepository.findAccountByUsername(username);
			
//			Cart cart = cartRepository.findCartByAccount(account);
//			if(cart != null) {
//				cartService.deleteCart(cart);
//			}
			accountRepository.delete(account);
			return account;
		}
	}
	
	@Transactional 
	public List<Account> findAccountByTotalPointsBetween(int minPoint, int maxPoint){
		List<Account> accountList = new ArrayList<Account>();
		for(Account a: accountRepository.findAccountByTotalPointsBetween(minPoint,maxPoint)) {
			accountList.add(a);
		}
		return accountList;
	}
	
	@Transactional 
	public List<Account> findAccountByUsernameContainingIgnoreCase(String username){
		List<Account> accountList = new ArrayList<Account>();
		for(Account a: accountRepository.findAccountByUsernameContainingIgnoreCase(username)) {
			accountList.add(a);
		}
		return accountList;
	}
	
	@Transactional 
	public List<Account> findAccountByInTown(boolean inTown){
		List<Account> accountList = new ArrayList<Account>();
		for(Account a: accountRepository.findAccountByInTown(inTown)) {
			accountList.add(a);
		}
		return accountList;
	}
	
	@Transactional
	public Account findAccountByUsername(String username){
		if (username == null || username.trim().length() == 0) {
			throw new IllegalArgumentException("Username cannot be empty!");
		}else {
			return accountRepository.findAccountByUsername(username);
		}
	}
	
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
