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
     * create business hours for employees
     * @param day the specific day of the hour
     * @param startTime the start time of that shift
     * @param endTime the end time of the shift
     * @param working whether they are working that day or not
     * @param employee which employee this hour is for
     * @return BusinessHour
     */
    @Transactional
    public BusinessHour createBusinessHourforEmployee(WeekDay day, Time startTime, Time endTime, boolean working, Employee employee){
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
        if(employee==null) {
        	throw new IllegalArgumentException("Employee cannot be empty");
        }
        BusinessHour businessHour = new BusinessHour();
        businessHour.setDay(day);
        businessHour.setStartTime(startTime);
        businessHour.setEndTime(endTime);
        businessHour.setWorking(working);
        businessHour.setEmployee(employee);
        businessHourRepository.save(businessHour);
        return businessHour;
    }

    /**
     * create business hours for Grocery Store System
     * @param day the specific day of the hour
     * @param startTime the start time of that day
     * @param endTime the end time of the day
     * @param working whether its open that day or not
     * @param groceryStoreSystem which system this is for
     * @return BusinessHour
     */
    @Transactional
    public BusinessHour createBusinessHourforGroceryStoreSystem(WeekDay day, Time startTime, Time endTime, boolean working, GroceryStoreSystem groceryStoreSystem){
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
        if (groceryStoreSystem==null) {
        	throw new IllegalArgumentException("Grocery Store System cannot be empty");
        }
        BusinessHour businessHour = new BusinessHour();
        businessHour.setDay(day);
        businessHour.setStartTime(startTime);
        businessHour.setEndTime(endTime);
        businessHour.setWorking(working);
        businessHour.setGroceryStoreSystem(groceryStoreSystem);
        businessHourRepository.save(businessHour);
        return businessHour;
    }

    /**
     * get all the business hours 
     * @return List<BusinessHour>
     */
    @Transactional
	public List<BusinessHour> getAllBusinessHours(){
		return toList(businessHourRepository.findAll());
	}
	
    /**
     * get the business hour by id
     * @param id the id that is associated to that hour
     * @return BusinessHour
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
     * get all the business hours that is associated with a certain day
     * @param day the day we are searching for
     * @return List<BusinessHour>
     */
    @Transactional
	public List<BusinessHour> getBusinessHoursbyDay(WeekDay day){
    	if(day==null) {
    		throw new IllegalArgumentException("Day is empty");
    	}
        List<BusinessHour> businessHours = businessHourRepository.findBusinessHourByDay(day);
        if (businessHours == null || businessHours.isEmpty()){
            throw new IllegalArgumentException("No such business hour with weekday " + day + " exists");
        }
		return businessHours;
	}

    /**
     * get all the hours which are either working or not working
     * @param working detemining whether we want hours that is or isnt working
     * @return List<BusinessHour>
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
     * get all the hours with the start time between a range
     * @param startTime minimum start time
     * @param endTime maximum start time
     * @return List<BusinessHour>
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
     * Get all the hours that's associated by a certain employee
     * @param employee the employee that is associated with the hours
     * @return List<BusinessHour>
     */
    @Transactional
	public List<BusinessHour> getBusinessHoursbyEmployee(Employee employee){
    	if (employee==null) {
    		throw new IllegalArgumentException("Employee cannot be empty");
    	}
		return businessHourRepository.findBusinessHoursByEmployee(employee);
	}

    /**
     * getting the opening hour for a given grocery store system
     * @param system the system we want the hours for
     * @return List<BusinessHour>
     */
    @Transactional
	public List<BusinessHour> getOpeningHours(GroceryStoreSystem system){
    	if (system==null) {
    		throw new IllegalArgumentException("Grocery Store System cannot be empty");
    	}
    	return businessHourRepository.findBusinessHoursByGroceryStoreSystem(system);
	}

    /**
     * updating a business hour
     * @param id the id associated with the hour
     * @param groceryStoreSystem the new system with the hour
     * @param employee the new employee associated with the hour
     * @param day the new day for the hour
     * @param startTime the new start time for the hour
     * @param endTime the new end time for the hour
     * @param working the new working boolean for the hour
     * @return BusinessHour
     */
    @Transactional
    public BusinessHour updateBusinessHour(int id, GroceryStoreSystem groceryStoreSystem, Employee employee, WeekDay day, Time startTime, Time endTime, boolean working) {
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
        BusinessHour businessHour = businessHourRepository.findBusinessHourById(id);
        businessHour.setStartTime(startTime);
        businessHour.setEndTime(endTime);
        businessHour.setWorking(working);
    	businessHour.setGroceryStoreSystem(groceryStoreSystem);
    	businessHour.setEmployee(employee);
        businessHourRepository.save(businessHour);
        return businessHour;
    }

    /**
     * Deleting the business hour by ID
     * @param id the id associated with the hour
     * @return boolean - whether its deleted successfully or not
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
     * deleting all hours on a given day
     * @param day the given day
     * @return boolean - whetehr its deleted successfullt or not
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
     * deleting all hours that are either working or not working
     * @param working determining which category we want to delete
     * @return boolean - whether it deletes successfully or not
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
     * deleting all hours with the start time between a given range
     * @param startTime the minimum start time
     * @param endTime the maximum start time
     * @return boolean - whether it deletes successfully or not
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