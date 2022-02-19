package mcgill.ecse321.grocerystore.model;
import java.sql.Date;
import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Cart {
	public enum OrderStatus { Processed, Transit, Fullfilled }
	public enum OrderType { Delivery, Pickup }
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private Date date;
	
	@OneToMany(mappedBy="cart")	
	private Set<Receipt> receipt;
	
	@OneToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="account_username", referencedColumnName="username")
	private Account account;
	
	@OneToMany(cascade = {CascadeType.ALL})
	private Set<Quantity> itemQuantities;
	
	
	//Attributes getters and setters
	
	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}
	
	public void setDate(Date aDate){
		this.date = aDate;
	}

	public Date getDate(){
		return date;
	}
	
	//Relationships
	
	public Set<Quantity> getItemQuantities() {
		return this.itemQuantities;
	}

	public void setItemQuantities(Set<Quantity> itemQuantities) {
		this.itemQuantities = itemQuantities;
	}

	public Set<Receipt> getReceipts() {
		return this.receipt;
	}

	public void setReceipts(Set<Receipt> orders) {
		this.receipt = orders;
	}

	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
}