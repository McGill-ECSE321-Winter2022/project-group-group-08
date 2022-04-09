package mcgill.ecse321.grocerystore.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mcgill.ecse321.grocerystore.dto.PersonDto;
import mcgill.ecse321.grocerystore.dto.UserRoleDto;
import mcgill.ecse321.grocerystore.model.Person;
import mcgill.ecse321.grocerystore.model.UserRole;
import mcgill.ecse321.grocerystore.service.PersonService;
import mcgill.ecse321.grocerystore.service.UserRoleService;

@CrossOrigin(origins = "*")
@RestController
public class UserRoleRestController {
	
	@Autowired 
	private PersonService personService;
	@Autowired
	private UserRoleService userRoleService;
	
	//specific url gets all the existing UserRole transfer objects
	@GetMapping(value = { "/getAllUserRoles", "/getAllUserRoles/" })
	public List<UserRoleDto> getAllUserRoles() {
		return userRoleService.getAllUserRoles().stream().map(i -> convertToDto(i)).collect(Collectors.toList());
    }
	//specific url returns a transfer object UserRole that matches the given id
	@GetMapping(value = {"/getRoleById/{id}","/getRoleById/{id}/" })
    public UserRoleDto getUserRoleById(@PathVariable("id") int id) {
		UserRole userRole = userRoleService.findUserRoleById(id);
		return convertToDto(userRole);
    }
	@GetMapping(value = {"/getRoleByPerson", "/getRoleByPerson/"})
	public UserRoleDto getUserRoleByPerson(@RequestParam("personEmail") String personEmail) {
		Person person = personService.findPersonByEmail(personEmail);
		UserRole userRole = userRoleService.findUserRoleByPerson(person);
		return convertToDto(userRole);
    }
	//converts a UserRole instance to a transfer object UserRole
	private UserRoleDto convertToDto(UserRole user) {
		if(user == null) {
			throw new IllegalArgumentException("There is no such user role!");
			
		}
		UserRoleDto userRoleDto = new UserRoleDto(user.getId(), PersonDto.convertToDto(user.getPerson())); //fix person
		return userRoleDto;
	}
 }