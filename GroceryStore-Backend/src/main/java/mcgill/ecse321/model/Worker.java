package mcgill.ecse321.model;

import javax.persistence.Entity;

@Entity
public abstract class Worker extends UserRole
{
	public Worker(User aUser, GroceryStoreSystem aGroceryStoreSystem) {
		super(aUser, aGroceryStoreSystem);
	}
}
