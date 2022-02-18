package mcgill.ecse321.grocerystore.dao;

import java.sql.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mcgill.ecse321.model.Account;
import mcgill.ecse321.model.BusinessHour;
import mcgill.ecse321.model.BusinessHour.WeekDay;
import mcgill.ecse321.model.Cart;
import mcgill.ecse321.model.Customer;
import mcgill.ecse321.model.GroceryStoreSystem;
import mcgill.ecse321.model.Item;
import mcgill.ecse321.model.Manager;
import mcgill.ecse321.model.Order;
import mcgill.ecse321.model.Order.OrderStatus;
import mcgill.ecse321.model.Order.OrderType;
import mcgill.ecse321.model.Customer.TierClass;
import mcgill.ecse321.model.Employee;
import mcgill.ecse321.model.User;

@Repository
public class GroceryStoreRepository {
	private GroceryStoreSystem groceryStoreSystem;

	@Autowired
	EntityManager entityManager;

	@Transactional
	public Account createAccount(Boolean inTown, String username, String password, int cartId) {
		Account a = new Account();
		a.setInTown(inTown);
		a.setUsername(username);
		a.setPassword(password);
		Cart c = getCart(cartId);
		a.setCart(c);
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
	public User createUser(String firstName, String lastName, String email, String Address, int phoneNumber, String username) {
		User u = new User();
		u.setPhoneNumber(phoneNumber);
		u.setAddress(Address);
		u.setFirstName(firstName);
		u.setLastName(lastName);
		u.setEmail(email);
		Account a = getAccount(username);
		u.setAccount(a);
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
	public List<Customer> getAllCustomers() {
		TypedQuery<Customer> q = entityManager.createQuery("select * from Customer",Customer.class);
		List<Customer> resultList = q.getResultList();
		return resultList;
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

	@Transactional
	public Employee createEmployee(String email) {
		BusinessHour.WeekDay[] weekdays = {WeekDay.Sunday, WeekDay.Monday, WeekDay.Tuesday, WeekDay.Wednesday, WeekDay.Thursday, WeekDay.Friday, WeekDay.Saturday};
		HashSet<BusinessHour> bhSet= new HashSet<BusinessHour>();
		User u = entityManager.find(User.class, email);
		Employee e = new Employee(u, groceryStoreSystem);
		for(int i=0; i<weekdays.length; i++) {
			BusinessHour bh = new BusinessHour();
			bh.setDay(WeekDay.Monday);
			bh.setWorking(false);
			bhSet.add(bh);
		}
		e.setBusinessHours(bhSet);
		entityManager.persist(e);
		return e;
	}

	@Transactional
	public Employee getEmployee(int id) {
		Employee e = entityManager.find(Employee.class, id);
		return e;
	}
	
	@Transactional
	public List<Employee> getAllEmployees() {
		TypedQuery<Employee> q = entityManager.createQuery("select * from Employee",Employee.class);
		List<Employee> resultList = q.getResultList();
		return resultList;
	}

	@Transactional
	public List<Employee> getEmployeeFilteredByWorkdays(BusinessHour.WeekDay workday, boolean isWorking) {
		TypedQuery<Employee> q = entityManager.createQuery("select e from Employee where e.day = :workday AND e.working = :isWorking", Employee.class);
		q.setParameter("workday", workday);
		q.setParameter("isWorking", isWorking);
		List<Employee> resultList = q.getResultList();
		return resultList;
	}

	@Transactional
	public Manager createManager(String email) {
		User u = entityManager.find(User.class, email);
		Manager m = new Manager(u, groceryStoreSystem);
		entityManager.persist(m);
		return m;
	}

	@Transactional
	public Manager getManager(int id) {
		Manager m = entityManager.find(Manager.class, id);
		return m;
	}

	@Transactional
	public Item createItem(String name, int price, int point, Date returnDate, boolean pickup, boolean delivery, boolean inStore) {
		Item i = new Item();
		i.setName(name);
		i.setPrice(price);
		i.setPoint(point);
		i.setReturnDate(returnDate);
		i.setPickup(pickup);
		i.setDelivery(delivery);
		i.setInStore(inStore);
		entityManager.persist(i);
		return i;
	}

	@Transactional
	public Item getItem(int id) {
		Item i = entityManager.find(Item.class, id);
		return i;
	}
	
	@Transactional
	public List<Item> getAllItems() {
		TypedQuery<Item> q = entityManager.createQuery("select * from Item",Item.class);
		List<Item> resultList = q.getResultList();
		return resultList;
	}

	@Transactional
	public List<Item> getItemFilteredByPrice(int aPrice, String comparisonSymbol) {
		//Comparison Symbol: =, >, <, >=, <=
		TypedQuery<Item> q = entityManager.createQuery("select i from Item where i.price :comparisonSymbol :aPrice", Item.class);
		q.setParameter("aPrice", aPrice);
		q.setParameter("comparisonSymbol", comparisonSymbol);
		List<Item> resultList = q.getResultList();
		return resultList;
	}
	
	@Transactional
	public List<Item> getItemFilteredByPoint(int aPoint, String comparisonSymbol) {
		//Comparison Symbol: =, >, <, >=, <=
		TypedQuery<Item> q = entityManager.createQuery("select i from Item where i.point :comparisonSymbol :aPoint", Item.class);
		q.setParameter("aPoint", aPoint);
		q.setParameter("comparisonSymbol", comparisonSymbol);
		List<Item> resultList = q.getResultList();
		return resultList;
	}
	
//	!!! Get Item filtered by return date
//	@Transactional
//	public List<Item> getItemFilteredByDate() {
	
//	}
	
	@Transactional
	public List<Item> getItemFilteredByPickupDeliveryInStore(Boolean aPickup, Boolean aDelivery, Boolean aInStore) {
		//boolean may be null. If null, condition is not put into consideration when filtering
		//if another attribute is added, just do conditions.put("the name of the attribute", the parameter)
		HashMap<String, Boolean> conditions = new HashMap<String, Boolean>();
		conditions.put("pickup", aPickup);
		conditions.put("delivery", aDelivery);
		conditions.put("inStore", aInStore);
		
		String queryCondition = "select i from Item where";
		int initQueryLength = queryCondition.length();
		
		for(Map.Entry<String, Boolean> entry : conditions.entrySet()) {
		    String conditionName = entry.getKey();
		    Boolean conditionValue = entry.getValue();
		    if(conditionValue) {
				if(queryCondition.length() > initQueryLength) {
					queryCondition += " AND ";
				}
				queryCondition += "i." + conditionName + "=" + conditionValue;
			}
		}
		TypedQuery<Item> q = entityManager.createQuery(queryCondition, Item.class);
		List<Item> resultList = q.getResultList();
		return resultList;
	}
	
	@Transactional
	public Order createOrder(int OrderNum, OrderStatus aOrderStatus, OrderType type, int cartId) {
		Order o = new Order();
		o.setOrderNum(OrderNum);
		o.setOrderStatus(aOrderStatus);
		o.setOrderType(type);
		Cart c = getCart(cartId);
		o.setCart(c);
		entityManager.persist(o);
		return o;
	}

	@Transactional
	public Order getOrder(int orderNum) {
		Order i = entityManager.find(Order.class, orderNum);
		return i;
	}
	
	@Transactional
	public List<Order> getAllOrders() {
		TypedQuery<Order> q = entityManager.createQuery("select * from Order",Order.class);
		List<Order> resultList = q.getResultList();
		return resultList;
	}

	@Transactional
	public List<Order> getOrderFilteredByStatus(OrderStatus aOrderStatus) {
		TypedQuery<Order> q = entityManager.createQuery("select o from Order where o.status = :aOrderStatus", Order.class);
		q.setParameter("aOrderStatus", aOrderStatus);
		List<Order> resultList = q.getResultList();
		return resultList;
	}
	
	@Transactional
	public List<Order> getOrderFilteredByType(OrderType aType) {
		TypedQuery<Order> q = entityManager.createQuery("select o from Order where o.type = :aType", Order.class);
		q.setParameter("aType", aType);
		List<Order> resultList = q.getResultList();
		return resultList;
	}
	
	@Transactional
	public Cart createCart(String username, Date date) {
		Account a = getAccount(username);
		Cart c = new Cart();
		c.setAccount(a);
		c.setDate(date);
		return c;
	}

	@Transactional
	public Cart getCart(int id) {
		Cart c = entityManager.find(Cart.class, id);
		return c;
	}
	
	//!!! businesshour: pk is id
}