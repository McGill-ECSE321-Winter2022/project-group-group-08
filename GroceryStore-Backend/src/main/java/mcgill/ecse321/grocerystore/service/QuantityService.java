package mcgill.ecse321.grocerystore.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mcgill.ecse321.grocerystore.dao.CartRepository;
import mcgill.ecse321.grocerystore.dao.ItemRepository;
import mcgill.ecse321.grocerystore.dao.QuantityRepository;
import mcgill.ecse321.grocerystore.model.Cart;
import mcgill.ecse321.grocerystore.model.Item;
import mcgill.ecse321.grocerystore.model.Quantity;

@Service
public class QuantityService {
	
	@Autowired
	private QuantityRepository quantityRepository;
	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private CartRepository cartRepository;
	
	@Transactional
	public Quantity createQuantity(int count, Item item, Cart cart) {
		//checking for input validation
		if(item == null) {
			throw new IllegalArgumentException("Item cannot be null or empty");
		}
		if(cart == null) {
			throw new IllegalArgumentException("Cart cannot be null or empty");
		}
		Quantity quantity = new Quantity();
		quantity.setCount(count);
		quantity.setItem(item);
		quantity.setCart(cart);
		quantityRepository.save(quantity);
		return quantity;
	}
	
	@Transactional
	public List<Quantity> getAllQuantities() {
		//getting all items
		return toList(quantityRepository.findAll());
	}
	
	@Transactional
	public Quantity getQuantityById(int id) {
		//giving the user a specific error description when able to find one
		if (id < 0) {
			throw new IllegalArgumentException("The id cannot be a negative number");
		}
		Quantity quantity = quantityRepository.findQuantityById(id);
		if(quantity == null) {
			//telling the user that the item does not exist
			throw new IllegalArgumentException("No quantity with  id " + id + " exists");
		}
		return quantity;
	}
	
	@Transactional
	public List<Quantity> getQuantityByItemId(int itemId) {
		//id can never be negative
		if (itemId < 0) {
			throw new IllegalArgumentException("The itemId cannot be a negative number");
		}
		Item item = itemRepository.findItemById(itemId);
		if(item == null) {
			//item does not exist
			throw new IllegalArgumentException("No item found");
		}
		List<Quantity> quantities = quantityRepository.findQuantityByItem(item);
		if(quantities == null) {
			//quantity does not exist
			throw new IllegalArgumentException("No quantity found");
		}
		return quantities;
	}
	
	@Transactional
	public List<Quantity> getQuantityByCartId(int cartId) {
		//id can never be negative
		if (cartId < 0) {
			throw new IllegalArgumentException("The cartId cannot be a negative number");
		}
		Cart cart = cartRepository.findCartById(cartId);
		if(cart == null) {
			//cart does not exist
			throw new IllegalArgumentException("No cart found");
		}
		List<Quantity> quantities = quantityRepository.findQuantityByCart(cart);
		if(quantities == null) {
			//quantity does not exist
			throw new IllegalArgumentException("No quantity found");
		}
		return quantities;
	}
	
	@Transactional
	public Quantity updateQuantity(int id, int count, Item item, Cart cart) {
		//giving the user a specific error description when able to find one
		if (id < 0) {
			throw new IllegalArgumentException("The id cannot be a negative number");
		}
		if(count < 0) {
			throw new IllegalArgumentException("Count cannot be a negative number");
		}
		if(item == null) {
			throw new IllegalArgumentException("Item cannot be null");
		}
		if(cart == null) {
			throw new IllegalArgumentException("Cart cannot be null");
		}
		Quantity quantity = quantityRepository.findQuantityById(id);
		if(quantity == null) {
			//quantity does not exist
			throw new IllegalArgumentException("Quantity with id " + id + " does not exists");
		}
		quantity.setCount(count);
		quantity.setItem(item);
		quantity.setCart(cart);
		quantityRepository.save(quantity);
		return quantity;
	}
	
	@Transactional
	public Quantity deleteQuantityById(int id) {
		//id can never be negative
		if (id < 0) {
			return null;
		}else {
			Quantity quantity = quantityRepository.findQuantityById(id);
			if(quantity == null) {
				//quantity does not exist
				return null;
			}
			quantityRepository.delete(quantity);
			return quantity;
		}
	}
	
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
}