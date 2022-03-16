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

import mcgill.ecse321.grocerystore.dao.AccountRepository;
import mcgill.ecse321.grocerystore.dao.CartRepository;
import mcgill.ecse321.grocerystore.dao.QuantityRepository;
import mcgill.ecse321.grocerystore.dao.ReceiptRepository;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;
	@Autowired
    AccountRepository accountRepository;
	@Autowired
    QuantityRepository quantityRepository;
	@Autowired
    ReceiptRepository receiptRepository;
	@Autowired
	QuantityService quantityService;
	@Autowired
	ReceiptService receiptService;

	//Cart
	/**
	 * Creates cart with a specific date and account
	 * @param date specify the date for the account
	 * @param account a specific account linked to the cart
	 * @return unique cart linked to account
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
	 * Finds cart by id
	 * @param id cart id that corresponds to the specific cart 
	 * @return unique cart linked to the id
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
	 * @return a list of existing carts
	 */
	@Transactional
	public List<Cart> getAllCarts() {
		return toList(cartRepository.findAll());
	}

	/**
	 * Finds cart by account
	 * @param account account object
	 * @return unique cart linked to the account
	 */
	 @Transactional
	 public Cart getCartbyAccount(Account account) {
	 	return cartRepository.findCartByAccount(account);
	 }
	
	/**
	 * Finds cart by date
	 * @param date date that corresponds to a specific cart 
	 * @return unique cart linked to the date
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
	 * Updates cart with a specific id, date and account
	 * @param id specify the id for the account
	 * @param date specify the date for the account
	 * @param account a specific account linked to the cart
	 * @return unique cart linked to account, id and date
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
   	 * Deletes the specific
   	 * @param cart a specific cart object 
   	 * @return a boolean indicating whether the cart is deleted
   	 */
	@Transactional
	public Cart deleteCart(Cart cart) {
		if(cart==null){
			throw new InvalidInputException("Invalid cart");
		}
		if(cart==null) {
			return null;
		}
		List<Quantity> quantity=quantityRepository.findQuantityByCart(cart);
		for(int i=0; i<quantity.size(); i++) {
			quantityService.deleteQuantityById(quantity.get(i).getId());
		}

		List<Receipt> receipt=receiptRepository.findReceiptsByCart(cart);
		for(int i=0; i<receipt.size(); i++) {
			receiptService.deleteReceipt(receipt.get(i).getReceiptNum());
		}	
		cartRepository.delete(cart);
		return cart;
	}

	/**
   	 * Deletes a cart by specific id
   	 * @param id a specific cart's id 
   	 * @return a boolean indicating whether the cart is deleted
   	 */
	@Transactional
	public Cart deleteCartbyID(int id) {
		if(id<0){
			throw new InvalidInputException("Invalid id");
		}
		Cart cart=cartRepository.findCartById(id);
		List<Quantity> quantity=quantityRepository.findQuantityByCart(cart);
		for(int i=0; i<quantity.size(); i++) {
			quantityService.deleteQuantityById(quantity.get(i).getId());
		}

		List<Receipt> receipt=receiptRepository.findReceiptsByCart(cart);
		for(int i=0; i<receipt.size(); i++) {
			receiptService.deleteReceipt(receipt.get(i).getReceiptNum());
		}	
		cartRepository.delete(cart);
		return cart;
	}

	/**
   	 * Deletes a cart by specific id
   	 * @param id a specific cart's id 
   	 * @return a boolean indicating whether the cart is deleted
   	 */
	@Transactional
	public boolean deleteCartbyAccount(Account account) {
		if(account==null){
			return false;
		}
		else{
			Cart cart=cartRepository.findCartByAccount(account);
			List<Quantity> quantity=quantityRepository.findQuantityByCart(cart);
			for(int i=0; i<quantity.size(); i++) {
				quantityService.deleteQuantityById(quantity.get(i).getId());
			}

			List<Receipt> receipt=receiptRepository.findReceiptsByCart(cart);
			for(int i=0; i<receipt.size(); i++) {
				receiptService.deleteReceipt(receipt.get(i).getReceiptNum());
			}	
			cartRepository.delete(cart);
			return true;
		}
	}
	
	/**
   	 * Deletes a cart by specific date
   	 * @param id a specific date linked to a cart
   	 * @return a boolean indicating whether the cart is deleted
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