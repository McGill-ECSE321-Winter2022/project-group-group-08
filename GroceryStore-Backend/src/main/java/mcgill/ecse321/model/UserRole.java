package mcgill.ecse321.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
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

	// !!! How to do generalization
	@ManyToOne(cascade={CascadeType.ALL})
	public User getUser() {
		return this.user;
	}
	public void setUser(User user) {
		this.user = user;
	}

}