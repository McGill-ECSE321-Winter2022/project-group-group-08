package mcgill.ecse321.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public abstract class UserRole
{
	private User user;
	private GroceryStoreSystem groceryStoreSystem;
	private int id;

	public UserRole(User aUser, GroceryStoreSystem aGroceryStoreSystem){
		setUser(aUser);
		this.groceryStoreSystem = aGroceryStoreSystem;
	}

	public void setId(int aId){
		this.id = aId;
	}

	@Id
	public int getId(){
		return id;
	}

	// !!! How to do generalization
	@ManyToOne(optional=false)
	public User getUser() {
		return this.user;
	}
	public void setUser(User user) {
		this.user = user;
	}

}