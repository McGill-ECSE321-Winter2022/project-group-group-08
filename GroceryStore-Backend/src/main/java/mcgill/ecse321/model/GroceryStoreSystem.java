package mcgill.ecse321.model;
import java.util.*;
import java.sql.Time;
import java.sql.Date;

public class GroceryStoreSystem{

  public enum OrderStatus { Processed, Transit, Fullfilled }
  public enum OrderType { Delivery, Pickup }
  public enum WeekDay { Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday }

  //GroceryStoreSystem Attributes
  private int employeeDiscount;

  public boolean setEmployeeDiscount(int aEmployeeDiscount){
    boolean wasSet = false;
    employeeDiscount = aEmployeeDiscount;
    wasSet = true;
    return wasSet;
  }

  public int getEmployeeDiscount(){
    return employeeDiscount;
  }
  
  //GroceryStoreSystem Associations
  private Set<BusinessHour> openingHour;
  
  public Set<BusinessHour> getOpeningHour() {
	   return this.openingHour;
	}

	public void setOpeningHour(Set<BusinessHour> openingHour) {
	   this.openingHour = openingHour;
	}
  
  private Set<Account> account;
  
  public Set<Account> getAccount() {
	   return this.account;
  }

  public void setAccount(Set<Account> account) {
	   this.account = account;
  }
	
  private Set<User> user;
  
  public Set<User> getUser() {
	   return this.user;
	}

	public void setUser(Set<User> user) {
	   this.user = user;
	}
	
  private Set<Cart> cart;
  
  public Set<Cart> getCart() {
	   return this.cart;
	}

	public void setCart(Set<Cart> cart) {
	   this.cart = cart;
	}
	
  private Set<Quantity> quantity;
  
  public Set<Quantity> getQuantity() {
	   return this.quantity;
	}

	public void setQuantity(Set<Quantity> quantity) {
	   this.quantity = quantity;
	}
	
  private Set<Item> item;
  
  public Set<Item> getItem() {
	   return this.item;
	}

	public void setItem(Set<Item> item) {
	   this.item = item;
	}
	
  private Set<UserRole> userRole;
  
  public Set<UserRole> getUserRole() {
	   return this.userRole;
	}

	public void setUserRole(Set<UserRole> userRole) {
	   this.userRole = userRole;
	}
	
  private Set<Order> order;
  
  public Set<Order> getOrder() {
	   return this.order;
	}

	public void setOrder(Set<Order> order) {
	   this.order = order;
	}
}