package mcgill.ecse321.grocerystore.controller;

import java.sql.Date;
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

import mcgill.ecse321.grocerystore.model.Cart;
import mcgill.ecse321.grocerystore.model.Account;
import mcgill.ecse321.grocerystore.dto.AccountDto;
import mcgill.ecse321.grocerystore.dto.CartDto;
import mcgill.ecse321.grocerystore.service.AccountService;
import mcgill.ecse321.grocerystore.service.CartService;

@CrossOrigin(origins = "*")
@RestController
public class CartRestController {

    @Autowired
	private CartService cartService;
	@Autowired
	private AccountService accountService;

	@GetMapping(value = {"/cart/{id}", "/cart/{id}/"})
	public CartDto getCart(@PathVariable("id") int id) {
		Cart cart = cartService.getCart(id);
		return convertToDto(cart);
	}

	@GetMapping(value = { "/cart/all", "/cart/all/" })
    public List<CartDto> getAllBusinessHours(){
		return cartService.getAllCarts().stream().map(b -> convertToDto(b)).collect(Collectors.toList());
    }
	
	@PostMapping(value = {"/cart", "/cart/"})
	public CartDto createCart(
			@RequestParam(name = "date") Date date,
			@RequestParam(name = "accountUsername") String username
			) {
		Account account=accountService.findAccountByUsername(username);
		Cart cart = cartService.createCart(date,account);
		return convertToDto(cart);
	}
	
	@PatchMapping(value = {"/cart/update/{id}", "/cart/update/{id}/"})
	public CartDto updateCart(
			@PathVariable("id") int id,
			@RequestParam(name = "date") Date date,
			@RequestParam(name = "accountUsername") String username
			) {
		Account account=accountService.findAccountByUsername(username);
		Cart cart = cartService.updateCart(id, date, account);
		return convertToDto(cart);
	}
	
	@DeleteMapping(value = {"/cart/delete/{id}", "/cart/delete/{id}/"})
	public boolean deleteCart(@PathVariable("car") int id) {
		boolean deleted = cartService.deleteCartbyID(id);
		return deleted;
	}

	private CartDto convertToDto(Cart c) {
		if (c == null) {
			throw new IllegalArgumentException("There is no such Cart!");
		}
		CartDto cartDto = new CartDto(c.getId(),c.getDate(),AccountDto.convertToDto(c.getAccount()));
		return cartDto;
	}
}