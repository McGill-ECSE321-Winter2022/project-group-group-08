package mcgill.ecse321.grocerystore.dto;

import mcgill.ecse321.grocerystore.model.BusinessHour;
import mcgill.ecse321.grocerystore.model.BusinessHour.WeekDay;
import java.sql.Time;

public class BusinessHourDto {
    private int id;
	private WeekDay day;
	private Time startTime;
	private Time endTime;
	private boolean working;
	private EmployeeDto employee;
	private GroceryStoreSystemDto grocerystoresystem;
	
	public BusinessHourDto() {
		
	}

	public BusinessHourDto(int id, WeekDay day, Time startTime, Time endTime, Boolean working, EmployeeDto employee, GroceryStoreSystemDto grocerystoresystem) {
		this.id = id;
		this.day = day;
		this.startTime = startTime;
		this.endTime = endTime;
        this.working = working;
		this.employee=employee;
		this.grocerystoresystem=grocerystoresystem;
 	}
		
	public int getId() {
		return id;
	}
	
	public WeekDay getDay() {
		return day;
	}
	
	public Time getStarTime() {
		return startTime;
	}
	
	public Time getEndTime() {
		return endTime;
	}
	
	public boolean getWorking() {
		return working;
	}
	
	public EmployeeDto getEmployee() {
		return employee;
	}
	
	public GroceryStoreSystemDto getGroceryStoreSystem() {
		return grocerystoresystem;
	}
	
	public static BusinessHourDto convertToDto(BusinessHour b) {
		if (b == null) {
			throw new IllegalArgumentException("There is no such Business Hour!");
		}
		BusinessHourDto businessHourDto = new BusinessHourDto(b.getId(),b.getDay(),b.getStartTime(),b.getEndTime(),b.getWorking(),
				EmployeeDto.convertToDto(b.getEmployee()),GroceryStoreSystemDto.convertToDto(b.getGroceryStoreSystem()));
		return businessHourDto;
	}
}
