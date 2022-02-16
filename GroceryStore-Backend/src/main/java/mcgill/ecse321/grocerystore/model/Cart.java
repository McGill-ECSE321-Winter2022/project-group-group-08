package mcgill.ecse321.grocerystore.model;
import java.sql.Date;
import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Cart {
	public enum OrderStatus { Processed, Transit, Fullfilled }
	public enum OrderType { Delivery, Pickup }

	private Date date;
	public void setDate(Date aDate){
		this.date = aDate;
	}

	public Date getDate(){
		return date;
	}
	
	UUID uuid = UUID.randomUUID();
	private int id  = Integer.parseInt(uuid.toString());
	
	public void setId(int id){
		this.id = id;
	}

	@Id
	public int getId(){
		return id;
	}
	
	private Set<Item> itemInCart;
	@OneToMany(cascade={CascadeType.ALL})
	public Set<Item> getItemInCart() {
		return this.itemInCart;
	}

	public void setItemInCart(Set<Item> itemInCart) {
		this.itemInCart = itemInCart;
	}

	private Set<Order> orders;
	@OneToMany(cascade={CascadeType.ALL})
	public Set<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
	
	private Account account;
	@OneToOne(optional=false)
	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
}