package mcgill.ecse321.grocerystore.service;

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
	
	@Transactional
	public Item createItem(String name, int price, int point, int returnPolicy, boolean pickup, int inStoreQuantity) {
		if(name == null || name.strip() == ""|| name.equals("undefined")) {
			throw new IllegalArgumentException("Item name cannot be null or empty");
		}
		Item item = new Item();
		item.setName(name);
		item.setPrice(price);
		item.setPoint(point);
		item.setReturnPolicy(returnPolicy);
		item.setPickup(pickup);
		item.setInStoreQuantity(inStoreQuantity);
		return item;
	}
	

	
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
	
	@Transactional
	public List<Item> getItemByName(String name) {
		
		if (name == null || name == "") {
			throw new IllegalArgumentException("The name cannot be null or empty");
		}
		List<Item> items = itemRepository.findItemByNameIgnoreCase(name);
		if(items == null || items.isEmpty()) {
			throw new IllegalArgumentException("No items with name '" + name + "' exists");
		}
		return items;
	}
	
	@Transactional
	public List<Item> getItemByNameAndPickup(String name, boolean pickup) {
		
		if (name == null || name == "") {
			throw new IllegalArgumentException("The name cannot be null or empty");
		}
		List<Item> items = itemRepository.findItemByNameAndPickup(name, pickup);
		if(items == null || items.isEmpty()) {
			throw new IllegalArgumentException("No items with name '" + name + "' and pickup '" + pickup + "' exists");
		}
		return items;
	}
	
	@Transactional
	public List<Item> getItemByNameContaining(String name) {
		
		if (name == null || name == "") {
			throw new IllegalArgumentException("The name cannot be null or empty");
		}
		List<Item> items = itemRepository.findItemByNameContaining(name);
		if(items == null || items.isEmpty()) {
			throw new IllegalArgumentException("No items with name containing '" + name + "' exists");
		}
		return items;
	}
	
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
	
	@Transactional
	public List<Item> getItemByPickup(boolean pickup) {
		
		List<Item> items = itemRepository.findItemByPickup(pickup);
		if(items == null || items.isEmpty()) {
			throw new IllegalArgumentException("No items with pickup being " + pickup + " exists");
		}
		return items;
	}
	
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
	
	@Transactional
	public boolean deleteItem(Item item) {
		if (item == null) {
			return false;
		}else {
			itemRepository.delete(item);
			return true;
		}
	}
	
	@Transactional
	public boolean deleteItemById(int id) {
		if (id < 0) {
			return false;
		}else {
			Item item = itemRepository.findItemById(id);
			itemRepository.delete(item);
			return true;
		}
	}
}
