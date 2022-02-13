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

  public void setId(int aId){
    this.id = aId;
  }

  public void setName(String aName) {
    this.name = aName;
  }

  public void setPrice(int aPrice){
    this.price = aPrice;
  }

  public void setPoint(int aPoint) {
    this.point = aPoint;
  }

  public void setReturnDate(Date aReturnDate){
    this.returnDate = aReturnDate;
  }

  public void setPickup(boolean aPickup){
    this.pickup = aPickup;
  }

  public void setDelivery(boolean aDelivery) {
    this.delivery = aDelivery;
  }

  public void setInStore(boolean aInStore){
    this.inStore = aInStore;
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
  public Quantity getQuantityInStore() {
    return quantityInStore;
  }
}