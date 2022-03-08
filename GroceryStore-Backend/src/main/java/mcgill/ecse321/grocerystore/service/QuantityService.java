package mcgill.ecse321.grocerystore.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mcgill.ecse321.grocerystore.dao.QuantityRepository;
import mcgill.ecse321.grocerystore.model.Item;
import mcgill.ecse321.grocerystore.model.Quantity;

@Service
public class QuantityService {
	
	@Autowired
	private QuantityRepository quantityRepository;
	
	@Transactional
	public Quantity createQuantity(int count, Item item) {
		if(item == null || item.equals("undefined")) {
			throw new IllegalArgumentException("Item cannot be null or empty");
		}
		Quantity quantity = new Quantity();
		quantity.setCount(count);
		quantity.setItem(item);
		return quantity;
	}
	
	@Transactional
	public List<Quantity> getAllQuantities() {
		return toList(quantityRepository.findAll());
	}
	
	@Transactional
	public Quantity getQuantityById(int id) {
		Quantity quantity = quantityRepository.findQuantityById(id);
		if(quantity == null) {
			throw new IllegalArgumentException("No quantity with  id " + id + " exists");
		}
		return quantity;
	}
	
	@Transactional
	public List<Quantity> getQuantityByCount(int count){
		List<Quantity> quantityList = quantityRepository.findQuantityByCount(count);
		if(quantityList == null || quantityList.isEmpty()) {
			throw new IllegalArgumentException("No quantity with count " + count + " exists");
		}
		return quantityList;
		
	}
	
	@Transactional
	public Quantity updateQuantity(int id, int count, Item item) {
		if(count < 0) {
			throw new IllegalArgumentException("Count cannot be a negative number");
		}
		if(item == null) {
			throw new IllegalArgumentException("Item cannot be null");
		}
		Quantity quantity = quantityRepository.findQuantityById(id);
		if(quantity == null) {
			throw new IllegalArgumentException("Quantity with id " + id + " does not exists");
		}
		quantity.setCount(count);
		quantity.setItem(item);
		quantityRepository.save(quantity);
		return quantity;
	}
	
	@Transactional
	public boolean deleteQuantity(Quantity quantity) {
		if (quantity == null) {
			return false;
		}else {
			quantityRepository.delete(quantity);
			return true;
		}
	}
	
	@Transactional
	public boolean deleteQuantityById(int id) {
		if (id < 0) {
			return false;
		}else {
			Quantity quantity = quantityRepository.findQuantityById(id);
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