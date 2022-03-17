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

import mcgill.ecse321.grocerystore.dto.CustomerDto;
import mcgill.ecse321.grocerystore.dto.PersonDto;
import mcgill.ecse321.grocerystore.model.Customer;
import mcgill.ecse321.grocerystore.model.Customer.TierClass;
import mcgill.ecse321.grocerystore.model.Person;
import mcgill.ecse321.grocerystore.service.CustomerService;
import mcgill.ecse321.grocerystore.service.PersonService;

@CrossOrigin(origins = "*")
@RestController
public class CustomerRestController {

	@Autowired
	private CustomerService service;
	@Autowired
	private PersonService personService;

	/**
	 * Creates customer
	 * 
	 * @param TierClass tierClass
	 * @param boolean   ban
	 * @param String    personEmail
	 * @return CustomerDto
	 */
	@PostMapping(value = { "/customer", "/customer/" })
	public CustomerDto createCustomer(@RequestParam(name = "tierClass") TierClass tierClass,
			@RequestParam(name = "ban") boolean ban, @RequestParam(name = "personEmail") String personEmail) {
		Person person = personService.findPersonByEmail(personEmail);
		Customer customer = service.createCustomer(person, tierClass, ban);
		return convertToDto(customer);
	}

	/**
	 * Updates customer info
	 * 
	 * @param int       id
	 * @param TierClass tierClass
	 * @param boolean   ban
	 * @param String    personEmail
	 * @return CustomerDto
	 */
	@PatchMapping(value = { "/customer/update/{id}", "/customer/update/{id}/" })
	public CustomerDto updateCustomer(@PathVariable(name = "id") int id,
			@RequestParam(name = "tierClass") TierClass tierClass, @RequestParam(name = "ban") boolean ban,
			@RequestParam(name = "personEmail") String personEmail) {
		Customer customer = service.updateCustomer(id, personService.findPersonByEmail(personEmail), tierClass, ban);
		return convertToDto(customer);
	}

	/**
	 * Gets customer from given id
	 * 
	 * @param id
	 * @return List<CustomerDto>
	 */
	@GetMapping(value = { "/customer/{id}", "/customer/{id}/" })
	public CustomerDto getCustomer(@PathVariable("id") int id) {
		return convertToDto(service.getCustomer(id));
	}

	/**
	 * Gets the list of CustomerDtos
	 * 
	 * @return List<CustomerDto>
	 */
	@GetMapping(value = { "/allCustomers", "/allCustomers/" })
	public List<CustomerDto> getAllCustomers() {
		return service.getAllCustomers().stream().map(p -> convertToDto(p)).collect(Collectors.toList());
	}

	/**
	 * Gets the list of CustomerDtos of a certain tier
	 * 
	 * @return List<CustomerDto>
	 */
	@GetMapping(value = { "/allCustomersByTier", "/allCustomersByTier/" })
	public List<CustomerDto> getAllCustomersByTier(@PathVariable("tier") TierClass tier) {
		return service.getAllCustomerByTier(tier).stream().map(p -> convertToDto(p)).collect(Collectors.toList());
	}

	/**
	 * Gets the list of CustomerDtos of a certain ban
	 * 
	 * @return List<CustomerDto>
	 */
	@GetMapping(value = { "/allCustomersByBan", "/allCustomersByBan/" })
	public List<CustomerDto> getAllCustomersByBan(@PathVariable("ban") boolean ban) {
		return service.getAllCustomerByBan(ban).stream().map(p -> convertToDto(p)).collect(Collectors.toList());
	}

	/**
	 * Delete customer from given id
	 * 
	 * @param id
	 * @return CustomerDto
	 */
	@DeleteMapping(value = { "/customer/delete/{id}", "/customer/delete/{id}/" })
	public CustomerDto deleteCustomer(@PathVariable("id") int id) {
		Customer customer = service.getCustomer(id);
		service.deleteCustomer(id);
		return convertToDto(customer);
	}

	private CustomerDto convertToDto(Customer customer) {
		if (customer == null) {
			throw new IllegalArgumentException("There is no such customer!");
		}
		CustomerDto customerDto = new CustomerDto(customer.getId(), PersonDto.convertToDto(customer.getPerson()),
				customer.getTierclass(), customer.getBan());
		return customerDto;
	}

}
