//package mcgill.ecse321.grocerystore.model;
//
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.OneToOne;
//
//@Entity
//public class UserRole{
//	private Person person;
//	private int id;
//
//	public void setId(int aId){
//		this.id = aId;
//	}
//
//	@Id
//	public int getId(){
//		return id;
//	}
//
//	@OneToOne(optional=false)
//	public Person getPerson() {
//		return this.person;
//	}
//	public void setPerson(Person person) {
//		this.person = person;
//	}
//
//}