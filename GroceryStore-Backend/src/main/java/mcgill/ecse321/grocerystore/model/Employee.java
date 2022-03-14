package mcgill.ecse321.grocerystore.model;
import java.util.Set;

import javax.persistence.Entity;

@Entity
public class Employee extends UserRole{

	private Set<BusinessHour> hours;

    public Set<BusinessHour> getWorkingHours() {
		return hours;
	}
}