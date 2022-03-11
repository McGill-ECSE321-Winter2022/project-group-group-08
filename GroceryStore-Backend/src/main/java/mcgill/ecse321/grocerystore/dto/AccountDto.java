package mcgill.ecse321.grocerystore.dto;

public class AccountDto {

	private String username;
	private String password;
	private boolean inTown;
	private int totalPoints;
	private PersonDto person;
	private CartDto cart;
	
	
	public AccountDto() {
		
	}

	public AccountDto(String username, String password, boolean inTown, int totalPoints,PersonDto person) {
		this.username = username;
		this.password = password;
		this.inTown = inTown;
		this.totalPoints = totalPoints;
		this.person = person;
	}
	
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public boolean isInTown() {
		return inTown;
	}

	public int getTotalPoints() {
		return totalPoints;
	}

	public PersonDto getPerson() {
		return person;
	}

	public CartDto getCart() {
		return cart;
	}
}
