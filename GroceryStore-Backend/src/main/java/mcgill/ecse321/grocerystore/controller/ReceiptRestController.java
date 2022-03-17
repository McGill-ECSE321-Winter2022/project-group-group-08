package mcgill.ecse321.grocerystore.controller;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mcgill.ecse321.grocerystore.dto.CartDto;
import mcgill.ecse321.grocerystore.dto.ReceiptDto;
import mcgill.ecse321.grocerystore.model.Cart;
import mcgill.ecse321.grocerystore.model.Receipt;
import mcgill.ecse321.grocerystore.model.Receipt.ReceiptStatus;
import mcgill.ecse321.grocerystore.model.Receipt.ReceiptType;
import mcgill.ecse321.grocerystore.service.CartService;
import mcgill.ecse321.grocerystore.service.ReceiptService;


@CrossOrigin(origins = "*")
@RestController
public class ReceiptRestController {

	@Autowired
	private ReceiptService receiptService;
	@Autowired
	private CartService cartService;
	private static final String baseURL = "/receipt";
	
	//if link/receipt/all is visited, return all the receipt transfer objects
	@GetMapping(value = {baseURL+"/all", baseURL+"/all/"})
	public List<ReceiptDto> getAllReceipts() {
		return receiptService.getAllReceipts().stream().map(i -> convertToDto(i)).collect(Collectors.toList());
	}
	//specific url returns all receipt transfer objects with a specific status
	@GetMapping(value = {baseURL+"/getWithStatus", baseURL+"/getWithStatus/"})
	public List<ReceiptDto> getAllReceiptsWithStatus(
			@RequestParam(name = "status") ReceiptStatus receiptStatus
			) {
		return receiptService.getReceiptByReceiptStatus(receiptStatus).stream().map(i -> convertToDto(i)).collect(Collectors.toList());
	}
	//specific url returns all receipt transfer objects with a specific type
	@GetMapping(value = {baseURL+"/getWithType", baseURL+"/getWithType/"})
	public List<ReceiptDto> getAllReceiptsWithType(
			@RequestParam(name = "type") ReceiptType receiptType
			) {
		return receiptService.getReceiptByReceiptType(receiptType).stream().map(i -> convertToDto(i)).collect(Collectors.toList());
	}
	//specific url returns all receipt transfer objects with a specific type and status
	@GetMapping(value = {baseURL+"/getWithTypeAndStatus", baseURL+"/getWithTypeAndStatus/"})
	public List<ReceiptDto> getAllReceiptsWithTypeAndStatus(
			@RequestParam(name = "status") ReceiptStatus receiptStatus,
			@RequestParam(name = "type") ReceiptType receiptType
			) {
		return receiptService.getReceiptByReceiptStatusAndReceiptType(receiptStatus,receiptType).stream().map(i -> convertToDto(i)).collect(Collectors.toList());
	}
	//specific url returns a receipt with the given receipt number (id)
	@GetMapping(value = {baseURL+"/{receiptNum}", baseURL+"/{receiptNum}/"})
	public ReceiptDto getReceipt(@PathVariable("receiptNum") int receiptnum) {
		
		Receipt receipt = receiptService.getReceiptByReceiptNum(receiptnum);
		return convertToDto(receipt);
	}
	//specific url creates a new receipt transfer object given the specified parameters
	@PostMapping(value = {baseURL, baseURL+"/"})
	public ReceiptDto createReceipt(
		@RequestParam(name = "cartId") int cartid,
		@RequestParam(name = "receiptStatus") ReceiptStatus receiptStatus,
		@RequestParam(name = "receiptType") ReceiptType receiptType
		) {
		
		Cart attCart = cartService.getCart(cartid);
		Receipt receipt = receiptService.createReceipt(attCart, receiptStatus, receiptType);
		return convertToDto(receipt);
	}
	//specific url updates an existing receipt transfer object given the specified parameters
	@PatchMapping(value = {baseURL + "/update/{receiptNum}", baseURL+"/update/{receiptNum}/"})
	public ReceiptDto updateReceipt(
			@PathVariable("receiptNum") int receiptNum,
			@RequestParam(name = "cartId") int cartid,
			@RequestParam(name = "receiptStatus") ReceiptStatus receiptStatus,
			@RequestParam(name = "receiptType") ReceiptType receiptType
			) {
		Cart attCart = cartService.getCart(cartid);
		Receipt receipt = receiptService.updateReceipt(receiptNum, receiptStatus, receiptType, attCart);
		return convertToDto(receipt);
	}
	
	//specific url deletes an existing receipt transfer object given a receipt number
	@DeleteMapping(value = {baseURL + "/delete/{id}", baseURL+"/delete/{id}/"})
	public Boolean  deleteReceipt(@PathVariable("id") int id) { 
		 
		return receiptService.deleteReceipt(id);
	}
	
	//method recieves receipt object and returns a transfer object receipt of the inputted receipt
	private ReceiptDto convertToDto(Receipt receipt) {
		if(receipt == null) {
			throw new IllegalArgumentException("There is no such receipt!");
			
		}
		ReceiptDto receiptDto = new ReceiptDto(receipt.getReceiptNum(), receipt.getReceiptStatus(), receipt.getReceiptType(), CartDto.convertToDto(receipt.getCart()));
		return receiptDto;
	}

}