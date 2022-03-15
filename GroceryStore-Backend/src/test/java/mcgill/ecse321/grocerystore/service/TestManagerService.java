package mcgill.ecse321.grocerystore.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import mcgill.ecse321.grocerystore.dao.ManagerRepository;
import mcgill.ecse321.grocerystore.dao.PersonRepository;
import mcgill.ecse321.grocerystore.model.Manager;
import mcgill.ecse321.grocerystore.model.Person;

@ExtendWith(MockitoExtension.class)
public class TestManagerService {
	
	@Mock
	private ManagerRepository managerDao;
	
	@Mock
	private PersonRepository personDao;

	@InjectMocks
	private ManagerService service;
	
	@InjectMocks
	private PersonService personService;
	
	
	@BeforeEach
	public void setMockOutput() {
		lenient().when(managerDao.findManagerById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(0)) {
				Person person = personService.createPerson("email@gmail.com", "Bob", 
						"The Builder", "111-222-3333", "123 street");
				Manager manager = new Manager();
				manager.setPerson(person);
				return manager;
			} else {
				return null;
			}
		});
		lenient().when(managerDao.findAll()).thenAnswer((InvocationOnMock invocation) -> {
			Person person = personService.createPerson("email@gmail.com", "Bob", 
					"The Builder", "111-222-3333", "123 street");
			Manager manager = new Manager();
			manager.setPerson(person);
			List<Manager> managers = new ArrayList<Manager>();
			managers.add(manager);
			return managers;
		});
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(managerDao.save(any(Manager.class))).thenAnswer(returnParameterAsAnswer);
	}
	
	@Test
	public void testCreateManager() {
		
		Manager manager = null;
		Person person = personService.createPerson("email@gmail.com", "Bob", "The Builder", "111-222-3333", "123 street");
		try {
			manager = service.createManager(person);
		} catch (IllegalArgumentException e) {
			fail();
		}
		int id = manager.getId();
		assertNotNull(manager);
		
		assertEquals(id,manager.getId());
	}
	
	@Test
	public void testGetManagerById() {
		Manager manager = null;
		try {
			manager = service.getManager(0);
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertNotNull(manager);
		assertEquals(0,manager.getId());
		
	}
	

	@Test
	public void testGetManagerByIdNegative() {
		Manager manager = null;
		String error = "";
		int id = -1;
		try {
			manager = service.getManager(id);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(manager);
		assertEquals(error,"The id cannot be a negative number");
	}
	
	@Test
	public void testGetManagerByIdNull() {
		Manager manager = null;
		String error = "";
		int id = 3;
		try {
			manager = service.getManager(id);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(manager);
		assertEquals(error,"No manager with id " + id + " exists");
	}
	
	
	
	
	@Test
	public void testDeleteManager() {
		Person person = personService.createPerson("email@gmail.com", "Bob", "The Builder", "111-222-3333", "123 street");
		Manager manager = service.createManager(person);
		assertTrue(service.deleteManager(manager));
	}
	
	@Test
    public void testDeleteManagerById() {
		boolean managerDeleted = false;
        try {
        	managerDeleted = service.deleteManagerById(0);
        } catch (IllegalArgumentException e) {
        	fail();
        }
        assertTrue(managerDeleted);
    }
	
	@Test
    public void testDeleteManagerByIdNegative() {
		boolean managerDeleted = true;
        try {
        	managerDeleted = service.deleteManagerById(-1);
        } catch (IllegalArgumentException e) {
        	fail();
        }
        assertFalse(managerDeleted);
    }
	
	@Test
    public void testDeleteManagerByIdNotExists() {
		boolean managerDeleted = true;
        try {
        	managerDeleted = service.deleteManagerById(4);
        } catch (IllegalArgumentException e) {
        	fail();
        }
        assertFalse(managerDeleted);
    }
	
	@Test
	public void testGetAllManagers() {
		List<Manager> managers = new ArrayList<Manager>();
		managers = service.getAllManagers();
		Manager manager = managers.get(0);
		assertNotNull(manager);
	}
	
}
