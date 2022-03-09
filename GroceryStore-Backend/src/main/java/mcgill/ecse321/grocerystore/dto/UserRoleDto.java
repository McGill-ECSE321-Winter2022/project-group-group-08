package mcgill.ecse321.grocerystore.dto;

public class UserRoleDto {
	
	private int id;
	private PersonDto person;
	
	public UserRoleDto() { }
	
	public UserRoleDto (int id, PersonDto person) {
		this.id = id;
		this.person = person;
	}
	
	public int getId() {
		return this.id;
	}

}
