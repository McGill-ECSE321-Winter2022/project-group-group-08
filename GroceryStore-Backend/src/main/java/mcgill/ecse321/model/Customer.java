package mcgill.ecse321.model;

import javax.persistence.Entity;

@Entity
public class Customer extends UserRole{

	public enum TierClass { Gold, Silver, Bronze }

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