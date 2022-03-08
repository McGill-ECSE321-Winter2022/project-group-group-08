package mcgill.ecse321.grocerystore.service;

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

}
