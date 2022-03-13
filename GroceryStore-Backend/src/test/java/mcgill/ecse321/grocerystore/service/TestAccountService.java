package mcgill.ecse321.grocerystore.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import mcgill.ecse321.grocerystore.dao.AccountRepository;
import mcgill.ecse321.grocerystore.dao.PersonRepository;
import mcgill.ecse321.grocerystore.model.Account;
import mcgill.ecse321.grocerystore.model.Person;


@ExtendWith(MockitoExtension.class)
public class TestAccountService {
	
	@Mock 
	private AccountRepository accountDao;
	
	@Mock
	private PersonRepository personDao;
	
	@InjectMocks
	private AccountService accountService;
	
	@InjectMocks
	private PersonService personService;
	

	private static final String USERNAME = "Bob";
	private static final String PASSWORD = "101";
	private static final String NEWPASSWORD = "111";
	private static final boolean INTOWN = true;
	private static final boolean NEWINTOWN = false;
	private static final int TOTALPOINTS = 0;
	private static final int NEWTOTALPOINTS = 10;
	
	private static final String EMAIL = "abc@gmail.com";
	private static final String PHONENUMBER = "1112223333";
	private static final String ADDRESS = "845 Sherbrooke St W, Montreal, Quebec H3A 0G4";
	private static final String FIRSTNAME = "Bob";
	private static final String LASTNAME = "Smith";

	@BeforeEach
	public void setMockOutput() {
	    lenient().when(accountDao.findAccountByUsername(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
	        if(invocation.getArgument(0).equals(USERNAME)) {
	            Account account = new Account();
	            account.setUsername(USERNAME);
	            account.setPassword(PASSWORD);
	            account.setInTown(INTOWN);
	            account.setTotalPoints(TOTALPOINTS);
	            account.setPerson(personService.createPerson(EMAIL, FIRSTNAME, LASTNAME, PHONENUMBER, ADDRESS));
	            return account;
	        } else {
	            return null;
	        }
	    });
	    lenient().when(personDao.findPersonByEmail(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
	        if(invocation.getArgument(0).equals(EMAIL)) {
	            Person person = new Person();
	            person.setEmail(EMAIL);
	            person.setPhoneNumber(PHONENUMBER);
	            person.setAddress(ADDRESS);
	            person.setFirstName(FIRSTNAME);
	            person.setLastName(LASTNAME);
	            return person;
	        } else {
	            return null;
	        }
	    });	    
	    Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(accountDao.save(any(Account.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(personDao.save(any(Person.class))).thenAnswer(returnParameterAsAnswer);
	}
	
	@Test
	public void testCreateAccount() {
		lenient().when(personDao.existsById(anyString())).thenReturn(true);
		Person person = personService.findPersonByEmail(EMAIL);
		Account account = null;
		assertEquals(0, accountService.getAllAccounts().size());
		assertNotNull(person);
		
		try {
			account = accountService.createAccount(USERNAME, PASSWORD, INTOWN, TOTALPOINTS, person);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}
		assertNotNull(account);
		assertEquals(USERNAME,account.getUsername());
		assertEquals(PASSWORD,account.getPassword());
		assertEquals(INTOWN,account.getInTown());
		assertEquals(TOTALPOINTS,account.getTotalPoints());
	}
	
	@Test
	public void testCreateAccountWithInvalidUsername() {
		lenient().when(personDao.existsById(anyString())).thenReturn(true);
		Person person = personService.findPersonByEmail(EMAIL);
		Account account = null;
		String error = "";
		assertEquals(0, accountService.getAllAccounts().size());
		assertNotNull(person);
		
		try {
			account = accountService.createAccount("", PASSWORD, INTOWN, TOTALPOINTS, person);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(account);
		assertEquals("Account username cannot be empty!",error);
	}
	
	@Test
	public void testUpdateAccount() {
		lenient().when(personDao.existsById(anyString())).thenReturn(true);
		Person person = personService.findPersonByEmail(EMAIL);
		Account account = null;
		assertEquals(0, accountService.getAllAccounts().size());
		assertNotNull(person);
		
		try {
			account = accountService.updateAccount(USERNAME, NEWPASSWORD, NEWINTOWN, NEWTOTALPOINTS, person);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}
		assertNotNull(account);
		assertEquals(USERNAME,account.getUsername());
		assertEquals(NEWPASSWORD,account.getPassword());
		assertEquals(NEWINTOWN,account.getInTown());
		assertEquals(NEWTOTALPOINTS,account.getTotalPoints());
	}
	
	@Test
	public void testUpdateAccountWithInvalidTotalPoints() {
		lenient().when(personDao.existsById(anyString())).thenReturn(true);
		Person person = personService.findPersonByEmail(EMAIL);
		Account account = null;
		String error = "";
		assertEquals(0, accountService.getAllAccounts().size());
		assertNotNull(person);
		
		try {
			account = accountService.updateAccount(USERNAME, NEWPASSWORD, NEWINTOWN, -1, person);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(account);
		assertEquals("Account Total points cannot be negative",error);
	}
	
	@Test
	public void testDeleteAccount() {
		Account account = accountService.findAccountByUsername(USERNAME);
		
		try {
			account = accountService.deleteAccount(account);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}
		assertNotNull(account);
	}
	
	@Test
	public void testGetPersonByAccount() {
		Person person = null;
		Account account = accountDao.findAccountByUsername(USERNAME);
		try {
			person = accountService.getPersonByAccount(account);
		}catch(IllegalArgumentException e) {
			fail();
		}
		assertNotNull(person);
		assertEquals(EMAIL, person.getEmail());
		assertEquals(FIRSTNAME, person.getFirstName());
		assertEquals(LASTNAME, person.getLastName());
		assertEquals(PHONENUMBER, person.getPhoneNumber());
		assertEquals(ADDRESS, person.getAddress());	
	}
	
	
}