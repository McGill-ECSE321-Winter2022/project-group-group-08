package mcgill.ecse321.grocerystore.dto;

public class EmployeeDto {
	
	private UserRoleDto userRoleDto;
	private BusinessHourDto businessHourDto;
	
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
	public EmployeeDto(UserRoleDto userRoleDto, BusinessHourDto businessHourDto) {
		this.userRoleDto = userRoleDto;
		this.businessHourDto = businessHourDto;
	}
	
	/**
	 * Getter for userRole DTO
	 * @return userRole DTO
	 */
	public UserRoleDto getUserRoleDto() {
		return userRoleDto;
	}
	
	/**
	 * Getter for businessHour DTO
	 * @return businessHour DTO
	 */
	public BusinessHourDto getBusinessHourDto() {
		return businessHourDto;
	}

}
