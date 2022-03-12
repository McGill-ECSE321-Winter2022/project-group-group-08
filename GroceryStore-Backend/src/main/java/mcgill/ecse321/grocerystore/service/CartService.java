package mcgill.ecse321.grocerystore.service;

import java.sql.Date;
import java.util.List;
import javax.transaction.Transactional;

import mcgill.ecse321.grocerystore.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mcgill.ecse321.grocerystore.dao.CartRepository;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;

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
	public List<Cart> getCartbyDate(Date minDate, Date maxDate) {
		List<Cart> cart  = cartRepository.findCartByDateAfterAndDateBefore(minDate,maxDate);
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

	public Cart getCartById(int cartid) {
		Cart cart  = cartRepository.findCartById(cartid);
		return cart;
	}
}
