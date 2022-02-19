package mcgill.ecse321.grocerystore.model;
import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Employee extends UserRole{
	@OneToMany(cascade = CascadeType.ALL)
	private Set<BusinessHour> workingHours;
	
	//relationships
	public Set<BusinessHour> getWorkingHours() {
		return this.workingHours;
	}

	public void setWorkingHours(Set<BusinessHour> workingHours) {
		this.workingHours = workingHours;
	}

}