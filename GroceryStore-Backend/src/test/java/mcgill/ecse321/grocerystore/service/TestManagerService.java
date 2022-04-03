// package mcgill.ecse321.grocerystore.service;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertNotNull;
// import static org.junit.jupiter.api.Assertions.assertNull;
// import static org.junit.jupiter.api.Assertions.assertTrue;
// import static org.junit.jupiter.api.Assertions.fail;
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.ArgumentMatchers.anyInt;
// import static org.mockito.ArgumentMatchers.anyString;
// import static org.mockito.Mockito.lenient;

// import java.util.ArrayList;
// import java.util.List;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.invocation.InvocationOnMock;
// import org.mockito.junit.jupiter.MockitoExtension;
// import org.mockito.stubbing.Answer;

// import mcgill.ecse321.grocerystore.dao.ManagerRepository;
// import mcgill.ecse321.grocerystore.dao.PersonRepository;
// import mcgill.ecse321.grocerystore.dao.UserRoleRepository;
// import mcgill.ecse321.grocerystore.model.Manager;
// import mcgill.ecse321.grocerystore.model.Person;

// @ExtendWith(MockitoExtension.class)
// public class TestManagerService {
	
// 	@Mock
// 	private ManagerRepository managerDao;
// 	@Mock
// 	private PersonRepository personDao;
// 	@Mock
// 	private UserRoleRepository userRoleDao;

// 	@InjectMocks
// 	private ManagerService service;
	
// 	@InjectMocks
// 	private PersonService personService;
	
	
// 	@BeforeEach
// 	public void setMockOutput() {
// 		lenient().when(managerDao.findManagerById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
// 			if (invocation.getArgument(0).equals(0)) {
// 				Manager manager = new Manager();
// 				return manager;
// 			} else {
// 				return null;
// 			}
// 		});
// 		lenient().when(managerDao.findAll()).thenAnswer((InvocationOnMock invocation) -> {
// 			Manager manager = new Manager();
// 			List<Manager> managers = new ArrayList<Manager>();
// 			managers.add(manager);
// 			return managers;
// 		});
// 		lenient().when(managerDao.existsById(anyInt())).thenReturn(true);
// 		lenient().when(personDao.existsById(anyString())).thenReturn(true);
// 		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
// 			return invocation.getArgument(0);
// 		};
// 		lenient().when(managerDao.save(any(Manager.class))).thenAnswer(returnParameterAsAnswer);
// 		lenient().when(personDao.save(any(Person.class))).thenAnswer(returnParameterAsAnswer);
// 	}
	
// 	@Test
// 	public void testCreateManager() {
// 		Person person = personService.createPerson("email@gmail.com", "Bob", 
// 				"The Builder", "111-222-3333", "123 street");
// 		Manager manager = null;
// 		try {
// 			manager = service.createManager(person);
// 		} catch (IllegalArgumentException e) {
// 			fail();
// 		}
// 		int id = manager.getId();
// 		assertNotNull(manager);
		
// 		assertEquals(id,manager.getId());
// 	}
	
// 	@Test
// 	public void testGetManagerById() {
// 		Manager manager = null;
// 		try {
// 			manager = service.getManager(0);
// 		} catch (IllegalArgumentException e) {
// 			fail();
// 		}
// 		assertNotNull(manager);
// 		assertEquals(0,manager.getId());
		
// 	}
	

// 	@Test
// 	public void testGetManagerByIdNegative() {
// 		Manager manager = null;
// 		String error = "";
// 		int id = -1;
// 		try {
// 			manager = service.getManager(id);
// 		} catch (IllegalArgumentException e) {
// 			error = e.getMessage();
// 		}
// 		assertNull(manager);
// 		assertEquals(error,"The id cannot be a negative number");
// 	}
	
// 	@Test
// 	public void testGetManagerByIdNull() {
// 		Manager manager = null;
// 		String error = "";
// 		int id = 3;
// 		try {
// 			manager = service.getManager(id);
// 		} catch (IllegalArgumentException e) {
// 			error = e.getMessage();
// 		}
// 		assertNull(manager);
// 		assertEquals(error,"No manager with id " + id + " exists");
// 	}
	
	
// 	@Test
// 	public void testUpdateManager() {
// 		Person person = personService.createPerson("email@gmail.com", "Bob", 
// 				"The Builder", "111-222-3333", "123 street");
// 		Manager manager = null;
// 		try {
// 			manager = service.updateManager(0, person);
// 		} catch (IllegalArgumentException e) {
// 			fail();
// 		}
// 		assertNotNull(manager);
// 		assertEquals(0,manager.getId());
// 	}
	
// 	@Test
// 	public void testUpdateManagerByIdNegative() {
// 		Person person = personService.createPerson("email@gmail.com", "Bob", 
// 				"The Builder", "111-222-3333", "123 street");
// 		Manager manager = null;
// 		String error = "";
// 		int id = -1;
// 		try {
// 			manager = service.updateManager(id, person);
// 		} catch (IllegalArgumentException e) {
// 			error = e.getMessage();
// 		}
// 		assertNull(manager);
// 		assertEquals(error,"The id cannot be a negative number");
// 	}
	
	
// 	@Test
// 	public void testUpdateManagerByPersonNull() {
// 		Person person = null;
// 		Manager manager = null;
// 		String error = "";
// 		int id = 0;
// 		try {
// 			manager = service.updateManager(id, person);
// 		} catch (IllegalArgumentException e) {
// 			  error=e.getMessage();
// 		}
// 		assertNull(manager);
// 		assertEquals(error,"The person cannot be null");
// 	}
	
	
// 	@Test
// 	public void testDeleteManager() {
// 		Person person = personService.createPerson("email@gmail.com", "Bob", "The Builder", "111-222-3333", "123 street");
// 		Manager manager = service.createManager(person);
// 		boolean delete = false;
// 		try {
// 			delete = service.deleteManager(manager);
// 		}catch(IllegalArgumentException e){
// 			fail();
// 		}
// 		assertTrue(delete);
// 	}
	
// 	@Test
//     public void testDeleteManagerById() {
// 		Manager managerDeleted = null;
//         try {
//         	managerDeleted = service.deleteManagerById(0);
//         } catch (IllegalArgumentException e) {
//         	fail();
//         }
//         assertNotNull(managerDeleted);
//     }
	
// 	@Test
//     public void testDeleteManagerByIdNegative() {
// 		Manager managerDeleted = null;
// 		String error = null;
//         try {
//         	managerDeleted = service.deleteManagerById(-1);
//         } catch (IllegalArgumentException e) {
//         	error = e.getMessage();
//         }
//         assertNull(managerDeleted);
//         assertEquals(error, "Negative Id");
//     }
	
// 	@Test
//     public void testDeleteManagerByIdNotExists() {
// 		Manager managerDeleted = null;
// 		String error = null;

//         try {
//         	managerDeleted = service.deleteManagerById(4);
//         } catch (IllegalArgumentException e) {
//         	error = e.getMessage();
//         }
//         assertNull(managerDeleted);
//         assertEquals(error, "No manager with that id");
//     }
	
// 	@Test
// 	public void testGetAllManagers() {
// 		List<Manager> managers = new ArrayList<Manager>();
// 		managers = service.getAllManagers();
// 		Manager manager = managers.get(0);
// 		assertNotNull(manager);
// 	}
	
// }
