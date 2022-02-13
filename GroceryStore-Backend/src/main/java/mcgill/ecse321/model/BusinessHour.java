package mcgill.ecse321.model;
import java.sql.Time;

public class BusinessHour
{

  public enum WeekDay { Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday }

  //BusinessHour Attributes
  private WeekDay day;
  private Time startTime;
  private Time endTime;
  private boolean working;


  public boolean setDay(WeekDay aDay)
  {
    boolean wasSet = false;
    day = aDay;
    wasSet = true;
    return wasSet;
  }

  public boolean setStartTime(Time aStartTime)
  {
    boolean wasSet = false;
    startTime = aStartTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndTime(Time aEndTime)
  {
    boolean wasSet = false;
    endTime = aEndTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setWorking(boolean aWorking)
  {
    boolean wasSet = false;
    working = aWorking;
    wasSet = true;
    return wasSet;
  }

  public WeekDay getDay()
  {
    return day;
  }

  public Time getStartTime()
  {
    return startTime;
  }

  public Time getEndTime()
  {
    return endTime;
  }

  public boolean getWorking()
  {
    return working;
  }
}