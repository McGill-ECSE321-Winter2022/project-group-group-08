package mcgill.ecse321.grocerystore.controller;

import java.sql.Date;
import java.sql.Time;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mcgill.ecse321.grocerystore.model.Cart;
import mcgill.ecse321.grocerystore.model.Receipt;
import mcgill.ecse321.grocerystore.model.Quantity;
import mcgill.ecse321.grocerystore.model.Account;
import mcgill.ecse321.grocerystore.model.BusinessHour.WeekDay;
import mcgill.ecse321.grocerystore.dto.AccountDto;
import mcgill.ecse321.grocerystore.dto.ReceiptDto;
import mcgill.ecse321.grocerystore.dto.CartDto;
import mcgill.ecse321.grocerystore.dto.QuantityDto;
import mcgill.ecse321.grocerystore.dto.UserRoleDto;
import mcgill.ecse321.grocerystore.service.AccountService;
import mcgill.ecse321.grocerystore.service.BusinessHourService;
import mcgill.ecse321.grocerystore.service.CartService;

@CrossOrigin(origins = "*")
@RestController
public class CartRestController {

    @Autowired
	private CartService cartService;
	@Autowired
	private BusinessHourService businesshourService;

	//EMLPOYEEDTO
	@GetMapping(value = { "/employee/{id}", "/employee/{id}/" })
	public UserRoleDto getEmployee(@PathVariable("id") int id) throws IllegalArgumentException {
		return convertToDto(service.getEmployee(id));
	}
	
	@PostMapping(value = { "/employee/{id}", "/employee/{id}/" })
	public UserRoleDto createEmployee(@PathVariable("id") int id) throws IllegalArgumentException {
		Employee employee = service.createEmployee(id);
		return convertToDto(employee);
	}
	
	//GROCERYSTORESYSTEMDTO
	@GetMapping(value = { "/grocerystoresystem/{storeName}", "/grocerystoresystem/{storeName}/" })
	public GroceryStoreSystemDto getgroceryStoreSystem(@PathVariable("storeName") String storeName) throws IllegalArgumentException {
		return convertToDto(service.getgroceryStoreSystem(storeName));
	}
	
	@PostMapping(value = { "/grocerystoresystem/{storeName}", "/grocerystoresystem/{storeName}/"})
	public GroceryStoreSystemDto createGroceryStoreSystem(@PathVariable("storeName") String storeName, @RequestParam String address, @RequestParam int employeeDiscount) throws IllegalArgumentException {
		GroceryStoreSystem grocerystoresystem = service.creategrocerystoresystem(storeName);
		return convertToDto(grocerystoresystem);
	}
	
	//BUSINESSHOUR
	@PostMapping(value = { "/businesshour", "/businesshour/" })
	public BusinessHourDto creatBusinessHour(@RequestParam int id, @RequestParam WeekDay day, @RequestParam Time startTime,
			@RequestParam Time endTime, @RequestParam Boolean working, @RequestParam(name = "employee") EmployeeDto employeeDto,
			@RequestParam(name = "grocerystoresystem") GreoceryStoreSystem greocerystoresystemDto) throws IllegalArgumentException {
		Employee e = service.getEmployee(employeeDto.getId());
		GroceryStoreSystem g = service.getgroceryStoreSystem(greocerystoresystemDto.getstoreName());

		BusinessHour b = service.createBusinessHour(id, day, startTime, endTime, working, e, g);
		return convertToDto(b);
	}
	
	
	private CartDto convertToDto(Cart c) {
		if (c == null) {
			throw new IllegalArgumentException("There is no such Cart!");
		}
		CartDto cartDto = new CartDto();
		return cartDto;
	}
}
