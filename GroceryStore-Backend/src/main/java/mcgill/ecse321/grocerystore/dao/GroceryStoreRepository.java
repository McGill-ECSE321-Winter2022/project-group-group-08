package mcgill.ecse321.grocerystore.dao;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.yaml.snakeyaml.events.AliasEvent;

import mcgill.ecse321.model.Account;
import mcgill.ecse321.model.Customer;
import mcgill.ecse321.model.GroceryStoreSystem;
import mcgill.ecse321.model.Customer.TierClass;
import mcgill.ecse321.model.User;
import mcgill.ecse321.model.UserRole;

@Repository
public class GroceryStoreRepository {
	private GroceryStoreSystem groceryStoreSystem;

	@Autowired
	EntityManager entityManager;

	@Transactional
	public Account createAccount(Boolean inTown, String username, String password) {
		Account a = new Account();
		a.setInTown(inTown);
		a.setUsername(username);
		a.setPassword(password);
		entityManager.persist(a);
		return a;
	}

	@Transactional
	public Account getAccount(String username) {
		Account a = entityManager.find(Account.class, username);
		return a;
	}
	
	@Transactional
	public List<Account> getAllAccountsFilteredByInTown(boolean inTownCondition) {
		TypedQuery<Account> q = entityManager.createQuery("select a from Account where a.inTown = :inTownCondition", Account.class);
		q.setParameter("inTownCondition", inTownCondition);
		List<Account> resultList = q.getResultList();
		return resultList;
	}
	
	@Transactional
	public User createUser(String firstName, String lastName, String email, String Address, int phoneNumber) {
		User u = new User();
		u.setPhoneNumber(phoneNumber);
		u.setAddress(Address);
		u.setFirstName(firstName);
		u.setLastName(lastName);
		u.setEmail(email);
		entityManager.persist(u);
		return u;
	}
	
	@Transactional
	public User getUser(String email) {
		User u = entityManager.find(User.class, email);
		return u;
	}
	
	
	@Transactional
	public List<User> getAllUsers() {
		TypedQuery<User> q = entityManager.createQuery("select * from User",User.class);
		List<User> resultList = q.getResultList();
		return resultList;
	}
	
	@Transactional
	public Customer createCustomer(String email, TierClass tierClass, boolean ban) {
		User u = entityManager.find(User.class, email);
		Customer c = new Customer(u, groceryStoreSystem, tierClass, ban);
		entityManager.persist(c);
		return c;
	}

	@Transactional
	public Customer getCustomer(int id) {
		Customer c = entityManager.find(Customer.class, id);
		return c;
	}
	
	@Transactional
	public List<Customer> getAllCustomerFilteredByBan(boolean isBanned) {
		TypedQuery<Customer> q = entityManager.createQuery("select c from Customer where c.ban = :isBanned", Customer.class);
		q.setParameter("isBanned", isBanned);
		List<Customer> resultList = q.getResultList();
		return resultList;
	}
	
	@Transactional
	public List<Customer> getAllCustomerFilteredByTier(TierClass aTierClass) {
		TypedQuery<Customer> q = entityManager.createQuery("select c from Customer where c.tierClass = :aTierClass", Customer.class);
		q.setParameter("aTierClass", aTierClass);
		List<Customer> resultList = q.getResultList();
		return resultList;
	}

	//userRole: pk is id
	//item: pk is id
	//order: pk is orderNum
	//businesshour: pk is id
	//quantity: pk is id
	//cart: pk is id


}