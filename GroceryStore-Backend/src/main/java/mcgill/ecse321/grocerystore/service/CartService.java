package mcgill.ecse321.grocerystore.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;

import mcgill.ecse321.grocerystore.model.Cart;
import mcgill.ecse321.grocerystore.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mcgill.ecse321.grocerystore.dao.CartRepository;
import mcgill.ecse321.grocerystore.dao.QuantityRepository;
import mcgill.ecse321.grocerystore.dao.ReceiptRepository;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;
	@Autowired
    QuantityRepository quantityRepository;
	@Autowired
    ReceiptRepository receiptRepository;

	//Cart
	@Transactional
	public Cart createCart(int id, Date date) {
        if (id == 0) {
			throw new IllegalArgumentException("Cart id cannot be empty!");
		}
		if (date == null) {
			throw new IllegalArgumentException("Cart date cannot be empty!");
		}
		Cart cart  = new Cart();
		cart.setId(id);
		cart.setDate(date);
		cartRepository.save(cart);
		return cart;
	}
	
	@Transactional
	public Cart getCart(int id) {
		Cart cart  = cartRepository.findCartById(id);
		return cart;
	}

	@Transactional
	public Cart getCartbyAccount(Account account) {
		return account.getCart();
	}

	@Transactional
	public List<Cart> getCartbyDate(Date date) {
		List<Cart> cart  = cartRepository.findCartByDate(date);
		return cart;
	}

	@Transactional
	public Cart updateCart(int id, Date date) {
		if (id == 0) {
			throw new IllegalArgumentException("Cart id cannot be empty!");
		}
		if (date == null) {
			throw new IllegalArgumentException("Cart date cannot be empty!");
		}
		Cart cart = cartRepository.findCartById(id);
		cart.setId(id);
		cart.setDate(date);
		return cart;
	}

	@Transactional
	public boolean deleteCart(Cart cart) {
		if(cart==null){
			return false;
		}
		else{
			//Quantity quantity=quantityRepository.findQuantityByCart(cart);
			//quantityService.delete(quantity);

			//Receipt receipt=receiptRepository.findReceiptByCart(cart);
			//receiptService.delete(receipt);

			cartRepository.delete(cart);
			return true;
		}
	}

	@Transactional
	public boolean deleteCartbyID(int id) {
		if(id<0){
			return false;
		}
		else{
			Cart cart=cartRepository.findCartById(id);
			cartRepository.delete(cart);
			return true;
		}
	}

	@Transactional
	public boolean deleteCartbyDate(Date date) {
		if(date==null){
			return false;
		}
		else{
			List<Cart> allCarts=toList(cartRepository.findAll());
			List<Cart> cart=cartRepository.findCartByDate(date);
			allCarts.removeAll(cart);
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
