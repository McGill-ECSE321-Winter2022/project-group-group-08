package mcgill.ecse321.grocerystore.dto;

import mcgill.ecse321.grocerystore.model.Person;

public class PersonDto {

	private String email;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String address;
	private String image;
	public PersonDto() {
		
	}

	public PersonDto(String image, String email, String firstName, String lastName, String phoneNumber, String address) {
		this.image = image;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}

	public String getImage() {
		return this.image;
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
	
	public static PersonDto convertToDto(Person p) {
		if (p == null) {
			throw new IllegalArgumentException("There is no such Person!");
		}
		PersonDto personDto = new PersonDto(p.getImage(), p.getEmail(),p.getFirstName(),p.getLastName(),p.getPhoneNumber(),
				p.getAddress());
		return personDto;
	}
}
