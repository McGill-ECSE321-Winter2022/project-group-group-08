package mcgill.ecse321.grocerystore.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Account{

	private boolean inTown;
	private String username;
	private String password;

	public void setInTown(boolean aInTown){
		this.inTown = aInTown;
	}
	
	public boolean getInTown(){
		return inTown;
	}

	public void setUsername(String aUsername){
		this.username = aUsername;
	}
	
	@Id
	public String getUsername(){
		return username;
	}

	public void setPassword(String aPassword){
		this.password = aPassword;
	}

	public String getPassword(){
		return password;
	}

	private Cart cart;
	@OneToOne(optional=false)
	public Cart getCart() {
	   return this.cart;
	}

	public void setCart(Cart cart) {
	   this.cart = cart;
	}
}