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

	// !!! userRole is an abstract class. How do we reference it properly
	private Set<UserRole> userRole;
	@OneToMany(cascade={CascadeType.ALL})
	public Set<UserRole> getUserRole() {
		return this.userRole;
	}
	public void setUserRole(Set<UserRole> userRole) {
		this.userRole = userRole;
	}

	private Account account;
	@OneToOne(optional=false)
	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
}
