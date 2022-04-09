//package mcgill.ecse321.grocerystore.service;
//
//import static org.mockito.Mockito.lenient;
//import java.sql.Time;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyInt;
//import static org.mockito.ArgumentMatchers.anyString;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//
//import mcgill.ecse321.grocerystore.dao.BusinessHourRepository;
//import mcgill.ecse321.grocerystore.dao.EmployeeRepository;
//import mcgill.ecse321.grocerystore.dao.GroceryStoreSystemRepository;
//import mcgill.ecse321.grocerystore.dao.PersonRepository;
//import mcgill.ecse321.grocerystore.dao.UserRoleRepository;
//import mcgill.ecse321.grocerystore.model.BusinessHour;
//import mcgill.ecse321.grocerystore.model.Employee;
//import mcgill.ecse321.grocerystore.model.GroceryStoreSystem;
//import mcgill.ecse321.grocerystore.model.Person;
//import mcgill.ecse321.grocerystore.model.BusinessHour.WeekDay;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.invocation.InvocationOnMock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.mockito.stubbing.Answer;
//
//@ExtendWith(MockitoExtension.class)
//public class TestBusinessHourService {
//
// @Mock
// private BusinessHourRepository businessDao;
// @Mock
// private GroceryStoreSystemRepository groceryStoreSystemDao;
// @Mock
// private EmployeeRepository employeeDao;
// @Mock
// private PersonRepository personDao;
// @Mock
// private UserRoleRepository userRoleDao;
//
// @InjectMocks
// private BusinessHourService service;
// @InjectMocks
// private GroceryStoreSystemService groceryService;
// @InjectMocks
// private EmployeeService employeeService;
// @InjectMocks
// private PersonService personService;
// @InjectMocks
// private UserRoleService userRoleService;
// 
// private static final int ID=0;
// private static final WeekDay day=WeekDay.Tuesday;
// private static final Time startTime = Time.valueOf("18:30:00");
// private static final Time endTime = Time.valueOf("18:31:00");
// private static final boolean working = true;
// 
// @BeforeEach
// public void setMockOutput() {
//	 GroceryStoreSystem groceryStoreSystem= groceryService.createGroceryStoreSystem("a","a", 1);
//	 Person person = personService.createPerson("email@gmail.com", "Bob", "The Builder", "111-222-3333", "123 street");
//	 Employee employee= new Employee();
//	 employee.setPerson(person);
//	 
//  lenient().when(businessDao.findBusinessHourById(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
//    if(invocation.getArgument(0).equals(ID)) {
//        BusinessHour businessHour = new BusinessHour();
//        businessHour.setDay(day);
//        businessHour.setWorking(working);
//        businessHour.setStartTime(startTime);
//        businessHour.setEndTime(endTime);
//        businessHour.setGroceryStoreSystem(groceryStoreSystem);
//        businessHour.setEmployee(employee);
//        return businessHour;
//    } 
//    else {
//        return null;
//    }
//  });
//
//  lenient().when(businessDao.findAll()).thenAnswer( (InvocationOnMock invocation) -> {
//	  	BusinessHour businessHour = new BusinessHour();
//        employee.setPerson(person);
//        businessHour.setEmployee(employee);
//        businessHour.setDay(day);
//        businessHour.setWorking(working);
//        businessHour.setStartTime(startTime);
//        businessHour.setEndTime(endTime);
//        businessHour.setGroceryStoreSystem(groceryService.createGroceryStoreSystem("a","a", 1));
//        ArrayList<BusinessHour> list = new ArrayList<BusinessHour>();
//        list.add(businessHour);
//        return list;
//  });
//  
//  lenient().when(businessDao.findBusinessHourByStartTimeBetween(startTime,endTime)).thenAnswer( (InvocationOnMock invocation) -> {
//	    BusinessHour businessHour = new BusinessHour();
//	    businessHour.setDay(day);
//	    businessHour.setWorking(working);
//	    businessHour.setStartTime(startTime);
//	    businessHour.setEndTime(endTime);
//	    businessHour.setId(ID);
//	    businessHour.setGroceryStoreSystem(groceryStoreSystem);
//	    businessHour.setEmployee(employee);
//	    ArrayList<BusinessHour> list=new ArrayList<BusinessHour>();
//	    list.add(businessHour);
//	    return list;
//  });
//  
//  lenient().when(businessDao.findBusinessHourByDayOrderByIdAsc(day)).thenAnswer( (InvocationOnMock invocation) -> {
//	    BusinessHour businessHour = new BusinessHour();
//	    businessHour.setDay(day);
//	    businessHour.setWorking(working);
//	    businessHour.setStartTime(startTime);
//	    businessHour.setEndTime(endTime);
//	    businessHour.setId(ID);
//	    businessHour.setGroceryStoreSystem(groceryStoreSystem);
//	    businessHour.setEmployee(employee);
//	    ArrayList<BusinessHour> list=new ArrayList<BusinessHour>();
//	    list.add(businessHour);
//	    return list;
//  });
//  
//  lenient().when(businessDao.findBusinessHourByWorking(working)).thenAnswer( (InvocationOnMock invocation) -> {
//	    BusinessHour businessHour = new BusinessHour();
//	    businessHour.setDay(day);
//	    businessHour.setWorking(working);
//	    businessHour.setStartTime(startTime);
//	    businessHour.setEndTime(endTime);
//	    businessHour.setId(ID);
//	    businessHour.setGroceryStoreSystem(groceryStoreSystem);
//	    businessHour.setEmployee(employee);
//	    ArrayList<BusinessHour> list=new ArrayList<BusinessHour>();
//	    list.add(businessHour);
//	    return list;
//  });
//  
//  
//  lenient().when(businessDao.existsById(anyInt())).thenReturn(true);
//  lenient().when(groceryStoreSystemDao.existsById(any())).thenReturn(true);
//  lenient().when(employeeDao.existsById(anyInt())).thenReturn(true);
//  lenient().when(personDao.existsById(anyString())).thenReturn(true);
//  Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
//    return invocation.getArgument(0);
//  };
//  lenient().when(businessDao.save(any(BusinessHour.class))).thenAnswer(returnParameterAsAnswer);
//  lenient().when(groceryStoreSystemDao.save(any(GroceryStoreSystem.class))).thenAnswer(returnParameterAsAnswer);
//  lenient().when(employeeDao.save(any(Employee.class))).thenAnswer(returnParameterAsAnswer);
//  lenient().when(personDao.save(any(Person.class))).thenAnswer(returnParameterAsAnswer);
// }
//
//    @Test
//    public void testGetAllBusinessHours() {
//        List<BusinessHour> businessHours= new ArrayList<BusinessHour>();
//        businessHours=service.getAllBusinessHours();
//        BusinessHour businesshour=businessHours.get(0);
//        assertNotNull(businesshour);
//    }
//    
//    @Test
//    public void testGetBusinessHourbyID() {
//        BusinessHour businessHour = null;
//        try {
//            businessHour = service.getBusinessHoursbyID(ID);
//        } catch (IllegalArgumentException e) {
//            fail();
//        }
//        assertNotNull(businessHour);
//        assertEquals(ID,businessHour.getId());
//        assertEquals(day,businessHour.getDay());
//        assertEquals(startTime,businessHour.getStartTime());
//        assertEquals(endTime,businessHour.getEndTime());
//        assertEquals(working,businessHour.getWorking());
//    }
//
//    @Test
//    public void testGetBusinessHourbyIDNegative() {
//        BusinessHour businessHour = null;
//        String error="";
//        int id=-1;
//        try {
//            businessHour = service.getBusinessHoursbyID(id);
//        } catch (IllegalArgumentException e) {
//            error=e.getMessage();
//        }
//        assertEquals(businessHour,null);
//        assertEquals(error,"Id cannot be negative");
//    }
//
//    @Test
//    public void testGetBusinessHourbyDay() {
//        List<BusinessHour> businessHour = new ArrayList<BusinessHour>();
//        try{
//        	businessHour=service.getBusinessHoursbyDay(day);
//        }catch(IllegalArgumentException e){
//        	fail();
//        }
//        BusinessHour businesshour=businessHour.get(0);
//        assertNotNull(businesshour);
//        assertEquals(ID,businesshour.getId());
//        assertEquals(day,businesshour.getDay());
//        assertEquals(startTime,businesshour.getStartTime());
//        assertEquals(endTime,businesshour.getEndTime());
//        assertEquals(working,businesshour.getWorking());
//    }
//
//     @Test
//     public void testGetBusinessHourbyEmployee() {
//    	 Person person = personService.createPerson("email@gmail.com", "Bob", "The Builder", "111-222-3333", "123 street");
//         Employee employee= new Employee();
//         employee.setPerson(person);
//         List<BusinessHour> businessHours = new ArrayList<BusinessHour>();
//    	 lenient().when(businessDao.findBusinessHoursByEmployeeOrderByIdAsc(employee)).thenAnswer( (InvocationOnMock invocation) -> {
//    		  	BusinessHour businessHour = new BusinessHour();
//    		    businessHour.setDay(day);
//    		    businessHour.setWorking(working);
//    		    businessHour.setStartTime(startTime);
//    		    businessHour.setEndTime(endTime);
//    		    businessHour.setId(ID);
//    		    businessHour.setEmployee(employee);
//    		    ArrayList<BusinessHour> list=new ArrayList<BusinessHour>();
//    		    list.add(businessHour);
//    		    return list;
//    	 });
//         try {
//        	 businessHours = service.getBusinessHoursbyEmployee(employee);
//         }catch(IllegalArgumentException e) {
//        	 fail();
//         }
//         BusinessHour businesshour = businessHours.get(0);
//         assertNotNull(businesshour);
//         assertEquals(ID,businesshour.getId());
//         assertEquals(day,businesshour.getDay());
//         assertEquals(startTime,businesshour.getStartTime());
//         assertEquals(endTime,businesshour.getEndTime());
//         assertEquals(working,businesshour.getWorking());
//     }
//
//     @Test
//     public void testGetBusinessHourbyGroceryStoreSystem() {
//    	 GroceryStoreSystem groceryStoreSystem= groceryService.createGroceryStoreSystem("a","a", 1);
//         List<BusinessHour> businessHours = new ArrayList<BusinessHour>();
//         lenient().when(businessDao.findBusinessHoursByGroceryStoreSystem(groceryStoreSystem)).thenAnswer( (InvocationOnMock invocation) -> {
//       	  BusinessHour businessHour = new BusinessHour();
//       	    businessHour.setDay(day);
//       	    businessHour.setWorking(working);
//       	    businessHour.setStartTime(startTime);
//       	    businessHour.setEndTime(endTime);
//       	    businessHour.setId(ID);
//       	    businessHour.setGroceryStoreSystem(groceryStoreSystem);
//       	    ArrayList<BusinessHour> list=new ArrayList<BusinessHour>();
//       	    list.add(businessHour);
//       	    return list;
//         });
//         try {
//        	 businessHours=service.getOpeningHours(groceryStoreSystem);
//         }catch(IllegalArgumentException e) {
//        	 fail();
//         }
//         BusinessHour businesshour=businessHours.get(0);
//         assertNotNull(businesshour);
//         assertEquals(ID,businesshour.getId());
//         assertEquals(day,businesshour.getDay());
//         assertEquals(startTime,businesshour.getStartTime());
//         assertEquals(endTime,businesshour.getEndTime());
//         assertEquals(working,businesshour.getWorking());
//     }
//
//    @Test
//    public void testGetBusinessHourbyWorking() {
//    	List<BusinessHour> businessHour = new ArrayList<BusinessHour>();
//        try {
//        	businessHour=service.getBusinessHoursbyWorking(working);
//        }catch(IllegalArgumentException e) {
//       	 	fail();
//        }
//        BusinessHour businesshour=businessHour.get(0);
//        assertNotNull(businesshour);
//        assertEquals(ID,businesshour.getId());
//        assertEquals(day,businesshour.getDay());
//        assertEquals(startTime,businesshour.getStartTime());
//        assertEquals(endTime,businesshour.getEndTime());
//        assertEquals(working,businesshour.getWorking());
//    }
//
//    @Test
//    public void testGetBusinessHourbyTime() {
//        List<BusinessHour> businessHour = new ArrayList<BusinessHour>();
//        try {
//        	businessHour=service.getBusinessHoursbyStartTimebetween(startTime,endTime);
//        }catch(IllegalArgumentException e) {
//       	 	fail();
//        }
//        BusinessHour businesshour=businessHour.get(0);
//        assertNotNull(businesshour);
//        assertEquals(ID,businesshour.getId());
//        assertEquals(day,businesshour.getDay());
//        assertEquals(startTime,businesshour.getStartTime());
//        assertEquals(endTime,businesshour.getEndTime());
//        assertEquals(working,businesshour.getWorking());
//    }
//
//    @Test
//    public void testGetBusinessHourbystartTimeNull() {
//        List<BusinessHour> businessHour = new ArrayList<BusinessHour>();
//        String error="";
//        Time startTime=null;
//        Time endTime=Time.valueOf("23:45:21");
//        try {
//            businessHour = service.getBusinessHoursbyStartTimebetween(startTime,endTime);
//        } catch (IllegalArgumentException e) {
//            error=e.getMessage();
//        }
//        assertEquals(businessHour.size(),0);
//        assertEquals(error,"Start time is Empty");
//    }
//
//    @Test
//    public void testGetBusinessHourbyendTimeNull() {
//        List<BusinessHour> businessHour = new ArrayList<BusinessHour>();
//        String error="";
//        Time startTime=Time.valueOf("23:45:21");
//        Time endTime=null;
//        try {
//            businessHour = service.getBusinessHoursbyStartTimebetween(startTime,endTime);
//        } catch (IllegalArgumentException e) {
//            error=e.getMessage();
//        }
//        assertEquals(businessHour.size(),0);
//        assertEquals(error,"End time is Empty");
//    }
//
//     @Test
//     public void testCreateBusinessHourforEmployee() {
//         BusinessHour businessHour = null;
//         Person person = personService.createPerson("email@gmail.com", "Bob", "The Builder", "111-222-3333", "123 street");
//         Employee employee= new Employee();
//         employee.setPerson(person);
//         try {
//        	 businessHour = service.createBusinessHourforEmployee(day, startTime, endTime, working, employee);
//         } catch (IllegalArgumentException e) {
//             fail();
//         }
//         assertNotNull(businessHour);
//         assertEquals(day,businessHour.getDay());
//         assertEquals(startTime,businessHour.getStartTime());
//         assertEquals(endTime,businessHour.getEndTime());
//         assertEquals(working,businessHour.getWorking());
//         assertEquals(employee,businessHour.getEmployee());
//     }
//
// 
//     @Test
//     public void testCreatBusinessHourforEmployeeDayNull() {  
//    	 BusinessHour businessHour = null;
//         Person person = personService.createPerson("email@gmail.com", "Bob", "The Builder", "111-222-3333", "123 street");
//         Employee employee= new Employee();
//         employee.setPerson(person);
//         WeekDay day = null;
//         Time startTime = Time.valueOf("18:45:20");
//         Time endTime = Time.valueOf("18:45:20");
//         boolean working = true;
//         String error = "";
//         try {
//             businessHour = service.createBusinessHourforEmployee(day, startTime, endTime, working, employee);
//         } catch (IllegalArgumentException e) {
//             error = e.getMessage();
//         }
//         assertNull(businessHour);
//         assertEquals(error,"Week day cannot be empty");
//     }
//    
//     @Test
//     public void testCreatBusinessHourforEmployeeemployeeNull() {  
//    	 Employee employee= null;
//    	 BusinessHour businessHour = null;
//         String error = "";
//         try {
//             businessHour = service.createBusinessHourforEmployee(day, startTime, endTime, working, employee);
//         } catch (IllegalArgumentException e) {
//             error = e.getMessage();
//         }
//         assertNull(businessHour);
//         assertEquals(error,"Employee cannot be empty");
//     }
//     
//     @Test
//     public void testCreateBusinessHourforEmployeestartTimeNull() {  
//    	 BusinessHour businessHour = null;
//         Person person = personService.createPerson("email@gmail.com", "Bob", "The Builder", "111-222-3333", "123 street");
//         Employee employee= new Employee();
//         employee.setPerson(person);
//         WeekDay day = WeekDay.Monday;
//         Time startTime = null;
//         Time endTime = Time.valueOf("18:45:20");
//         boolean working = true;
//         String error = "";
//         try {
//             businessHour = service.createBusinessHourforEmployee(day, startTime, endTime, working, employee);
//         } catch (IllegalArgumentException e) {
//             error = e.getMessage();
//         }
//         assertNull(businessHour);
//         assertEquals(error,"Start time cannot be empty");
//     }
// 
//     @Test
//     public void testCreateBusinessHourforEmployeeendTimeNull() {  
//    	 BusinessHour businessHour = null;
//         Person person = personService.createPerson("email@gmail.com", "Bob", "The Builder", "111-222-3333", "123 street");
//         Employee employee= new Employee();
//         employee.setPerson(person);
//         WeekDay day = WeekDay.Monday;
//         Time startTime = Time.valueOf("18:45:20");
//         Time endTime = null;
//         boolean working = true;
//         String error = "";
//         try {
//             businessHour = service.createBusinessHourforEmployee(day, startTime, endTime, working, employee);
//         } catch (IllegalArgumentException e) {
//             error = e.getMessage();
//         }
//         assertNull(businessHour);
//         assertEquals(error,"End time cannot be empty");
//     }
//
//     @Test
//     public void testCreateBusinessHourForEmployeeWithBadOrderTimes() {  
//    	 BusinessHour businessHour = null;
//         Person person = personService.createPerson("email@gmail.com", "Bob", "The Builder", "111-222-3333", "123 street");
//         Employee employee= new Employee();
//         employee.setPerson(person);
//         WeekDay day = WeekDay.Monday;
//         Time startTime = Time.valueOf("18:45:21");
//         Time endTime = Time.valueOf("18:45:20");
//         boolean working = true;
//         String error = "";
//         try {
//             businessHour = service.createBusinessHourforEmployee(day, startTime, endTime, working, employee);
//         } catch (IllegalArgumentException e) {
//             error = e.getMessage();
//         }
//         assertNull(businessHour);
//         assertEquals(error,"End time cannot be earlier than Start time");
//     }
//
//     @Test
//     public void testCreateBusinessHourforEmployeeWithSameTime() {  
//    	 BusinessHour businessHour = null;
//         Person person = personService.createPerson("email@gmail.com", "Bob", "The Builder", "111-222-3333", "123 street");
//         Employee employee= new Employee();
//         employee.setPerson(person);
//         WeekDay day = WeekDay.Monday;
//         Time startTime = Time.valueOf("18:45:20");
//         Time endTime = Time.valueOf("18:45:20");
//         boolean working = true;
//         String error = "";
//         try {
//             businessHour = service.createBusinessHourforEmployee(day, startTime, endTime, working, employee);
//         } catch (IllegalArgumentException e) {
//             error = e.getMessage();
//         }
//         assertNull(businessHour);
//         assertEquals(error,"End time cannot be the same as Start time");
//     } 
//
//     @Test
//	 public void createBusinessHourforEmployeeGood() {
//        Person person = personService.createPerson("email@gmail.com", "Bob", "The Builder", "111-222-3333", "123 street");
//        Employee employee= new Employee();
//        employee.setPerson(person);
//		BusinessHour curr = service.createBusinessHourforEmployee(WeekDay.Monday, Time.valueOf("18:45:20"),Time.valueOf("18:45:21"), true, employee);
//		
//	 	assertEquals(curr.getDay(), WeekDay.Monday);
//	 	assertEquals(curr.getStartTime(),Time.valueOf("18:45:20"));
//	 	assertEquals(curr.getEndTime(),Time.valueOf("18:45:21"));
//        assertEquals(curr.getEmployee(),employee);
//	  }
//
//     //GroceryStoreSystem
//    @Test
//    public void testCreateBusinessHourforGroceryStoreSystem() {
//        BusinessHour businessHour = null;
//        GroceryStoreSystem groceryStoreSystem = groceryService.createGroceryStoreSystem("a","a", 1);
//        try {
//            businessHour = service.createBusinessHourforGroceryStoreSystem(day, startTime, endTime, working,groceryStoreSystem);
//        } catch (IllegalArgumentException e) {
//        	fail();
//        }
//        assertNotNull(businessHour);
//        assertEquals(day,businessHour.getDay());
//        assertEquals(startTime,businessHour.getStartTime());
//        assertEquals(endTime,businessHour.getEndTime());
//        assertEquals(working,businessHour.getWorking());
//        assertEquals(groceryStoreSystem,businessHour.getGroceryStoreSystem());
//    }
// 
//    @Test
//    public void testCreatBusinessHourforGroceryStoreSystemDayNull() {  
//        WeekDay day = null;
//        Time startTime = Time.valueOf("18:45:20");
//        Time endTime = Time.valueOf("18:45:20");
//        boolean working = true;
//        BusinessHour businessHour=null;
//        GroceryStoreSystem groceryStoreSystem = groceryService.createGroceryStoreSystem("a","a", 1);
//        String error = "";
//        try {
//            businessHour = service.createBusinessHourforGroceryStoreSystem(day, startTime, endTime, working, groceryStoreSystem);
//        } catch (IllegalArgumentException e) {
//            error = e.getMessage();
//        }
//        assertNull(businessHour);
//        assertEquals(error,"Week day cannot be empty");
//    }
//    
//    @Test
//    public void testCreateBusinessHourforGroceryStoreSystemstartTimeNull() {  
//        WeekDay day = WeekDay.Monday;
//        Time startTime = null;
//        Time endTime = Time.valueOf("18:45:20");
//        boolean working = true;
//        BusinessHour businessHour=null;
//        GroceryStoreSystem groceryStoreSystem = groceryService.createGroceryStoreSystem("a","a", 1);
//        String error = "";
//        try {
//            businessHour = service.createBusinessHourforGroceryStoreSystem(day, startTime, endTime, working, groceryStoreSystem);
//        } catch (IllegalArgumentException e) {
//            error = e.getMessage();
//        }
//        assertNull(businessHour);
//        assertEquals(error,"Start time cannot be empty");
//    }
// 
//    @Test
//    public void testCreateBusinessHourforGroceryStoreSystemendTimeNull() {  
//        WeekDay day = WeekDay.Monday;
//        Time startTime = Time.valueOf("18:45:20");
//        Time endTime = null;
//        boolean working = true;
//        BusinessHour businessHour=null;
//        GroceryStoreSystem groceryStoreSystem = groceryService.createGroceryStoreSystem("a","a", 1);
//        String error = "";
//        try {
//            businessHour = service.createBusinessHourforGroceryStoreSystem(day, startTime, endTime, working, groceryStoreSystem);
//        } catch (IllegalArgumentException e) {
//            error = e.getMessage();
//        }
//        assertNull(businessHour);
//        assertEquals(error,"End time cannot be empty");
//    }
//
//    @Test
//    public void testCreateBusinessHourForGroceryStoreSystemWithBadOrderTimes() {  
//        WeekDay day = WeekDay.Monday;
//        Time startTime = Time.valueOf("18:45:21");
//        Time endTime = Time.valueOf("18:45:20");
//        boolean working = true;
//        BusinessHour businessHour=null;
//        GroceryStoreSystem groceryStoreSystem = groceryService.createGroceryStoreSystem("a","a", 1);
//        String error = "";
//        try {
//            businessHour = service.createBusinessHourforGroceryStoreSystem(day, startTime, endTime, working, groceryStoreSystem);
//        } catch (IllegalArgumentException e) {
//            error = e.getMessage();
//        }
//        assertNull(businessHour);
//        assertEquals(error,"End time cannot be earlier than Start time");
//    }
//
//    @Test
//    public void testCreateBusinessHourforGroceryStoreSystemWithSameTime() {  
//        WeekDay day = WeekDay.Monday;
//        Time startTime = Time.valueOf("18:45:20");
//        Time endTime = Time.valueOf("18:45:20");
//        boolean working = true;
//        BusinessHour businessHour=null;
//        GroceryStoreSystem groceryStoreSystem = groceryService.createGroceryStoreSystem("a","a", 1);
//        String error = "";
//        try {
//            businessHour = service.createBusinessHourforGroceryStoreSystem(day, startTime, endTime, working, groceryStoreSystem);
//        } catch (IllegalArgumentException e) {
//            error = e.getMessage();
//        }
//        assertNull(businessHour);
//        assertEquals(error,"End time cannot be the same as Start time");
//    } 
//
//    @Test
//	public void createBusinessHourforGroceryStoreSystemGood() {
//    	GroceryStoreSystem groceryStoreSystem = groceryService.createGroceryStoreSystem("a","a", 1);
//		BusinessHour curr = service.createBusinessHourforGroceryStoreSystem(WeekDay.Monday, Time.valueOf("18:45:20"),Time.valueOf("18:45:21"), true, groceryStoreSystem);
//		
//		assertEquals(curr.getDay(), WeekDay.Monday);
//		assertEquals(curr.getStartTime(),Time.valueOf("18:45:20"));
//		assertEquals(curr.getEndTime(),Time.valueOf("18:45:21"));
//        assertEquals(curr.getGroceryStoreSystem(), groceryStoreSystem);
//	}
//    
//    //updating
//	@Test
//	public void testUpdateBusinessHourWithDayEmpty() {
//		
//		String error = "";
//        GroceryStoreSystem currSystem = groceryService.createGroceryStoreSystem("a", "a", 0);
//        Person person = personService.createPerson("email@gmail.com", "Bob", "The Builder", "111-222-3333", "123 street");
//        Employee employee = employeeService.createEmployee(person);
//		try {
//			service.updateBusinessHour(0, currSystem, employee, null, Time.valueOf("18:45:20"),Time.valueOf("18:45:21"), true);
//		}
//		catch (IllegalArgumentException e){
//			error = e.getMessage();
//		}
//		
//		assertEquals("Week day cannot be empty", error);
//	}
//	
//	@Test
//	public void testUpdateeBusinessHourWithStartTimeEmpty() {
//		
//		String error = "";
//		GroceryStoreSystem currSystem = groceryService.createGroceryStoreSystem("a", "a", 0);
//		Person person = personService.createPerson("email@gmail.com", "Bob", "The Builder", "111-222-3333", "123 street");
//        Employee employee = employeeService.createEmployee(person);
//		try {
//			service.updateBusinessHour(0, currSystem, employee, WeekDay.Monday, null,Time.valueOf("18:45:21"), true);
//		}
//		catch (IllegalArgumentException e){
//			error = e.getMessage();
//		}
//	
//		assertEquals("Start time cannot be empty", error);
//	}
//	
//	@Test
//	public void testUpdateBusinessHourWithEndTimeEmpty() {
//		
//		String error = "";
//		GroceryStoreSystem currSystem = groceryService.createGroceryStoreSystem("a", "a", 0);
//		Person person = personService.createPerson("email@gmail.com", "Bob", "The Builder", "111-222-3333", "123 street");
//        Employee employee = employeeService.createEmployee(person);
//		try {
//			service.updateBusinessHour(0, currSystem, employee, WeekDay.Monday, Time.valueOf("18:45:20"),null, true);
//		}
//		catch (IllegalArgumentException e){
//			error = e.getMessage();
//		}
//		
//		assertEquals("End time cannot be empty", error);
//		
//	
//	}
//	
//	@Test
//	public void testUpdateBusinessHourWithBadOrderTimes() {
//		
//		String error = "";
//		GroceryStoreSystem currSystem = groceryService.createGroceryStoreSystem("a", "a", 0);
//		Person person = personService.createPerson("email@gmail.com", "Bob", "The Builder", "111-222-3333", "123 street");
//        Employee employee = employeeService.createEmployee(person);
//		try {
//			service.updateBusinessHour(0, currSystem, employee, WeekDay.Monday, Time.valueOf("18:45:21"),Time.valueOf("18:45:20"), true);
//		}
//		catch (IllegalArgumentException e){
//			error = e.getMessage();
//		}
//
//		assertEquals("End time cannot be earlier than Start time", error);
//	}
//	
//	@Test
//	public void testUpdateBusinessHourGood() {
//		GroceryStoreSystem groceryStoreSystem = groceryService.createGroceryStoreSystem("a","a", 1);
//		BusinessHour curr = service.createBusinessHourforGroceryStoreSystem(WeekDay.Monday, Time.valueOf("18:45:20"),Time.valueOf("18:45:21"), true, groceryStoreSystem);
//		GroceryStoreSystem currSystem = groceryService.createGroceryStoreSystem("a", "a", 0);
//		Person person = personService.createPerson("email@gmail.com", "Bob", "The Builder", "111-222-3333", "123 street");
//        Employee employee = employeeService.createEmployee(person);
//		service.updateBusinessHour(0, currSystem, employee, WeekDay.Monday, Time.valueOf("18:45:20"),Time.valueOf("18:45:21"), true);
//		
//		assertEquals(curr.getDay(), WeekDay.Monday);
//		assertEquals(curr.getStartTime(),Time.valueOf("18:45:20"));
//		assertEquals(curr.getEndTime(),Time.valueOf("18:45:21"));
//	}
//	 
//    @Test
//    public void testDeleteBusinessHourByID(){
//        boolean businesshourdeleted=false;
//        try{
//            businesshourdeleted=service.deleteBusinessHourbyID(ID);
//        }catch(IllegalArgumentException e){
//            fail();
//        }
//        assertTrue(businesshourdeleted);
//    }
//
//    @Test
//    public void testDeleteBusinessHourByIDNegative(){
//        boolean businesshourdeleted=false;
//        try{
//            businesshourdeleted=service.deleteBusinessHourbyID(-1);
//        }catch(IllegalArgumentException e){
//            fail();
//        }
//        assertEquals(businesshourdeleted,false);
//    }
//
//    @Test
//    public void testDeleteBusinessHourByDay(){
//        boolean businesshourdeleted=false;
//        try{
//            businesshourdeleted=service.deleteBusinessHourbyDay(day);
//        }catch(IllegalArgumentException e){
//            fail();
//        }
//        assertTrue(businesshourdeleted);
//    }
//
//    @Test
//    public void testDeleteBusinessHourByTime(){
//        boolean businesshourdeleted=false;
//        try{
//            businesshourdeleted=service.deleteBusinessHourbyTime(startTime,endTime);
//        }catch(IllegalArgumentException e){
//            fail();
//        }
//        assertTrue(businesshourdeleted);
//    }
//
//    @Test
//    public void testDeleteBusinessHourByWrongTime(){
//        boolean businesshourdeleted=false;
//        try{
//            businesshourdeleted=service.deleteBusinessHourbyTime(Time.valueOf("18:45:21"),Time.valueOf("18:45:20"));
//        }catch(IllegalArgumentException e){
//            fail();
//        }
//        assertTrue(businesshourdeleted);
//    }
//
//    @Test
//    public void testDeleteBusinessHourBySameTime(){
//        boolean businesshourdeleted=false;
//        try{
//            businesshourdeleted=service.deleteBusinessHourbyTime(Time.valueOf("18:45:20"),Time.valueOf("18:45:20"));
//        }catch(IllegalArgumentException e){
//            fail();
//        }
//        assertTrue(businesshourdeleted);
//    }
//
//    @Test
//    public void testDeleteBusinessHourByWorking(){
//        boolean businesshourdeleted=false;
//        try{
//            businesshourdeleted=service.deleteBusinessHourbyWoring(working);
//        }catch(IllegalArgumentException e){
//            fail();
//        }
//        assertTrue(businesshourdeleted);
//    }
//}