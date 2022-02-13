/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package mcgill.ecse321.model;

import java.util.Date;

public class Item{
	
  //Item Attributes
  private int id;
  private String name;
  private int price;
  private int point;
  private Date returnDate;
  private boolean pickup;
  private boolean delivery;
  private boolean inStore;

  //Item Associations
  private Quantity quantityInStore;

  
  public boolean setId(int aId)
  {
    boolean wasSet = false;
    id = aId;
    wasSet = true;
    return wasSet;
  }

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setPrice(int aPrice)
  {
    boolean wasSet = false;
    price = aPrice;
    wasSet = true;
    return wasSet;
  }

  public boolean setPoint(int aPoint)
  {
    boolean wasSet = false;
    point = aPoint;
    wasSet = true;
    return wasSet;
  }

  public boolean setReturnDate(Date aReturnDate)
  {
    boolean wasSet = false;
    returnDate = aReturnDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setPickup(boolean aPickup)
  {
    boolean wasSet = false;
    pickup = aPickup;
    wasSet = true;
    return wasSet;
  }

  public boolean setDelivery(boolean aDelivery)
  {
    boolean wasSet = false;
    delivery = aDelivery;
    wasSet = true;
    return wasSet;
  }

  public boolean setInStore(boolean aInStore)
  {
    boolean wasSet = false;
    inStore = aInStore;
    wasSet = true;
    return wasSet;
  }

  public int getId()
  {
    return id;
  }

  public String getName()
  {
    return name;
  }

  public int getPrice()
  {
    return price;
  }

  public int getPoint()
  {
    return point;
  }

  public Date getReturnDate()
  {
    return returnDate;
  }

  public boolean getPickup()
  {
    return pickup;
  }

  public boolean getDelivery()
  {
    return delivery;
  }

  public boolean getInStore()
  {
    return inStore;
  }
  /* Code from template association_GetOne */
  public Quantity getQuantityInStore()
  {
    return quantityInStore;
  }
}