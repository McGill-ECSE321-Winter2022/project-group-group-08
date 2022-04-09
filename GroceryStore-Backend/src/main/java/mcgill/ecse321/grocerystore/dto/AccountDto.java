package mcgill.ecse321.grocerystore.dto;

import mcgill.ecse321.grocerystore.model.Account;

public class AccountDto {

	private String username;
	private String password;
	private boolean inTown;
	private int totalPoints;
	private PersonDto person;
	
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
	
	public static AccountDto convertToDto(Account a) {
		if (a == null) {
			throw new IllegalArgumentException("There is no such Account!");
		}
		AccountDto accountDto = new AccountDto(a.getUsername(),a.getPassword(),a.getInTown(),a.getTotalPoints(),
				PersonDto.convertToDto(a.getPerson()));
		return accountDto;
	}
}
