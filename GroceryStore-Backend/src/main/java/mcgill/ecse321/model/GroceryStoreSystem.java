package mcgill.ecse321.model;
import java.util.*;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.Entity;

@Entity
public class GroceryStoreSystem{

	public enum OrderStatus { Processed, Transit, Fullfilled }
	public enum OrderType { Delivery, Pickup }
	public enum WeekDay { Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday }

	//GroceryStoreSystem Attributes
	private int employeeDiscount;

	public void setEmployeeDiscount(int aEmployeeDiscount){
		this.employeeDiscount = aEmployeeDiscount;
	}

	public int getEmployeeDiscount(){
		return employeeDiscount;
	}

	//!!! one to seven
	private Set<BusinessHour> openingHours;
	
	@OneToMany(cascade={CascadeType.ALL})
	public Set<BusinessHour> getOpeningHours() {
		return this.openingHours;
	}

	public void setOpeningHours(Set<BusinessHour> openingHours) {
		this.openingHours = openingHours;
	}

	
	private Set<Account> accounts;

	@OneToMany(cascade={CascadeType.ALL})
	public Set<Account> getAccounts() {
		return this.accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}

	
	private Set<User> users;

	@OneToMany(cascade={CascadeType.ALL})
	public Set<User> getUsers() {
		return this.users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	
	private Set<Cart> carts;

	@OneToMany(cascade={CascadeType.ALL})
	public Set<Cart> getCarts() {
		return this.carts;
	}

	public void setCarts(Set<Cart> carts) {
		this.carts = carts;
	}

	
	private Set<Quantity> quantities;

	@OneToMany(cascade={CascadeType.ALL})
	public Set<Quantity> getQuantities() {
		return this.quantities;
	}

	public void setQuantities(Set<Quantity> quantities) {
		this.quantities = quantities;
	}

	
	private Set<Item> items;

	@OneToMany(cascade={CascadeType.ALL})
	public Set<Item> getItems() {
		return this.items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}

	
	private Set<UserRole> userRoles;

	@OneToMany(cascade={CascadeType.ALL})
	public Set<UserRole> getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	
	private Set<Order> orders;

	@OneToMany(cascade={CascadeType.ALL})
	public Set<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
}