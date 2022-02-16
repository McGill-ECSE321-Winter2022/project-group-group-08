package mcgill.ecse321.grocerystore.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Account{

	private boolean inTown;
	
	@Id
	private String username;
	
	private String password;
	
	@OneToOne(mappedBy="account")
	private Cart cart;
	
	@OneToOne(mappedBy="account")
	private Person person;

	public void setInTown(boolean aInTown){
		this.inTown = aInTown;
	}
	
	public boolean getInTown(){
		return inTown;
	}

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


	public Cart getCart() {
	   return this.cart;
	}

	public void setCart(Cart cart) {
	   this.cart = cart;
	}
}