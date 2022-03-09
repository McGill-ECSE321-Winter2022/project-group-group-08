package mcgill.ecse321.grocerystore.service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mcgill.ecse321.grocerystore.dao.BusinessHourRepository;
import mcgill.ecse321.grocerystore.dao.ItemRepository;
import mcgill.ecse321.grocerystore.dao.GroceryStoreSystemRepository;
import mcgill.ecse321.grocerystore.model.BusinessHour;
import mcgill.ecse321.grocerystore.model.BusinessHour.WeekDay;
import mcgill.ecse321.grocerystore.model.Item;
import mcgill.ecse321.grocerystore.model.GroceryStoreSystem;


@Service
public class GroceryStoreSystemService {
	
	@Autowired
	BusinessHourRepository businessHourRepository;
	@Autowired
	ItemRepository itemRepository;
	@Autowired
	GroceryStoreSystemRepository groceryStoreSystemRepository;
	
	@Transactional
	public GroceryStoreSystem createGroceryStoreSystem(String storeName, String address, int employeeDiscount) {
		if (storeName == null || address == null) {
			throw new IllegalArgumentException("The store name or the address cannot be empty.");
		}
		if(employeeDiscount < 0) {
			throw new IllegalArgumentException("The employee discount cannot be less than 0.");
		}
		GroceryStoreSystem groceryStoreSystem = new GroceryStoreSystem();
		groceryStoreSystem.setStoreName(storeName);
		groceryStoreSystem.setAddress(address);
		groceryStoreSystem.setEmployeeDiscount(employeeDiscount);
		return groceryStoreSystem;
	}
	
	@Transactional
	public GroceryStoreSystem getGroceryStoreSystem(String storeName) {
		GroceryStoreSystem groceryStoreSystem = groceryStoreSystemRepository.findGroceryStoreSystemByStoreName(storeName);
		return groceryStoreSystem;
	}
	
	@Transactional
	public Item createItem(int id, String name, int price, int point, int returnPolicy, boolean pickup, int inStoreQuantity) {
		String error = "";
		if (name == null || name.trim().length() == 0) {
		    error = error + "Item name cannot be empty! ";
		}
		if (id < 0) {
		    error = error + "ID cannot be negative ";
		}
		if (price < 0) {
			error = error + "Price cannot be negative ";
		}
		if (point < 0) {
			error = error + "Point cannot be negative ";
		}
		if (returnPolicy < 0) {
			error = error + "Return policy cannot be negative ";
		}
		if (inStoreQuantity < 0) {
			error = error + "In store quantity cannot be negative ";
		}
		error = error.trim();
		if (error.length() > 0) {
		    throw new IllegalArgumentException(error);
		}
		Item item = new Item();
		item.setId(id);
		item.setName(name);
		item.setPrice(price);
		item.setPoint(point);
		item.setReturnPolicy(returnPolicy);
		item.setPickup(pickup);
		item.setInStoreQuantity(inStoreQuantity);
		return item;
	}
	
	@Transactional
	public Item getItem(int id) {
		Item item = itemRepository.findItemById(id);
		return item;
	}
	
	@Transactional
	public List<Item> getAllItems(){
		return toList(itemRepository.findAll());
	}
	
	@Transactional 
	public BusinessHour createBusinessHour(int id, WeekDay day, Time startTime, Time endTime, boolean working){
		String error= "";
		if (id == 0){
			throw new IllegalArgumentException("Business Hour id cannot be empty");
		}
		if (day == null){
			throw new IllegalArgumentException("Week day cannot be empty");
		}
		if (startTime == null){
			throw new IllegalArgumentException("Start time cannot be empty");
		}
		if (endTime == null){
			throw new IllegalArgumentException("End time cannot be empty");
		}
		if (startTime.toLocalTime().isAfter(endTime.toLocalTime())){
			throw new IllegalArgumentException("End time cannot be earlier than Start time");
		}
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		BusinessHour businessHour = new BusinessHour();
		businessHour.setId(id);
		businessHour.setDay(day);
		businessHour.setStartTime(startTime);
		businessHour.setEndTime(endTime);
		return businessHour;
	}
	

	
	@Transactional
	public BusinessHour getBusinessHour(int id) {
		BusinessHour businessHour = businessHourRepository.findBusinessHourById(id);
		return businessHour;
}
	


	
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}	
	
	
}






