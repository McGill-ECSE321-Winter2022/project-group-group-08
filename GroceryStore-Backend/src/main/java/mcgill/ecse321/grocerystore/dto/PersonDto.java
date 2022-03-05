package mcgill.ecse321.grocerystore.dto;

public class PersonDto {

	private String email;
	private String firstName;
	private String lastName;
	private int phoneNumber;
	private String address;
	private UserRoleDto userRole;
	private AccountDto account;
	
	public PersonDto() {
		
	}

	public PersonDto(String email, String firstName, String lastName, int phoneNumber, String address, 
			UserRoleDto userRole, AccountDto account) {
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.userRole = userRole;
		this.account = account;
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
	
	public int getPhoneNumber() {
		return phoneNumber;
	}
	
	public String getAddress() {
		return address;
	}
	
	public UserRoleDto getUserRole() {
		return userRole;
	}
	
	public AccountDto getAccount() {
		return account;
	}
		
}
