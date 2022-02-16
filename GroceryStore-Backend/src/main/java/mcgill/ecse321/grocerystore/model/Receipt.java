package mcgill.ecse321.grocerystore.model;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Receipt{
	public enum OrderStatus { Processed, Transit, Fullfilled }
	public enum OrderType { Delivery, Pickup }
	
	@Id
	private int orderNum;
	
	@Enumerated
	private OrderStatus orderStatus;
	
	@Enumerated
	private OrderType orderType;
	
	@ManyToOne
	@JoinColumn(name="cart_id", nullable=false)
	private Cart cart;
	
	public void setOrderNum(int value) {
		this.orderNum = value;
	}
	
	public int getOrderNum() {
		return this.orderNum;
	}
	
	public void setOrderStatus(OrderStatus value) {
		this.orderStatus = value;
	}
	
	public OrderStatus getOrderStatus() {
		return this.orderStatus;
	}
	
	public void setOrderType(OrderType value) {
		this.orderType = value;
	}
	
	public OrderType getOrderType() {
		return this.orderType;
	}
	
	public Cart getCart() {
	   return this.cart;
	}

	public void setCart(Cart cart) {
	   this.cart = cart;
	}
}
