package mcgill.ecse321.grocerystore.model;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Quantity{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int count;

	@ManyToOne(cascade={CascadeType.MERGE}, optional=false)
	private Item item;
	
	@ManyToOne(cascade={CascadeType.MERGE}, optional=false)
	private Cart cart;
	
	//attribute getters and setters
	public void setId(int id){
		this.id = id;
	}
	
	public int getId(){
		return this.id;
	}

	public void setCount(int count){
		this.count = count;
	}
	
	public int getCount(){
		return this.count;
	}
	
	//relationships
	
	public Item getItem() {
		return this.item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
	
	public Cart getCart() {
		return this.cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}
}