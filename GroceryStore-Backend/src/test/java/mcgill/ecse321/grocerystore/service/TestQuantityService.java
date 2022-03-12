package mcgill.ecse321.grocerystore.service;

import static org.mockito.Mockito.lenient;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import mcgill.ecse321.grocerystore.dao.ItemRepository;
import mcgill.ecse321.grocerystore.dao.QuantityRepository;
import mcgill.ecse321.grocerystore.model.Item;
import mcgill.ecse321.grocerystore.model.Quantity;

import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;


@ExtendWith(MockitoExtension.class)
public class TestQuantityService {

	@Mock
	private QuantityRepository quantityDao;


	@InjectMocks
	private QuantityService service;
	
	@InjectMocks
	private ItemService itemService;

	private static int ID = 0;
	private static int COUNT = 10;

	@BeforeEach
	public void setMockOutput() {
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(quantityDao.save(any(Quantity.class))).thenAnswer(returnParameterAsAnswer);
	}
	
	@Test
	public void testCreateItem() {
		assertEquals(1, service.getAllQuantities().size());
		Item item = itemService.createItem("Carrot", 2, 10, 2, true, 58);
		int count = 2;
		Quantity quantity = null;
		try {
			quantity = service.createQuantity(count, null, null);
		} catch (IllegalArgumentException e) {
			fail();
		}
		int id = item.getId();
		assertNotNull(item);

//		assertEquals(id,item.getId());
//		assertEquals(name,item.getName());
//		assertEquals(price,item.getPrice());
//		assertEquals(point,item.getPoint());
//		assertEquals(returnPolicy,item.getReturnPolicy());
//		assertEquals(pickup,item.getPickup());
//		assertEquals(inStoreQuantity,item.getInStoreQuantity());
	}
	
	

}