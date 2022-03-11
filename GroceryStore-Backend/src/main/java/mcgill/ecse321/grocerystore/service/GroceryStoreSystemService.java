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
	public BusinessHour createBusinessHour(int id, WeekDay day, Time startTime, Time endTime, boolean working){
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
	
	@Transactional
	public void updateBusinessHour(GroceryStoreSystem groceryStoreSystem, WeekDay day, Time startTime, Time endTime, boolean working) {
		if(day == null) {
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
		Set<BusinessHour> businessHours = groceryStoreSystem.getOpeningHours();
		for(BusinessHour businessHour : businessHours) {
			WeekDay shift = businessHour.getDay();
			if(day.equals(shift)) {
				businessHour.setStartTime(startTime);
				businessHour.setEndTime(endTime);
				businessHour.setWorking(working);
			}
		}	
		
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
		if (storeName.trim().length() == 0 || storeName.equals(null)) {
			return false;
		}else {
			GroceryStoreSystem groceryStoreSystem = groceryStoreSystemRepository.findGroceryStoreSystemByStoreName(storeName);
			groceryStoreSystemRepository.delete(groceryStoreSystem);
			return true;
		}
	}
	
	@Transactional
	public GroceryStoreSystem updateGroceryStoreSystem(String storeName, String address, int employeeDiscount) {
		if(storeName.trim().length() == 0 || storeName.equals(null)) {
			throw new IllegalArgumentException("Store name cannot be empty");
		}
		if(address.trim().length() == 0 || address.equals(null)) {
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
