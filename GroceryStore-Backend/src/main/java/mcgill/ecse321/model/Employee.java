package mcgill.ecse321.model;
import java.util.*;
import java.sql.Time;

public class Employee extends Worker{

  public enum WeekDay { Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday }

  //Employee Associations
  private List<BusinessHour> businessHours;
  
  public Employee(User aUser, GroceryStoreSystem aGroceryStoreSystem, BusinessHour... allBusinessHours)
  {
    super(aUser, aGroceryStoreSystem);
    businessHours = new ArrayList<BusinessHour>();
    boolean didAddBusinessHours = setBusinessHours(allBusinessHours);
    if (!didAddBusinessHours)
    {
      throw new RuntimeException("Unable to create Employee, must have 7 businessHours. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  /* Code from template association_GetMany */
  public BusinessHour getBusinessHour(int index)
  {
    BusinessHour aBusinessHour = businessHours.get(index);
    return aBusinessHour;
  }

  public List<BusinessHour> getBusinessHours()
  {
    List<BusinessHour> newBusinessHours = Collections.unmodifiableList(businessHours);
    return newBusinessHours;
  }

  public int numberOfBusinessHours()
  {
    int number = businessHours.size();
    return number;
  }

  /* Code from template association_RequiredNumberOfMethod */
  public static int requiredNumberOfBusinessHours()
  {
    return 7;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBusinessHours()
  {
    return 7;
  }
  /* Code from template association_MaximumNumberOfMethod */
  public static int maximumNumberOfBusinessHours()
  {
    return 7;
  }
  /* Code from template association_SetUnidirectionalN */
  public boolean setBusinessHours(BusinessHour... newBusinessHours)
  {
    boolean wasSet = false;
    ArrayList<BusinessHour> verifiedBusinessHours = new ArrayList<BusinessHour>();
    for (BusinessHour aBusinessHour : newBusinessHours)
    {
      if (verifiedBusinessHours.contains(aBusinessHour))
      {
        continue;
      }
      verifiedBusinessHours.add(aBusinessHour);
    }

    if (verifiedBusinessHours.size() != newBusinessHours.length || verifiedBusinessHours.size() != requiredNumberOfBusinessHours())
    {
      return wasSet;
    }

    businessHours.clear();
    businessHours.addAll(verifiedBusinessHours);
    wasSet = true;
    return wasSet;
  }
}