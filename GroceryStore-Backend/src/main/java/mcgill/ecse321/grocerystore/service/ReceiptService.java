package mcgill.ecse321.grocerystore.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mcgill.ecse321.grocerystore.dao.ReceiptRepository;
import mcgill.ecse321.grocerystore.model.Cart;
import mcgill.ecse321.grocerystore.model.Receipt;
import mcgill.ecse321.grocerystore.model.Receipt.ReceiptStatus;
import mcgill.ecse321.grocerystore.model.Receipt.ReceiptType;

@Service
public class ReceiptService {
	@Autowired
	private ReceiptRepository receiptRepository;
	
	@Transactional
	public Receipt createReceipt(Cart curr, ReceiptStatus status, ReceiptType type) {
		
		Receipt currReceipt = new Receipt();

		currReceipt.setCart(curr);
		currReceipt.setReceiptStatus(status);
		currReceipt.setReceiptType(type);
		receiptRepository.save(currReceipt);
		
		return currReceipt;
	}
	
	@Transactional
	public Receipt updateReceipt(int id, ReceiptStatus status, ReceiptType type, Cart cart) {
		
		Receipt currReceipt = receiptRepository.findReceiptByReceiptNum(id);
		
		currReceipt.setReceiptStatus(status);
		currReceipt.setReceiptType(type);
		currReceipt.setCart(cart);
		receiptRepository.save(currReceipt);
		
		return currReceipt;
	}
	
	
	@Transactional
	public boolean deleteReceipt(int id) {
		if (id < 0) {
			return false;
		}else {
			Receipt curr = receiptRepository.findReceiptByReceiptNum(id);
			receiptRepository.delete(curr);
			return true;
		}
	}
	
	
	@Transactional
	public Receipt getReceiptByReceiptNum(int receiptNum) {
		Receipt curr = receiptRepository.findReceiptByReceiptNum(receiptNum);
		if (curr == null) {
			throw new IllegalArgumentException("No receipt with that id");
		}
		return curr;
	};
	@Transactional
	public List<Receipt> getReceiptByReceiptStatus(ReceiptStatus receiptStatus) {
		if (receiptStatus == null) {
			throw new IllegalArgumentException("The receipt status is null");
		}
		List<Receipt> curr = receiptRepository.findReceiptByReceiptStatus(receiptStatus);
		if (curr == null || curr.isEmpty()) {
			throw new IllegalArgumentException("There are no receipts with that status");
		}
		return curr;
	};
	@Transactional
	public List<Receipt> getReceiptByReceiptType(ReceiptType receiptType) {
		if (receiptType == null) {
			throw new IllegalArgumentException("The receipt type is null");
		}
		List<Receipt> curr = receiptRepository.findReceiptByReceiptType(receiptType);
		if (curr == null || curr.isEmpty()) {
			throw new IllegalArgumentException("There are no receipts with that type");
		}
		return curr;
	};
	@Transactional
	public List<Receipt>  getReceiptByReceiptStatusAndReceiptType(ReceiptStatus receiptStatus, ReceiptType receiptType) {
		if (receiptStatus == null) {
			throw new IllegalArgumentException("The receipt is null");
		}
		List<Receipt> curr = receiptRepository.findReceiptByReceiptStatusAndReceiptType(receiptStatus, receiptType);
		if (curr == null || curr.isEmpty()) {
			throw new IllegalArgumentException("There are no receipts with that status and type");
		}
		return curr;
	};
	@Transactional
	public List<Receipt>  getAllReceipts() {
		return toList(receiptRepository.findAll());
	};
	
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
	
}