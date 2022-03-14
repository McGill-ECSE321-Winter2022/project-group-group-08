package mcgill.ecse321.grocerystore.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mcgill.ecse321.grocerystore.model.Cart;
import mcgill.ecse321.grocerystore.model.Account;
import mcgill.ecse321.grocerystore.dto.CartDto;
import mcgill.ecse321.grocerystore.service.CartService;

@CrossOrigin(origins = "*")
@RestController
public class CartRestController {

    @Autowired
	private CartService cartService;

	@GetMapping(value = {"/cart/{id}", "/cart/{id}/"})
	public CartDto getCart(@PathVariable("id") int id) {
		Cart cart = cartService.getCart(id);
		return convertToDto(cart);
	}
	
	@PostMapping(value = {"/cart", "/cart/"})
	public CartDto createCart(
			@RequestParam(name = "date") Date date,
			@RequestParam(name = "account") Account account
			) {
		Cart cart = cartService.createCart(date,account);
		return convertToDto(cart);
	}
	
	@PatchMapping(value = {"/cart/update/{id}", "/cart/update/{id}/"})
	public CartDto updateCart(
			@PathVariable("id") int id,
			@RequestParam(name = "date") Date date,
			@RequestParam(name = "account") Account account
			) {
		Cart cart = cartService.updateCart(id, date, account);
		return convertToDto(cart);
	}
	
	@DeleteMapping(value = {"/cart/delete/{id}", "/cart/delete/{id}/"})
	public boolean deleteCart(@PathVariable("car") Cart cart) {
		boolean deleted = cartService.deleteCart(cart);
		return deleted;
	}

	private CartDto convertToDto(Cart c) {
		if (c == null) {
			throw new IllegalArgumentException("There is no such Cart!");
		}
		CartDto cartDto = new CartDto();
		return cartDto;
	}
}
