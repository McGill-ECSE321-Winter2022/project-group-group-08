package mcgill.ecse321.grocerystore.dto;

public class EmployeeDto {
	
	private UserRoleDto userRoleDto;
	
	/**
	 * Constructor to create an employee DTO
	 */
	public EmployeeDto() {
	}
	
	/**
	 * Constructor to create an employee DTO
	 * @param userRoleDto
	 * @param businessHourDto
	 */
	public EmployeeDto(UserRoleDto userRoleDto) {
		this.userRoleDto = userRoleDto;
	}
	
	/**
	 * Getter for userRole DTO
	 * @return userRole DTO
	 */
	public UserRoleDto getUserRoleDto() {
		return userRoleDto;
	}

}
