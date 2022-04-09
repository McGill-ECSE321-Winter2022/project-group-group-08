package mcgill.ecse321.grocerystore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mcgill.ecse321.grocerystore.dto.GroceryStoreSystemDto;
import mcgill.ecse321.grocerystore.model.GroceryStoreSystem;
import mcgill.ecse321.grocerystore.service.GroceryStoreSystemService;

@CrossOrigin(origins = "*")
@RestController
public class GroceryStoreSystemRestController {
	
	@Autowired
	private GroceryStoreSystemService groceryStoreSystemService;
	private static final String baseURL = "/grocerystoresystem";
	
	/**
	 * Creates grocery store system
	 * @param storename primary key 
	 * @param address address of store
	 * @param employeeDiscount discount of store
	 * @return grocery store system
	 */
	@PostMapping(value = {baseURL, baseURL+"/"})
	public GroceryStoreSystemDto createGroceryStoreSystem(
			@RequestParam(name = "storename") String storename,
			@RequestParam(name = "address") String address,
			@RequestParam(name = "employeeDiscount") int employeeDiscount
			) {
		GroceryStoreSystem groceryStoreSystem = groceryStoreSystemService.createGroceryStoreSystem(storename, address, employeeDiscount);
		return convertToDto(groceryStoreSystem);
	}
	
	/**
	 * Gets grocery store system
	 * @param storename primary key
	 * @return grocery store system
	 */
	@GetMapping(value = {baseURL+"/{storename}", baseURL+"/{storename}/"})
	public GroceryStoreSystemDto getGroceryStoreSystem(@PathVariable("storename")String storename) {
		GroceryStoreSystem groceryStoreSystem = groceryStoreSystemService.getGroceryStoreSystem(storename);
		return convertToDto(groceryStoreSystem);
	}
	
	/**
	 * Updates grocery store system
	 * @param storename primary key 
	 * @param address new address
	 * @param employeeDiscount new Employee discount
	 * @return grocery store system
	 */
	@PatchMapping(value = {baseURL + "/update/{storename}", baseURL+"/update/{storename}/"})
	public GroceryStoreSystemDto updateGroceryStoreSystem(
			@PathVariable("storename") String storename,
			@RequestParam(name = "address") String address,
			@RequestParam(name = "employeeDiscount") int employeeDiscount
			) {
		GroceryStoreSystem groceryStoreSystem = groceryStoreSystemService.updateGroceryStoreSystem(storename, address, employeeDiscount);
		return convertToDto(groceryStoreSystem);
	}
	
	/**
	 * Delete grocery store system
	 * @param storename primary key of store
	 * @return grocery store system
	 */
	@DeleteMapping(value = {baseURL + "/delete/{storename}", baseURL+"/delete/{storename}/"})
	public GroceryStoreSystemDto deleteGroceryStoreSystem(@PathVariable("storename") String storename) {
		GroceryStoreSystem groceryStoreSystem = groceryStoreSystemService.getGroceryStoreSystem(storename);
		groceryStoreSystemService.deleteGroceryStoreSystem(groceryStoreSystem);
		return convertToDto(groceryStoreSystem);
	}
	

	private GroceryStoreSystemDto convertToDto(GroceryStoreSystem groceryStoreSystem) {
		GroceryStoreSystemDto groceryStoreSystemDto = new GroceryStoreSystemDto(groceryStoreSystem.getStoreName(), 
		groceryStoreSystem.getAddress(), groceryStoreSystem.getEmployeeDiscount());
		return groceryStoreSystemDto;
	}
	
	
	
}
