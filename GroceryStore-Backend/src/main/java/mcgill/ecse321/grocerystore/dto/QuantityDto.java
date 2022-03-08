package mcgill.ecse321.grocerystore.dto;

public class QuantityDto {

	private int id;
	private int count;
	
	
	public QuantityDto() {
		
	}

	public QuantityDto(int id, int count) {
		this.id = id;
		this.count = count;
	}
	
	public int getId() {
		return id;
	}
	
	public int getCount() {
		return count;
	}
}
