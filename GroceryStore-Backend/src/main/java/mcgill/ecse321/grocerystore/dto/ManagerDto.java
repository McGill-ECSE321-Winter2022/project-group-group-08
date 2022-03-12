package mcgill.ecse321.grocerystore.dto;

import mcgill.ecse321.grocerystore.model.UserRole;

public class ManagerDto {
	
	private UserRoleDto userRole;
	
	public ManagerDto() {
		
	}
	
	public ManagerDto(UserRoleDto userRole) {
		this.userRole = userRole;
	}
	
	public UserRoleDto getManager() {
		return userRole;
	}


}
