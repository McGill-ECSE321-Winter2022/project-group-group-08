package mcgill.ecse321.grocerystore.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import mcgill.ecse321.grocerystore.dao.UserRoleRepository;
import mcgill.ecse321.grocerystore.dto.CartDto;
import mcgill.ecse321.grocerystore.dto.PersonDto;
import mcgill.ecse321.grocerystore.dto.ReceiptDto;
import mcgill.ecse321.grocerystore.dto.UserRoleDto;
import mcgill.ecse321.grocerystore.model.Person;
import mcgill.ecse321.grocerystore.model.Receipt;
import mcgill.ecse321.grocerystore.model.UserRole;
import mcgill.ecse321.grocerystore.service.ReceiptService;
import mcgill.ecse321.grocerystore.service.UserRoleService;


public class UserRoleRestController {
	
	private UserRoleService userRoleService;
	private static final String baseURL="userRole";
	@GetMapping(value = { "/getAllItems", "/getAllItems/" })
    
	public List<UserRoleDto> getAllUserRoles() {
        return userRoleService.getAllUserRoles().stream().map(u -> convertToDto(u)).collect(Collectors.toList());
    }
	
	@GetMapping(value = { "/getItemById/{id}", "/getItemById/{id}/" })
    public UserRoleDto getUserRoleById(@PathVariable("id") int id) {
		UserRole userRole = userRoleService.findUserRoleById(id);
		return convertToDto(userRole);
    }
	
	private UserRoleDto convertToDto(UserRole user) {
		if(user == null) {
			throw new IllegalArgumentException("There is no such receipt!");
			
		}
		UserRoleDto userRoleDto = new UserRoleDto(user.getId(), PersonDto.convertToDto(user.getPerson())); //fix person
		return userRoleDto;
	}
	
	
	
 	
 }