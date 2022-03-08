package mcgill.ecse321.grocerystore.dto;

public class ItemDto {

	private int id;
	private String name;
	private int price;
	private int point;
	private int returnPolicy;
	private boolean pickup;
	private int inStoreQuantity;
	
	
	public ItemDto() {
		
	}

	public ItemDto(int id, String name, int price, int point, int returnPolicy, boolean pickup, int inStoreQuantity) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.point = point;
		this.returnPolicy = returnPolicy;
		this.pickup = pickup;
		this.inStoreQuantity = inStoreQuantity;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public int getPrice() {
		return price;
	}
	
	public int getPoint() {
		return point;
	}
	
	public int getReturnPolicy() {
		return returnPolicy;
	}
	
	public boolean getPickup() {
		return pickup;
	}
	
	public int getInStoreQuantity() {
		return inStoreQuantity;
	}
}
