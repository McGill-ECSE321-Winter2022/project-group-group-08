package mcgill.ecse321.grocerystore.model;
import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class BusinessHour{
	public enum WeekDay { Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday }
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Enumerated
	private WeekDay day;
	private Time startTime;
	private Time endTime;
	private boolean working;
	
	@ManyToOne(optional=true)
	private Employee employee;
	
	@ManyToOne(optional=true)
	private GroceryStoreSystem groceryStoreSystem;
	
	//Attribute getters and setters
	public void setId(int id){
		this.id = id;
	}
	
	public int getId(){
		return this.id;
	}

	public void setDay(WeekDay aDay){
		this.day = aDay;
	}

	public WeekDay getDay(){
		return day;
	}

	public void setStartTime(Time aStartTime){
		this.startTime = aStartTime;
	}

	public Time getStartTime(){
		return startTime;
	}

	public void setEndTime(Time aEndTime){
		this.endTime = aEndTime;
	}

	public Time getEndTime(){
		return endTime;
	}

	public void setWorking(boolean aWorking){
		this.working = aWorking;
	}
	
	public boolean getWorking(){
		return working;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setGroceryStoreSystem(GroceryStoreSystem groceryStoreSystem) {
		this.groceryStoreSystem = groceryStoreSystem;
	}

	public GroceryStoreSystem getGroceryStoreSystem() {
		return this.groceryStoreSystem;
	}
}
