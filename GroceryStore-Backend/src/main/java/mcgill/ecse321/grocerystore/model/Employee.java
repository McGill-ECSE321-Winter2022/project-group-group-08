//package mcgill.ecse321.grocerystore.model;
//import java.util.*;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Entity;
//import javax.persistence.OneToMany;
//
//@Entity
//public class Employee extends Worker{
//
//	private Set<BusinessHour> workingHours;
//	@OneToMany(cascade={CascadeType.ALL})
//	public Set<BusinessHour> getWorkingHours() {
//		return this.workingHours;
//	}
//
//	public void setWorkingHours(Set<BusinessHour> workingHours) {
//		this.workingHours = workingHours;
//	}
//
//}