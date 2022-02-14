package mcgill.ecse321.model;
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
	
	private int id;
	public void setId(int id){
		this.id = id;
	}

	@Id
	public int getId(){
		return id;
	}
	
	private Set<Item> items;
	@ManyToMany(cascade={CascadeType.ALL})
	public Set<Item> getItems() {
		return this.items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
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