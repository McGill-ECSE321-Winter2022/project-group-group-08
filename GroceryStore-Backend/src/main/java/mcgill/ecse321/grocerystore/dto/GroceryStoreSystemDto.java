package mcgill.ecse321.grocerystore.dto;

import mcgill.ecse321.grocerystore.model.GroceryStoreSystem;

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
	
	public static GroceryStoreSystemDto convertToDto(GroceryStoreSystem groceryStoreSystem) {
		if (groceryStoreSystem == null) {
			throw new IllegalArgumentException("The grocery store system does not exist");
		}
		GroceryStoreSystemDto groceryStoreSystemDto = new GroceryStoreSystemDto(groceryStoreSystem.getStoreName(), groceryStoreSystem.getAddress(), groceryStoreSystem.getEmployeeDiscount());
		return groceryStoreSystemDto;
	}
}
