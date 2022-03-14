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
import mcgill.ecse321.grocerystore.model.Employee;
import mcgill.ecse321.grocerystore.model.Receipt;
import mcgill.ecse321.grocerystore.model.Quantity;
import mcgill.ecse321.grocerystore.model.Account;
import mcgill.ecse321.grocerystore.model.BusinessHour.WeekDay;
import mcgill.ecse321.grocerystore.dto.AccountDto;
import mcgill.ecse321.grocerystore.dto.ReceiptDto;
import mcgill.ecse321.grocerystore.dto.CartDto;
import mcgill.ecse321.grocerystore.service.CartService;

@CrossOrigin(origins = "*")
@RestController
public class CartRestController {

    @Autowired
	private CartService cartService;

	private CartDto convertToDto(Cart c) {
		if (c == null) {
			throw new IllegalArgumentException("There is no such Cart!");
		}
		CartDto cartDto = new CartDto();
		return cartDto;
	}
}
