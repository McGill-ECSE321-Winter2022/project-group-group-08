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
		return toList(quantityRepository.findAll());
	}
	
	@Transactional
	public Quantity getQuantityById(int id) {
		if (id < 0) {
			throw new IllegalArgumentException("The id cannot be a negative number");
		}
		Quantity quantity = quantityRepository.findQuantityById(id);
		if(quantity == null) {
			throw new IllegalArgumentException("No quantity with  id " + id + " exists");
		}
		return quantity;
	}
	
	@Transactional
	public List<Quantity> getQuantityByItemId(int itemId) {
		if (itemId < 0) {
			throw new IllegalArgumentException("The itemId cannot be a negative number");
		}
		Item item = itemRepository.findItemById(itemId);
		if(item == null) {
			throw new IllegalArgumentException("No item found");
		}
		List<Quantity> quantities = quantityRepository.findQuantityByItem(item);
		if(quantities == null) {
			throw new IllegalArgumentException("No quantity found");
		}
		return quantities;
	}
	
	@Transactional
	public List<Quantity> getQuantityByCartId(int cartId) {
		if (cartId < 0) {
			throw new IllegalArgumentException("The cartId cannot be a negative number");
		}
		Cart cart = cartRepository.findCartById(cartId);
		if(cart == null) {
			throw new IllegalArgumentException("No cart found");
		}
		List<Quantity> quantities = quantityRepository.findQuantityByCart(cart);
		if(quantities == null) {
			throw new IllegalArgumentException("No quantity found");
		}
		return quantities;
	}
	
	@Transactional
	public Quantity updateQuantity(int id, int count, Item item, Cart cart) {
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
			throw new IllegalArgumentException("Quantity with id " + id + " does not exists");
		}
		quantity.setCount(count);
		quantity.setItem(item);
		quantity.setCart(cart);
		quantityRepository.save(quantity);
		return quantity;
	}
	
	@Transactional
	public boolean deleteQuantityById(int id) {
		if (id < 0) {
			return false;
		}else {
			Quantity quantity = quantityRepository.findQuantityById(id);
			if(quantity == null) {
				return false;
			}
			quantityRepository.delete(quantity);
			return true;
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