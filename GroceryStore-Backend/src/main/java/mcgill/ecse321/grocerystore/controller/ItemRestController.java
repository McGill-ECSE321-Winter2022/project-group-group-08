package mcgill.ecse321.grocerystore.controller;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mcgill.ecse321.grocerystore.dto.ItemDto;
import mcgill.ecse321.grocerystore.model.Item;
import mcgill.ecse321.grocerystore.service.ItemService;

@CrossOrigin(origins = "*")
@RestController
public class ItemRestController {

	@Autowired
	private ItemService itemService;
	private static final String baseURL = "/item";
	
	//getting all items
	@GetMapping(value = {baseURL+"/all", baseURL+"/all/"})
	public List<ItemDto> getAllItems() {
		return itemService.getAllItems().stream().map(i -> convertToDto(i)).collect(Collectors.toList());
	}
	
	//getting a particular item with it's id
	@GetMapping(value = {baseURL+"/{id}", baseURL+"/{id}/"})
	public ItemDto getItem(@PathVariable("id") int id) {
		Item item = itemService.getItemById(id);
		return convertToDto(item);
	}
	
	//getting a list of items with the name containing a given string
	@GetMapping(value = {baseURL+"/name/{name}", baseURL+"/name/{name}/"})
	public List<ItemDto> getItemByNameContaining(@PathVariable("name") String name) {
		List<ItemDto> items = itemService.getItemByNameContaining(name).stream().map(i -> convertToDto(i)).collect(Collectors.toList());
		return items;
	}
	
	//getting a list of items with prices between a given range
	@GetMapping(value = {baseURL+"/price/{minPrice}/{maxPrice}", baseURL+"/price/{minPrice}/{maxPrice}/"})
	public List<ItemDto> getItemByPriceBetween(
			@PathVariable("minPrice") int minPrice, 
			@PathVariable("maxPrice") int maxPrice
			) {
		List<ItemDto> items = itemService.getItemByPriceBetween(minPrice, maxPrice).stream().map(i -> convertToDto(i)).collect(Collectors.toList());
		return items;
	}
	
	//getting a list of items with points between a given range
	@GetMapping(value = {baseURL+"/point/{minPoint}/{maxPoint}", baseURL+"/point/{minPoint}/{maxPoint}/"})
	public List<ItemDto> getItemByPointBetween(
			@PathVariable("minPoint") int minPoint, 
			@PathVariable("maxPoint") int maxPoint
			) {
		List<ItemDto> items = itemService.getItemByPointBetween(minPoint, maxPoint).stream().map(i -> convertToDto(i)).collect(Collectors.toList());
		return items;
	}
	
	//getting a list of items with return policy between a given range
	@GetMapping(value = {baseURL+"/returnPolicy/{minReturnPolicy}/{maxReturnPolicy}", baseURL+"/returnPolicy/{minReturnPolicy}/{maxReturnPolicy}/"})
	public List<ItemDto> getItemByReturnPolicyBetween(
			@PathVariable("minReturnPolicy") int minReturnPolicy, 
			@PathVariable("maxReturnPolicy") int maxReturnPolicy
			) {
		List<ItemDto> items = itemService.getItemByReturnPolicyBetween(minReturnPolicy, maxReturnPolicy).stream().map(i -> convertToDto(i)).collect(Collectors.toList());
		return items;
	}
	
	//getting a list of item categorized by whether the item is available for pickup or not
	@GetMapping(value = {baseURL+"/pickup/{pickup}", baseURL+"/pickup/{pickup}/"})
	public List<ItemDto> getItemByPickup(@PathVariable("pickup") boolean pickup) {
		List<ItemDto> items = itemService.getItemByPickup(pickup).stream().map(i -> convertToDto(i)).collect(Collectors.toList());
		return items;
	}
	
	//getting a lists of items with in store quantity given a range
	@GetMapping(value = {baseURL+"/inStoreQuantity/{minInStoreQuantity}/{maxInStoreQuantity}", baseURL+"/inStoreQuantity/{minInStoreQuantity}/{maxInStoreQuantity}/"})
	public List<ItemDto> getItemByInStoreQuantityBetween(
			@PathVariable("minInStoreQuantity") int minInStoreQuantity, 
			@PathVariable("maxInStoreQuantity") int maxInStoreQuantity
			) {
		List<ItemDto> items = itemService.getItemByInStoreQuantityBetween(minInStoreQuantity, maxInStoreQuantity).stream().map(i -> convertToDto(i)).collect(Collectors.toList());
		return items;
	}
	
	//creating an item
	@PostMapping(value = {baseURL, baseURL+"/"})
	public ItemDto createItem(
			@RequestParam(name = "name") String name,
			@RequestParam(name = "price") int price,
			@RequestParam(name = "point") int point,
			@RequestParam(name = "returnPolicy") int returnPolicy,
			@RequestParam(name = "pickup") boolean pickup,
			@RequestParam(name = "inStoreQuantity") int inStoreQuantity
			) {
		Item item = itemService.createItem(name, price, point, returnPolicy, pickup, inStoreQuantity);
		return convertToDto(item);
	}
	
	//updating an item
	@PatchMapping(value = {baseURL + "/update/{id}", baseURL+"/update/{id}/"})
	public ItemDto updateItem(
			@PathVariable("id") int id,
			@RequestParam(name = "name") String name,
			@RequestParam(name = "price") int price,
			@RequestParam(name = "point") int point,
			@RequestParam(name = "returnPolicy") int returnPolicy,
			@RequestParam(name = "pickup") boolean pickup,
			@RequestParam(name = "inStoreQuantity") int inStorQuantity
			) {
		Item item = itemService.updateItem(id, name, price, point, returnPolicy, pickup, inStorQuantity);
		return convertToDto(item);
	}
	
	//deleting an ite,
	@DeleteMapping(value = {baseURL + "/delete/{id}", baseURL+"/delete/{id}/"})
	public ItemDto deleteItem(@PathVariable("id") int id) {
		Item item = itemService.deleteItemById(id);
		return convertToDto(item);
	}
	
	//converting item to its DTO because of the MVC model
	private ItemDto convertToDto(Item item) {
		if(item == null) {
			throw new IllegalArgumentException("There is no such item!");
		}
		ItemDto itemDto = new ItemDto(item.getId(), item.getName(), item.getPrice(), item.getPoint(), item.getReturnPolicy(), item.getPickup(), item.getInStoreQuantity());
		return itemDto;
	}

}