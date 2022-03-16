package mcgill.ecse321.grocerystore.dto;

public class UserRoleDto {
	
	private int id;
	private PersonDto person;
	
	public UserRoleDto() { }
	//returns a transfer object of the specific UserRole transfer object
	public UserRoleDto (int id, PersonDto person) {
		this.id = id;
		this.person = person;
	}
	//get the id for this specific UserRole transfer object
	public int getId() {
		return this.id;
	}
	//return the person for this specific UserRole transfer object
	public PersonDto getPersonDto() {
		 return this.person;
	}

}
