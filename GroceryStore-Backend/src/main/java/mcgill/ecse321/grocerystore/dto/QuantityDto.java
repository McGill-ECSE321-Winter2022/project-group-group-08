package mcgill.ecse321.grocerystore.dto;

import mcgill.ecse321.grocerystore.model.Quantity;

public class QuantityDto {

	private int id;
	private int count;
	private ItemDto item;
	private CartDto cart;
	
	
	public QuantityDto() {
		
	}

	public QuantityDto(int id, int count, ItemDto item, CartDto cart) {
		this.id = id;
		this.count = count;
		this.item = item;
		this.cart = cart;
	}
	
	public static QuantityDto convertToDto(Quantity quantity) {
		QuantityDto quantityDto = new QuantityDto(quantity.getId(), quantity.getCount(), ItemDto.convertToDto(quantity.getItem()), CartDto.convertToDto(quantity.getCart()));
		return quantityDto;
	}
	
	public int getId() {
		return id;
	}
	
	public int getCount() {
		return count;
	}
	
	public ItemDto getItem() {
		return item;
	}
	
	public CartDto getCart() {
		return cart;
	}
}
