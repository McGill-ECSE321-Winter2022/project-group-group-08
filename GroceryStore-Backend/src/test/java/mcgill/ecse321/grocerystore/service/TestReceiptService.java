package mcgill.ecse321.grocerystore.service;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import mcgill.ecse321.grocerystore.dao.AccountRepository;
import mcgill.ecse321.grocerystore.dao.PersonRepository;
import mcgill.ecse321.grocerystore.dao.ReceiptRepository;
import mcgill.ecse321.grocerystore.model.Account;
import mcgill.ecse321.grocerystore.model.Cart;
import mcgill.ecse321.grocerystore.model.Receipt;
import mcgill.ecse321.grocerystore.model.Receipt.ReceiptStatus;
import mcgill.ecse321.grocerystore.model.Receipt.ReceiptType;

@ExtendWith(MockitoExtension.class)
 public class TestReceiptService {

 	@Mock
 	private ReceiptRepository receiptDao;


 	@InjectMocks
 	private ReceiptService service;
 	
 	@Mock 
	private AccountRepository accountDao;
	
	@Mock
	private PersonRepository personDao;
	
	@InjectMocks
	private AccountService accountService;
	
	@InjectMocks
	private PersonService personService;
 	
 	private static int ID = 0;
 	
 	//mock data for Person and Account
 	private static final String USERNAME = "Bob";
	private static final String PASSWORD = "101";
	private static final boolean INTOWN = true;
	private static final int TOTALPOINTS = 0;
	private static final String EMAIL = "abc@gmail.com";
	private static final String PHONENUMBER = "1112223333";
	private static final String ADDRESS = "845 Sherbrooke St W, Montreal, Quebec H3A 0G4";
	private static final String FIRSTNAME = "Bob";
	private static final String LASTNAME = "Smith";
	private static final Date date = java.sql.Date.valueOf(LocalDate.of(2022, Month.DECEMBER, 31));;

 	@BeforeEach
 	public void setMockOutput() {
 		//mock data when findReceiptByReceiptNum is called
 		lenient().when(receiptDao.findReceiptByReceiptNum(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
 			if(invocation.getArgument(0).equals(ID)) {
 				//make new receipt
 				Receipt curr = new Receipt();
 				curr.setCart(null);
 				curr.setReceiptStatus(ReceiptStatus.Processed);
 				curr.setReceiptType(ReceiptType.Pickup);
 				//return the receipt
 				return curr;
 			} else {
 				return null;
 			}
 		});
 		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
 			return invocation.getArgument(0);
 		};
 		lenient().when(receiptDao.save(any(Receipt.class))).thenAnswer(returnParameterAsAnswer);
 		//mock data when findReceiptByReceiptStatus is called
 		lenient().when(receiptDao.findReceiptByReceiptStatus(ReceiptStatus.Processed)).thenAnswer((InvocationOnMock invocation) -> {
 			//make a new receipt
 			Receipt curr = new Receipt();
			curr.setCart(null);
			curr.setReceiptStatus(ReceiptStatus.Processed);
			curr.setReceiptType(ReceiptType.Pickup);
			//make a new arraylist that will store the new receipt
			ArrayList<Receipt> list = new ArrayList<Receipt>();
			list.add(curr);
			//return the receipt list
			return list;
		});
 		//similar to above
 		lenient().when(receiptDao.findReceiptByReceiptType(ReceiptType.Pickup)).thenAnswer((InvocationOnMock invocation) -> {
 			Receipt curr = new Receipt();
			curr.setCart(null);
			curr.setReceiptStatus(ReceiptStatus.Processed);
			curr.setReceiptType(ReceiptType.Pickup);
			ArrayList<Receipt> list = new ArrayList<Receipt>();
			list.add(curr);
			return list;
		});
 		//similar to above
 		lenient().when(receiptDao.findReceiptByReceiptStatusAndReceiptType(ReceiptStatus.Processed, ReceiptType.Pickup)).thenAnswer((InvocationOnMock invocation) -> {
 			Receipt curr = new Receipt();
			curr.setCart(null);
			curr.setReceiptStatus(ReceiptStatus.Processed);
			curr.setReceiptType(ReceiptType.Pickup);
			ArrayList<Receipt> list = new ArrayList<Receipt>();
			list.add(curr);
			return list;
		});
 		//similar to above
 		lenient().when(receiptDao.findAll()).thenAnswer((InvocationOnMock invocation) -> {
 			Receipt curr = new Receipt();
			curr.setCart(null);
			curr.setReceiptStatus(ReceiptStatus.Processed);
			curr.setReceiptType(ReceiptType.Pickup);
			ArrayList<Receipt> list = new ArrayList<Receipt>();
			list.add(curr);
			return list;
		});
 			
 	}

 	@Test
 	public void testCreateReceipt() {
 		//testing creating receipt
 		//make a new account
 		Account account = new Account();
 		account.setUsername(USERNAME);
        account.setPassword(PASSWORD);
        account.setInTown(INTOWN);
        account.setTotalPoints(TOTALPOINTS);
        //set a person to the account
        account.setPerson(personService.createPerson(EMAIL, FIRSTNAME, LASTNAME, PHONENUMBER, ADDRESS));
        //make a new cart
        Cart cart = new Cart();
        cart.setDate(date);
        //make a new receipt
        Receipt curr = service.createReceipt(cart, ReceiptStatus.Processed, ReceiptType.Pickup);
        //assert that the cart is the same, and the receipt type and status are the same
        assertEquals(curr.getCart(), cart);
        assertEquals(ReceiptStatus.Processed,curr.getReceiptStatus());
 		assertEquals(ReceiptType.Pickup,curr.getReceiptType());
        
 		
 	}
 	
 	@Test
 	public void testUpdateReceipt() {
 		//similar to above
 		Account account = new Account();
 		account.setUsername(USERNAME);
        account.setPassword(PASSWORD);
        account.setInTown(INTOWN);
        account.setTotalPoints(TOTALPOINTS);
        account.setPerson(personService.createPerson(EMAIL, FIRSTNAME, LASTNAME, PHONENUMBER, ADDRESS));
        Cart cart = new Cart();
        cart.setDate(date);
        Receipt curr = service.updateReceipt(0, ReceiptStatus.Processed, ReceiptType.Pickup, cart);

        assertEquals(ReceiptStatus.Processed,curr.getReceiptStatus());
 		assertEquals(ReceiptType.Pickup,curr.getReceiptType());
        
 		
 	}
 	@Test
 	public void testGetReceiptNum() {
 		//create a new receipt
 		testCreateReceipt();
 		Receipt curr = service.getReceiptByReceiptNum(ID);
 		assertEquals(ReceiptStatus.Processed,curr.getReceiptStatus());
 		assertEquals(ReceiptType.Pickup,curr.getReceiptType());
 		
 	}
 	@Test
 	public void testGetReceiptInvalidNum() {
 		Receipt curr = null;
 		String error = "";
 		try {
 			curr = service.getReceiptByReceiptNum(-1);
 		} catch (IllegalArgumentException e) {
 			error = e.getMessage();
 		}
 		assertNull(curr);
 		assertEquals("No receipt with that id",error);
 	}
 	
 	@Test
 	public void testGetReceiptWithStatus() {
 		List<Receipt> receipts = new ArrayList<Receipt>();
 		//set the list to all the receipts with the status
 		receipts = service.getReceiptByReceiptStatus(ReceiptStatus.Processed);
		Receipt receipt = receipts.get(0);
		//assert that there is a receipt
		assertNotNull(receipt);
 	}
 	@Test
 	public void testGetReceiptWithBadStatus() {
 		List<Receipt> receipts = new ArrayList<Receipt>();
 		String error = "";
 		try {
 			//Unsuccessful since there are no receipts with that status
 			receipts = service.getReceiptByReceiptStatus(ReceiptStatus.Fullfilled);
 		}
 		catch (IllegalArgumentException e) {
 			error = e.getMessage();
 		}
 		assertEquals(0,receipts.size());
 		assertEquals("There are no receipts with that status",error);
 	}
 	
 	
 	@Test
 	public void testGetReceiptWithReceiptType() {
 		//get receipts that exist with pickup type
 		List<Receipt> receipts = new ArrayList<Receipt>();
 		receipts = service.getReceiptByReceiptType(ReceiptType.Pickup);
		Receipt receipt = receipts.get(0);
		assertNotNull(receipt);
 	}
 	@Test
 	public void testGetReceiptWithBadReceiptType() {
 		//get all receipts that with delivery type that no receipts have
 		List<Receipt> receipts = new ArrayList<Receipt>();
 		String error = "";
 		try {
 			receipts = service.getReceiptByReceiptType(ReceiptType.Delivery);
 		}
 		catch (IllegalArgumentException e) {
 			error = e.getMessage();
 		}
 		assertEquals(0,receipts.size());
 		assertEquals("There are no receipts with that type",error);
 	}
 	@Test
 	public void testGetReceiptWithReceiptStatusAndReceiptType() {
 		//get all receipts with processed status and pickup type
 		List<Receipt> receipts = new ArrayList<Receipt>();
 		receipts = service.getReceiptByReceiptStatusAndReceiptType(ReceiptStatus.Processed, ReceiptType.Pickup);
		Receipt receipt = receipts.get(0);
		assertNotNull(receipt);
 	}
 	@Test
 	public void testGetReceiptWithBadReceiptStatusAndReceiptType() {
 		//get all receipts with processed status and pickup type but none exist
 		List<Receipt> receipts = new ArrayList<Receipt>();
 		String error = "";
 		try {
 			receipts = service.getReceiptByReceiptStatusAndReceiptType(ReceiptStatus.Fullfilled, ReceiptType.Pickup);
 		}
 		catch (IllegalArgumentException e) {
 			error = e.getMessage();
 		}
 		assertEquals(0,receipts.size());
 		assertEquals("There are no receipts with that status and type",error);
 	}
 	@Test
 	public void testGetAllReceipts() {
 		//get all the existing receipts
 		List<Receipt> receipts = new ArrayList<Receipt>();
 		receipts = service.getAllReceipts();
		Receipt receipt = receipts.get(0);
		assertNotNull(receipt);
 	}
 	
 	
 	


 } 