//package mcgill.ecse321.grocerystore.model;
//
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.OneToOne;
//
//@Entity
//public class UserRole{
//	private User user;
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
//	public User getUser() {
//		return this.user;
//	}
//	public void setUser(User user) {
//		this.user = user;
//	}
//
//}