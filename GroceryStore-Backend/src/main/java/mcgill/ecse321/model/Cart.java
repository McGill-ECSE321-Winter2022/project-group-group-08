package mcgill.ecse321.model;
import java.sql.Date;
import java.util.*;

public class Cart {


  public enum OrderStatus { Processed, Transit, Fullfilled }
  public enum OrderType { Delivery, Pickup }
  
  //Cart Attributes
  private Date date;

  //Cart Associations
  private List<Quantity> quantities;
  private Account account;
  private List<Order> orders;


  public boolean setDate(Date aDate)
  {
    boolean wasSet = false;
    date = aDate;
    wasSet = true;
    return wasSet;
  }

  public Date getDate()
  {
    return date;
  }

  public List<Quantity> getQuantities()
  {
    List<Quantity> newQuantities = Collections.unmodifiableList(quantities);
    return newQuantities;
  }

  /* Code from template association_GetOne */
  public Account getAccount()
  {
    return account;
  }

  public List<Order> getOrders()
  {
    List<Order> newOrders = Collections.unmodifiableList(orders);
    return newOrders;
  }

  public boolean addOrder(Order aOrder)
  {
    boolean wasAdded = false;
    if (orders.contains(aOrder)) { return false; }
    Cart existingCart = aOrder.getCart();
    boolean isNewCart = existingCart != null && !this.equals(existingCart);
    if (isNewCart)
    {
      aOrder.setCart(this);
    }
    else
    {
      orders.add(aOrder);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeOrder(Order aOrder)
  {
    boolean wasRemoved = false;
    //Unable to remove aOrder, as it must always have a cart
    if (!this.equals(aOrder.getCart()))
    {
      orders.remove(aOrder);
      wasRemoved = true;
    }
    return wasRemoved;
  }

}