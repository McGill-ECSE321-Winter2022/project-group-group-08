package mcgill.ecse321.grocerystore.dto;

public class PersonDto {

	private String email;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String address;
	
	public PersonDto() {
		
	}

	public PersonDto(String email, String firstName, String lastName, String phoneNumber, String address) {
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}
		
	public String getEmail() {
		return email;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public String getAddress() {
		return address;
	}
		
}
