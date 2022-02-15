/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package mcgill.ecse321.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Item{

	private int id;
	private String name;
	private int price;
	private int point;
	private Date returnDate;
	private boolean pickup;
	private boolean delivery;
	private boolean inStore;

	@Id
	public void setId(int aId){
		this.id = aId;
	}

	public int getId(){
		return id;
	}

	public void setName(String aName) {
		this.name = aName;
	}

	public String getName(){
		return name;
	}

	public void setPrice(int aPrice){
		this.price = aPrice;
	}

	public int getPrice(){
		return price;
	}

	public void setPoint(int aPoint) {
		this.point = aPoint;
	}

	public int getPoint(){
		return point;
	}

	public void setReturnDate(Date aReturnDate){
		this.returnDate = aReturnDate;
	}

	public Date getReturnDate(){
		return returnDate;
	}

	public void setPickup(boolean aPickup){
		this.pickup = aPickup;
	}

	public boolean getPickup(){
		return pickup;
	}

	public void setDelivery(boolean aDelivery) {
		this.delivery = aDelivery;
	}

	public boolean getDelivery(){
		return delivery;
	}

	public void setInStore(boolean aInStore){
		this.inStore = aInStore;
	}

	public boolean getInStore(){
		return inStore;
	}

	private Quantity quantity;
	@OneToOne(optional=false)
	public Quantity getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Quantity quantity) {
		this.quantity = quantity;
	}
}