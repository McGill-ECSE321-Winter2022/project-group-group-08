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
	
	//create a receipt with an attached cart
	@Transactional
	public Receipt createReceipt(Cart curr, ReceiptStatus status, ReceiptType type) {
		//new receipt
		Receipt currReceipt = new Receipt();
		//set the attributes
		currReceipt.setCart(curr);
		currReceipt.setReceiptStatus(status);
		currReceipt.setReceiptType(type);
		//we save the receipt to the repository
		receiptRepository.save(currReceipt);
		//return the newly created receipt
		return currReceipt;
	}
	//update an already existing receipt
	@Transactional
	public Receipt updateReceipt(int id, ReceiptStatus status, ReceiptType type, Cart cart) {
		//find the wanted receipt via id
		Receipt currReceipt = receiptRepository.findReceiptByReceiptNum(id);
		//update it
		currReceipt.setReceiptStatus(status);
		currReceipt.setReceiptType(type);
		currReceipt.setCart(cart);
		//save the receipt to the repository
		receiptRepository.save(currReceipt);
		//return the updated receipt
		return currReceipt;
	}
	
	//delete an already existing receipt via id
	@Transactional
	public boolean deleteReceipt(int id) {
		if (id < 0) {
			return false;
		}else {
			Receipt curr = receiptRepository.findReceiptByReceiptNum(id);
			//delete the receipt from the repository
			receiptRepository.delete(curr);
			return true;
		}
	}
	
	//find a receipt using the id
	@Transactional
	public Receipt getReceiptByReceiptNum(int receiptNum) {
		Receipt curr = receiptRepository.findReceiptByReceiptNum(receiptNum);
		if (curr == null) {
			throw new IllegalArgumentException("No receipt with that id");
		}
		return curr;
	};
	//find receipts with a certain status
	@Transactional
	public List<Receipt> getReceiptByReceiptStatus(ReceiptStatus receiptStatus) {
		if (receiptStatus == null) {
			throw new IllegalArgumentException("The receipt status is null");
		}
		//List contains all the receipts with the certain status
		List<Receipt> curr = receiptRepository.findReceiptByReceiptStatus(receiptStatus);
		if (curr == null || curr.isEmpty()) {
			throw new IllegalArgumentException("There are no receipts with that status");
		}
		return curr;
	};
	//find receipts with a certain type
	@Transactional
	public List<Receipt> getReceiptByReceiptType(ReceiptType receiptType) {
		if (receiptType == null) {
			throw new IllegalArgumentException("The receipt type is null");
		}
		//List contains all the receipt with the certain type
		List<Receipt> curr = receiptRepository.findReceiptByReceiptType(receiptType);
		if (curr == null || curr.isEmpty()) {
			throw new IllegalArgumentException("There are no receipts with that type");
		}
		return curr;
	};
	//find receipts with a certain status AND type
	@Transactional
	public List<Receipt>  getReceiptByReceiptStatusAndReceiptType(ReceiptStatus receiptStatus, ReceiptType receiptType) {
		if (receiptStatus == null) {
			throw new IllegalArgumentException("The receipt is null");
		}
		//List contains all the receipt with the certain status and type
		List<Receipt> curr = receiptRepository.findReceiptByReceiptStatusAndReceiptType(receiptStatus, receiptType);
		if (curr == null || curr.isEmpty()) {
			throw new IllegalArgumentException("There are no receipts with that status and type");
		}
		return curr;
	};
	//returns all the existing receipts
	@Transactional
	public List<Receipt>  getAllReceipts() {
		return toList(receiptRepository.findAll());
	};
	//iterates over iterable data and returns a list
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
	
}