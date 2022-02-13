package mcgill.ecse321.model;
import java.util.*;
import java.sql.Time;

public class Employee extends Worker{

  public enum WeekDay { Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday }

  //Employee Associations
  private Set<BusinessHour> businessHours;
  

  public Set<BusinessHour> getBusinessHours(){
    return businessHours;
  }

  public void setBusinessHours(Set<BusinessHour> newBusinessHours){
	  this.businessHours = newBusinessHours;
  }
  
}