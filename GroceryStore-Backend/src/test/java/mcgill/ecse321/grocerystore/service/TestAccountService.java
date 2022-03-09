package mcgill.ecse321.grocerystore.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
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
	

	private static final String EMAIL = "abc@gmail.com";
	private static final String PHONENUMBER = "1112223333";
	private static final String ADDRESS = "845 Sherbrooke St W, Montreal, Quebec H3A 0G4";
	private static final String FIRSTNAME = "Bob";
	private static final String LASTNAME = "Smith";

	private static final String USERNAME = "Bob";
	private static final String PASSWORD = "101";
	private static final boolean INTOWN = true;
	private static final int TOTALPOINTS = 0;
	
	private static final Date DATE = java.sql.Date.valueOf(LocalDate.of(2021, Month.DECEMBER, 2));
	private static final int ID = 1;


	@BeforeEach
	public void setMockOutput() {
	    lenient().when(accountDao.findAccountByUsername(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
	        if(invocation.getArgument(0).equals(USERNAME)) {
	            Account account = new Account();
	            account.setUsername(USERNAME);
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
	    lenient().when(cartDao.findCartById(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
	        if(invocation.getArgument(0).equals(ID)) {
	           Cart cart = new Cart();
	           cart.setDate(DATE);
	           return cart;
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
		
		String email = "abc@gmail.com";
		String phoneNumber = "1112223333";
		String address = "845 Sherbrooke St W, Montreal, Quebec H3A 0G4";
		String firstName = "Bob";
		String lastName = "Smith";
		Person person = service.createPerson(email, firstName, lastName, phoneNumber, address);
		
		Date date = java.sql.Date.valueOf(LocalDate.of(2021, Month.DECEMBER, 2));
		Cart cart = service.createCart(date);
		
		String username = "Bob";
		String password = "101";
		boolean inTown = true;
		int totalPoints = 0;
		Account account = null;
		
		try {
			account = service.createAccount(username, password, inTown, totalPoints, person, cart);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}
		
		assertNotNull(account);
		assertEquals(username, account.getUsername());
	}
}