package mcgill.ecse321.grocerystore.model;

import javax.persistence.Entity;
import javax.persistence.Enumerated;

@Entity
public class Customer extends UserRole{
	public enum TierClass { Gold, Silver, Bronze }

	@Enumerated
	private TierClass tierclass;
	private boolean ban;

	public TierClass getTierclass(){
		return tierclass;
	}
	
	public void setTierclass(TierClass aTierclass){
		this.tierclass = aTierclass;
	}

	public boolean getBan(){
		return ban;
	}
	
	public void setBan(boolean aBan){
		this.ban = aBan;
	}
}