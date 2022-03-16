package mcgill.ecse321.grocerystore.dto;

import mcgill.ecse321.grocerystore.model.Employee;
import mcgill.ecse321.grocerystore.model.GroceryStoreSystem;

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
	
	public static EmployeeDto convertToDto(Employee e) {
		if (e == null) {
			throw new IllegalArgumentException("There is no such Employee!");
		}
		 EmployeeDto employeeDto = new EmployeeDto();
		return employeeDto;
	}
}
