package mcgill.ecse321.grocerystore.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mcgill.ecse321.grocerystore.dao.ItemRepository;
import mcgill.ecse321.grocerystore.dao.QuantityRepository;
import mcgill.ecse321.grocerystore.model.Item;
import mcgill.ecse321.grocerystore.model.Quantity;

@Service
public class ItemService {
	
	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private QuantityRepository quantityRepository;
	@Autowired
	private QuantityService quantityService;
	
	/**
	 * creating an item
	 * @param name the name of an item
	 * @param price the price of an item
	 * @param point the point the item has
	 * @param returnPolicy the num of days the item can be returned
	 * @param pickup whether the item can be picked up or not
	 * @param inStoreQuantity the num of items available (inventory)
	 * @return
	 */
	@Transactional
	public Item createItem(String image, String name, int price, int point, int returnPolicy, boolean pickup, int inStoreQuantity) {
		if(name == null || name.strip() == ""|| name.equals("undefined")) {
			throw new IllegalArgumentException("Item name cannot be null or empty");
		}
		if (price < 0) {
			throw new IllegalArgumentException("The price cannot be a negative number");
		}
		if (point < 0) {
			throw new IllegalArgumentException("The point cannot be a negative number");
		}
		if (returnPolicy < 0) {
			throw new IllegalArgumentException("The returnPolicy cannot be a negative number");
		}
		if (inStoreQuantity < 0) {
			throw new IllegalArgumentException("The inStoreQuantity cannot be a negative number");
		}
		Item item = new Item();
		item.setImage(image);
		item.setName(name);
		item.setPrice(price);
		item.setPoint(point);
		item.setReturnPolicy(returnPolicy);
		item.setPickup(pickup);
		item.setInStoreQuantity(inStoreQuantity);
		itemRepository.save(item);
		return item;
	}
	
	/**
	 * getting all the items
	 * @return List<Item>
	 */
	@Transactional
	public List<Item> getAllItems() {
		return toList(itemRepository.findAll());
	}
	
	/**
	 * getting the item by id
	 * @param id the id of the item
	 * @return Item
	 */
	@Transactional
	public Item getItemById(int id) {
		
		if (id < 0) {
			throw new IllegalArgumentException("The id cannot be a negative number");
		}
		Item item = itemRepository.findItemById(id);
		if(item == null) {
			throw new IllegalArgumentException("No item with id " + id + " exists");
		}
		return item;
	}
	
	/**
	 * getting all items containing ____
	 * @param name the string the item name should contain
	 * @return List<Item>
	 */
	@Transactional
	public List<Item> getItemByNameContaining(String name) {
		
		if (name == null || name == "") {
			throw new IllegalArgumentException("The name cannot be null or empty");
		}
		List<Item> items = itemRepository.findItemByNameContainingIgnoreCase(name);
		if(items == null || items.isEmpty()) {
			throw new IllegalArgumentException("No items with name containing '" + name + "' exists");
		}
		return items;
	}
	
	/**
	 * getting all items with the prices between a given range
	 * @param minPrice the minimum price
	 * @param maxPrice the maximum price
	 * @return List<Item>
	 */
	@Transactional
	public List<Item> getItemByPriceBetween(int minPrice, int maxPrice) {
		
		if (maxPrice < minPrice) {
			throw new IllegalArgumentException("The max price cannot be lower than min price");
		}
		List<Item> items = itemRepository.findItemByPriceBetween(minPrice, maxPrice);
		if(items == null || items.isEmpty()) {
			throw new IllegalArgumentException("No items with price between $" + minPrice + " & $" + maxPrice + " exists");
		}
		return items;
	}
	
	/**
	 * getting all items with the prices between a given range
	 * @param minPoint the minimum point
	 * @param maxPoint the maximum point
	 * @return List<Item>
	 */
	@Transactional
	public List<Item> getItemByPointBetween(int minPoint, int maxPoint) {
		
		if (maxPoint < minPoint) {
			throw new IllegalArgumentException("The max amount of points cannot be lower than min amount of points");
		}
		List<Item> items = itemRepository.findItemByPointBetween(minPoint, maxPoint);
		if(items == null || items.isEmpty()) {
			throw new IllegalArgumentException("No items with point between " + minPoint + " & " + maxPoint + " exists");
		}
		return items;
	}
	
	/**
	 * getting all items with the return policy between a given range
	 * @param minDays the minimum return policy
	 * @param maxDays the maximum return policy
	 * @return
	 */
	@Transactional
	public List<Item> getItemByReturnPolicyBetween(int minDays, int maxDays) {
		
		if (maxDays < minDays) {
			throw new IllegalArgumentException("The max return policy days cannot be lower than min return policy days");
		}
		List<Item> items = itemRepository.findItemByReturnPolicyBetween(minDays, maxDays);
		if(items == null || items.isEmpty()) {
			throw new IllegalArgumentException("No items with return policy between " + minDays + " & " + maxDays + " exists");
		}
		return items;
	}
	
	/**
	 * getting all items that is either available to be picked up, or not available to be picked up
	 * @param pickup determining the category we want to get
	 * @return List<Item>
	 */
	@Transactional
	public List<Item> getItemByPickup(boolean pickup) {
		
		List<Item> items = itemRepository.findItemByPickup(pickup);
		if(items == null || items.isEmpty()) {
			throw new IllegalArgumentException("No items with pickup being " + pickup + " exists");
		}
		return items;
	}
	
	/**
	 * getting all items with the in store quantity between a given range
	 * @param minQuantity the minimum quantity
	 * @param maxQuantity the maximum quantity
	 * @return List<Item>
	 */
	@Transactional
	public List<Item> getItemByInStoreQuantityBetween(int minQuantity, int maxQuantity) {
		
		if (maxQuantity < minQuantity) {
			throw new IllegalArgumentException("The max quantity cannot be lower than min quantity");
		}
		List<Item> items = itemRepository.findItemByInStoreQuantityBetween(minQuantity, maxQuantity);
		if(items == null || items.isEmpty()) {
			throw new IllegalArgumentException("No items with in store quantity between " + minQuantity + " & " + maxQuantity + " exists");
		}
		return items;
	}
	
	/**
	 * updating the item. Update could apply to either one or all attirbutes
	 * @param id the item's id
	 * @param name the new name of the tem
	 * @param price the new price
	 * @param point the new point
	 * @param returnPolicy the new return policy
	 * @param pickup the new pickup
	 * @param inStoreQuantity the new in store quantity
	 * @return
	 */
	@Transactional
	public Item updateItem(String image, int id, String name, int price, int point, int returnPolicy, boolean pickup, int inStoreQuantity) {
		if(id < 0) {
			throw new IllegalArgumentException("Id cannot be a negative number");
		}
		if(name == null || name.strip() == "") {
			throw new IllegalArgumentException("Name cannot be null or empty");
		}
		if(price < 0) {
			throw new IllegalArgumentException("Price cannot be a negative number");
		}
		if(point < 0) {
			throw new IllegalArgumentException("Point cannot be a negative number");
		}
		if(returnPolicy < 0) {
			throw new IllegalArgumentException("Return policy cannot be a negative number");
		}
		if(inStoreQuantity < 0) {
			throw new IllegalArgumentException("In store quantity cannot be a negative number");
		}
		Item item = itemRepository.findItemById(id);
		if(item == null) {
			throw new IllegalArgumentException("Item with id " + id + " does not exists");
		}
		item.setImage(image);
		item.setName(name);
		item.setPrice(price);
		item.setPoint(point);
		item.setReturnPolicy(returnPolicy);
		item.setPickup(pickup);
		item.setInStoreQuantity(inStoreQuantity);
		itemRepository.save(item);
		return item;
	}
	
	/**
	 * deleting the item by id
	 * @param id the id of that item
	 * @return boolean- whether it is deleted succcessfully or not
	 */
	@Transactional
	public boolean deleteItemById(int id) {
		if (id < 0) {
			return false;
		}else {
			Item item = itemRepository.findItemById(id);
			if(item == null) {
				return false;
			}
			List<Quantity> quantities = quantityRepository.findQuantityByItem(item);
			for(int i=0; i<quantities.size(); i++) {
				quantityService.deleteQuantityById(quantities.get(i).getId());
			}
			itemRepository.delete(item);
			return true;
		}
	}
	
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
}
