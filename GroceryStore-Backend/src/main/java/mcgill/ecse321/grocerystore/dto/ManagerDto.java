package mcgill.ecse321.grocerystore.dto;

import mcgill.ecse321.grocerystore.model.UserRole;

public class ManagerDto {
	
	private int id;
	private UserRoleDto userRole;
	
	public ManagerDto() {
		
	}
	
	public UserRoleDto getManager() {
		return userRole;
	}


}
