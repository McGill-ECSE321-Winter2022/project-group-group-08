package mcgill.ecse321.grocerystore.model;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Person{
	
	@Id
	private String email;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String address;
	private String image;
	public void setImage(String value) {
		this.image = value;
	}

	public String getImage() {
		return this.image;
	}
	public void setEmail(String value) {
		this.email = value;
	}

	public String getEmail() {
		return this.email;
	}
	
	public void setFirstName(String value) {
		this.firstName = value;
	}
	
	public String getFirstName() {
		return this.firstName;
	}

	public void setLastName(String value) {
		this.lastName = value;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public void setPhoneNumber(String value) {
		this.phoneNumber = value;
	}
	
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setAddress(String value) {
		this.address = value;
	}
	
	public String getAddress() {
		return this.address;
	}
}
