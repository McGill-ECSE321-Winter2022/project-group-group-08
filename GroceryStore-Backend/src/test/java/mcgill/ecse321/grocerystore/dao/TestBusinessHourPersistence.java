//package mcgill.ecse321.grocerystore.dao;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//import java.sql.Time;
//import java.time.LocalTime;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import mcgill.ecse321.grocerystore.model.BusinessHour;
//import mcgill.ecse321.grocerystore.model.BusinessHour.WeekDay;
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest
//public class TestBusinessHourPersistence {
//
//	@Autowired
//	private BusinessHourRepository businessHourRepository;
//
//	@AfterEach
//	public void clearDatabase() {
//		// First, we clear the repositories to avoid exceptions due to inconsistencies
//		businessHourRepository.deleteAll();
//	}
//	
//	@Test
//	public void testPersistAndLoadBusinessHour() {
//		//business hour attributes
//		WeekDay dayOfWeek = WeekDay.Monday;
//		Time startTime = java.sql.Time.valueOf(LocalTime.of(9, 30));
//		Time endTime = java.sql.Time.valueOf(LocalTime.of(17, 00));
//		
//		BusinessHour bH = new BusinessHour();
//		bH.setDay(dayOfWeek);
//		bH.setStartTime(startTime);
//		bH.setEndTime(endTime);
//		bH.setWorking(true);
//		businessHourRepository.save(bH);
//		int id = bH.getId();
//		
//		
//		bH = null;
//		bH = businessHourRepository.findBusinessHourById(id);
//		
//		//testing
//		assertNotNull(bH);
//		assertEquals(dayOfWeek, bH.getDay());
//		assertEquals(startTime, bH.getStartTime());
//		assertEquals(endTime, bH.getEndTime());
//	}
//}
