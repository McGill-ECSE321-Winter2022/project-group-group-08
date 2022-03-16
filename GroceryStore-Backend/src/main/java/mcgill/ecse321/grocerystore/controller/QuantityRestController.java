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

import mcgill.ecse321.grocerystore.dto.CartDto;
import mcgill.ecse321.grocerystore.dto.ItemDto;
import mcgill.ecse321.grocerystore.dto.QuantityDto;
import mcgill.ecse321.grocerystore.model.Cart;
import mcgill.ecse321.grocerystore.model.Item;
import mcgill.ecse321.grocerystore.model.Quantity;
import mcgill.ecse321.grocerystore.service.CartService;
import mcgill.ecse321.grocerystore.service.ItemService;
import mcgill.ecse321.grocerystore.service.QuantityService;

@CrossOrigin(origins = "*")
@RestController
public class QuantityRestController {

	@Autowired
	private QuantityService quantityService;
	@Autowired
	private ItemService itemService;
	@Autowired
	private CartService cartService;
	
	private static final String baseURL = "/quantity";
	
	//getting all quantities
	@GetMapping(value = {baseURL+"/all", baseURL+"/all/"})
	public List<QuantityDto> getAllQuantities() {
		return quantityService.getAllQuantities().stream().map(q -> convertToDto(q)).collect(Collectors.toList());
	}
	
	//getting a specific quantity with a given id
	@GetMapping(value = {baseURL+"/{id}", baseURL+"/{id}/"})
	public QuantityDto getQuantity(@PathVariable("id") int id) {
		Quantity quantity = quantityService.getQuantityById(id);
		return convertToDto(quantity);
	}
	
	//getting a specific quantity with an item id
	@GetMapping(value = {baseURL+"/itemId/{itemId}", baseURL+"/itemId/{itemId}/"})
	public List<QuantityDto> getQuantityByItem(@PathVariable("itemId") int itemId) {
		return quantityService.getQuantityByItemId(itemId).stream().map(q -> convertToDto(q)).collect(Collectors.toList());
	}
	
	//getting a list of quantities that are associated to a cart
	@GetMapping(value = {baseURL+"/cartId/{cartId}", baseURL+"/cartId/{cartId}/"})
	public List<QuantityDto> getQuantityByCart(@PathVariable("cartId") int cartId) {
		return quantityService.getQuantityByCartId(cartId).stream().map(q -> convertToDto(q)).collect(Collectors.toList());
	}
	
	//adding a new quantity
	@PostMapping(value = {baseURL, baseURL+"/"})
	public QuantityDto createQuantity(
			@RequestParam(name = "count") int count,
			@RequestParam(name = "itemId") int itemId,
			@RequestParam(name = "cartId") int cartId
			) {
		Item item = itemService.getItemById(itemId);
		if(item == null) {
			throw new IllegalArgumentException("This item does not exists");
		}
		Cart cart = cartService.getCart(cartId);
		if(cart == null) {
			throw new IllegalArgumentException("This cart does not exists");
		}
		Quantity quantity = quantityService.createQuantity(count, item, cart);
		return convertToDto(quantity);
	}
	
	//updating a quantity
	@PatchMapping(value = {baseURL + "/update/{id}", baseURL+"/update/{id}/"})
	public QuantityDto updateQuantity(
			@PathVariable("id") int id,
			@RequestParam(name = "count") int count,
			@RequestParam(name = "itemId") int itemId,
			@RequestParam(name = "cartId") int cartId
			) {
		Item item = itemService.getItemById(itemId);
		if(item == null) {
			throw new IllegalArgumentException("This item does not exists");
		}
		Cart cart = cartService.getCart(cartId);
		if(cart == null) {
			throw new IllegalArgumentException("This cart does not exists");
		}
		Quantity quantity = quantityService.updateQuantity(id, count, item, cart);
		return convertToDto(quantity);
	}
	
	//deleting a  quantity
	@DeleteMapping(value = {baseURL + "/delete/{id}", baseURL+"/delete/{id}/"})
	public QuantityDto deleteQuantity(@PathVariable("id") int id) {
		Quantity quantity = quantityService.getQuantityById(id);
		quantityService.deleteQuantityById(id);
		return convertToDto(quantity);
	}
	
	//converting item to its DTO because of the MVC model
	private QuantityDto convertToDto(Quantity quantity) {
		if(quantity == null) {
			throw new IllegalArgumentException("There is no such quantity!");
		}
		QuantityDto quantityDto = new QuantityDto(quantity.getId(), quantity.getCount(), ItemDto.convertToDto(quantity.getItem()), CartDto.convertToDto(quantity.getCart()));
		return quantityDto;
	}

}