package mcgill.ecse321.grocerystore.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestAccountPersistence {

	@Autowired
	private AccountRepository accountRepository;

	@AfterEach
	public void clearDatabase() {
		// First, we clear registrations to avoid exceptions due to inconsistencies
		accountRepository.deleteAll();
	}
	
	@Test
	public void testPersistAndLoadAccount() {
		
	}
	
}
