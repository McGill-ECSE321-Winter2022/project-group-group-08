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
import java.util.Set;

@Service
public class BusinessHourService {
    @Autowired
    BusinessHourRepository businessHourRepository;

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
        businessHour.setWorking(working);
        return businessHour;
    }

    @Transactional
	public List<BusinessHour> getAllBusinessHours(){
		return toList(businessHourRepository.findAll());
	}
	
    @Transactional
	public BusinessHour getBusinessHoursbyID(int id){
        BusinessHour businesshour = businessHourRepository.findBusinessHourById(id);
        if (businesshour==null){
            throw new IllegalArgumentException("No such id " + id + " exists");
        }
		return businesshour;
	}

    @Transactional
	public List<BusinessHour> getBusinessHoursbyDay(WeekDay day){
        List<BusinessHour> businessHours = businessHourRepository.findBusinessHourByDay(day);
        if (businessHours == null || businessHours.isEmpty()){
            throw new IllegalArgumentException("No such business hour with weekday " + day + " exists");
        }
		return businessHours;
	}

    @Transactional
	public List<BusinessHour> getBusinessHoursbyWorking(Boolean working){
        List<BusinessHour> businessHours = businessHourRepository.findBusinessHourByWorking(working);
        if (businessHours == null || businessHours.isEmpty()){
            throw new IllegalArgumentException("No such business hour with working status " + working + " exists");
        }
		return businessHours;
	}

    @Transactional
	public List<BusinessHour> getBusinessHoursbyTime(Time startTime, Time endTime){
        List<BusinessHour> businessHours = businessHourRepository.findBusinessHourByStartTimeBetween(startTime,endTime);
        if (endTime.toLocalTime().isAfter(startTime.toLocalTime())){
            throw new IllegalArgumentException("Start time is later than end time");
        }
		return businessHours;
	}

    @Transactional
	public Set<BusinessHour> getBusinessHoursbyEmployee(Employee employee){
		return employee.getWorkingHours();
	}

    @Transactional
	public Set<BusinessHour> getOpeningHours(GroceryStoreSystem system){
		return system.getOpeningHours();
	}

    @Transactional
    public void updateBusinessHour(GroceryStoreSystem groceryStoreSystem, WeekDay day, Time startTime, Time endTime, boolean working) {
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
        Set<BusinessHour> businessHours = groceryStoreSystem.getOpeningHours();
        for(BusinessHour businessHour : businessHours) {
            WeekDay shift = businessHour.getDay();
            if(day.equals(shift)) {
                businessHour.setStartTime(startTime);
                businessHour.setEndTime(endTime);
                businessHour.setWorking(working);
            }
        } 
    }

    @Transactional
	public boolean deleteBusinessHour(BusinessHour businessHour){
        if (businessHour==null){
            return false;
        }
        else{
            businessHourRepository.delete(businessHour);
            return true;
        }
		
	}

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
