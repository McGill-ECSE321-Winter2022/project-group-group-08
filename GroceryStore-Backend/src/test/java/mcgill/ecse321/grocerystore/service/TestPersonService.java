package mcgill.ecse321.grocerystore.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

import mcgill.ecse321.grocerystore.dao.PersonRepository;
import mcgill.ecse321.grocerystore.model.Person;

@ExtendWith(MockitoExtension.class)
public class TestPersonService {
	
	@Mock
	private PersonRepository personDao;
	
	@InjectMocks
	private PersonService personService;
	

	private static final String EMAIL = "abc@gmail.com";
	private static final String NEWEMAIL = "def@gmail.com";
	private static final String FIRSTNAME = "Bob";
	private static final String LASTNAME = "Smith";
	private static final String PHONENUMBER = "1112223333";
	private static final String ADDRESS = "845 Sherbrooke St W, Montreal, Quebec H3A 0G4";

	@BeforeEach
	public void setMockOutput() {
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
		lenient().when(personDao.save(any(Person.class))).thenAnswer(returnParameterAsAnswer);
	}
	
	@Test
	public void testCreatePerson() {
		assertEquals(0, personService.getAllPerson().size());
		Person person = null;
		try {
			person = personService.createPerson(EMAIL, FIRSTNAME, LASTNAME, PHONENUMBER, ADDRESS);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}
		assertNotNull(person);
		assertEquals(EMAIL, person.getEmail());
		assertEquals(FIRSTNAME, person.getFirstName());
		assertEquals(LASTNAME, person.getLastName());
		assertEquals(PHONENUMBER, person.getPhoneNumber());
		assertEquals(ADDRESS, person.getAddress());	
	}
	
	@Test
	public void testCreatePersonWithInvalidEmail() {
		assertEquals(0, personService.getAllPerson().size());
		Person person = null;
		String error = "";
		try {
			person = personService.createPerson(null, FIRSTNAME, LASTNAME, PHONENUMBER, ADDRESS);
		} catch (Exception e) {
			error = e.getMessage();
		}
		assertNull(person);
		assertEquals("Person email cannot be empty!", error);
	}
	
	@Test
	public void testCreatePersonWithInvalidLastName() {
		assertEquals(0, personService.getAllPerson().size());
		Person person = null;
		String error = "";
		try {
			person = personService.createPerson(EMAIL, FIRSTNAME, null, PHONENUMBER, ADDRESS);
		} catch (Exception e) {
			error = e.getMessage();
		}
		assertNull(person);
		assertEquals("Person last name cannot be empty!", error);
	}
	
	@Test
	public void testUpdatePerson() {
		assertEquals(0, personService.getAllPerson().size());
		Person person = null;
		try {
			person = personService.updatePerson(EMAIL, NEWEMAIL, FIRSTNAME, LASTNAME, PHONENUMBER, ADDRESS);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}
		assertNotNull(person);
		assertEquals(NEWEMAIL, person.getEmail());
		assertEquals(FIRSTNAME, person.getFirstName());
		assertEquals(LASTNAME, person.getLastName());
		assertEquals(PHONENUMBER, person.getPhoneNumber());
		assertEquals(ADDRESS, person.getAddress());	
	}
	
	@Test
	public void testUpdatePersonWithNewInvalidNewEmail() {
		assertEquals(0, personService.getAllPerson().size());
		Person person = null;
		String error = "";
		try {
			person = personService.updatePerson(EMAIL, null, FIRSTNAME, LASTNAME, PHONENUMBER, ADDRESS);
		} catch (Exception e) {
			error = e.getMessage();
		}
		assertNull(person);
		assertEquals("Person new email cannot be empty!", error);
	}
	
	@Test
	public void testUpdatePersonWithInvalidAddress() {
		assertEquals(0, personService.getAllPerson().size());
		Person person = null;
		String error = "";
		try {
			person = personService.updatePerson(EMAIL, NEWEMAIL, FIRSTNAME, LASTNAME, PHONENUMBER, null);
		} catch (Exception e) {
			error = e.getMessage();
		}
		assertNull(person);
		assertEquals("Person address name cannot be empty!", error);
	}
	
	@Test
	public void testFindPersonByEmail() {
		assertEquals(0, personService.getAllPerson().size());
		Person person = null;
		try {
			person = personService.findPersonByEmail(EMAIL);
		}catch(IllegalArgumentException e){
			fail();
		}
		assertNotNull(person);
	}
	
	@Test
	public void testDeletePerson() {
		assertEquals(0, personService.getAllPerson().size());
		boolean delete = false;
		Person person = personService.findPersonByEmail(EMAIL);
		try {
			delete = personService.deletePerson(person);
		}catch(IllegalArgumentException e){
			fail();
		}
		assertTrue(delete);
	}
	
	@Test
	public void testDeletePersonByEmail() {
		assertEquals(0, personService.getAllPerson().size());
		boolean delete = false;
		try {
			delete = personService.deletePersonByEmail(EMAIL);
		}catch(IllegalArgumentException e){
			fail();
		}
		assertTrue(delete);
	}
}