package mcgill.ecse321.grocerystore.service;

import static org.mockito.Mockito.lenient;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import mcgill.ecse321.grocerystore.dao.BusinessHourRepository;
import mcgill.ecse321.grocerystore.model.BusinessHour;
import mcgill.ecse321.grocerystore.model.Employee;
import mcgill.ecse321.grocerystore.model.GroceryStoreSystem;
import mcgill.ecse321.grocerystore.model.BusinessHour.WeekDay;

import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;

public class TestBusinessHourService {

 @Mock
 private BusinessHourRepository businessDao;

 @InjectMocks
 private BusinessHourService service;
 @InjectMocks
 private GroceryStoreSystemService groceryService;
 
 private static final int ID=0;
 private static final WeekDay day=WeekDay.Tuesday;
 private static final Time startTime = Time.valueOf("8:30:00");
 private static final Time endTime = Time.valueOf("18:30:00");
 private static final boolean working = true;

 BusinessHourRepository BusinessHourDao;
 
 @BeforeEach
 public void setMockOutput() {
  lenient().when(BusinessHourDao.findBusinessHourById(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
    if(invocation.getArgument(0).equals(ID)) {
        BusinessHour businessHour = new BusinessHour();
        businessHour.setId(ID);
        businessHour.setDay(day);
        businessHour.setWorking(working);
        businessHour.setStartTime(startTime);
        businessHour.setEndTime(endTime);
        return businessHour;
    } 
    else {
        return null;
    }
  });

  lenient().when(BusinessHourDao.findAll()).thenAnswer( (InvocationOnMock invocation) -> {
        BusinessHour businessHour = new BusinessHour();
        businessHour.setId(ID);
        businessHour.setDay(day);
        businessHour.setWorking(working);
        businessHour.setStartTime(startTime);
        businessHour.setEndTime(endTime);
        ArrayList<BusinessHour> list = new ArrayList<BusinessHour>();
        list.add(businessHour);
        return list;
  });

  lenient().when(BusinessHourDao.findBusinessHourByDay(day)).thenAnswer( (InvocationOnMock invocation) -> {
    BusinessHour businessHour = new BusinessHour();
        businessHour.setId(ID);
        businessHour.setDay(day);
        businessHour.setWorking(working);
        businessHour.setStartTime(startTime);
        businessHour.setEndTime(endTime);
        ArrayList<BusinessHour> list = new ArrayList<BusinessHour>();
        list.add(businessHour);
        return list;
  });

  lenient().when(BusinessHourDao.findBusinessHourByWorking(working)).thenAnswer( (InvocationOnMock invocation) -> {
    BusinessHour businessHour = new BusinessHour();
        businessHour.setId(ID);
        businessHour.setDay(day);
        businessHour.setWorking(working);
        businessHour.setStartTime(startTime);
        businessHour.setEndTime(endTime);
        ArrayList<BusinessHour> list = new ArrayList<BusinessHour>();
        list.add(businessHour);
        return list;
  });

  lenient().when(BusinessHourDao.findBusinessHourByTimeBetween(startTime,endTime)).thenAnswer( (InvocationOnMock invocation) -> {
    BusinessHour businessHour = new BusinessHour();
        businessHour.setId(ID);
        businessHour.setDay(day);
        businessHour.setWorking(working);
        businessHour.setStartTime(startTime);
        businessHour.setEndTime(endTime);
        ArrayList<BusinessHour> list = new ArrayList<BusinessHour>();
        list.add(businessHour);
        return list;
  });

 }

    @Test
    public void testGetAllBusinessHours() {
        List<BusinessHour> businessHours= new ArrayList<BusinessHour>();
        businessHours=service.getAllBusinessHours();
        BusinessHour businesshour=businessHours.get(0);
        assertNotNull(businesshour);
    }
    
    @Test
    public void testGetBusinessHourbyID() {
        BusinessHour businessHour = null;
        try {
            businessHour = service.getBusinessHoursbyID(ID);
        } catch (IllegalArgumentException e) {
            fail();
        }
        assertNotNull(businessHour);
        assertEquals(ID,businessHour.getId());
        assertEquals(day,businessHour.getDay());
        assertEquals(startTime,businessHour.getStartTime());
        assertEquals(endTime,businessHour.getEndTime());
        assertEquals(working,businessHour.getWorking());
    }

    @Test
    public void testGetBusinessHourbyIDNegative() {
        BusinessHour businessHour = null;
        String error="";
        int id=-1;
        try {
            businessHour = service.getBusinessHoursbyID(id);
        } catch (IllegalArgumentException e) {
            error=e.getMessage();
        }
        assertNotNull(businessHour);
        assertEquals(error,"The id cannot be a negative number");
    }

    @Test
    public void testGetBusinessHourbyIDNull() {
        BusinessHour businessHour = null;
        String error="";
        int id=0;
        try {
            businessHour = service.getBusinessHoursbyID(id);
        } catch (IllegalArgumentException e) {
            error=e.getMessage();
        }
        assertNotNull(businessHour);
        assertEquals(error,"The id cannot be null");
    }

    @Test
    public void testGetBusinessHourbyDay() {
        List<BusinessHour> businessHour = new ArrayList<BusinessHour>();
        businessHour=service.getBusinessHoursbyDay(day);
        BusinessHour businesshour=businessHour.get(0);
        assertNotNull(businessHour);
    }

    @Test
    public void testGetBusinessHourbyWorking() {
        List<BusinessHour> businessHour = new ArrayList<BusinessHour>();
        businessHour=service.getBusinessHoursbyWorking(working);
        BusinessHour businesshour=businessHour.get(0);
        assertNotNull(businessHour);
    }

    @Test
    public void testGetBusinessHourbyTime() {
        List<BusinessHour> businessHour = new ArrayList<BusinessHour>();
        businessHour=service.getBusinessHoursbyTime(startTime,endTime);
        BusinessHour businesshour=businessHour.get(0);
        assertNotNull(businessHour);
    }

    @Test
    public void testGetBusinessHourbystartTimeNull() {
        List<BusinessHour> businessHour = new ArrayList<BusinessHour>();
        String error="";
        Time startTime=null;
        Time endTime=Time.valueOf("23:45:21");
        try {
            businessHour = service.getBusinessHoursbyTime(startTime,endTime);
        } catch (IllegalArgumentException e) {
            error=e.getMessage();
        }
        assertEquals(businessHour.size(),0);
        assertEquals(error,"The start time cannot be null");
    }

    @Test
    public void testGetBusinessHourbyendTimeNull() {
        List<BusinessHour> businessHour = new ArrayList<BusinessHour>();
        String error="";
        Time startTime=Time.valueOf("23:45:21");
        Time endTime=null;
        try {
            businessHour = service.getBusinessHoursbyTime(startTime,endTime);
        } catch (IllegalArgumentException e) {
            error=e.getMessage();
        }
        assertEquals(businessHour.size(),0);
        assertEquals(error,"The end time cannot be null");
    }

    @Test
    public void testCreateBusinessHour() {
        BusinessHour businessHour = null;
        int id=0;
        try {
            businessHour = service.createBusinessHour(id, day, startTime, endTime, working);
        } catch (IllegalArgumentException e) {
            fail();
        }
        assertNotNull(businessHour);
        assertEquals(id,businessHour.getId());
        assertEquals(day,businessHour.getDay());
        assertEquals(startTime,businessHour.getStartTime());
        assertEquals(endTime,businessHour.getEndTime());
        assertEquals(working,businessHour.getWorking());
    }
 
 
    @Test
    public void testCreateBusinessHourIDNull() {  
        int id=0;
        WeekDay day = WeekDay.Tuesday;
        Time startTime = Time.valueOf("18:45:20");
        Time endTime = Time.valueOf("18:45:20");
        boolean working = true;
        BusinessHour businessHour=null;
        String error = "";
        try {
            businessHour = service.createBusinessHour(id, day, startTime, endTime, working);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(businessHour);
        assertEquals(error,"Business hour's id cannot be null");
    }
 
    @Test
    public void testCreatBusinessHourDayNull() {  
        int id=2;
        WeekDay day = null;
        Time startTime = Time.valueOf("18:45:20");
        Time endTime = Time.valueOf("18:45:20");
        boolean working = true;
        BusinessHour businessHour=null;
        String error = "";
        try {
            businessHour = service.createBusinessHour(id, day, startTime, endTime, working);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(businessHour);
        assertEquals(error,"Business hour's day cannot be null");
    }
 
    // @Test
    // public void testCreateBusinessHourWorkingNull() {  
    //     int id=2;
    //     WeekDay day = WeekDay.Wednesday;
    //     Time startTime = Time.valueOf("18:45:20");
    //     Time endTime = Time.valueOf("18:45:20");
    //     Boolean working = null;
    //     BusinessHour businessHour=null;
    //     String error = "";
    //     try {
    //         businessHour = service.createBusinessHour(id, day, startTime, endTime, working);
    //     } catch (IllegalArgumentException e) {
    //         error = e.getMessage();
    //     }
    //     assertNull(businessHour);
    //     assertEquals(error,"Business hour's working status cannot be null");
    // }
    
    @Test
    public void testCreateBusinessHourstartTimeNull() {  
        int id=2;
        WeekDay day = WeekDay.Monday;
        Time startTime = null;
        Time endTime = Time.valueOf("18:45:20");
        boolean working = true;
        BusinessHour businessHour=null;
        String error = "";
        try {
            businessHour = service.createBusinessHour(id, day, startTime, endTime, working);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(businessHour);
        assertEquals(error,"Business hour's start Time cannot be null");
    }
 
    @Test
    public void testCreateBusinessHourendTimeNull() {  
        int id=2;
        WeekDay day = WeekDay.Monday;
        Time startTime = Time.valueOf("18:45:20");
        Time endTime = null;
        boolean working = true;
        BusinessHour businessHour=null;
        String error = "";
        try {
            businessHour = service.createBusinessHour(id, day, startTime, endTime, working);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(businessHour);
        assertEquals(error,"Business hour's end Time cannot be null");
    }

    @Test
    public void testCreateBusinessHourWithBadOrderTimes() {  
        int id=2;
        WeekDay day = WeekDay.Monday;
        Time startTime = Time.valueOf("18:45:21");
        Time endTime = Time.valueOf("18:45:20");
        boolean working = true;
        BusinessHour businessHour=null;
        String error = "";
        try {
            businessHour = service.createBusinessHour(id, day, startTime, endTime, working);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(businessHour);
        assertEquals(error,"Business hour's end time cannot be earlier than start time");
    }

    @Test
    public void testCreateBusinessHourWithSameTime() {  
        int id=2;
        WeekDay day = WeekDay.Monday;
        Time startTime = Time.valueOf("18:45:20");
        Time endTime = Time.valueOf("18:45:20");
        boolean working = true;
        BusinessHour businessHour=null;
        String error = "";
        try {
            businessHour = service.createBusinessHour(id, day, startTime, endTime, working);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(businessHour);
        assertEquals(error,"Business hour's end time cannot be equal to start time");
    } 

    @Test
	public void createBusinessHourGood() {
	
		BusinessHour curr = service.createBusinessHour(2, WeekDay.Monday, Time.valueOf("18:45:20"),Time.valueOf("18:45:21"), true);
		
		assertEquals(curr.getId(),2);
		assertEquals(curr.getDay(), WeekDay.Monday);
		assertEquals(curr.getStartTime(),Time.valueOf("18:45:20"));
		assertEquals(curr.getEndTime(),Time.valueOf("18:45:21"));
	}

    //updating
	
	@Test
	public void testUpdateBusinessHourWithGroceryEmpty() {
		
		String error = "";
		BusinessHour curr = service.createBusinessHour(2, WeekDay.Monday, Time.valueOf("18:45:20"),Time.valueOf("18:45:21"), true);

		try {
			service.updateBusinessHour(null, WeekDay.Monday, Time.valueOf("18:45:20"),Time.valueOf("18:45:21"), true);
		}
		catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		
		assertEquals("Grocery store is null", error);
	}
	
	@Test
	public void testUpdateBusinessHourWithDayEmpty() {
		
		String error = "";
		BusinessHour curr = service.createBusinessHour(2, WeekDay.Monday, Time.valueOf("18:45:20"),Time.valueOf("18:45:21"), true);
        GroceryStoreSystem currSystem = groceryService.createGroceryStoreSystem("a", "a", 0);
		try {
			service.updateBusinessHour(currSystem, null, Time.valueOf("18:45:20"),Time.valueOf("18:45:21"), true);
		}
		catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		
		assertEquals("Week day cannot be empty", error);
	}
	
	@Test
	public void testUpdateeBusinessHourWithStartTimeEmpty() {
		
		String error = "";
		BusinessHour curr = service.createBusinessHour(2, WeekDay.Monday, Time.valueOf("18:45:20"),Time.valueOf("18:45:21"), true);
		GroceryStoreSystem currSystem = groceryService.createGroceryStoreSystem("a", "a", 0);
		try {
			service.updateBusinessHour(currSystem, WeekDay.Monday, null,Time.valueOf("18:45:21"), true);
		}
		catch (IllegalArgumentException e){
			error = e.getMessage();
		}
	
		assertEquals("Start time cannot be empty", error);
	}
	
	@Test
	public void testUpdateBusinessHourWithEndTimeEmpty() {
		
		String error = "";
		BusinessHour curr = service.createBusinessHour(2, WeekDay.Monday, Time.valueOf("18:45:20"),Time.valueOf("18:45:21"), true);
		GroceryStoreSystem currSystem = groceryService.createGroceryStoreSystem("a", "a", 0);
		try {
			service.updateBusinessHour(currSystem, WeekDay.Monday, Time.valueOf("18:45:20"),null, true);
		}
		catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		
		assertEquals("End time cannot be empty", error);
		
	
	}
	
	@Test
	public void testUpdateBusinessHourWithBadOrderTimes() {
		
		String error = "";
		BusinessHour curr = service.createBusinessHour(2, WeekDay.Monday, Time.valueOf("18:45:20"),Time.valueOf("18:45:21"), true);
		GroceryStoreSystem currSystem = groceryService.createGroceryStoreSystem("a", "a", 0);
		try {
			service.updateBusinessHour(currSystem, WeekDay.Monday, Time.valueOf("18:45:21"),Time.valueOf("18:45:20"), true);
		}
		catch (IllegalArgumentException e){
			error = e.getMessage();
		}

		assertEquals("End time cannot be earlier than Start time", error);
	}
	
	@Test
	public void testUpdateBusinessHourGood() {
		String error = "";
		BusinessHour curr = service.createBusinessHour(2, WeekDay.Monday, Time.valueOf("18:45:20"),Time.valueOf("18:45:21"), true);
		GroceryStoreSystem currSystem = groceryService.createGroceryStoreSystem("a", "a", 0);
		service.updateBusinessHour(currSystem, WeekDay.Monday, Time.valueOf("18:45:20"),Time.valueOf("18:45:21"), true);
		
		assertEquals(curr.getDay(), WeekDay.Monday);
		assertEquals(curr.getStartTime(),Time.valueOf("18:45:20"));
		assertEquals(curr.getEndTime(),Time.valueOf("18:45:21"));
	}
}