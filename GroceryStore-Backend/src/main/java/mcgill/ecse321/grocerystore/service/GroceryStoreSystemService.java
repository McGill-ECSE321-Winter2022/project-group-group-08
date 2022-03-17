package mcgill.ecse321.grocerystore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mcgill.ecse321.grocerystore.dao.BusinessHourRepository;
import mcgill.ecse321.grocerystore.dao.GroceryStoreSystemRepository;
import mcgill.ecse321.grocerystore.model.BusinessHour;
import mcgill.ecse321.grocerystore.model.GroceryStoreSystem;


@Service
public class GroceryStoreSystemService {
	
	@Autowired
	BusinessHourRepository businessHourRepository;
	@Autowired
	GroceryStoreSystemRepository groceryStoreSystemRepository;
	@Autowired
	BusinessHourService businessHourService;
	
	/**
	 * creating the grocery store system
	 * @param storeName the name of the store
	 * @param address the address of the store
	 * @param employeeDiscount the discount all employees can have
	 * @return
	 */
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
		groceryStoreSystemRepository.save(groceryStoreSystem);
		return groceryStoreSystem;
	}
	
	/**
	 * getting grocery store by name
	 * @param storeName the name of the grocery store
	 * @return GroceryStoreSystem
	 */
	@Transactional
	public GroceryStoreSystem getGroceryStoreSystem(String storeName) {
		if (storeName == null || storeName.trim().length() == 0) {
			throw new IllegalArgumentException("The store name cannot be empty.");
		}
		GroceryStoreSystem groceryStoreSystem = groceryStoreSystemRepository.findGroceryStoreSystemByStoreName(storeName);
		return groceryStoreSystem;
	}
	
	/**
	 * deleting a grocerystore system
	 * @param groceryStoreSystem the system we want to delete
	 * @return boolean - whether the deletion was successfull or not
	 */
	@Transactional
	public boolean deleteGroceryStoreSystem(GroceryStoreSystem groceryStoreSystem) {
		if (groceryStoreSystem == null) {
			return false;
		}else {
			List<BusinessHour> businessHours = businessHourRepository.findBusinessHoursByGroceryStoreSystem(groceryStoreSystem);
			for(int i=0; i<businessHours.size(); i++) {
				businessHourService.deleteBusinessHourbyID(businessHours.get(i).getId());
			}
			groceryStoreSystemRepository.delete(groceryStoreSystem);
			return true;
		}
	}
	
	/**
	 * deleting a grocerystore system using its name
	 * @param storeName the name of the grocery store 
	 * @return boolean - whether the deletion was successfull or not
	 */
	@Transactional
	public boolean deleteGroceryStoreSystemByStoreName(String storeName) {
		if (storeName == null || storeName.trim().length() == 0) {
			return false;
		}else {
			GroceryStoreSystem groceryStoreSystem = groceryStoreSystemRepository.findGroceryStoreSystemByStoreName(storeName);
			List<BusinessHour> businessHours = businessHourRepository.findBusinessHoursByGroceryStoreSystem(groceryStoreSystem);
			for(int i=0; i<businessHours.size(); i++) {
				businessHourService.deleteBusinessHourbyID(businessHours.get(i).getId());
			}
			groceryStoreSystemRepository.delete(groceryStoreSystem);
			return true;	
		}
	}
	
	/**
	 * updating a grocery store system
	 * @param storeName the store name
	 * @param address the new address
	 * @param employeeDiscount the new employee discount
	 * @return GroceryStoreSystem
	 */
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
		groceryStoreSystemRepository.save(groceryStoreSystem);
		return groceryStoreSystem;
	}	
}
