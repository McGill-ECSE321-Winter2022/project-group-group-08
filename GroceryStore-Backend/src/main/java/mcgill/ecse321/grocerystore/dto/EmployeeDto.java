package mcgill.ecse321.grocerystore.dto;

public class EmployeeDto extends UserRoleDto{
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
	public EmployeeDto(int userRoleId, PersonDto person) {
		super(userRoleId, person);
	}
}
