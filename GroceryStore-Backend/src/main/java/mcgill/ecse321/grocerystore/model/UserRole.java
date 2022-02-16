package mcgill.ecse321.grocerystore.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

//@MappedSuperclass
@Entity
public class UserRole{
	
	@Id
	private int id;
	
	@OneToOne(mappedBy="userRole")
	private Person person;

	public void setId(int aId){
		this.id = aId;
	}
	public int getId(){
		return id;
	}

	public Person getPerson() {
		return this.person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
}