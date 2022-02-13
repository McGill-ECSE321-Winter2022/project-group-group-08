package mcgill.ecse321.model;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

public class Account{

	//Account Attributes
	private boolean inTown;
	private String username;
	private String password;

	public boolean setInTown(boolean aInTown){
		boolean wasSet = false;
		inTown = aInTown;
		wasSet = true;
		return wasSet;
	}
	
	public boolean getInTown(){
		return inTown;
	}

	public boolean setUsername(String aUsername){
		boolean wasSet = false;
		username = aUsername;
		wasSet = true;
		return wasSet;
	}
	
	@Id
	public String getUsername(){
		return username;
	}

	public boolean setPassword(String aPassword){
		boolean wasSet = false;
		password = aPassword;
		wasSet = true;
		return wasSet;
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