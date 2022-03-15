package mcgill.ecse321.grocerystore.dto;

public class CustomerDto {

	private enum TierClass { Gold, Silver, Bronze }
	
	private TierClass tierclass;
	private boolean ban;
	private UserRoleDto userRoleDto; 
	
	/**
	 * Constructor to create a customer DTO
	 */
	public CustomerDto() {
	}
	
	/**
	 * Constructor to create a customer DTO with only tier
	 * @param tierClass of customer
	 */
	public CustomerDto(TierClass tierClass){
		this.tierclass = tierClass;
	}
	
	/**
	 * Constructor to create a customer DTO
	 * @param tierClass of customer
	 * @param ban status of customer
	 * @param userRole DTO
	 */
	public CustomerDto(TierClass tierClass, boolean ban, UserRoleDto userRoleDto){
		this.tierclass = tierClass;
		this.ban = ban;
		this.userRoleDto = userRoleDto;
	}

	/**
	 * Getter for TierClass
	 * @return TierClass
	 */
	public String getTierClass() {
		return tierclass.toString();
	}

	/**
	 * Getter for Ban
	 * @return ban
	 */
	public boolean isBan() {
		return ban;
	}

	/**
	 * Getter for UserRole DTO
	 * @return UserRole DTO
	 */
	public UserRoleDto getUserRoleDto() {
		return userRoleDto;
	}
 
}
