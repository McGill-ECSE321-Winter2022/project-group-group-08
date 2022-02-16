//package mcgill.ecse321.grocerystore.model;
//
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.OneToOne;
//
//@Entity
//@Table(name="users")
//public class User{
//
//	private int phoneNumber;
//	private String address;
//	private String firstName;
//	private String lastName;
//	private String email;
//	
//	private UserRole userRole;
//	private Account account;
//	
//	public void setPhoneNumber(int value) {
//		this.phoneNumber = value;
//	}
//	public int getPhoneNumber() {
//		return this.phoneNumber;
//	}
//
//	public void setAddress(String value) {
//		this.address = value;
//	}
//	public String getAddress() {
//		return this.address;
//	}
//
//	public void setFirstName(String value) {
//		this.firstName = value;
//	}
//	public String getFirstName() {
//		return this.firstName;
//	}
//
//	public void setLastName(String value) {
//		this.lastName = value;
//	}
//	public String getLastName() {
//		return this.lastName;
//	}
//
//	
//	public void setEmail(String value) {
//		this.email = value;
//	}
//	@Id
//	public String getEmail() {
//		return this.email;
//	}
//	
//	@OneToOne(optional=false)
//	public UserRole getUserRole() {
//		return this.userRole;
//	}
//	public void setUserRole(UserRole userRole) {
//		this.userRole = userRole;
//	}
//
//	@OneToOne(optional=false)
//	public Account getAccount() {
//		return this.account;
//	}
//
//	public void setAccount(Account account) {
//		this.account = account;
//	}
//}
