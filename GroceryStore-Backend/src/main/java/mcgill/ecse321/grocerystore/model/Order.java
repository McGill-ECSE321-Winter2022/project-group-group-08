package mcgill.ecse321.grocerystore.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Order{
	public enum OrderStatus { Processed, Transit, Fullfilled }
	public enum OrderType { Delivery, Pickup }
	
	private int orderNum;
	private OrderStatus orderStatus;
	private OrderType orderType;
	
	private Cart cart;
	
	public void setOrderNum(int value) {
		this.orderNum = value;
	}
	
	@Id
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
	
	@ManyToOne(optional=false)
	public Cart getCart() {
	   return this.cart;
	}

	public void setCart(Cart cart) {
	   this.cart = cart;
	}
}
