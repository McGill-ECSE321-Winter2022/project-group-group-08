package mcgill.ecse321.grocerystore.service;

import static org.mockito.Mockito.lenient;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import mcgill.ecse321.grocerystore.dao.ItemRepository;
import mcgill.ecse321.grocerystore.model.Item;

import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;


@ExtendWith(MockitoExtension.class)
public class TestItemService {

	@Mock
	private ItemRepository itemDao;


	@InjectMocks
	private ItemService service;

	private static int ID = 0;
	private static final String NAME = "Carrot";
	private static final int PRICE = 3;
	private static final int POINT = 100;
	private static final int RETURN_POLICY = 2;
	private static final boolean PICKUP = true;
	private static final int IN_STORE_QUANTITY = 52;
	
	private static final int MIN_NUM = 0;
	private static final int MAX_NUM = 1000;

	@BeforeEach
	public void setMockOutput() {
		lenient().when(itemDao.findItemById(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(ID)) {
				Item item = new Item();
				item.setName(NAME);
				item.setPrice(PRICE);
				item.setPoint(POINT);
				item.setReturnPolicy(RETURN_POLICY);
				item.setPickup(PICKUP);
				item.setInStoreQuantity(IN_STORE_QUANTITY);
				return item;
			} else {
				return null;
			}
		});

		lenient().when(itemDao.findAll()).thenAnswer((InvocationOnMock invocation) -> {
			Item item = new Item();
			item.setName(NAME);
			item.setPrice(PRICE);
			item.setPoint(POINT);
			item.setReturnPolicy(RETURN_POLICY);
			item.setPickup(PICKUP);
			item.setInStoreQuantity(IN_STORE_QUANTITY);
			ArrayList<Item> list = new ArrayList<Item>();
			list.add(item);
			return list;
		});
		
		lenient().when(itemDao.findItemByNameContaining(NAME)).thenAnswer((InvocationOnMock invocation) -> {
			Item item = new Item();
			item.setName(NAME);
			item.setPrice(PRICE);
			item.setPoint(POINT);
			item.setReturnPolicy(RETURN_POLICY);
			item.setPickup(PICKUP);
			item.setInStoreQuantity(IN_STORE_QUANTITY);
			ArrayList<Item> list = new ArrayList<Item>();
			list.add(item);
			return list;
		});

		lenient().when(itemDao.findItemByPriceBetween(MIN_NUM, MAX_NUM)).thenAnswer((InvocationOnMock invocation) -> {
			Item item = new Item();
			item.setName(NAME);
			item.setPrice(PRICE);
			item.setPoint(POINT);
			item.setReturnPolicy(RETURN_POLICY);
			item.setPickup(PICKUP);
			item.setInStoreQuantity(IN_STORE_QUANTITY);
			ArrayList<Item> list = new ArrayList<Item>();
			list.add(item);
			return list;
		});

		lenient().when(itemDao.findItemByPointBetween(MIN_NUM, MAX_NUM)).thenAnswer((InvocationOnMock invocation) -> {
			Item item = new Item();
			item.setName(NAME);
			item.setPrice(PRICE);
			item.setPoint(POINT);
			item.setReturnPolicy(RETURN_POLICY);
			item.setPickup(PICKUP);
			item.setInStoreQuantity(IN_STORE_QUANTITY);
			ArrayList<Item> list = new ArrayList<Item>();
			list.add(item);
			return list;
		});
		
		lenient().when(itemDao.findItemByReturnPolicyBetween(MIN_NUM, MAX_NUM)).thenAnswer((InvocationOnMock invocation) -> {
			Item item = new Item();
			item.setName(NAME);
			item.setPrice(PRICE);
			item.setPoint(POINT);
			item.setReturnPolicy(RETURN_POLICY);
			item.setPickup(PICKUP);
			item.setInStoreQuantity(IN_STORE_QUANTITY);
			ArrayList<Item> list = new ArrayList<Item>();
			list.add(item);
			return list;
		});
		
		lenient().when(itemDao.findItemByPickup(PICKUP)).thenAnswer((InvocationOnMock invocation) -> {
			Item item = new Item();
			item.setName(NAME);
			item.setPrice(PRICE);
			item.setPoint(POINT);
			item.setReturnPolicy(RETURN_POLICY);
			item.setPickup(PICKUP);
			item.setInStoreQuantity(IN_STORE_QUANTITY);
			ArrayList<Item> list = new ArrayList<Item>();
			list.add(item);
			return list;
		});
		
		lenient().when(itemDao.findItemByInStoreQuantityBetween(MIN_NUM, MAX_NUM)).thenAnswer((InvocationOnMock invocation) -> {
			Item item = new Item();
			item.setName(NAME);
			item.setPrice(PRICE);
			item.setPoint(POINT);
			item.setReturnPolicy(RETURN_POLICY);
			item.setPickup(PICKUP);
			item.setInStoreQuantity(IN_STORE_QUANTITY);
			ArrayList<Item> list = new ArrayList<Item>();
			list.add(item);
			return list;
		});

		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(itemDao.save(any(Item.class))).thenAnswer(returnParameterAsAnswer);
	}

	@Test
	public void testCreateItem() {
		assertEquals(1, service.getAllItems().size());

		String name = "Carrot";
		int price = 2;
		int point = 10;
		int returnPolicy = 2;
		boolean pickup = true;
		int inStoreQuantity = 58;
		Item item = null;
		try {
			item = service.createItem(name, price, point, returnPolicy, pickup, inStoreQuantity);
		} catch (IllegalArgumentException e) {
			fail();
		}
		int id = item.getId();
		assertNotNull(item);

		assertEquals(id,item.getId());
		assertEquals(name,item.getName());
		assertEquals(price,item.getPrice());
		assertEquals(point,item.getPoint());
		assertEquals(returnPolicy,item.getReturnPolicy());
		assertEquals(pickup,item.getPickup());
		assertEquals(inStoreQuantity,item.getInStoreQuantity());
	}

	@Test
	public void testCreateItemNameNull() {		
		String name = null;
		int price = 2;
		int point = 10;
		int returnPolicy = 2;
		boolean pickup = true;
		int inStoreQuantity = 58;
		Item item = null;
		String error = null;
		try {
			item = service.createItem(name, price, point, returnPolicy, pickup, inStoreQuantity);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(item);
		assertEquals(error,"Item name cannot be null or empty");
	}

	@Test
	public void testCreateItemNameEmpty() {		
		String name = "";
		int price = -1;
		int point = -1;
		int returnPolicy = -1;
		boolean pickup = true;
		int inStoreQuantity = -1;
		Item item = null;
		String error = null;
		try {
			item = service.createItem(name, price, point, returnPolicy, pickup, inStoreQuantity);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(item);
		assertEquals(error,"Item name cannot be null or empty");
	}

	@Test
	public void testCreateItemNameSpaces() {		
		String name = " ";
		int price = -1;
		int point = -1;
		int returnPolicy = -1;
		boolean pickup = true;
		int inStoreQuantity = -1;
		Item item = null;
		String error = null;
		try {
			item = service.createItem(name, price, point, returnPolicy, pickup, inStoreQuantity);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(item);
		assertEquals(error,"Item name cannot be null or empty");
	}

	@Test
	public void testCreateItemPriceNegative() {		
		String name = "Carrot";
		int price = -2;
		int point = 10;
		int returnPolicy = 2;
		boolean pickup = true;
		int inStoreQuantity = 58;
		Item item = null;
		String error = null;
		try {
			item = service.createItem(name, price, point, returnPolicy, pickup, inStoreQuantity);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(item);
		assertEquals(error,"The price cannot be a negative number");
	}

	@Test
	public void testCreateItemPointNegative() {		
		String name = "Carrot";
		int price = 2;
		int point = -10;
		int returnPolicy = 2;
		boolean pickup = true;
		int inStoreQuantity = 58;
		Item item = null;
		String error = null;
		try {
			item = service.createItem(name, price, point, returnPolicy, pickup, inStoreQuantity);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(item);
		assertEquals(error,"The point cannot be a negative number");
	}

	@Test
	public void testCreateItemReturnPolicyNegative() {		
		String name = "Carrot";
		int price = 2;
		int point = 10;
		int returnPolicy = -2;
		boolean pickup = true;
		int inStoreQuantity = 58;
		Item item = null;
		String error = null;
		try {
			item = service.createItem(name, price, point, returnPolicy, pickup, inStoreQuantity);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(item);
		assertEquals(error,"The returnPolicy cannot be a negative number");
	}

	@Test
	public void testCreateItemInStoreQuantityyNegative() {		
		String name = "Carrot";
		int price = 2;
		int point = 10;
		int returnPolicy = 2;
		boolean pickup = true;
		int inStoreQuantity = -58;
		Item item = null;
		String error = null;
		try {
			item = service.createItem(name, price, point, returnPolicy, pickup, inStoreQuantity);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(item);
		assertEquals(error,"The inStoreQuantity cannot be a negative number");
	}

	@Test
	public void testGetAllItems() {
		List<Item> items = new ArrayList<Item>();
		items = service.getAllItems();
		Item item = items.get(0);
		assertNotNull(item);
	}

	@Test
	public void testGetItemById() {
		Item item = null;
		try {
			item = service.getItemById(ID);
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertNotNull(item);
		assertEquals(ID,item.getId());
		assertEquals(NAME,item.getName());
		assertEquals(PRICE,item.getPrice());
		assertEquals(POINT,item.getPoint());
		assertEquals(RETURN_POLICY,item.getReturnPolicy());
		assertEquals(PICKUP,item.getPickup());
		assertEquals(IN_STORE_QUANTITY,item.getInStoreQuantity());
	}

	@Test
	public void testGetItemByIdNegative() {
		Item item = null;
		String error = "";
		int id = -1;
		try {
			item = service.getItemById(id);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(item);
		assertEquals(error,"The id cannot be a negative number");
	}

	@Test
	public void testGetItemByIdNull() {
		Item item = null;
		String error = "";
		int id = 3;
		try {
			item = service.getItemById(id);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertNull(item);
		assertEquals(error,"No item with id " + id + " exists");
	}
	
	@Test
	public void testGetItemByNameContaining() {
		List<Item> items = new ArrayList<Item>();
		items = service.getItemByNameContaining(NAME);
		Item item = items.get(0);
		assertNotNull(item);
	}
	
	@Test
	public void testGetItemByNameContainingNull() {
		List<Item> items = new ArrayList<Item>();
		String error = "";
		try {
			items = service.getItemByNameContaining("");
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals(items.size(),0);
		assertEquals(error,"The name cannot be null or empty");
	}
	
	@Test
	public void testGetItemByNameContainingNotExist() {
		String tempName = "abc";
		List<Item> items = new ArrayList<Item>();
		String error = "";
		try {
			items = service.getItemByNameContaining(tempName);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals(items.size(),0);
		assertEquals(error,"No items with name containing '" + tempName + "' exists");
	}
	
	@Test
	public void testGetItemByPriceBetween() {
		List<Item> items = new ArrayList<Item>();
		items = service.getItemByPriceBetween(MIN_NUM, MAX_NUM);
		Item item = items.get(0);
		assertNotNull(item);
	}
	
	@Test
	public void testGetItemByPriceBetweenMinMoreThanMax() {
		List<Item> items = new ArrayList<Item>();
		String error = "";
		try {
			items = service.getItemByPriceBetween(MAX_NUM, MIN_NUM);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals(items.size(),0);
		assertEquals(error,"The max price cannot be lower than min price");
	}
	
	@Test
	public void testGetItemByPriceBetweenNotExist() {
		int tempMin = 2000;
		int tempMax = 3000;
		List<Item> items = new ArrayList<Item>();
		String error = "";
		try {
			items = service.getItemByPriceBetween(tempMin, tempMax);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals(items.size(),0);
		assertEquals(error,"No items with price between $" + tempMin + " & $" + tempMax + " exists");
	}
	
	@Test
	public void testGetItemByPointBetween() {
		List<Item> items = new ArrayList<Item>();
		items = service.getItemByPointBetween(MIN_NUM, MAX_NUM);
		Item item = items.get(0);
		assertNotNull(item);
	}
	
	@Test
	public void testGetItemByPointBetweenMinMoreThanMax() {
		List<Item> items = new ArrayList<Item>();
		String error = "";
		try {
			items = service.getItemByPointBetween(MAX_NUM, MIN_NUM);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals(items.size(),0);
		assertEquals(error,"The max amount of points cannot be lower than min amount of points");
	}
	
	@Test
	public void testGetItemByPointBetweenNotExist() {
		int tempMin = 2000;
		int tempMax = 3000;
		List<Item> items = new ArrayList<Item>();
		String error = "";
		try {
			items = service.getItemByPointBetween(tempMin, tempMax);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals(items.size(),0);
		assertEquals(error,"No items with point between " + tempMin + " & " + tempMax + " exists");
	}
	
	@Test
	public void testGetItemByReturnPolicyBetween() {
		List<Item> items = new ArrayList<Item>();
		items = service.getItemByReturnPolicyBetween(MIN_NUM, MAX_NUM);
		Item item = items.get(0);
		assertNotNull(item);
	}
	
	@Test
	public void testGetItemByReturnPolicyBetweenMinMoreThanMax() {
		List<Item> items = new ArrayList<Item>();
		String error = "";
		try {
			items = service.getItemByReturnPolicyBetween(MAX_NUM, MIN_NUM);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals(items.size(),0);
		assertEquals(error,"The max return policy days cannot be lower than min return policy days");
	}
	
	@Test
	public void testGetItemByReturnPolicyBetweenNotExist() {
		int tempMin = 2000;
		int tempMax = 3000;
		List<Item> items = new ArrayList<Item>();
		String error = "";
		try {
			items = service.getItemByReturnPolicyBetween(tempMin, tempMax);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals(items.size(),0);
		assertEquals(error,"No items with return policy between " + tempMin + " & " + tempMax + " exists");
	}
	
	@Test
	public void testGetItemByPickup() {
		List<Item> items = new ArrayList<Item>();
		items = service.getItemByPickup(PICKUP);
		Item item = items.get(0);
		assertNotNull(item);
	}
	
	@Test
	public void testGetItemByPickupNotExist() {
		List<Item> items = new ArrayList<Item>();
		String error = "";
		try {
			items = service.getItemByPickup(!PICKUP);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals(items.size(),0);
		assertEquals(error, "No items with pickup being " + !PICKUP + " exists");
	}
	
	@Test
	public void testGetItemByInStoreQuantityBetween() {
		List<Item> items = new ArrayList<Item>();
		items = service.getItemByInStoreQuantityBetween(MIN_NUM, MAX_NUM);
		Item item = items.get(0);
		assertNotNull(item);
	}
	
	@Test
	public void testGetItemByInStoreQuantityBetweenMinMoreThanMax() {
		List<Item> items = new ArrayList<Item>();
		String error = "";
		try {
			items = service.getItemByInStoreQuantityBetween(MAX_NUM, MIN_NUM);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals(items.size(),0);
		assertEquals(error,"The max quantity cannot be lower than min quantity");
	}
	
	@Test
	public void testGetItemByInStoreQuantityBetweenNotExist() {
		int tempMin = 2000;
		int tempMax = 3000;
		List<Item> items = new ArrayList<Item>();
		String error = "";
		try {
			items = service.getItemByInStoreQuantityBetween(tempMin, tempMax);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals(items.size(),0);
		assertEquals(error,"No items with in store quantity between " + tempMin + " & " + tempMax + " exists");
	}
	
	@Test
    public void testUpdateItem() {
		String name = "Onion";
		int price = 20;
		int point = 100;
		int returnPolicy = 20;
		boolean pickup = false;
		int inStoreQuantity = 580;
		Item item = null;
		
        try {
            item = service.updateItem(ID, name, price, point, returnPolicy, pickup, inStoreQuantity);
        } catch (IllegalArgumentException e) {
            fail();
        }
        assertNotNull(item);
        assertEquals(ID,item.getId());
		assertEquals(name,item.getName());
		assertEquals(price,item.getPrice());
		assertEquals(point,item.getPoint());
		assertEquals(returnPolicy,item.getReturnPolicy());
		assertEquals(pickup,item.getPickup());
		assertEquals(inStoreQuantity,item.getInStoreQuantity());
    }
	
	@Test
    public void testUpdateItemNegative() {
		String name = "Onion";
		int price = 20;
		int point = 100;
		int returnPolicy = 20;
		boolean pickup = false;
		int inStoreQuantity = 580;
		Item item = null;
		String error = "";
		
        try {
            item = service.updateItem(-1, name, price, point, returnPolicy, pickup, inStoreQuantity);
        } catch (IllegalArgumentException e) {
        	error = e.getMessage();
        }
        assertNull(item);
		assertEquals(error,"Id cannot be a negative number");
    }
	
	@Test
    public void testUpdateItemName() {
		int price = 20;
		int point = 100;
		int returnPolicy = 20;
		boolean pickup = false;
		int inStoreQuantity = 580;
		Item item = null;
		String error = "";
		
        try {
            item = service.updateItem(ID, "", price, point, returnPolicy, pickup, inStoreQuantity);
        } catch (IllegalArgumentException e) {
        	error = e.getMessage();
        }
        assertNull(item);
		assertEquals(error,"Name cannot be null or empty");
    }
	
	@Test
    public void testUpdateItemPrice() {
		String name = "Onion";
		int point = 100;
		int returnPolicy = 20;
		boolean pickup = false;
		int inStoreQuantity = 580;
		Item item = null;
		String error = "";
		
        try {
            item = service.updateItem(ID, name, -1, point, returnPolicy, pickup, inStoreQuantity);
        } catch (IllegalArgumentException e) {
        	error = e.getMessage();
        }
        assertNull(item);
		assertEquals(error,"Price cannot be a negative number");
    }
	
	@Test
    public void testUpdateItemPoint() {
		String name = "Onion";
		int price = 20;
		int returnPolicy = 20;
		boolean pickup = false;
		int inStoreQuantity = 580;
		Item item = null;
		String error = "";
		
        try {
            item = service.updateItem(ID, name, price, -1, returnPolicy, pickup, inStoreQuantity);
        } catch (IllegalArgumentException e) {
        	error = e.getMessage();
        }
        assertNull(item);
		assertEquals(error,"Point cannot be a negative number");
    }
	
	@Test
    public void testUpdateItemReturnPolicy() {
		String name = "Onion";
		int price = 20;
		int point = 100;
		boolean pickup = false;
		int inStoreQuantity = 580;
		Item item = null;
		String error = "";
		
        try {
            item = service.updateItem(ID, name, price, point, -1, pickup, inStoreQuantity);
        } catch (IllegalArgumentException e) {
        	error = e.getMessage();
        }
        assertNull(item);
		assertEquals(error,"Return policy cannot be a negative number");
    }
	
	@Test
    public void testUpdateItemReturnInStorePolicy() {
		String name = "Onion";
		int price = 20;
		int point = 100;
		int returnPolicy = 20;
		boolean pickup = false;
		Item item = null;
		String error = "";
		
        try {
            item = service.updateItem(ID, name, price, point, returnPolicy, pickup, -1);
        } catch (IllegalArgumentException e) {
        	error = e.getMessage();
        }
        assertNull(item);
		assertEquals(error,"In store quantity cannot be a negative number");
    }
	
	@Test
    public void testUpdateItemReturnInStorePolicyNotExist() {
		int tempId = 2;
		String name = "Onion";
		int price = 20;
		int point = 100;
		int returnPolicy = 20;
		boolean pickup = false;
		int inStoreQuantity = 580;
		Item item = null;
		String error = "";
		
        try {
            item = service.updateItem(tempId, name, price, point, returnPolicy, pickup, inStoreQuantity);
        } catch (IllegalArgumentException e) {
        	error = e.getMessage();
        }
        assertNull(item);
		assertEquals(error,"Item with id " + tempId + " does not exists");
    }
	
	@Test
    public void testDeleteItemById() {
		boolean itemDeleted = false;
        try {
        	itemDeleted = service.deleteItemById(ID);
        } catch (IllegalArgumentException e) {
        	fail();
        }
        assertTrue(itemDeleted);
    }
	
	@Test
    public void testDeleteItemByIdNegative() {
		boolean itemDeleted = true;
        try {
        	itemDeleted = service.deleteItemById(-1);
        } catch (IllegalArgumentException e) {
        	fail();
        }
        assertFalse(itemDeleted);
    }
	
	@Test
    public void testDeleteItemByIdNotExists() {
		boolean itemDeleted = true;
        try {
        	itemDeleted = service.deleteItemById(4);
        } catch (IllegalArgumentException e) {
        	fail();
        }
        assertFalse(itemDeleted);
    }

}