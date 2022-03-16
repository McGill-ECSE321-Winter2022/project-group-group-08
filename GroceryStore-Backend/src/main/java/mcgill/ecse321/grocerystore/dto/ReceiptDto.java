package mcgill.ecse321.grocerystore.dto;

import mcgill.ecse321.grocerystore.model.Cart;
import mcgill.ecse321.grocerystore.model.Receipt.ReceiptStatus;
import mcgill.ecse321.grocerystore.model.Receipt.ReceiptType;

public class ReceiptDto {
		
	private int receiptNum;

	private ReceiptStatus receiptStatus;

	private ReceiptType receiptType;
	
	private CartDto cart;
	
	public ReceiptDto() { }
	//returns a transfer object of the specific Receipt transfer object
	public ReceiptDto (int receiptNum, ReceiptStatus receiptStatus, ReceiptType receiptType, CartDto cart) {
		this.receiptNum = receiptNum;
		this.receiptStatus = receiptStatus;
		this.receiptType = receiptType;
		this.cart = cart;
	}
	//code below is straight forward
	public int getReceiptNum(){
		return this.receiptNum;
	}
	
	public ReceiptStatus getReceiptStatus() {
		return this.receiptStatus;
	}
	
	public ReceiptType getReceiptType() {
		return this.receiptType;
	}
	
	public CartDto getCartDto() {
	   return this.cart;
	}

}