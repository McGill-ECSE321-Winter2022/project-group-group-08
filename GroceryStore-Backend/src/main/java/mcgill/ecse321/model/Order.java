package mcgill.ecse321.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Order{
	public enum OrderStatus { Processed, Transit, Fullfilled }
	public enum OrderType { Delivery, Pickup }
	
	private int orderNum;
	public void setOrderNum(int value) {
		this.orderNum = value;
	}
	@Id
	public int getOrderNum() {
		return this.orderNum;
	}
	
	private OrderStatus orderStatus;
	public void setOrderStatus(OrderStatus value) {
		this.orderStatus = value;
	}
	
	public OrderStatus getOrderStatus() {
		return this.orderStatus;
	}
	
	private OrderType orderType;
	public void setOrderType(OrderType value) {
		this.orderType = value;
	}
	
	public OrderType getOrderType() {
		return this.orderType;
	}
	
	private Cart cart;
	@ManyToOne(optional=false)
	public Cart getCart() {
	   return this.cart;
	}

	public void setCart(Cart cart) {
	   this.cart = cart;
	}
}
