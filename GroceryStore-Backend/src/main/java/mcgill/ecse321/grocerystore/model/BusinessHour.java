package mcgill.ecse321.grocerystore.model;
import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BusinessHour{
	public enum WeekDay { Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday }
	
	private WeekDay day;
	private Time startTime;
	private Time endTime;
	private boolean working;
	private int id;
	
	
	public void setId(int id){
		this.id = id;
	}
	
	@Id
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
}