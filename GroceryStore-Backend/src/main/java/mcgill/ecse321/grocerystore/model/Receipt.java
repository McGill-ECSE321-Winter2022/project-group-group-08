package mcgill.ecse321.grocerystore.model;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Receipt{
	public enum ReceiptStatus { Processed, Transit, Fullfilled }
	public enum ReceiptType { Delivery, Pickup }
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int receiptNum;
	@Enumerated
	private ReceiptStatus receiptStatus;
	@Enumerated
	private ReceiptType receiptType;
	
	@ManyToOne
	private Cart cart;
	
	//attribute getters and setters
	public void setReceiptNum(int value) {
		this.receiptNum = value;
	}
	
	public int getReceiptNum() {
		return this.receiptNum;
	}
	
	public void setReceiptStatus(ReceiptStatus value) {
		this.receiptStatus = value;
	}
	
	public ReceiptStatus getReceiptStatus() {
		return this.receiptStatus;
	}
	
	public void setReceiptType(ReceiptType value) {
		this.receiptType = value;
	}
	
	public ReceiptType getReceiptType() {
		return this.receiptType;
	}
	
	//relationships
	
	public Cart getCart() {
	   return this.cart;
	}

	public void setCart(Cart cart) {
	   this.cart = cart;
	}
}