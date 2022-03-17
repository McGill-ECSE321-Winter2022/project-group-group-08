package mcgill.ecse321.grocerystore.dto;

import mcgill.ecse321.grocerystore.model.Employee;

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
	
	public static EmployeeDto convertToDto(Employee employee) {
		if (employee == null) {
			throw new IllegalArgumentException("There is no such employee!");
		}
		EmployeeDto employeeDto = new EmployeeDto(employee.getId(), PersonDto.convertToDto(employee.getPerson()));
		return employeeDto;
	}
}
