package mcgill.ecse321.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Quantity{
	
	private int quantity;
	public void setQuantity(int value) {
		this.quantity = value;
	}
	public int getQuantity() {
		return this.quantity;
	}
	
	private int id;
	public void setId(int value) {
		this.id = value;
	}
	@Id
	public int getId() {
		return this.id;
	}
	
	private Cart cart;
	//!!! Double check the relationship
	@ManyToOne(optional=false)
	public Cart getCart() {
	   return this.cart;
	}

	public void setCart(Cart cart) {
	   this.cart = cart;
	}
	
	private Item storeItem;
	@OneToOne(cascade={CascadeType.ALL})
	public Item getStoreItem() {
	   return this.storeItem;
	}

	public void setStoreItem(Item storeItem) {
	   this.storeItem = storeItem;
	}
	
	private Item cartItem;
	@OneToOne(optional=false)
	public Item getCartItem() {
	   return this.cartItem;
	}

	public void getCartItem(Item cartItem) {
	   this.cartItem = cartItem;
	}
}
