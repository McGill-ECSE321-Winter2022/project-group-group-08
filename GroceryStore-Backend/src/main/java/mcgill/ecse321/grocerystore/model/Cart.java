package mcgill.ecse321.grocerystore.model;
import java.sql.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Cart {
	public enum OrderStatus { Processed, Transit, Fullfilled }
	public enum OrderType { Delivery, Pickup }
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private Date date;
	
	@OneToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="account_username", referencedColumnName="username")
	private Account account;
	
	//Attributes getters and setters
	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}
	
	public void setDate(Date aDate){
		this.date = aDate;
	}

	public Date getDate(){
		return date;
	}
	
	//Relationships
	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
}