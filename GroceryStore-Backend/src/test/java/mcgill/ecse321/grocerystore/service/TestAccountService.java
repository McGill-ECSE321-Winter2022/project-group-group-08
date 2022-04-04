//package mcgill.ecse321.grocerystore.service;
//
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.junit.jupiter.MockitoExtension;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertNull;
//import static org.junit.jupiter.api.Assertions.fail;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.ArgumentMatchers.anyBoolean;
//import static org.mockito.ArgumentMatchers.anyInt;
//import static org.mockito.Mockito.lenient;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.invocation.InvocationOnMock;
//import org.mockito.stubbing.Answer;
//
//import mcgill.ecse321.grocerystore.dao.AccountRepository;
//import mcgill.ecse321.grocerystore.dao.CartRepository;
//import mcgill.ecse321.grocerystore.dao.PersonRepository;
//import mcgill.ecse321.grocerystore.model.Account;
//import mcgill.ecse321.grocerystore.model.Cart;
//import mcgill.ecse321.grocerystore.model.Person;
//
//
//@ExtendWith(MockitoExtension.class)
//public class TestAccountService {
//	
//	@Mock 
//	private AccountRepository accountDao;
//	
//	@Mock
//	private PersonRepository personDao;
//	
//	@Mock
//	private CartRepository cartDao;
//	
//	@InjectMocks
//	private AccountService accountService;
//	
//	@InjectMocks
//	private PersonService personService;
//	
//
//	private static final String USERNAME = "Bob";
//	private static final String PASSWORD = "101";
//	private static final String NEWPASSWORD = "111";
//	private static final boolean INTOWN = true;
//	private static final boolean NEWINTOWN = false;
//	private static final int TOTALPOINTS = 0;
//	private static final int NEWTOTALPOINTS = 10;
//	
//	private static final String EMAIL = "abc@gmail.com";
//	private static final String PHONENUMBER = "1112223333";
//	private static final String ADDRESS = "845 Sherbrooke St W, Montreal, Quebec H3A 0G4";
//	private static final String FIRSTNAME = "Bob";
//	private static final String LASTNAME = "Smith";
//
//	@BeforeEach
//	public void setMockOutput() {
//	    lenient().when(accountDao.findAccountByUsername(USERNAME)).thenAnswer( (InvocationOnMock invocation) -> {
//	        if(invocation.getArgument(0).equals(USERNAME)) {
//	            Account account = new Account();
//	            account.setUsername(USERNAME);
//	            account.setPassword(PASSWORD);
//	            account.setInTown(INTOWN);
//	            account.setTotalPoints(TOTALPOINTS);
//	            account.setPerson(personService.createPerson(EMAIL, FIRSTNAME, LASTNAME, PHONENUMBER, ADDRESS));
//	            return account;
//	        } else {
//	            return null;
//	        }
//	    });
//	    lenient().when(personDao.findPersonByEmail(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
//	        if(invocation.getArgument(0).equals(EMAIL)) {
//	            Person person = new Person();
//	            person.setEmail(EMAIL);
//	            person.setPhoneNumber(PHONENUMBER);
//	            person.setAddress(ADDRESS);
//	            person.setFirstName(FIRSTNAME);
//	            person.setLastName(LASTNAME);
//	            return person;
//	        } else {
//	            return null;
//	        }
//	    });	    
//	    lenient().when(accountDao.findAccountByInTown(anyBoolean())).thenAnswer((InvocationOnMock invocation) -> {
//	    	 Account account = new Account();
//	         account.setUsername(USERNAME);
//	         account.setPassword(PASSWORD);
//	         account.setInTown(INTOWN);
//	         account.setTotalPoints(TOTALPOINTS);
//	         account.setPerson(personService.createPerson(EMAIL, FIRSTNAME, LASTNAME, PHONENUMBER, ADDRESS));
//	               
//
//	        List<Account> accounts = new ArrayList<Account>();
//	        accounts.add(account);
//	        return accounts;
//	      });
//	    lenient().when(accountDao.findAccountByUsernameContainingIgnoreCase(anyString())).thenAnswer((InvocationOnMock invocation) -> {
//	    	 Account account = new Account();
//	         account.setUsername(USERNAME);
//	         account.setPassword(PASSWORD);
//	         account.setInTown(INTOWN);
//	         account.setTotalPoints(TOTALPOINTS);
//	         account.setPerson(personService.createPerson(EMAIL, FIRSTNAME, LASTNAME, PHONENUMBER, ADDRESS));
//	               
//
//	        List<Account> accounts = new ArrayList<Account>();
//	        accounts.add(account);
//	        return accounts;
//	      });
//	    lenient().when(accountDao.findAccountByTotalPointsBetween(anyInt(),anyInt())).thenAnswer((InvocationOnMock invocation) -> {
//	    	 Account account = new Account();
//	         account.setUsername(USERNAME);
//	         account.setPassword(PASSWORD);
//	         account.setInTown(INTOWN);
//	         account.setTotalPoints(TOTALPOINTS);
//	         account.setPerson(personService.createPerson(EMAIL, FIRSTNAME, LASTNAME, PHONENUMBER, ADDRESS));
//	               
//
//	        List<Account> accounts = new ArrayList<Account>();
//	        accounts.add(account);
//	        return accounts;
//	      });
//	    lenient().when(accountDao.existsById(anyString())).thenReturn(true);
//	    lenient().when(personDao.existsById(anyString())).thenReturn(true);
//	    lenient().when(cartDao.existsById(anyInt())).thenReturn(true);
//	    Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
//			return invocation.getArgument(0);
//		};
//		lenient().when(accountDao.save(any(Account.class))).thenAnswer(returnParameterAsAnswer);
//		lenient().when(personDao.save(any(Person.class))).thenAnswer(returnParameterAsAnswer);
//		lenient().when(cartDao.save(any(Cart.class))).thenAnswer(returnParameterAsAnswer);
//		
//	}
//	
//	@Test
//	public void testCreateAccount() {
//		Person person = personService.findPersonByEmail(EMAIL);
//		Account account = null;
//		assertNotNull(person);
//		
//		try {
//			account = accountService.createAccount(USERNAME, PASSWORD, INTOWN, TOTALPOINTS, person);
//		} catch (InvalidInputException e) {
//			// Check that no error occurred
//			fail();
//		}
//		assertNotNull(account);
//		assertEquals(USERNAME,account.getUsername());
//		assertEquals(PASSWORD,account.getPassword());
//		assertEquals(INTOWN,account.getInTown());
//		assertEquals(TOTALPOINTS,account.getTotalPoints());
//	}
//	
//	@Test
//	public void testCreateAccountWithInvalidUsername() {
//		Person person = personService.findPersonByEmail(EMAIL);
//		Account account = null;
//		String error = "";
//		assertNotNull(person);
//		
//		try {
//			account = accountService.createAccount("", PASSWORD, INTOWN, TOTALPOINTS, person);
//		} catch (InvalidInputException e) {
//			error = e.getMessage();
//		}
//		assertNull(account);
//		assertEquals("Account username cannot be empty!",error);
//	}
//	
//	@Test
//	public void testCreateAccountWithInvalidTotalPoints() {
//		Person person = personService.findPersonByEmail(EMAIL);
//		Account account = null;
//		String error = "";
//		assertNotNull(person);
//		
//		try {
//			account = accountService.createAccount(USERNAME, PASSWORD, INTOWN, -1, person);
//		} catch (InvalidInputException e) {
//			error = e.getMessage();
//		}
//		assertNull(account);
//		assertEquals("Account Total points cannot be negative!",error);
//	}
//	
//	@Test
//	public void testCreateAccountWithInvalidPassword() {
//		Person person = personService.findPersonByEmail(EMAIL);
//		Account account = null;
//		String error = "";
//		assertNotNull(person);
//		
//		try {
//			account = accountService.createAccount(USERNAME, null, INTOWN, TOTALPOINTS, person);
//		} catch (InvalidInputException e) {
//			error = e.getMessage();
//		}
//		assertNull(account);
//		assertEquals("Account password cannot be empty!",error);
//	}
//	
//	@Test
//	public void testUpdateAccount() {
//		Person person = personService.findPersonByEmail(EMAIL);
//		Account account = null;
//		assertNotNull(person);
//		
//		try {
//			account = accountService.updateAccount(USERNAME, NEWPASSWORD, NEWINTOWN, NEWTOTALPOINTS, person);
//		} catch (InvalidInputException e) {
//			// Check that no error occurred
//			fail();
//		}
//		assertNotNull(account);
//		assertEquals(USERNAME,account.getUsername());
//		assertEquals(NEWPASSWORD,account.getPassword());
//		assertEquals(NEWINTOWN,account.getInTown());
//		assertEquals(NEWTOTALPOINTS,account.getTotalPoints());
//	}
//	
//	@Test
//	public void testUpdateAccountWithBlankPassword() {
//		Person person = personService.findPersonByEmail(EMAIL);
//		Account account = null;
//		String error = "";
//		assertNotNull(person);
//		
//		try {
//			account = accountService.updateAccount(USERNAME, "", NEWINTOWN, NEWTOTALPOINTS, person);
//		} catch (InvalidInputException e) {
//			error = e.getMessage();
//		}
//		assertNull(account);
//		assertEquals("Account password cannot be empty!",error);
//	}
//	
//	@Test
//	public void testUpdateAccountWithInvalidTotalPoints() {
//		Person person = personService.findPersonByEmail(EMAIL);
//		Account account = null;
//		String error = "";
//		assertEquals(0, accountService.getAllAccounts().size());
//		assertNotNull(person);
//		
//		try {
//			account = accountService.updateAccount(USERNAME, NEWPASSWORD, NEWINTOWN, -1, person);
//		} catch (InvalidInputException e) {
//			error = e.getMessage();
//		}
//		assertNull(account);
//		assertEquals("Account Total points cannot be negative",error);
//	}
//	
//	@Test
//	public void testUpdateAccountWithNullPerson() {
//		Account account = null;
//		String error = "";
//
//		try {
//			account = accountService.updateAccount(USERNAME, NEWPASSWORD, NEWINTOWN, NEWTOTALPOINTS, null);
//		} catch (InvalidInputException e) {
//			error = e.getMessage();
//		}
//		assertNull(account);
//		assertEquals("Person needs to be selected for account!",error);
//	}
//
//	@Test
//	public void testUpdateAccountWithInvalidUsername() {
//		Account account = null;
//		String error = "";
//
//		try {
//			account = accountService.updateAccount("jo", NEWPASSWORD, NEWINTOWN, NEWTOTALPOINTS, null);
//		} catch (InvalidInputException e) {
//			error = e.getMessage();
//		}
//		assertNull(account);
//		assertEquals("Account with username " + account + " does not exists",error);
//	}
//	
//	@Test
//	public void testLoginAccount() {
//		Account account = null;
//		try {
//			account = accountService.loginAccount(USERNAME, PASSWORD);
//		}catch(InvalidInputException e) {
//			fail();
//		}
//		assertNotNull(account);
//		assertEquals(USERNAME,account.getUsername());
//		assertEquals(PASSWORD,account.getPassword());
//		assertEquals(INTOWN,account.getInTown());
//		assertEquals(TOTALPOINTS,account.getTotalPoints());
//	}
//	
//	@Test
//	public void testLoginAccountInvalidPassword() {
//		Account account = null;
//		String error = "";
//		try {
//			account = accountService.loginAccount(USERNAME, NEWPASSWORD);
//		}catch(InvalidInputException e) {
//			error = e.getMessage();
//		}
//		assertNull(account);
//		assertEquals("incorrect password : " + NEWPASSWORD,error);
//		
//	}
//	
//	@Test
//	public void testLoginAccountInvalidUsername() {
//		Account account = null;
//		String error = "";
//		try {
//			account = accountService.loginAccount("bob", PASSWORD);
//		}catch(InvalidInputException e) {
//			error = e.getMessage();
//		}
//		assertNull(account);
//		assertEquals("no account exists with this username bob",error);
//	}
//	
//	@Test
//	public void testGetPersonByAccount() {
//		Person person = null;
//		Account account = accountDao.findAccountByUsername(USERNAME);
//		try {
//			person = accountService.getPersonByAccount(account);
//		}catch(InvalidInputException e) {
//			fail();
//		}
//		assertNotNull(person);
//		assertEquals(EMAIL, person.getEmail());
//		assertEquals(FIRSTNAME, person.getFirstName());
//		assertEquals(LASTNAME, person.getLastName());
//		assertEquals(PHONENUMBER, person.getPhoneNumber());
//		assertEquals(ADDRESS, person.getAddress());	
//	}
//	
//	@Test
//	public void testGetAccountByTown() {
//		List<Account> accountInTown = new ArrayList<Account>();
//		
//		try {
//			accountInTown = accountService.findAccountByInTown(true);
//		}catch(InvalidInputException e) {
//			fail();
//		}
//		Account account = accountInTown.get(0);
//		assertNotNull(account);
//		assertEquals(USERNAME,account.getUsername());
//		assertEquals(PASSWORD,account.getPassword());
//		assertEquals(INTOWN,account.getInTown());
//		assertEquals(TOTALPOINTS,account.getTotalPoints());
//	}
//	
//	@Test
//	public void testGetAccountByUsernameContainingIgnoreCase() {
//		List<Account> accountInTown = new ArrayList<Account>();
//		
//		try {
//			accountInTown = accountService.findAccountByUsernameContainingIgnoreCase("bo");
//		}catch(InvalidInputException e) {
//			fail();
//		}
//		Account account = accountInTown.get(0);
//		assertNotNull(account);
//		assertEquals(USERNAME,account.getUsername());
//		assertEquals(PASSWORD,account.getPassword());
//		assertEquals(INTOWN,account.getInTown());
//		assertEquals(TOTALPOINTS,account.getTotalPoints());
//	}
//	
//	@Test
//	public void testGetAccountByTotalPointsBetween() {
//		List<Account> accountInTown = new ArrayList<Account>();
//		
//		try {
//			accountInTown = accountService.findAccountByTotalPointsBetween(0, 100);
//		}catch(InvalidInputException e) {
//			fail();
//		}
//		Account account = accountInTown.get(0);
//		assertNotNull(account);
//		assertEquals(USERNAME,account.getUsername());
//		assertEquals(PASSWORD,account.getPassword());
//		assertEquals(INTOWN,account.getInTown());
//		assertEquals(TOTALPOINTS,account.getTotalPoints());
//	}
//	
//	@Test
//	public void testDeleteAccount() {
//		Account account = accountService.findAccountByUsername(USERNAME);
//		
//		try {
//			account = accountService.deleteAccount(account);
//		} catch (InvalidInputException e) {
//			// Check that no error occurred
//			fail();
//		}
//		assertNotNull(account);
//	}
//	
//	@Test
//	public void testDeleteAccountByUsername() {
//		Account account = null;
//		try {
//			account = accountService.deleteAccountByUsername(USERNAME);
//		}catch(InvalidInputException e) {
//			fail();
//		}
//		assertNotNull(account);
//	}
//	
//	@Test
//	public void testDeleteAccountByInvalidUsername() {
//		Account account = null;
//		try {
//			account = accountService.deleteAccountByUsername("on");
//		}catch(InvalidInputException e) {
//			fail();
//		}
//		assertNull(account);
//	}
//	
//	
//}