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
import mcgill.ecse321.grocerystore.service.ReceiptService;


@CrossOrigin(origins = "*")
@RestController
public class ReceiptRestController {

	@Autowired
	private ReceiptService receiptService;
	@Autowired
	private CartService cartService;
	private static final String baseURL = "/receipt";
	
	@GetMapping(value = {baseURL+"/all", baseURL+"/all/"})
	public List<ReceiptDto> getAllReceipts() {
		return receiptService.getAllReceipts().stream().map(i -> convertToDto(i)).collect(Collectors.toList());
	}
	
	@GetMapping(value = {baseURL+"/{id}", baseURL+"/{id}/"})
	public ReceiptDto getReceipt(@PathVariable("receiptNum") int receiptnum) {
		
		Receipt receipt = receiptService.getReceiptByReceiptNum(receiptnum);
		return convertToDto(receipt);
	}
	
	@PostMapping(value = {baseURL, baseURL+"/"})
	public ReceiptDto createReceipt(
		@RequestParam(name = "cartId") int cartid,
		@RequestParam(name = "receiptNum") int receiptNum,
		@RequestParam(name = "receiptStatus") ReceiptStatus receiptStatus,
		@RequestParam(name = "receiptType") ReceiptType receiptType
		
		) {
		
		Cart attCart = CartService.getCartById(cartid);
		Receipt receipt = receiptService.createReceipt(receiptNum, receiptStatus, receiptType, attCart);
		return convertToDto(receipt);
	}
	
	@PatchMapping(value = {baseURL + "/update/{id}", baseURL+"/update/{id}/"})
	public ReceiptDto updateReceipt(
			@PathVariable("id") int id,
			@RequestParam(name = "cartId") int cartid,
			@RequestParam(name = "receiptNum") int receiptNum,
			@RequestParam(name = "receiptStatus") ReceiptStatus receiptStatus,
			@RequestParam(name = "receiptType") ReceiptType receiptType
			) {
		Cart attCart = CartService.getCartById(cartid);
		Receipt receipt = receiptService.createReceipt(receiptNum, receiptStatus, receiptType, attCart);
		return convertToDto(receipt);
	}
	
	@DeleteMapping(value = {baseURL + "/delete/{id}", baseURL+"/delete/{id}/"})
	public ReceiptDto deleteReceipt(@PathVariable("id") int id) {
		Receipt receipt = receiptService.getReceiptById(id);
		receiptService.deleteReceipt(receipt);
		return convertToDto(receipt);
	}
	
	private ReceiptDto convertToDto(Receipt receipt) {
		if(receipt == null) {
			throw new IllegalArgumentException("There is no such receipt!");
			//int receiptNum, ReceiptStatus receiptStatus, ReceiptType receiptType, CartDto cart
		}
		CartDto cartDto = CartDto.convertToDto(receipt.getCart());
		ReceiptDto receiptDto = new ReceiptDto(receipt.getReceiptNum(), receipt.getReceiptStatus(), receipt.getReceiptType(), cartDto);
		return receiptDto;
	}

}