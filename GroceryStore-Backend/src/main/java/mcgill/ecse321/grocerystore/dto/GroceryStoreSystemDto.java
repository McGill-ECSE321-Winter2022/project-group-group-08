package mcgill.ecse321.grocerystore.dto;

public class GroceryStoreSystemDto {
	
	private String storeName;
	private String address;
	private int employeeDiscount;

	public GroceryStoreSystemDto() {
		
	}
	
	public GroceryStoreSystemDto (String storeName, String address, int employeeDiscount) {
		this.storeName = storeName;
		this.address = address;
		this.employeeDiscount = employeeDiscount;
	}
	
	public String getStoreName() {
		return storeName;
	}
	
	public String getAddress() {
		return address;
	}
	
	public int getEmployeeDiscount() {
		return employeeDiscount;
	}
}
