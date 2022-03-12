package mcgill.ecse321.grocerystore.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import mcgill.ecse321.grocerystore.dao.ManagerRepository;
import mcgill.ecse321.grocerystore.model.Manager;

public class TestManagerService {
	
	@Mock
	private ManagerRepository managerDao;

	@InjectMocks
	private ManagerService service;
	
	private static final int ID = 12345;
	
	@BeforeEach
	public void setMockOutput() {
		lenient().when(managerDao.findManagerById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(ID)) {
				Manager manager = new Manager();
				manager.setId(ID);
				return manager;
			} else {
				return null;
			}
		});
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(managerDao.save(any(Manager.class))).thenAnswer(returnParameterAsAnswer);
	}
	
	@Test
	public void testCreateManager() {
		assertEquals(0, service.getAllManagers().size());
		
		Manager manager = null;
		try {
			manager = service.createManager(ID);
		} catch (IllegalArgumentException e) {
			fail();
		}
		int id = manager.getId();
		assertNotNull(manager);
		
		assertEquals(id,manager.getId());
	}
	
	@Test
	public void testCreateManagerIdNegative() {		
		int id = -1;
		Manager manager = null;
		String error = "";
		try {
			manager = service.createManager(id);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(manager);
		assertEquals(error,"Manager ID cannot be negative");
	}
	
	@Test
	public void testGetManagerById() {
		Manager manager = null;
		try {
			manager = service.getManager(ID);
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertNotNull(manager);
		assertEquals(ID,manager.getId());
		
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
	
}
