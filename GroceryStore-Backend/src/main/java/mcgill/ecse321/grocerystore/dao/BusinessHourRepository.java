package mcgill.ecse321.grocerystore.dao;

import java.sql.Time;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import mcgill.ecse321.grocerystore.model.BusinessHour;
import mcgill.ecse321.grocerystore.model.BusinessHour.WeekDay;
import mcgill.ecse321.grocerystore.model.Employee;
import mcgill.ecse321.grocerystore.model.GroceryStoreSystem;

public interface BusinessHourRepository extends CrudRepository<BusinessHour, Integer>{

	BusinessHour findBusinessHourById(int id);
	
	List<BusinessHour> findBusinessHoursByGroceryStoreSystem(GroceryStoreSystem groceryStoreSystem);
	
	List<BusinessHour> findBusinessHoursByEmployee(Employee employee);
	
	List<BusinessHour> findBusinessHourByDay(WeekDay weekday);
	
	List<BusinessHour> findBusinessHourByWorking(boolean working);
	
	List<BusinessHour> findBusinessHourByDayAndWorking(WeekDay weekday, boolean working);

//	List<BusinessHour> findBusinessHourByTimeBetween(Time startTime, Time endTime);
	
	List<BusinessHour> findBusinessHourByStartTimeBetween(Time minStartTime, Time maxStartTime);
	
	List<BusinessHour> findBusinessHourByEndTimeBetween(Time minEndTime, Time maxEndTime);
	
	List<BusinessHour> findBusinessHourByStartTimeBetweenAndEndTimeBetween(Time minStartTime, Time maxStartTime, Time minEndTime, Time maxEndTime);
	
	List<BusinessHour> findBusinessHourByDayAndWorkingAndStartTimeBetweenAndEndTimeBetween(WeekDay weekday, boolean working, Time minStartTime, Time maxStartTime, Time minEndTime, Time maxEndTime);

}
