package mcgill.ecse321.grocerystore.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import mcgill.ecse321.grocerystore.dto.PersonDto;
import mcgill.ecse321.grocerystore.dto.UserRoleDto;
import mcgill.ecse321.grocerystore.model.UserRole;
import mcgill.ecse321.grocerystore.service.UserRoleService;

@CrossOrigin(origins = "*")
@RestController
public class UserRoleRestController {
	
	@Autowired
	private UserRoleService userRoleService;
	
	@GetMapping(value = { "/getAllUserRoles", "/getAllUserRoles/" })
	public List<UserRoleDto> getAllUserRoles() {
		return userRoleService.getAllUserRoles().stream().map(i -> convertToDto(i)).collect(Collectors.toList());
    }
	
	@GetMapping(value = {"/getRoleById/{id}","/getRoleById/{id}/" })
    public UserRoleDto getUserRoleById(@PathVariable("id") int id) {
		UserRole userRole = userRoleService.findUserRoleById(id);
		return convertToDto(userRole);
    }
	
	private UserRoleDto convertToDto(UserRole user) {
		if(user == null) {
			throw new IllegalArgumentException("There is no such user role!");
			
		}
		UserRoleDto userRoleDto = new UserRoleDto(user.getId(), PersonDto.convertToDto(user.getPerson())); //fix person
		return userRoleDto;
	}
	
	
	
 	
 }