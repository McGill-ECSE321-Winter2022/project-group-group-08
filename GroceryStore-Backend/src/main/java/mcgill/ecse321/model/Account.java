package mcgill.ecse321.model;

public class Account{
	
  //Account Attributes
  private boolean inTown;
  private String username;
  private String password;

  //Account Associations
  private Cart cart;
  private User user;

  public boolean setInTown(boolean aInTown)
  {
    boolean wasSet = false;
    inTown = aInTown;
    wasSet = true;
    return wasSet;
  }

  public boolean setUsername(String aUsername)
  {
    boolean wasSet = false;
    username = aUsername;
    wasSet = true;
    return wasSet;
  }

  public boolean setPassword(String aPassword)
  {
    boolean wasSet = false;
    password = aPassword;
    wasSet = true;
    return wasSet;
  }

  public boolean getInTown()
  {
    return inTown;
  }

  public String getUsername()
  {
    return username;
  }

  public String getPassword()
  {
    return password;
  }
  /* Code from template association_GetOne */
  public Cart getCart()
  {
    return cart;
  }
  /* Code from template association_GetOne */
  public User getUser()
  {
    return user;
  }
}