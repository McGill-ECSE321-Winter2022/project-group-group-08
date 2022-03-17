package mcgill.ecse321.grocerystore.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;

import mcgill.ecse321.grocerystore.model.Cart;
import mcgill.ecse321.grocerystore.model.Quantity;
import mcgill.ecse321.grocerystore.model.Receipt;
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
	@Autowired
    QuantityService quantityService;
	@Autowired
    ReceiptService receiptService;
	
	/**
	 * creating cart
	 * @param date the date for the cart
	 * @param account the account associated by cart
	 * @return Cart
	 */
	@Transactional
	public Cart createCart(Date date, Account account) {
		if (date == null) {
			throw new IllegalArgumentException("Cart date cannot be empty!");
		}
		if (account==null) {
			throw new IllegalArgumentException("Account cannot be empty!");
		}
		Cart cart  = new Cart();
		cart.setDate(date);
		cart.setAccount(account);
		cartRepository.save(cart);
		return cart;
	}

	/**
	 * getting a specific cart with id
	 * @param id the id associated with the cart
	 * @return Cart
	 */
	@Transactional
	public Cart getCart(int id) {
		if(id<0) {
			throw new IllegalArgumentException("The id cannot be negative");
		}
		Cart cart  = cartRepository.findCartById(id);
		return cart;
	}

	/**
	 * getting all the carts
	 * @return List<Cart>
	 */
	@Transactional
	public List<Cart> getAllCarts() {
		return toList(cartRepository.findAll());
	}
	
	/**
	 * getting all the cart with the same date
	 * @param date the desired date
	 * @return List<Cart>
	 */
	@Transactional
	public List<Cart> getCartbyDate(Date date) {
		if(date==null) {
			throw new IllegalArgumentException("The date cannot be empty");
		}
		List<Cart> cart  = cartRepository.findCartByDate(date);
		return cart;
	}
	
	/**
	 * updating cart
	 * @param id the cart's id
	 * @param date the new date
	 * @param account the new account
	 * @return Cart
	 */
	@Transactional
	public Cart updateCart(int id, Date date, Account account) {
		if (id < 0) {
			throw new IllegalArgumentException("Cart id cannot be negative!");
		}
		if (date == null) {
			throw new IllegalArgumentException("Cart date cannot be empty!");
		}
		if (account==null) {
			throw new IllegalArgumentException("Account cannot be empty!");
		}
		Cart cart = cartRepository.findCartById(id);
		cart.setDate(date);
		cart.setAccount(account);
		return cart;
	}

	/**
	 * deleting cart with a given ID
	 * @param id the id associated by cart
	 * @return boolean - whether it is deleted successfully or not
	 */
	@Transactional
	public boolean deleteCartbyID(int id) {
		if(id<0){
			return false;
		}
		else{
			Cart cart=cartRepository.findCartById(id);
			List<Quantity> quantities = quantityRepository.findQuantityByCart(cart);
			for(int i=0; i<quantities.size(); i++) {
				quantityService.deleteQuantityById(quantities.get(i).getId());
			}
			List<Receipt> receipts = receiptRepository.findReceiptsByCart(cart);
			for(int i=0; i<receipts.size(); i++) {
				receiptService.deleteReceipt(receipts.get(i).getReceiptNum());
			}
			cartRepository.delete(cart);
			return true;
		}
	}

	/**
	 * deleting all the carts with the same date
	 * @param date the desired date
	 * @return boolean - whether it is deleted succesfully or not
	 */
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