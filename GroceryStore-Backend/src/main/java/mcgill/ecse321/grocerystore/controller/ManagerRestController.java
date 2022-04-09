package mcgill.ecse321.grocerystore.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mcgill.ecse321.grocerystore.dto.ManagerDto;
import mcgill.ecse321.grocerystore.dto.PersonDto;
import mcgill.ecse321.grocerystore.model.Manager;
import mcgill.ecse321.grocerystore.model.Person;
import mcgill.ecse321.grocerystore.service.ManagerService;
import mcgill.ecse321.grocerystore.service.PersonService;

@CrossOrigin(origins = "*")
@RestController
public class ManagerRestController {
	
	@Autowired
	private ManagerService managerService;
	@Autowired
	private PersonService personService;
	private static final String baseURL = "/manager";

	/**
	 * gets manager
	 * @param id primary key of manager
	 * @return manager
	 */
	@GetMapping(value = {baseURL+"/{id}", baseURL+"/{id}/"})
	public ManagerDto getManager(@PathVariable("id") int id) {
		Manager manager = managerService.getManager(id);
		return convertToDto(manager);
	}
	
	/**
	 * creates manager 
	 * @param personEmail person associated to manager
	 * @return manager
	 */
	@PostMapping(value = {baseURL, baseURL+"/"})
	public ManagerDto createManager(
			@RequestParam(name = "personEmail") String personEmail
			) {
		Person person = personService.findPersonByEmail(personEmail);
		Manager manager = managerService.createManager(person);
		return convertToDto(manager);
	}
	
	/**
	 * updates manager 
	 * @param id primary key of manager
	 * @param personEmail person object associated with manager
	 * @return manager
	 */
	@PatchMapping(value = {baseURL+"/update/{id}", baseURL+"/update/{id}/"})
	public ManagerDto updateManager(
			@PathVariable(name = "id") int id,
			@RequestParam(name = "personEmail") String personEmail
			) {
		Manager manager = managerService.updateManager(id, personService.findPersonByEmail(personEmail));
		return convertToDto(manager);
	}
	
	/**
	 * Deletes manager
	 * @param id primary key of manager
	 * @return manager
	 */
	@DeleteMapping(value = {baseURL + "/delete/{id}", baseURL+"/delete/{id}/"})
	public ManagerDto deleteManager(@PathVariable("id") int id) {
		Manager manager = managerService.getManager(id);
		managerService.deleteManager(manager);
		return convertToDto(manager);
	}
	
	
	private ManagerDto convertToDto(Manager manager) {
		if (manager == null) {
			throw new IllegalArgumentException("There is no such manager!");
		}
		ManagerDto managerDto = new ManagerDto(manager.getId(), PersonDto.convertToDto(manager.getPerson()));
		return managerDto;
	}
	
}
