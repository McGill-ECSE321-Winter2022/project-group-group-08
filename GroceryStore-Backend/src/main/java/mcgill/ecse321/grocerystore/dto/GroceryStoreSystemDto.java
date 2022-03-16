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
	
	public static GroceryStoreSystemDto convertToDto(GroceryStoreSystem g) {
		if (g == null) {
			throw new IllegalArgumentException("There is no such Grocery Store System!");
		}
		 GroceryStoreSystemDto groceryStoreSystemDto = new GroceryStoreSystemDto(g.getStoreName(),g.getAddress(),g.getEmployeeDiscount());
		return groceryStoreSystemDto;
	}
}
