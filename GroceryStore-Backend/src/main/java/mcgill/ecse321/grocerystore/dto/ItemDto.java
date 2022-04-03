package mcgill.ecse321.grocerystore.dto;

import mcgill.ecse321.grocerystore.model.Item;

public class ItemDto {

	private int id;
	private String name;
	private int price;
	private int point;
	private int returnPolicy;
	private boolean pickup;
	private int inStoreQuantity;
	private String image;
	
	public ItemDto() {
		
	}

	public ItemDto(String image, int id, String name, int price, int point, int returnPolicy, boolean pickup, int inStoreQuantity) {
		this.image = image;
		this.id = id;
		this.name = name;
		this.price = price;
		this.point = point;
		this.returnPolicy = returnPolicy;
		this.pickup = pickup;
		this.inStoreQuantity = inStoreQuantity;
	}
	
	public static ItemDto convertToDto(Item item) {
		ItemDto itemDto = new ItemDto(item.getImage(), item.getId(), item.getName(), item.getPrice(), item.getPoint(), item.getReturnPolicy(), item.getPickup(), item.getInStoreQuantity());
		return itemDto;
	}

	public String getImage() {
		return image;
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
