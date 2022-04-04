package mcgill.ecse321.grocerystore.controller;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mcgill.ecse321.grocerystore.model.BusinessHour;
import mcgill.ecse321.grocerystore.model.Employee;
import mcgill.ecse321.grocerystore.model.GroceryStoreSystem;
import mcgill.ecse321.grocerystore.model.BusinessHour.WeekDay;
import mcgill.ecse321.grocerystore.dto.BusinessHourDto;
import mcgill.ecse321.grocerystore.dto.EmployeeDto;
import mcgill.ecse321.grocerystore.dto.GroceryStoreSystemDto;
import mcgill.ecse321.grocerystore.service.BusinessHourService;
import mcgill.ecse321.grocerystore.service.EmployeeService;
import mcgill.ecse321.grocerystore.service.GroceryStoreSystemService;

@CrossOrigin(origins = "*")
@RestController
public class BusinessHourRestController {

    @Autowired
	private BusinessHourService businesshourService;
    @Autowired
	private GroceryStoreSystemService groceryStoreSystemService;
    @Autowired
   	private EmployeeService employeeService;
	
	/**
	* @return list of business hours
	*/  
    @GetMapping(value = { "/businesshour/all", "/businesshour/all/" })
    public List<BusinessHourDto> getAllBusinessHours(){
        return businesshourService.getAllBusinessHours().stream().map(b -> convertToDto(b)).collect(Collectors.toList());
    }

	/**
	 * Gets business hour 
	 * @param id primary key 
	 * @return business hour 
	 */
	@GetMapping(value = { "/businesshour/{id}", "/businesshour/{id}/" })
    public BusinessHourDto getBusinessHour(@PathVariable("id") int id){
        BusinessHour businessHour = businesshourService.getBusinessHoursbyID(id);
        return convertToDto(businessHour);
    }

	@GetMapping(value = { "businesshour/employeeId", "/businesshour/employeeId/" })
	public List<BusinessHourDto> getBusinessHoursByEmployeeId(@RequestParam int id) {
		Employee employee = employeeService.getEmployee(id);
		return businesshourService.getBusinessHoursbyEmployee(employee).stream().map(b -> convertToDto(b)).collect(Collectors.toList());
	}

	/**
	 * Creates a business hour for an employee
	 * @param day day of business hour
	 * @param working working on that day
	 * @param employeeId primary key of employee
	 * @param startTime start time of shift 
	 * @param endTime end time of shift
	 * @return business hour
	 */
    @PostMapping(value = { "/businesshour/employee", "/businesshour/employee/" })
	public BusinessHourDto creatBusinessHourforEmployee(
			@RequestParam (name = "day") WeekDay day,
			@RequestParam (name = "working") Boolean working, 
			@RequestParam (name = "employeeId") int employeeId,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime startTime,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime endTime
			){
    	Employee employee = employeeService.getEmployee(employeeId);
        BusinessHour b = businesshourService.createBusinessHourforEmployee(day, Time.valueOf(startTime), Time.valueOf(endTime), working, employee);
		return convertToDto(b);
	}
	
	/**
	 * Creates business hour for a grocery store 
	 * @param day day of business hour
	 * @param working working on that day
	 * @param employeeId primary key of employee
	 * @param startTime start time of shift 
	 * @param endTime end time of shift
	 * @return business hour
	 */
    @PostMapping(value = { "/businesshour/groceryStoreSystem", "/businesshour/groceryStoreSystem/" })
	public BusinessHourDto creatBusinessHourforGroceryStoreSystem( 
			@RequestParam (name = "day") WeekDay day,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime startTime,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime endTime,
			@RequestParam (name = "working") Boolean working,
			@RequestParam (name = "groceryStoreSystemName") String name){
    	GroceryStoreSystem groceryStoreSystem = groceryStoreSystemService.getGroceryStoreSystem(name);
        BusinessHour b = businesshourService.createBusinessHourforGroceryStoreSystem(day, Time.valueOf(startTime), Time.valueOf(endTime), working, groceryStoreSystem);
		return convertToDto(b);
	}

	/**
	 * Update business hours
	 * @param id primary ey of business hour
	 * @param day day of business hour
	 * @param working working on that day
	 * @param employeeId primary key of employee
	 * @param storeName primary key of store name 
	 * @param startTime start time of shift 
	 * @param endTime end time of shift
	 * @return business hour
	 */
    @PatchMapping(value = {"/businesshour/update/{id}", "/businesshour/update/{id}/"})
    public BusinessHourDto updateBusinessHour(
    		@PathVariable (name = "id") int id, 
    		@RequestParam (name = "day") WeekDay day, 
    		@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime startTime,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime endTime,
    		@RequestParam (name = "working") Boolean working, 
    		@RequestParam (name = "groceryStoreSystemName") String storeName, 
    		@RequestParam (name = "employeeId") int employeeId) {
        GroceryStoreSystem system = null;
        if(storeName.length() != 0) {
        	system=groceryStoreSystemService.getGroceryStoreSystem(storeName);
        }
        Employee employee = null;
        if(employeeId != -1) {
        	employee = employeeService.getEmployee(employeeId);
        }
        BusinessHour b = businesshourService.updateBusinessHour(id, system, employee, day, Time.valueOf(startTime), Time.valueOf(endTime), working);
		return convertToDto(b);
    }

	/**
	 * Deletes business hour
	 * @param id primary key of business hour 
	 * @return business hour
	 */
    @DeleteMapping(value = {"/businesshour/delete/{id}", "/busineshour/delete/{id}/"})
	public boolean deleteBusinessHour(@PathVariable("id") int id) {
		boolean deleted = businesshourService.deleteBusinessHourbyID(id);
		return deleted;
	}

	private BusinessHourDto convertToDto(BusinessHour businesshour) {
		BusinessHourDto businessHourDto = null;
		if (businesshour == null) {
			throw new IllegalArgumentException("There is no such BusinessHour!");
		}
		if(businesshour.getGroceryStoreSystem() != null) {
			businessHourDto = new BusinessHourDto(businesshour.getId(),businesshour.getDay(), businesshour.getStartTime(),businesshour.getEndTime(),businesshour.getWorking(),
					null,GroceryStoreSystemDto.convertToDto(businesshour.getGroceryStoreSystem()));
		}else{
			businessHourDto = new BusinessHourDto(businesshour.getId(),businesshour.getDay(), businesshour.getStartTime(),businesshour.getEndTime(),businesshour.getWorking(),
					EmployeeDto.convertToDto(businesshour.getEmployee()),null);
		}
		
		return businessHourDto;
	}
	
}
