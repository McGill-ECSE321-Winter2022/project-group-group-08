package mcgill.ecse321.grocerystore.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Account{
	@Id
	private String username;
	private String password;
	private boolean inTown;
	private int totalPoints;
	
	@OneToOne(mappedBy="account")
	private Cart cart;
	
	@OneToOne(mappedBy="account")
	private Person person;
	
	//Attribute getters and setters
	
	public void setUsername(String aUsername){
		this.username = aUsername;
	}
	
	public String getUsername(){
		return username;
	}
	
	public void setPassword(String aPassword){
		this.password = aPassword;
	}

	public String getPassword(){
		return password;
	}

	public void setInTown(boolean aInTown){
		this.inTown = aInTown;
	}
	
	public boolean getInTown(){
		return inTown;
	}
	
	public void setTotalPoints(int totalPoints){
		this.totalPoints = totalPoints;
	}
	
	public int getTotalPoints(){
		return totalPoints;
	}

	//Relationships

	public Cart getCart() {
	   return this.cart;
	}

	public void setCart(Cart cart) {
	   this.cart = cart;
	}
	
	public Person getPerson() {
	   return this.person;
	}

	public void setPerson(Person person) {
	   this.person = person;
	}
}