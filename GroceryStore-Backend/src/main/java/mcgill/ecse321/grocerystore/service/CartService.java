package mcgill.ecse321.grocerystore.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import mcgill.ecse321.grocerystore.model.Cart;
import mcgill.ecse321.grocerystore.model.Account;
import mcgill.ecse321.grocerystore.model.Receipt;
import mcgill.ecse321.grocerystore.model.Receipt.ReceiptStatus;
import mcgill.ecse321.grocerystore.model.Receipt.ReceiptType;
import mcgill.ecse321.grocerystore.model.Quantity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mcgill.ecse321.grocerystore.dao.CartRepository;
import mcgill.ecse321.grocerystore.dao.AccountRepository;
import mcgill.ecse321.grocerystore.dao.ReceiptRepository;
import mcgill.ecse321.grocerystore.dao.QuantityRepository;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    ReceiptRepository receiptRepository;
    @Autowired
    QuantityRepository quantityRepository;

    //ACCOUNT
	@Transactional
	public Account createAccount(String username, String password, boolean inTown, int totalPoints) {
		String error = "";
		if (username == null || username.trim().length() == 0) {
		    error = error + "Account username cannot be empty! ";
		}
		if (password == null || password.trim().length() == 0) {
		    error = error + "Account password cannot be empty! ";
		}
		if (totalPoints < 0) {
		    error = error + "Account Total points cannot be negative ";
		}
	    error = error.trim();
	    if (error.length() > 0) {
	        throw new IllegalArgumentException(error);
	    }
		Account account = new Account();
		account.setUsername(username);
		account.setPassword(password);
		account.setInTown(inTown);
		account.setTotalPoints(totalPoints);
		accountRepository.save(account);
		return account;
	}

    @Transactional
	public Account gerAccount(String username) {
		Account account = accountRepository.findAccountByUsername(username);
		return account;
	}
	
	//Quantity
	@Transactional
	public Quantity createQuantity(int id, int count) {
		if (id == 0) {
			throw new IllegalArgumentException("Quantity id cannot be empty!");
		}
        if (count == 0) {
			throw new IllegalArgumentException("Quantity count cannot be empty!");
		}
		Quantity quantity = new Quantity();
		quantity.setCount(count);
		quantityRepository.save(quantity);
		return quantity;
	}
	
	@Transactional
	public Quantity getQuantity(int id) {
		Quantity quantity  = quantityRepository.findQuantityById(id);
		return quantity;
	}
	
	//Receipt
    @Transactional
    public Receipt creatReceipt(int receiptNum, ReceiptStatus status, ReceiptType type){
        if (receiptNum == 0){
            throw new IllegalArgumentException("Receipt number cannot be empty");
        }
        if (status == null){
            throw new IllegalArgumentException("Receipt status cannot be empty");
        }
        if (type == null){
            throw new IllegalArgumentException("Receipt status cannot be empty");
        }
        Receipt receipt = new Receipt();
        receipt.setReceiptNum(receiptNum);
        receipt.setReceiptStatus(status);
        receipt.setReceiptType(type);
        receiptRepository.save(receipt);
        return receipt;
    }

    @Transactional
	public Receipt getReceipt(int receiptNum) {
		Receipt receipt  = receiptRepository.findReceiptByReceiptNum(receiptNum);
		return receipt;
	}
	
	//Cart
	@Transactional
	public Cart createCart(int id, Date date, Account account) {
        String error="";
        if (id == 0) {
			throw new IllegalArgumentException("Cart id cannot be empty!");
		}
		if (date == null) {
			throw new IllegalArgumentException("Cart date cannot be empty!");
		}
        if (account == null) {
	        error = error + "Account needs to be selected for Cart! ";
	    } else if (!accountRepository.existsById(account.getUsername())) {
	        error = error + "Account does not exist! ";
	    }
	   

        error = error.trim();
	    if (error.length() > 0) {
	        throw new IllegalArgumentException(error);
	    }
		Cart cart  = new Cart();
		cart.setDate(date);
		cartRepository.save(cart);
		return cart;
	}
	
	@Transactional
	public Cart getCart(int id) {
		Cart cart  = cartRepository.findCartById(id);
		return cart;
	}
}
