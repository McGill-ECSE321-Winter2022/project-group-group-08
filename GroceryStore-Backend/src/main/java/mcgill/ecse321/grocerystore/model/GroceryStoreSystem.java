package mcgill.ecse321.grocerystore.model;
import java.util.*;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class GroceryStoreSystem{
	@Id
	private String storeName;
	private String address;
	private int employeeDiscount;

	@OneToMany(cascade = CascadeType.ALL)
	private Set<BusinessHour> openingHours;
	
	@OneToMany(cascade = CascadeType.ALL)
	private Set<Item> items;
	
	
	public void setStoreName(String value) {
		this.storeName = value;
	}
	
	public String getStoreName() {
		return this.storeName;
	}
	
	public void setAddress(String value) {
		this.address = value;
	}
	
	public String getAddress() {
		return this.address;
	}
	
	public void setEmployeeDiscount(int value) {
		this.employeeDiscount = value;
	}
	
	public int getEmployeeDiscount() {
		return this.employeeDiscount;
	}
	
	public Set<BusinessHour> getOpeningHours() {
		return this.openingHours;
	}

	public void setOpeningHours(Set<BusinessHour> openingHours) {
		this.openingHours = openingHours;
	}
	
	public Set<Item> getItems() {
		return this.items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}
}