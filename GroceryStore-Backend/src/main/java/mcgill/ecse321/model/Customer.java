package mcgill.ecse321.model;

public class Customer extends UserRole{

  public enum TierClass { Gold, Silver, Bronze }


  //Customer Attributes
  private TierClass tierclass;
  private boolean ban;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Customer(User aUser, GroceryStoreSystem aGroceryStoreSystem, TierClass aTierclass, boolean aBan)
  {
    super(aUser, aGroceryStoreSystem);
    tierclass = aTierclass;
    ban = aBan;
  }

  public boolean setTierclass(TierClass aTierclass)
  {
    boolean wasSet = false;
    tierclass = aTierclass;
    wasSet = true;
    return wasSet;
  }

  public boolean setBan(boolean aBan)
  {
    boolean wasSet = false;
    ban = aBan;
    wasSet = true;
    return wasSet;
  }

  public TierClass getTierclass()
  {
    return tierclass;
  }

  public boolean getBan()
  {
    return ban;
  }

}