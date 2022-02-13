package mcgill.ecse321.model;
import java.util.*;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Employee extends Worker{

	public enum WeekDay { Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday }

	//Employee Associations
	private Set<BusinessHour> businessHours;

	// !!! One to seven relationship. How do to?
	@OneToOne(optional=false)
	public Set<BusinessHour> setBusinessHours() {
		return this.businessHours;
	}

	public void getBusinessHours(Set<BusinessHour> businessHours) {
		this.businessHours = businessHours;
	}

}