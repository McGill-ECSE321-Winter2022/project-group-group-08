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
//import static org.mockito.Mockito.lenient;
//
//import java.util.ArrayList;
//import java.util.List;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.invocation.InvocationOnMock;
//import org.mockito.stubbing.Answer;
//
//import mcgill.ecse321.grocerystore.dao.AccountRepository;
//import mcgill.ecse321.grocerystore.dao.PersonRepository;
//import mcgill.ecse321.grocerystore.dao.UserRoleRepository;
//import mcgill.ecse321.grocerystore.model.Person;
//
//@ExtendWith(MockitoExtension.class)
//public class TestPersonService {
//	
//	@Mock
//	private PersonRepository personDao;
//	@Mock
//	private AccountRepository accountDao;
//	@Mock
//	private UserRoleRepository userRoletDao;
//	
//	@InjectMocks
//	private PersonService personService;
//	@InjectMocks
//	private AccountService accountService;
//	@InjectMocks
//	private UserRoleService userRoleService;
//	
//
//	private static final String EMAIL = "abc@gmail.com";
//	private static final String FIRSTNAME = "Bob";
//	private static final String LASTNAME = "Smith";
//	private static final String PHONENUMBER = "1112223333";
//	private static final String ADDRESS = "845 Sherbrooke St W, Montreal, Quebec H3A 0G4";
//
//	@BeforeEach
//	public void setMockOutput() {
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
//	    lenient().when(personDao.findAll()).thenAnswer((InvocationOnMock invocation) -> {
//	    	 Person person = new Person();
//	         person.setEmail(EMAIL);
//	         person.setPhoneNumber(PHONENUMBER);
//	         person.setAddress(ADDRESS);
//	         person.setFirstName(FIRSTNAME);
//	         person.setLastName(LASTNAME);
//
//	        List<Person> persons = new ArrayList<Person>();
//	        persons.add(person);
//	        return persons;
//	      });
//	    lenient().when(personDao.findPersonByFirstNameContainingIgnoreCase(FIRSTNAME)).thenAnswer((InvocationOnMock invocation) -> {
//	    	 Person person = new Person();
//	         person.setEmail(EMAIL);
//	         person.setPhoneNumber(PHONENUMBER);
//	         person.setAddress(ADDRESS);
//	         person.setFirstName(FIRSTNAME);
//	         person.setLastName(LASTNAME);
//
//	        List<Person> persons = new ArrayList<Person>();
//	        persons.add(person);
//	        return persons;
//	      });
//	    lenient().when(personDao.findPersonByLastNameContainingIgnoreCase(LASTNAME)).thenAnswer((InvocationOnMock invocation) -> {
//	    	 Person person = new Person();
//	         person.setEmail(EMAIL);
//	         person.setPhoneNumber(PHONENUMBER);
//	         person.setAddress(ADDRESS);
//	         person.setFirstName(FIRSTNAME);
//	         person.setLastName(LASTNAME);
//
//	        List<Person> persons = new ArrayList<Person>();
//	        persons.add(person);
//	        return persons;
//	      });
//	    lenient().when(personDao.findPersonByAddressContainingIgnoreCase(anyString())).thenAnswer((InvocationOnMock invocation) -> {
//	    	 Person person = new Person();
//	         person.setEmail(EMAIL);
//	         person.setPhoneNumber(PHONENUMBER);
//	         person.setAddress(ADDRESS);
//	         person.setFirstName(FIRSTNAME);
//	         person.setLastName(LASTNAME);
//
//	        List<Person> persons = new ArrayList<Person>();
//	        persons.add(person);
//	        return persons;
//	      });
//	    lenient().when(personDao.existsById(anyString())).thenReturn(true);
//	    Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
//			return invocation.getArgument(0);
//		};
//		lenient().when(personDao.save(any(Person.class))).thenAnswer(returnParameterAsAnswer);
//	}
//	
//	@Test
//	public void testCreatePerson() {
//		Person person = null;
//		try {
//			person = personService.createPerson(EMAIL, FIRSTNAME, LASTNAME, PHONENUMBER, ADDRESS);
//		} catch (IllegalArgumentException e) {
//			// Check that no error occurred
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
//	public void testCreatePersonWithInvalidEmail() {
//		Person person = null;
//		String error = "";
//		try {
//			person = personService.createPerson(null, FIRSTNAME, LASTNAME, PHONENUMBER, ADDRESS);
//		} catch (Exception e) {
//			error = e.getMessage();
//		}
//		assertNull(person);
//		assertEquals("Person email cannot be empty!", error);
//	}
//	
//	@Test
//	public void testCreatePersonWithInvalidFirstName() {
//		Person person = null;
//		String error = "";
//		try {
//			person = personService.createPerson(EMAIL, null, LASTNAME, PHONENUMBER, ADDRESS);
//		} catch (Exception e) {
//			error = e.getMessage();
//		}
//		assertNull(person);
//		assertEquals("Person first name cannot be empty!", error);
//	}
//	
//	@Test
//	public void testCreatePersonWithInvalidLastName() {
//		Person person = null;
//		String error = "";
//		try {
//			person = personService.createPerson(EMAIL, FIRSTNAME, null, PHONENUMBER, ADDRESS);
//		} catch (Exception e) {
//			error = e.getMessage();
//		}
//		assertNull(person);
//		assertEquals("Person last name cannot be empty!", error);
//	}
//	
//	@Test
//	public void testCreatePersonWithInvalidPhoneNumber() {
//		Person person = null;
//		String error = "";
//		try {
//			person = personService.createPerson(EMAIL, FIRSTNAME, LASTNAME, "0987", ADDRESS);
//		} catch (Exception e) {
//			error = e.getMessage();
//		}
//		assertNull(person);
//		assertEquals("Person phone number is invalid!", error);
//	}	
//	
//	@Test
//	public void testCreatePersonWithInvalidAddress() {
//		Person person = null;
//		String error = "";
//		try {
//			person = personService.createPerson(EMAIL, FIRSTNAME, LASTNAME, PHONENUMBER, "");
//		} catch (Exception e) {
//			error = e.getMessage();
//		}
//		assertNull(person);
//		assertEquals("Person address cannot be empty!", error);
//	}
//	
//	@Test
//	public void testUpdatePerson() {
//		Person person = null;
//		try {
//			person = personService.updatePerson(EMAIL, FIRSTNAME, LASTNAME, PHONENUMBER, ADDRESS);
//		} catch (IllegalArgumentException e) {
//			// Check that no error occurred
//			fail();
//		}
//		assertNotNull(person);
//		assertEquals(FIRSTNAME, person.getFirstName());
//		assertEquals(LASTNAME, person.getLastName());
//		assertEquals(PHONENUMBER, person.getPhoneNumber());
//		assertEquals(ADDRESS, person.getAddress());	
//	}
//	
//	@Test
//	public void testUpdatePersonWithInvalidEmail() {
//		
//		Person person = null;
//		String error = "";
//		try {
//			person = personService.updatePerson(null,FIRSTNAME, LASTNAME, PHONENUMBER, ADDRESS);
//		} catch (Exception e) {
//			error = e.getMessage();
//		}
//		assertNull(person);
//		assertEquals("Person email cannot be empty!", error);
//	}
//	
//	@Test
//	public void testUpdatePersonWithInvalidFirstName() {
//		Person person = null;
//		String error = "";
//		try {
//			person = personService.updatePerson(EMAIL,"", LASTNAME, PHONENUMBER, ADDRESS);
//		} catch (Exception e) {
//			error = e.getMessage();
//		}
//		assertNull(person);
//		assertEquals("Person first name cannot be empty!", error);
//	}
//	
//	@Test
//	public void testUpdatePersonWithInvalidPhoneNumber() {
//		Person person = null;
//		String error = "";
//		try {
//			person = personService.updatePerson(EMAIL,FIRSTNAME, LASTNAME, "", ADDRESS);
//		} catch (Exception e) {
//			error = e.getMessage();
//		}
//		assertNull(person);
//		assertEquals("Person phone number is invalid!", error);
//	}
//	
//	@Test
//	public void testUpdatePersonWithInvalidAddress() {
//		Person person = null;
//		String error = "";
//		try {
//			person = personService.updatePerson(EMAIL,FIRSTNAME, LASTNAME, PHONENUMBER, null);
//		} catch (Exception e) {
//			error = e.getMessage();
//		}
//		assertNull(person);
//		assertEquals("Person address name cannot be empty!", error);
//	}
//	
//	@Test
//	public void testFindPersonByEmail() {
//		Person person = null;
//		try {
//			person = personService.findPersonByEmail(EMAIL);
//		}catch(IllegalArgumentException e){
//			fail();
//		}
//		assertNotNull(person);
//	}
//	
//	@Test
//	public void testFindPersonByEmptyEmail() {
//		String error = "";
//		Person person = null;
//		try {
//			person = personService.findPersonByEmail("");
//		}catch(InvalidInputException e){
//			error = e.getMessage();
//		}
//		assertNull(person);
//		assertEquals("Person email cannot be empty!",error);
//	}
//	
//	@Test
//	public void testfindPersonByFirstName() {
//		List<Person> persons = new ArrayList<Person>();
//		
//		try {
//			persons = personService.findPersonByFirstNameContainingIgnoreCase(FIRSTNAME);
//		}catch(IllegalArgumentException e){
//			fail();
//		}
//		Person person = persons.get(0);
//		assertNotNull(person);
//		assertEquals(FIRSTNAME, person.getFirstName());
//		assertEquals(LASTNAME, person.getLastName());
//		assertEquals(PHONENUMBER, person.getPhoneNumber());
//		assertEquals(ADDRESS, person.getAddress());		
//	}
//	
//	@Test
//	public void testfindPersonByLastName() {
//		List<Person> persons = new ArrayList<Person>();
//		
//		try {
//			persons = personService.findPersonByLastNameContainingIgnoreCase(LASTNAME);
//		}catch(IllegalArgumentException e){
//			fail();
//		}
//		Person person = persons.get(0);
//		assertNotNull(person);
//		assertEquals(FIRSTNAME, person.getFirstName());
//		assertEquals(LASTNAME, person.getLastName());
//		assertEquals(PHONENUMBER, person.getPhoneNumber());
//		assertEquals(ADDRESS, person.getAddress());				
//	}
//	
//	@Test
//	public void testfindPersonByAddress() {
//		List<Person> persons = new ArrayList<Person>();
//		
//		try {
//			persons = personService.findPersonByAddressContainingIgnoreCase("845");
//		}catch(IllegalArgumentException e){
//			fail();
//		}
//		Person person = persons.get(0);
//		assertNotNull(person);
//		assertEquals(FIRSTNAME, person.getFirstName());
//		assertEquals(LASTNAME, person.getLastName());
//		assertEquals(PHONENUMBER, person.getPhoneNumber());
//		assertEquals(ADDRESS, person.getAddress());				
//	}
//	
//	@Test
//	public void testDeletePerson() {
//		Person person = personService.findPersonByEmail(EMAIL);
//		try {
//			person = personService.deletePerson(person);
//		}catch(IllegalArgumentException e){
//			fail();
//		}
//		assertNotNull(person);
//	}
//	
//	@Test
//	public void testDeleteNullPerson() {
//		String error = "";
//		Person person = null;
//		try {
//			 person = personService.deletePerson(null);
//		}catch(InvalidInputException e){
//			error = e.getMessage();
//		}
//		assertNull(person);
//		assertEquals("Person does not exist.",error);
//		
//	}
//	
//	@Test
//	public void testDeletePersonByEmail() {
//		Person person = null;
//		try {
//			person = personService.deletePersonByEmail(EMAIL);
//		}catch(IllegalArgumentException e){
//			fail();
//		}
//		assertNotNull(person);
//		assertEquals(FIRSTNAME, person.getFirstName());
//		assertEquals(LASTNAME, person.getLastName());
//		assertEquals(PHONENUMBER, person.getPhoneNumber());
//		assertEquals(ADDRESS, person.getAddress());	
//	}
//	
//	@Test
//	public void testGetAllPerson() {
//		List<Person> persons = new ArrayList<Person>();
//		
//		try {
//			persons = personService.getAllPerson();
//		}catch(IllegalArgumentException e){
//			fail();
//		}
//		Person person = persons.get(0);
//		assertNotNull(person);
//		assertEquals(FIRSTNAME, person.getFirstName());
//		assertEquals(LASTNAME, person.getLastName());
//		assertEquals(PHONENUMBER, person.getPhoneNumber());
//		assertEquals(ADDRESS, person.getAddress());	
//	}
//}