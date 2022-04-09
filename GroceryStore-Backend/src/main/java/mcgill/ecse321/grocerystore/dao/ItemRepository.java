package mcgill.ecse321.grocerystore.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import mcgill.ecse321.grocerystore.model.Item;

public interface ItemRepository extends CrudRepository<Item, Integer>{

	Item findItemById(int id);
	
	List<Item> findItemByName(String name);
	
	List<Item> findItemByNameAndPickup(String name, boolean pickup);
	
	List<Item> findItemByNameContainingIgnoreCase(String name);
	
	List<Item> findItemByPriceBetween(int minPrice, int maxPrice);
	
	List<Item> findItemByPriceBetweenAndPickup(int minPrice, int maxPrice, boolean pickup);
	
	List<Item> findItemByPointBetween(int minPoint, int maxPoint);
	
	List<Item> findItemByPointBetweenAndPickup(int minPoint, int maxPoint, boolean pickup);
	
	List<Item> findItemByPriceBetweenAndPointBetween(int minPrice, int maxPrice, int minPoint, int maxPoint);
	
	List<Item> findItemByReturnPolicyBetween(int minDays, int maxDays);
	
	List<Item> findItemByReturnPolicyBetweenAndPickup(int minDays, int maxDays, boolean pickup);
	
	List<Item> findItemByPickup(boolean pickup);
	
	List<Item> findItemByInStoreQuantityBetween(int minQuantity, int maxQuantity);
}
