package mcgill.ecse321.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class User{
	
	private int phoneNumber;
	public void setPhoneNumber(int value) {
		this.phoneNumber = value;
	}
	@Id
	public int getPhoneNumber() {
		return this.phoneNumber;
	}
	
	private String address;
	public void setAddress(String value) {
		this.address = value;
	}
	public String getAddress() {
		return this.address;
	}
	
	private String firstName;
	public void setFirstName(String value) {
		this.firstName = value;
	}
	public String getFirstName() {
		return this.firstName;
	}
	
	private String lastName;
	public void setLastName(String value) {
		this.lastName = value;
	}
	public String getLastName() {
		return this.lastName;
	}
	
	@Id
	private String email;
	public void setEmail(String value) {
		this.email = value;
	}
	public String getEmail() {
		return this.email;
	}
	
	// !!! userrole is an abstract class. How do we reference it
	@OneToMany(cascade={CascadeType.ALL})
	public UserRole getEvent() {
	   return this.userRole;
	}
	public void setUserRole(UserRole userRole) {
		   this.userRole = userRole;
		}
	
	private Set<Account> accounts;

	@OneToOne(cascade={CascadeType.ALL})
	public Set<Account> getAccounts() {
	   return this.accounts;
	}

	public void setAccounts(Set<Account> accounts) {
	   this.accounts = accounts;
	}
}
