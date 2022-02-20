package mcgill.ecse321.grocerystore.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.OneToOne;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class UserRole{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@OneToOne(mappedBy="userRole")
	private Person person;

	//attribute getters and setters
	
	public void setId(int aId){
		this.id = aId;
	}
	public int getId(){
		return id;
	}
	
	// relationships
	
	public Person getPerson() {
		return this.person;
	}
	
	public void setPerson(Person person) {
		this.person = person;
	}
}