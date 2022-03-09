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
import static org.mockito.Mockito.when;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import mcgill.ecse321.grocerystore.dao.AccountRepository;
import mcgill.ecse321.grocerystore.dao.CartRepository;
import mcgill.ecse321.grocerystore.dao.PersonRepository;
import mcgill.ecse321.grocerystore.model.Account;
import mcgill.ecse321.grocerystore.model.Cart;
import mcgill.ecse321.grocerystore.model.Person;


@ExtendWith(MockitoExtension.class)
public class TestAccountService {
	
	@Mock 
	private AccountRepository accountDao;
	
	@Mock
	private PersonRepository personDao;
	
	@Mock
	private CartRepository cartDao;	
	
	@InjectMocks
	private AccountService service;
	
	private static final String ACCOUNT_KEY = "TestAccount";
	private static final String PASSWORD = "101";
	private static final boolean INTOWN = true;
	private static final int TOTALPOINTS = 200;
	
	private static final String EMAIL = "abc@gmail.com";
	private static final String PHONENUMBER = "1112223333";
	private static final String ADDRESS = "845 Sherbrooke St W, Montreal, Quebec H3A 0G4";
	private static final String FIRSTNAME = "Bob";
	private static final String LASTNAME = "Smith";
	
	
	@BeforeEach
	public void setMockOutput() {
	    lenient().when(accountDao.findAccountByUsername(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
	        if(invocation.getArgument(0).equals(ACCOUNT_KEY)) {
	            Account account = new Account();
	            account.setUsername(ACCOUNT_KEY);
	            account.setPassword(PASSWORD);
	            account.setInTown(INTOWN);
	            account.setTotalPoints(TOTALPOINTS);
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
	    lenient().when(accountDao.findAccountByUsername(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
	        if(invocation.getArgument(0).equals(ACCOUNT_KEY)) {
	            Account account = new Account();
	            account.setUsername(ACCOUNT_KEY);
	            account.setPassword(PASSWORD);
	            account.setInTown(INTOWN);
	            account.setTotalPoints(TOTALPOINTS);
	            return account;
	        } else {
	            return null;
	        }
	    });
	    Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(accountDao.save(any(Account.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(personDao.save(any(Person.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(cartDao.save(any(Cart.class))).thenAnswer(returnParameterAsAnswer);
	}
	
	@Test
	public void testCreateAccount() {
		
		String username = "Bob";
		String password = "101";
		boolean inTown = true;
		int totalPoints = 0;
		Account account = null;
		
		String email = "abc@gmail.com";
		String phoneNumber = "1112223333";
		String address = "845 Sherbrooke St W, Montreal, Quebec H3A 0G4";
		String firstName = "Bob";
		String lastName = "Smith";
		Person person = service.createPerson(email, firstName, lastName, phoneNumber, address);
		System.out.println(personDao.findPersonByEmail(email));
		
		Date date = java.sql.Date.valueOf(LocalDate.of(2021, Month.DECEMBER, 2));
		Cart cart = service.createCart(date);
		System.out.println(cart.getId());
		
//		try {
			account = service.createAccount(username, password, inTown, totalPoints, person, cart);
			
//		} catch (IllegalArgumentException e) {
//			// Check that no error occurred
//			fail();
//		}
		assertNotNull(account);
		assertEquals(username, account.getUsername());
	}
}