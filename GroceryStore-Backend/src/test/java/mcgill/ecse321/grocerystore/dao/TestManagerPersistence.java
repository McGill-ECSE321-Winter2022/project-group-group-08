package mcgill.ecse321.grocerystore.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Time;
import java.time.LocalTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import mcgill.ecse321.grocerystore.model.BusinessHour;
import mcgill.ecse321.grocerystore.model.Manager;
import mcgill.ecse321.grocerystore.model.BusinessHour.WeekDay;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestManagerPersistence {

	@Autowired
	private ManagerRepository managerRepository;

	@AfterEach
	public void clearDatabase() {
		// First, we clear registrations to avoid exceptions due to inconsistencies
		managerRepository.deleteAll();
	}
	
	public Manager createManager() {
		Manager manager = new Manager();
		managerRepository.save(manager);
		return manager;
	}
	
	@Test
	public void testPersistAndLoadManager() {
		Manager manager = createManager();
		int id = manager.getId();
		
		manager = null;
		manager = managerRepository.findManagerById(id);
		assertNotNull(manager);
		
		assertEquals(id,manager.getId());
	}
	
}
