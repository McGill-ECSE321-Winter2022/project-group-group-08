package mcgill.ecse321.grocerystore.service;

import mcgill.ecse321.grocerystore.model.BusinessHour;
import mcgill.ecse321.grocerystore.model.Employee;
import mcgill.ecse321.grocerystore.model.GroceryStoreSystem;
import mcgill.ecse321.grocerystore.model.BusinessHour.WeekDay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mcgill.ecse321.grocerystore.dao.BusinessHourRepository;
import mcgill.ecse321.grocerystore.dao.EmployeeRepository;
import mcgill.ecse321.grocerystore.dao.GroceryStoreSystemRepository;

import javax.transaction.Transactional;
import java.sql.Time;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalTime;

@Service
public class BusinessHourService {
    @Autowired
    BusinessHourRepository businessHourRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    GroceryStoreSystemRepository groceryStoreSystemRepository;

    //Employee
	@Transactional
	public Employee createEmployee(int id) {
		if (id == 0) {
			throw new IllegalArgumentException("Employee id cannot be empty!");
		}
		Employee employee  = new Employee();
		employee.setId(id);
		employeeRepository.save(employee);
		return employee;
	}
	
	@Transactional
	public Employee getEmployee(int id) {
		Employee employee  = employeeRepository.findEmployeeById(id);
		return employee;
	}

    //GroceryStoreSysytem
    @Transactional
    public GroceryStoreSystem creategroceryStoreSystem(String storeName, String address, int employeeDiscount){
        if (storeName == null || storeName.trim().length() == 0){
            throw new IllegalArgumentException("Store name cannot be empty");
        }
        if (address == null || address.trim().length() == 0){
            throw new IllegalArgumentException("Store address cannot be empty");
        }
        if (employeeDiscount == 0){
            throw new IllegalArgumentException("Receipt status cannot be empty");
        }
        GroceryStoreSystem groceryStoreSystem = new GroceryStoreSystem();
        groceryStoreSystem.setStoreName(storeName);
        groceryStoreSystem.setAddress(address);
        groceryStoreSystem.setEmployeeDiscount(employeeDiscount);
        groceryStoreSystemRepository.save(groceryStoreSystem);
        return groceryStoreSystem;
    }

    @Transactional
	public GroceryStoreSystem getgroceryStoreSystem(String storeName) {
		GroceryStoreSystem groceryStoreSystem  = groceryStoreSystemRepository.findGroceryStoreSystemByStoreName(storeName);
		return groceryStoreSystem;
	}

    //BusinessHour
    @Transactional
    public BusinessHour createBusinessHour(int id, WeekDay day, Time startTime, Time endTime, boolean working){
        if (id == 0){
            throw new IllegalArgumentException("Business Hour id cannot be empty");
        }
        if (day == null){
            throw new IllegalArgumentException("Week day cannot be empty");
        }
        if (startTime == null){
            throw new IllegalArgumentException("Start time cannot be empty");
        }
        if (endTime == null){
            throw new IllegalArgumentException("End time cannot be empty");
        }
        if (startTime.toLocalTime().isAfter(endTime.toLocalTime())){
            throw new IllegalArgumentException("End time cannot be earlier than Start time");
        }
        BusinessHour businessHour = new BusinessHour();
        businessHour.setId(id);
        businessHour.setDay(day);
        businessHour.setStartTime(startTime);
        businessHour.setEndTime(endTime);
        return businessHour;
    }

    @Transactional
	public List<BusinessHour> getAllBusinessHours(){
		return toList(businessHourRepository.findAll());
	}
	
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
}
