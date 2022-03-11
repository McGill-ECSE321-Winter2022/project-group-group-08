package mcgill.ecse321.grocerystore.dto;

import java.sql.Date;

public class CartDto {

    private int id;
    private Date date;
	private AccountDto account;
    //private QuantityDto quantity;
	//private ReceiptDto receipt;
	
	public CartDto() {
		
	}

	public CartDto(int id, Date date, AccountDto account/*, QuantityDto quantity, ReceiptDto receipt*/) {
		this.id = id;
		this.date = date;
		this.account = account;
		//this.quantity = quantity;
		//this.receipt = receipt;
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

    /*public QuantityDto getQuantity() {
		return quantity;
	}
	
	public ReceiptDto getReceipt() {
		return account;
	}
	
	}*/
}
