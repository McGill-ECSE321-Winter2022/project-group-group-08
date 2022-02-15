package mcgill.ecse321.model;
import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Employee extends Worker{

	public enum WeekDay { Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday }

	private Set<BusinessHour> businessHours;
	@OneToMany(cascade={CascadeType.ALL})
	public Set<BusinessHour> getBusinessHours() {
		return this.businessHours;
	}

	public void setBusinessHours(Set<BusinessHour> businessHours) {
		this.businessHours = businessHours;
	}

}