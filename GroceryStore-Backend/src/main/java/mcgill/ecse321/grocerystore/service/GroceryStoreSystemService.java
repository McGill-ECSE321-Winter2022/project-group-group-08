package mcgill.ecse321.grocerystore.service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
		if (storeName == null || storeName.trim().length() == 0) {
			throw new IllegalArgumentException("The store name cannot be empty.");
		}
		if (address == null || address.trim().length() == 0) {
			throw new IllegalArgumentException("The address cannot be empty.");
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
		if (storeName == null || storeName.trim().length() == 0) {
			throw new IllegalArgumentException("The store name cannot be empty.");
		}
		GroceryStoreSystem groceryStoreSystem = groceryStoreSystemRepository.findGroceryStoreSystemByStoreName(storeName);
		return groceryStoreSystem;
	}
	
	
	
	
	@Transactional
	public boolean deleteGroceryStoreSystem(GroceryStoreSystem groceryStoreSystem) {
		if (groceryStoreSystem == null) {
			return false;
		}else {
			groceryStoreSystemRepository.delete(groceryStoreSystem);
			return true;
		}
	}
	
	@Transactional
	public boolean deleteGroceryStoreSystemByStoreName(String storeName) {
		if (storeName == null || storeName.trim().length() == 0 ) {
			return false;
		}else {
			GroceryStoreSystem groceryStoreSystem = groceryStoreSystemRepository.findGroceryStoreSystemByStoreName(storeName);
			groceryStoreSystemRepository.delete(groceryStoreSystem);
			return true;
		}
	}
	
	@Transactional
	public GroceryStoreSystem updateGroceryStoreSystem(String storeName, String address, int employeeDiscount) {
		if(storeName == null || storeName.trim().length() == 0) {
			throw new IllegalArgumentException("Store name cannot be empty");
		}
		if(address == null || address.trim().length() == 0) {
			throw new IllegalArgumentException("Address cannot be empty");
		}
		if(employeeDiscount < 0) {
			throw new IllegalArgumentException("Employee discount cannot be negative");
		}
		GroceryStoreSystem groceryStoreSystem = groceryStoreSystemRepository.findGroceryStoreSystemByStoreName(storeName);
		if(groceryStoreSystem == null) {
			throw new IllegalArgumentException("The grocery store system does not exist");
		}
		groceryStoreSystem.setStoreName(storeName);
		groceryStoreSystem.setAddress(address);;
		groceryStoreSystem.setEmployeeDiscount(employeeDiscount);
		//groceryStoreSystem.setOpeningHours(businessHours);
		return groceryStoreSystem;
	}	
}
