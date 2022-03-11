package mcgill.ecse321.grocerystore.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mcgill.ecse321.grocerystore.dto.ManagerDto;
import mcgill.ecse321.grocerystore.model.Manager;
import mcgill.ecse321.grocerystore.service.ManagerService;

@CrossOrigin(origins = "*")
@RestController
public class ManagerRestController {
	
	@Autowired
	private ManagerService managerService;
	private static final String baseURL = "/manager";

	@GetMapping(value = {baseURL+"/{id}", baseURL+"/{id}/"})
	public ManagerDto getManager(@PathVariable("id") int id) {
		Manager manager = managerService.getManager(id);
		return convertToDto(manager);
	}
	
	@PostMapping(value = {baseURL, baseURL+"/"})
	public ManagerDto createManager() {
		Manager manager = managerService.createManager();
		return convertToDto(manager);
	}
	
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
		ManagerDto managerDto = new ManagerDto();
		return managerDto;
	}
	
}
