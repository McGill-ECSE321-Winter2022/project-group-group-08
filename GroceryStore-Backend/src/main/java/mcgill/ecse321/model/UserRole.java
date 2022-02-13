package mcgill.ecse321.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public abstract class UserRole
{

	//------------------------
	// MEMBER VARIABLES
	//------------------------

	//UserRole Associations
	private User user;
	private GroceryStoreSystem groceryStoreSystem;
	private int id;

	public void setId(int aId){
		this.id = aId;
	}

	@Id
	public int getId(){
		return id;
	}

	// !!! How to do generalization
	@ManyToOne(cascade={CascadeType.ALL})
	public User getUser() {
		return this.user;
	}
	public void setUser(User user) {
		this.user = user;
	}

}