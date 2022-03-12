package mcgill.ecse321.grocerystore.service;

import static org.mockito.Mockito.lenient;
import java.sql.Time;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import mcgill.ecse321.grocerystore.dao.BusinessHourRepository;
import mcgill.ecse321.grocerystore.model.BusinessHour;
import mcgill.ecse321.grocerystore.model.BusinessHour.WeekDay;

import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class TestBusinessHourService {

 @Mock
 private BusinessHourRepository groceryStoreSystemDao;

 @InjectMocks
 private BusinessHourService service;
 
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
  Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
   return invocation.getArgument(0);
  };
  lenient().when(groceryStoreSystemDao.save(any(BusinessHour.class))).thenAnswer(returnParameterAsAnswer);
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
        Time startTime = null;
        Time endTime = null;
        boolean working = true;
        BusinessHour businessHour=null;
        String error = null;
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
        Time startTime = null;
        Time endTime = null;
        boolean working = true;
        BusinessHour businessHour=null;
        String error = null;
        try {
            businessHour = service.createBusinessHour(id, day, startTime, endTime, working);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(businessHour);
        assertEquals(error,"Business hour's day cannot be null");
    }
 
    @Test
    public void testCreateBusinessHourWorkingNull() {  
        int id=2;
        WeekDay day = WeekDay.Wednesday;
        Time startTime = null;
        Time endTime = null;
        Boolean working = null;
        BusinessHour businessHour=null;
        String error = null;
        try {
            businessHour = service.createBusinessHour(id, day, startTime, endTime, working);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(businessHour);
        assertEquals(error,"Business hour's working status cannot be null");
    }
    
    @Test
    public void testCreateBusinessHourTimeNull() {  
        int id=2;
        WeekDay day = WeekDay.Monday;
        Time startTime = null;
        Time endTime = null;
        boolean working = true;
        BusinessHour businessHour=null;
        String error = null;
        try {
            businessHour = service.createBusinessHour(id, day, startTime, endTime, working);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(businessHour);
        assertEquals(error,"Business hour's Time cannot be null");
    }
 
 
}