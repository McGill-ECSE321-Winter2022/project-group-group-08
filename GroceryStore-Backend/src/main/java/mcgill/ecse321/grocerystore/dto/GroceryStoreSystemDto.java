package mcgill.ecse321.grocerystore.dto;

public class GroceryStoreSystemDto {
	
	private String storeName;
	private String address;
	private int employeeDiscount;
	//private BusinessHourDto businessHour;
	
	public GroceryStoreSystemDto() {
		
	}
	
	public GroceryStoreSystemDto (String storeName, String address, int employeeDiscount/*, BusinessHourDto businessHour*/) {
		this.storeName = storeName;
		this.address = address;
		this.employeeDiscount = employeeDiscount;
		//this.businessHour = businessHour;
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
/*	
	public BusinessHour getBusinessHour() {
		return businessHour;
	}
	*/
	
}
