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
import mcgill.ecse321.grocerystore.dto.ItemDto;
import mcgill.ecse321.grocerystore.dto.QuantityDto;
import mcgill.ecse321.grocerystore.model.Account;
import mcgill.ecse321.grocerystore.model.Cart;
import mcgill.ecse321.grocerystore.model.Item;
import mcgill.ecse321.grocerystore.model.Quantity;
import mcgill.ecse321.grocerystore.model.Receipt.ReceiptStatus;
import mcgill.ecse321.grocerystore.model.Receipt.ReceiptType;
import mcgill.ecse321.grocerystore.service.AccountService;
import mcgill.ecse321.grocerystore.service.CartService;
import mcgill.ecse321.grocerystore.service.ItemService;
import mcgill.ecse321.grocerystore.service.QuantityService;
import mcgill.ecse321.grocerystore.service.ReceiptService;

@CrossOrigin(origins = "*")
@RestController
public class QuantityRestController {

	@Autowired
	private QuantityService quantityService;
	@Autowired
	private ItemService itemService;
	@Autowired
	private CartService cartService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private ReceiptService receiptService;

	private static final String baseURL = "/quantity";

	/**
	 * @return list of all quantities
	 */
	@GetMapping(value = { baseURL + "/all", baseURL + "/all/" })
	public List<QuantityDto> getAllQuantities() {
		return quantityService.getAllQuantities().stream().map(q -> convertToDto(q)).collect(Collectors.toList());
	}

	/**
	 * Gets quantity
	 * 
	 * @param id primary key
	 * @return quantity
	 */
	@GetMapping(value = { baseURL + "/{id}", baseURL + "/{id}/" })
	public QuantityDto getQuantity(@PathVariable("id") int id) {
		Quantity quantity = quantityService.getQuantityById(id);
		return convertToDto(quantity);
	}

	/**
	 * Return list of quantities associated with that item
	 * 
	 * @param itemId primary key
	 * @return List<Quantity>
	 */
	@GetMapping(value = { baseURL + "/itemId/{itemId}", baseURL + "/itemId/{itemId}/" })
	public List<QuantityDto> getQuantityByItem(@PathVariable("itemId") int itemId) {
		return quantityService.getQuantityByItemId(itemId).stream().map(q -> convertToDto(q))
				.collect(Collectors.toList());
	}

	/**
	 * Return list of quantities associated with cart
	 * 
	 * @param cartId cart associated with quantities
	 * @return List<Quantity>
	 */
	@GetMapping(value = { baseURL + "/cartId/{cartId}", baseURL + "/cartId/{cartId}/" })
	public List<QuantityDto> getQuantityByCart(@PathVariable("cartId") int cartId) {
		return quantityService.getQuantityByCartId(cartId).stream().map(q -> convertToDto(q))
				.collect(Collectors.toList());
	}

	/**
	 * Creates quantity
	 * 
	 * @param count  count
	 * @param itemId item associated with quantity
	 * @param cartId cart associated with quatity
	 * @return quantity
	 */
	@PostMapping(value = { baseURL, baseURL + "/" })
	public QuantityDto createQuantity(
			@RequestParam(name = "count") int count,
			@RequestParam(name = "itemId") int itemId,
			@RequestParam(name = "cartId") int cartId
			) {
		Quantity quantity = quantityService.createQuantity(count, itemId, cartId);
		return convertToDto(quantity);
	}

	/**
	 * Updates quantity
	 * 
	 * @param id     primary key of quantity
	 * @param count  new count
	 * @param itemId item associated with quantity
	 * @param cartId cart associated with quantity
	 * @return quantity
	 */
	@PatchMapping(value = { baseURL + "/update/{id}", baseURL + "/update/{id}/" })
	public QuantityDto updateQuantity(
			@PathVariable("id") int id,
			@RequestParam(name = "count") int count,
			@RequestParam(name = "itemId") int itemId,
			@RequestParam(name = "cartId") int cartId) {
		Item item = itemService.getItemById(itemId);
		if (item == null) {
			throw new IllegalArgumentException("This item does not exists");
		}
		Cart cart = cartService.getCart(cartId);
		if (cart == null) {
			throw new IllegalArgumentException("This cart does not exists");
		}
		Quantity quantity = quantityService.updateQuantity(id, count, item, cart);
		return convertToDto(quantity);
	}

	/**
	 * Deletes quantity
	 * 
	 * @param id primary key of quantity
	 * @return quantity
	 */
	@DeleteMapping(value = { baseURL + "/delete/{id}", baseURL + "/delete/{id}/" })
	public QuantityDto deleteQuantity(@PathVariable("id") int id) {
		Quantity quantity = quantityService.getQuantityById(id);
		quantityService.deleteQuantityById(id);
		return convertToDto(quantity);
	}

	/**
	 * Deletes all quantity based on cartId
	 * 
	 * @param id primary key of quantity
	 * @return quantity
	 */
	@DeleteMapping(value = { baseURL + "/delete/cartId/{id}", baseURL + "/delete/cartId/{id}/" })
	public List<QuantityDto> deleteQuantityByCartId(@PathVariable("id") int id,
			@RequestParam(name = "buyItems") boolean buyItems) {
		List<Quantity> quantities = quantityService.getQuantityByCartId(id);

		if (buyItems) {
			for (Quantity quantity : quantities) {
				Account acc = quantity.getCart().getAccount();
				Item i = quantity.getItem();

				try {
					// update item in store quantity
					itemService.updateItem(
							i.getImage(),
							i.getId(),
							i.getName(),
							i.getPrice(),
							i.getPoint(),
							i.getReturnPolicy(),
							i.getPickup(),
							i.getInStoreQuantity() - quantity.getCount());
					// if quantity enough, update account points
					accountService.updateAccount(
							acc.getUsername(),
							acc.getPassword(),
							acc.getInTown(),
							acc.getTotalPoints() + quantity.getCount() * i.getPoint(),
							acc.getPerson());
					// finally delete (buy) the item
					quantityService.deleteQuantityById(quantity.getId());
				} catch (Exception e) {
					System.out.println(e.getStackTrace());
				}
			}
		// 	// create receipt
		// 	receiptService.createReceipt(cartService.getCart(id), ReceiptStatus.Fullfilled, ReceiptType.Pickup);
		} else {
			List<Integer> quantityIds = quantities.stream()
					.map(Quantity::getId)
					.collect(Collectors.toList());
			for (int qId : quantityIds) {
				quantityService.deleteQuantityById(qId);
			}
		}
		return quantities.stream().map(q -> convertToDto(q)).collect(Collectors.toList());
	}

	private QuantityDto convertToDto(Quantity quantity) {
		if (quantity == null) {
			throw new IllegalArgumentException("There is no such quantity!");
		}
		QuantityDto quantityDto = new QuantityDto(quantity.getId(), quantity.getCount(),
				ItemDto.convertToDto(quantity.getItem()), CartDto.convertToDto(quantity.getCart()));
		return quantityDto;
	}

}