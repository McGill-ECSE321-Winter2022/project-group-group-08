package mcgill.ecse321.grocerystore.dao;

import org.springframework.data.repository.CrudRepository;

import mcgill.ecse321.grocerystore.model.Receipt;

public interface ReceiptRepository extends CrudRepository<Receipt, Integer>{
	
	Receipt findReceiptByReceiptNum(int orderNum);
}
