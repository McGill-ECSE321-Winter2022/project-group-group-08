package mcgill.ecse321.grocerystore.model;
import java.sql.Date;
import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Cart {
	public enum OrderStatus { Processed, Transit, Fullfilled }
	public enum OrderType { Delivery, Pickup }
	
	@Id
	private int id = Integer.parseInt(UUID.randomUUID().toString());;

	private Date date;
	
	@OneToMany(mappedBy="cart")	
	private Set<Receipt> orders;
	
	@OneToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="account_username", referencedColumnName="username")
	private Account account;
	
	@OneToMany(cascade = {CascadeType.ALL})
	private Set<Item> itemInCart;
	
	public void setDate(Date aDate){
		this.date = aDate;
	}

	public Date getDate(){
		return date;
	}
	
	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}
	
	public Set<Item> getItemInCart() {
		return this.itemInCart;
	}

	public void setItemInCart(Set<Item> itemInCart) {
		this.itemInCart = itemInCart;
	}

	public Set<Receipt> getOrders() {
		return this.orders;
	}

	public void setOrders(Set<Receipt> orders) {
		this.orders = orders;
	}

	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
}