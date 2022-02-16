package mcgill.ecse321.grocerystore.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Item{
	@Id
	private int id  = Integer.parseInt(UUID.randomUUID().toString());
	private String name;
	private int price;
	private int point;
	private Date returnDate;
	private boolean pickup;
	private boolean delivery;
	private boolean inStore;
	private int quantity;
	
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
	
	public void setQuantity(int aQuantity){
		this.quantity = aQuantity;
	}

	public int getQuantity(){
		return quantity;
	}
}