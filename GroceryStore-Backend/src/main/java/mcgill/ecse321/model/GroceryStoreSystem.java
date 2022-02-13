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

  //GroceryStoreSystem Associations
  private Set<BusinessHour> openingHours;
  private Set<Account> accounts;
  private Set<User> users;
  private Set<Cart> carts;
  private Set<Quantity> quantities;
  private Set<Item> items;
  private Set<UserRole> userRoles;
  private Set<Order> orders;

  public boolean setEmployeeDiscount(int aEmployeeDiscount){
    boolean wasSet = false;
    employeeDiscount = aEmployeeDiscount;
    wasSet = true;
    return wasSet;
  }

  public int getEmployeeDiscount(){
    return employeeDiscount;
  }
  
  
  
}