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
		
//	//CART
//	@Transactional
//	public Cart createCart(Date date) {
//		if (date == null) {
//			throw new IllegalArgumentException("Cart date cannot be empty!");
//		}
//		Cart cart  = new Cart();
//		cart.setDate(date);
//		cartRepository.save(cart);
//		return cart;
//	}
//	
//	@Transactional
//	public Cart getCart(int id) {
//		Cart cart  = cartRepository.findCartById(id);
//		return cart;
//	}
//
//	//PERSON
//	@Transactional
//	public Person createPerson(String email, String firstName, String lastName, String phoneNumber, String address) {
//		String error = "";
//		if (email == null || email.trim().length() == 0) {
//		    error = error + "Person email cannot be empty! ";
//		}
//		if (firstName == null || firstName.trim().length() == 0) {
//		    error = error + "Person first name cannot be empty! ";
//		}
//		if (lastName == null || lastName.trim().length() == 0) {
//		    error = error + "Person last name cannot be empty! ";
//		}
//		if (phoneNumber == null || phoneNumber.trim().length() == 0) {
//		    error = error + "Person phone number cannot be empty! ";
//		}
//		if(phoneNumber.length() < 10) {
//			error = error + "Person phone number cannot be less than 10 digits";
//		}
//		if (address == null || address.trim().length() == 0) {
//		    error = error + "Person last name cannot be empty! ";
//		}
//		error = error.trim();
//		if (error.length() > 0) {
//		    throw new IllegalArgumentException(error);
//		}
//		Person person = new Person();
//		person.setEmail(email);
//		person.setFirstName(firstName);
//		person.setLastName(lastName);
//		person.setPhoneNumber(phoneNumber);
//		person.setAddress(address);
//		personRepository.save(person);
//		return person;
//	}
//	
//	@Transactional
//	public Person getPerson(String email) {
//		Person person = personRepository.findPersonByEmail(email);
//		return person;
//	}
	
	//ACCOUNT
	@Transactional
	public Account createAccount(String username, String password, boolean inTown,
			int totalPoints, Person person, Cart cart) {
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
	    if (cart == null) {
	        error = error + "Cart needs to be selected for account!";
	    } else if (!cartRepository.existsById(cart.getId())) {
	        error = error + "Cart does not exist!";
	    }
	    if (accountRepository.existsByPersonAndCart(person, cart)) {
	        error = error + "Account already has a person and cart associated!";
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
		account.setCart(cart);
		accountRepository.save(account);
		return account;
	}
	
	@Transactional
	public Account updateAccount(String oldUsername, String newUsername, String password, boolean inTown,
			int totalPoints, Person person, Cart cart) {
		String error = "";
		if (oldUsername == null || oldUsername.trim().length() == 0) {
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
	    if (cart == null) {
	        error = error + "Cart needs to be selected for account!";
	    } else if (!cartRepository.existsById(cart.getId())) {
	        error = error + "Cart does not exist!";
	    }
	    if (accountRepository.existsByPersonAndCart(person, cart)) {
	        error = error + "Account already has a person and cart associated!";
	    }
	    Account account = accountRepository.findAccountByUsername(oldUsername);
	    if(account == null) {
			throw new IllegalArgumentException("Account with username " + account + " does not exists");
		}
	    error = error.trim();
	    if (error.length() > 0) {
	        throw new IllegalArgumentException(error);
	    }
		account.setUsername(newUsername);
		account.setPassword(password);
		account.setInTown(inTown);
		account.setTotalPoints(totalPoints);
		account.setPerson(person);
		account.setCart(cart);
		accountRepository.save(account);
		return account;
	}
	
	@Transactional
	public void deleteAccount(Account account) {
	    if(account == null) {
			throw new IllegalArgumentException("Account with username " + account + " does not exists");
		}else {
			accountRepository.delete(account);
		}
	}
	
	@Transactional
	public void deleteAccountByUsername(String username) {
		Account account = accountRepository.findAccountByUsername(username);
	    if(account == null) {
			throw new IllegalArgumentException("Account with username " + account + " does not exists");
		}else {
			accountRepository.delete(account);
		}
	}
	
	@Transactional 
	public List<Account> findAccountByUsernameIgnoreCase(String username){
		List<Account> accountList = new ArrayList<Account>();
		for(Account a: accountRepository.findAccountByUsernameIgnoreCase(username)) {
			accountList.add(a);
		}
		return accountList;
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
	public List<Account> findAccountByUsernameContaining(String username){
		List<Account> accountList = new ArrayList<Account>();
		for(Account a: accountRepository.findAccountByUsernameContaining(username)) {
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
	public Account getAccount(String username) {
		Account account = accountRepository.findAccountByUsername(username);
		return account;
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
