package mcgill.ecse321.grocerystore.dto;

import java.sql.Date;

import mcgill.ecse321.grocerystore.controller.AccountRestController;
import mcgill.ecse321.grocerystore.model.Cart;
import mcgill.ecse321.grocerystore.model.Item;
import mcgill.ecse321.grocerystore.service.AccountService;

public class CartDto {

    private int id;
    private Date date;
	private AccountDto account;
	
	public CartDto() {
		
	}

	public CartDto(int id, Date date, AccountDto account) {
		this.id = id;
		this.date = date;
		this.account = account;
	}
		
	public int getId() {
		return id;
	}
	
	public Date getDate() {
		return date;
	}
	
	public AccountDto getAccount() {
		return account;
	}
	
	public static CartDto convertToDto(Cart cart) {
		CartDto cartDto = new CartDto(cart.getId(), cart.getDate(), AccountRestController
		return cartDto;
	}
	

}
