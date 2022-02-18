package mcgill.ecse321.grocerystore.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Item{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	private int price;
	private int point;
	private int returnPolicy;
	private boolean pickup;
	private boolean delivery;
	private boolean inStore;
	
	
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

	public void setReturnPolicy(int aReturnPolicy){
		this.returnPolicy = aReturnPolicy;
	}

	public int getReturnDate(){
		return returnPolicy;
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
}