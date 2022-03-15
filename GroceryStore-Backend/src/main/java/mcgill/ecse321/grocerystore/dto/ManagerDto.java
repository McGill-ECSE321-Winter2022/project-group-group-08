package mcgill.ecse321.grocerystore.dto;


public class ManagerDto extends UserRoleDto{
	
	public ManagerDto() {
		
	}
	
	public ManagerDto(int userRoleId, PersonDto person) {
		super(userRoleId, person);
	}
}
