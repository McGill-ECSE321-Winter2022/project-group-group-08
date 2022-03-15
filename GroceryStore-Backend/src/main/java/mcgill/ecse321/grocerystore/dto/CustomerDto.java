package mcgill.ecse321.grocerystore.dto;
import mcgill.ecse321.grocerystore.model.Customer.TierClass;

public class CustomerDto extends UserRoleDto {

//	private enum TierClass { Gold, Silver, Bronze }
	
	private TierClass tierclass;
	private boolean ban;
	
	/**
	 * Constructor to create a customer DTO
	 */
	public CustomerDto() {
	}
	
	
	/**
	 * Constructor to create a customer DTO
	 * @param tierClass of customer
	 * @param ban status of customer
	 * @param userRole DTO
	 */
	public CustomerDto(int userRoleId, PersonDto person, TierClass tierClass, boolean ban){
		super(userRoleId, person);
		this.tierclass = tierClass;
		this.ban = ban;
		
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
}
