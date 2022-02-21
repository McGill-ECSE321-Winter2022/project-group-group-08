package mcgill.ecse321.grocerystore.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import mcgill.ecse321.grocerystore.model.Receipt;
import mcgill.ecse321.grocerystore.model.Receipt.ReceiptStatus;
import mcgill.ecse321.grocerystore.model.Receipt.ReceiptType;

public interface ReceiptRepository extends CrudRepository<Receipt, Integer>{
	
	Receipt findReceiptByReceiptNum(int receiptNum);
	
	List<Receipt> findReceiptByReceiptStatus(ReceiptStatus receiptStatus);
	
	List<Receipt> findReceiptByReceiptType(ReceiptType receiptType);
	
	List<Receipt> findReceiptByReceiptStatusAndReceiptType(ReceiptStatus receiptStatus, ReceiptType receiptType);
}
