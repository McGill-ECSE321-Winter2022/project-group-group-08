package mcgill.ecse321.grocerystore.model;

import javax.persistence.Entity;

@Entity
public abstract class Worker extends UserRole
{
	// !!! how to do this. It is an abstract class itself, and is part of an abstract class
	public Worker(User aUser, GroceryStoreSystem aGroceryStoreSystem) {
		super(aUser, aGroceryStoreSystem);
	}
}