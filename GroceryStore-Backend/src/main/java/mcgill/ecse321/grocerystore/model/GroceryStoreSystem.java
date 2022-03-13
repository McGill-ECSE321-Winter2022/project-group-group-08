package mcgill.ecse321.grocerystore.model;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class GroceryStoreSystem{
	@Id
	private String storeName;
	private String address;
	private int employeeDiscount;
	private Set<BusinessHour> hours;
	//attributes getters and setters
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
		return hours;
	}
}