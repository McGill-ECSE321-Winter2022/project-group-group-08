package mcgill.ecse321.grocerystore.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import mcgill.ecse321.grocerystore.model.Cart;
import mcgill.ecse321.grocerystore.model.Receipt;
import mcgill.ecse321.grocerystore.model.Receipt.ReceiptStatus;
import mcgill.ecse321.grocerystore.model.Receipt.ReceiptType;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestReceiptPersistence {

	@Autowired
	private ReceiptRepository receiptRepository;
	@Autowired
	private CartRepository cartRepository;

	@AfterEach
	public void clearDatabase() {
		// First, we clear registrations to avoid exceptions due to inconsistencies
		receiptRepository.deleteAll();
		cartRepository.deleteAll();
	}
	
	public Receipt createReceipt(ReceiptStatus status, ReceiptType type) {
		Receipt receipt = new Receipt();
		receipt.setReceiptStatus(status);
		receipt.setReceiptType(type);
		receiptRepository.save(receipt);
		return receipt;
	}	
	
	public Cart createCart(Date date) {
		Cart cart = new Cart();
		cart.setDate(date);
		cartRepository.save(cart);
		return cart;
	}
	
	@Test
	public void testPersistAndLoadReceipt() {
		ReceiptStatus status = ReceiptStatus.Processed;
		ReceiptType type = ReceiptType.Pickup;
		
		Receipt receipt = createReceipt(status, type);
		int receiptNum = receipt.getReceiptNum();	
		
		Date date = java.sql.Date.valueOf(LocalDate.of(2021, Month.DECEMBER, 2));
		
		Cart cart = createCart(date);
		
		receipt.setCart(cart);	
		receiptRepository.save(receipt);
		
		cart = null;
		receipt = null;
		receipt = receiptRepository.findReceiptByReceiptNum(receiptNum);
		
		assertNotNull(receipt);
		
		assertEquals(receiptNum,receipt.getReceiptNum());
		assertEquals(status,receipt.getReceiptStatus());
		assertEquals(type,receipt.getReceiptType());
	}	
}