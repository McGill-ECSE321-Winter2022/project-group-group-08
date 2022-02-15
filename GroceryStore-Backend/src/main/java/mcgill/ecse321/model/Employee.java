package mcgill.ecse321.model;
import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Employee extends Worker{

	public enum WeekDay { Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday }

	private Set<BusinessHour> workingHours;
	@OneToMany(cascade={CascadeType.ALL})
	public Set<BusinessHour> getWorkingHours() {
		return this.workingHours;
	}

	public void setBusinessHours(Set<BusinessHour> workingHours) {
		this.workingHours = workingHours;
	}

}