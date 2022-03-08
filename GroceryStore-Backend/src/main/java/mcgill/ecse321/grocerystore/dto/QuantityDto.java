package mcgill.ecse321.grocerystore.dto;

import mcgill.ecse321.grocerystore.model.Item;
import mcgill.ecse321.grocerystore.model.Quantity;

public class QuantityDto {

	private int id;
	private int count;
	private ItemDto item;
	
	
	public QuantityDto() {
		
	}

	public QuantityDto(int id, int count, ItemDto item) {
		this.id = id;
		this.count = count;
		this.item = item;
	}
	
	public static QuantityDto convertToDto(Quantity quantity) {
		QuantityDto quantityDto = new QuantityDto(quantity.getId(), quantity.getCount(), ItemDto.convertToDto(quantity.getItem()));
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
}
