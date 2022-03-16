package mcgill.ecse321.grocerystore.service;

import mcgill.ecse321.grocerystore.model.BusinessHour;
import mcgill.ecse321.grocerystore.model.Employee;
import mcgill.ecse321.grocerystore.model.GroceryStoreSystem;
import mcgill.ecse321.grocerystore.model.BusinessHour.WeekDay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mcgill.ecse321.grocerystore.dao.BusinessHourRepository;
import javax.transaction.Transactional;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Service
public class BusinessHourService {
    @Autowired
    BusinessHourRepository businessHourRepository;

    /**
	 * Creates business hour with its primary key ID
	 * @param id unique identifier of business hour
	 * @param day specify the week day
	 * @param startTime specify the start time of the business hour
	 * @param endTime specify the end time of the business hour
	 * @param employee the employee object that is linked to the business hour
	 * @param working specify whether the employee is working
	 * @param groceryStoreSystem specify the grocery store system linked to the business hour
	 * @return unique business hour linked to employee or grocery store system
	 */
    @Transactional
    public BusinessHour createBusinessHour(int id, WeekDay day, Time startTime, Time endTime, boolean working, Employee employee, GroceryStoreSystem groceryStoreSystem){
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
        if (startTime.toLocalTime().equals(endTime.toLocalTime())){
            throw new IllegalArgumentException("End time cannot be the same as Start time");
        }
        BusinessHour businessHour = new BusinessHour();
        businessHour.setId(id);
        businessHour.setDay(day);
        businessHour.setStartTime(startTime);
        businessHour.setEndTime(endTime);
        businessHour.setWorking(working);
        businessHour.setEmployee(employee);
        businessHour.setGroceryStoreSystem(groceryStoreSystem);
        return businessHour;
    }

    /**
	 * @return a list of existing business hours
	 */
    @Transactional
	public List<BusinessHour> getAllBusinessHours(){
		return toList(businessHourRepository.findAll());
	}
	
    /**
	 * Finds business hour by id
	 * @param id business hour id that corresponds to the specific business hour
	 * @return unique business hour linked to the id
	 */
    @Transactional
	public BusinessHour getBusinessHoursbyID(int id){
    	if(id<0) {
    		throw new IllegalArgumentException("Id cannot be negative");
    	}
        BusinessHour businesshour = businessHourRepository.findBusinessHourById(id);
        if (businesshour==null){
            throw new IllegalArgumentException("No such id " + id + " exists");
        }
		return businesshour;
	}

    /**
	 * Finds business hour by a specific week day
	 * @param day week day that corresponds to the specific business hour
	 * @return a list of business hours with the specific week day
	 */
    @Transactional
	public List<BusinessHour> getBusinessHoursbyDay(WeekDay day){
        List<BusinessHour> businessHours = businessHourRepository.findBusinessHourByDay(day);
        if (businessHours == null || businessHours.isEmpty()){
            throw new IllegalArgumentException("No such business hour with weekday " + day + " exists");
        }
		return businessHours;
	}

    /**
	 * Finds business hour by a specific working status
	 * @param working working status that corresponds to the specific business hour
	 * @return a list of business hours with the specific working status
	 */
    @Transactional
	public List<BusinessHour> getBusinessHoursbyWorking(Boolean working){
        List<BusinessHour> businessHours = businessHourRepository.findBusinessHourByWorking(working);
        if (businessHours == null || businessHours.isEmpty()){
            throw new IllegalArgumentException("No such business hour with working status " + working + " exists");
        }
		return businessHours;
	}

    /**
	 * Finds business hour by two specific start times
	 * @param startTime Start time that corresponds to the specific business hour
	 * @param endTime end time that corresponds to the specific business hour
	 * @return a list of business hours with the specific startTime and endTime
	 */
    @Transactional
	public List<BusinessHour> getBusinessHoursbyStartTimebetween(Time startTime, Time endTime){
        if(startTime==null) {
        	throw new IllegalArgumentException("Start time is Empty");
        }
        if(endTime==null) {
        	throw new IllegalArgumentException("End time is Empty");
        }
    	List<BusinessHour> businessHours = businessHourRepository.findBusinessHourByStartTimeBetween(startTime,endTime);
        if (startTime.toLocalTime().isAfter(endTime.toLocalTime())){
            throw new IllegalArgumentException("Start time is later than end time");
        }
        if (endTime.toLocalTime().equals(startTime.toLocalTime())){
            throw new IllegalArgumentException("Start time cannot be the same as end time");
        }
		return businessHours;
	}

    /**
	 * Finds business hour for a specific employee
	 * @param employee employee object
	 * @return a list of business hours for the specific employee
	 */
    @Transactional
	public List<BusinessHour> getBusinessHoursbyEmployee(Employee employee){
    	if (employee==null) {
    		throw new IllegalArgumentException("Employee cannot be empty");
    	}
		return businessHourRepository.findBusinessHoursByEmployee(employee);
	}

    /**
	 * Finds business hour by a specific grocery store system
	 * @param groceryStoreSystem Grocery Store System object
	 * @return a list of business hours with for the specified grocery store system
	 */
    @Transactional
	public List<BusinessHour> getOpeningHours(GroceryStoreSystem system){
    	if (system==null) {
    		throw new IllegalArgumentException("Grocery Store System cannot be empty");
    	}
    	return businessHourRepository.findBusinessHoursByGroceryStoreSystem(system);
	}

    /**
	 * Updates business hour for grocery store system
	 * @param day specify the new week day
	 * @param startTime specify the new start time of the business hour
	 * @param endTime specify the new end time of the business hour
	 * @param working specify whether the grocery store system is open
	 * @param groceryStoreSystem specify the grocery store system object
	 * @return unique updated business hour linked to the grocery store system
	 */
    @Transactional
    public BusinessHour updateBusinessHour(GroceryStoreSystem groceryStoreSystem, WeekDay day, Time startTime, Time endTime, boolean working) {
        if(day == null) {
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
        if (startTime.toLocalTime().equals(endTime.toLocalTime())){
            throw new IllegalArgumentException("End time cannot be the same as Start time");
        }
        if (groceryStoreSystem==null) {
        	throw new IllegalArgumentException("Grocery store is null");
        }
        List<BusinessHour> businessHours = businessHourRepository.findBusinessHoursByGroceryStoreSystem(groceryStoreSystem);
        BusinessHour businessHour1=null;
        for(BusinessHour businessHour : businessHours) {
            WeekDay shift = businessHour.getDay();
            if(day.equals(shift)) {
                businessHour.setStartTime(startTime);
                businessHour.setEndTime(endTime);
                businessHour.setWorking(working);
                businessHour1=businessHour;
            } 
        }
        return businessHour1;
    }

    /**
   	 * Deletes a business hour by specific id
   	 * @param id unique identifier for business hour
   	 * @return a boolean indicating whether the business hour is deleted
   	 */
    @Transactional
	public boolean deleteBusinessHourbyID(int id){
        if (id<0){
            return false;
        }
        else{
            BusinessHour businesshour=businessHourRepository.findBusinessHourById(id);
            businessHourRepository.delete(businesshour);
            return true;
        }
		
	}

    /**
   	 * Deletes a business hour by specific week day
   	 *  @param day week day that corresponds to the specific business hour
   	 * @return a boolean indicating whether the business hour is deleted
   	 */
    @Transactional
	public boolean deleteBusinessHourbyDay(WeekDay day){
        if (day==null){
            return false;
        }
        else{
            List<BusinessHour> allbusinesshour=toList(businessHourRepository.findAll());
            List<BusinessHour> businesshour=businessHourRepository.findBusinessHourByDay(day);
            allbusinesshour.removeAll(businesshour);
            return true;
        }
	}

    /**
   	 * Deletes a business hour by specific working status
   	 * @param working working status that corresponds to the specific business hour
   	 * @return a boolean indicating whether the business hour is deleted
   	 */
    @Transactional
	public boolean deleteBusinessHourbyWoring(Boolean working){
        if (working==null){
            return false;
        }
        else{
            List<BusinessHour> allbusinesshour=toList(businessHourRepository.findAll());
            List<BusinessHour> businesshour=businessHourRepository.findBusinessHourByWorking(working);
            allbusinesshour.removeAll(businesshour);
            return true;
        }
	}

    /**
   	 * Deletes a business hour by specific start times between 
   	 * @param startTime Start time that corresponds to the specific business hour
	 * @param endTime end time that corresponds to the specific business hour
   	 * @return a boolean indicating whether the business hour is deleted
   	 */
    @Transactional
	public boolean deleteBusinessHourbyTime(Time startTime, Time endTime){
        if (startTime==null||endTime==null){
            return false;
        }
        else{
            List<BusinessHour> allbusinesshour=toList(businessHourRepository.findAll());
            List<BusinessHour> businesshour=businessHourRepository.findBusinessHourByStartTimeBetween(startTime,endTime);
            allbusinesshour.removeAll(businesshour);
            return true;
        }
	}

	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
}