package mcgill.ecse321.grocerystore.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import mcgill.ecse321.grocerystore.model.Cart;
import mcgill.ecse321.grocerystore.dto.CartDto;
import mcgill.ecse321.grocerystore.service.CartService;

@CrossOrigin(origins = "*")
@RestController
public class CartRestController {

    @Autowired
	private CartService cartService;

	@GetMapping(value = { "/employee/{id}", "/employee/{id}/" })
	public CartDto getCart(@PathVariable(name = "id") int id) {
        Cart cart=cartService.getCart(id);
		return convertToDto(cart);
	}
	
	@PostMapping(value = { "/employee/{id}", "/employee/{id}/" })
	public CartDto creatCart(@PathVariable(name = "id") int id, @PathVariable(name = "date") Date date) {
		Cart cart = cartService.createCart(id,date);
		return convertToDto(cart);
	}
	
    @PatchMapping(value = { "/employee/{id}", "/employee/{id}/" })
	public CartDto updateCart(@PathVariable(name = "id") int id, @PathVariable(name = "date") Date date) {
		Cart cart = cartService.createCart(id,date);
		return convertToDto(cart);
	}

	private CartDto convertToDto(Cart c) {
		if (c == null) {
			throw new IllegalArgumentException("There is no such Cart!");
		}
		CartDto cartDto = new CartDto();
		return cartDto;
	}
}
