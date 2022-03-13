package mcgill.ecse321.grocerystore.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import mcgill.ecse321.grocerystore.dto.PersonDto;
import mcgill.ecse321.grocerystore.model.Person;
import mcgill.ecse321.grocerystore.service.PersonService;

@CrossOrigin(origins = "*")
@RestController
public class PersonRestController {

	@Autowired
	private PersonService personService;
	
	@PostMapping(value = { "/createPerson/{email}", "/createPerson/{email}/" })
	public PersonDto createPerson(@PathVariable("email") String email, @RequestParam String firstName,
			@RequestParam String lastName,@RequestParam String phoneNumber,
			@RequestParam String address) throws IllegalArgumentException {
		
		Person person = personService.createPerson(email, firstName, lastName, phoneNumber, address);
		return convertToDto(person);
	}
	
	@PutMapping(value = { "/updatePerson/{email}", "/updatePerson/{email}/" })
	public PersonDto updatePerson(@PathVariable("email") String email,
			@RequestParam String firstName, @RequestParam String lastName,
			@RequestParam String phoneNumber, @RequestParam String address) throws IllegalArgumentException {
		
		Person person = personService.updatePerson(email, firstName, lastName, phoneNumber, address);
		return convertToDto(person);
	}
	
	@DeleteMapping(value = { "/deletePerson/{email}", "/deletePerson/{email}/" })
	public PersonDto deletePerson(@PathVariable("email") String email) throws IllegalArgumentException {
		
		Person person = personService.deletePersonByEmail(email);
		return convertToDto(person);
	}
	
	@GetMapping(value = { "/getAllPersons", "/getAllPersons/" })
	public List<PersonDto> getAllPersons(){
		List<PersonDto> personDtos = new ArrayList<>();
	    for (Person person : personService.getAllPerson()) {
	    	personDtos.add(convertToDto(person));
	    }
	    return personDtos;
	}
	
	@GetMapping(value = { "/getPersonByEmail/{email}", "/getPersonByEmail/{email}/" })
	public PersonDto getPersonByEmail(@PathVariable("email") String email){
		return convertToDto(personService.findPersonByEmail(email));
	}
	
	@GetMapping(value = { "/getPersonsByFirstName/{firstName}", "/getPersonsByFirstName/{firstName}/" })
	public List<PersonDto> getPersonsByFirstName(@PathVariable("firstName") String firstName){
		List<PersonDto> personDtos = new ArrayList<>();
	    for (Person person : personService.getAllPerson()) {
	    	if(person.getFirstName().equalsIgnoreCase(firstName)) {
	    		personDtos.add(convertToDto(person));
	    	}
	    }
	    return personDtos;
	}
	
	@GetMapping(value = { "/getPersonsByLastName/{lastName}", "/getPersonsByLastName/{lastName}/" })
	public List<PersonDto> getPersonsByLastName(@PathVariable("lastName") String lastName){
		List<PersonDto> personDtos = new ArrayList<>();
	    for (Person person : personService.getAllPerson()) {
	    	if(person.getLastName().equalsIgnoreCase(lastName)) {
	    		personDtos.add(convertToDto(person));
	    	}
	    }
	    return personDtos;
	}
	
	@GetMapping(value = { "/getPersonsByAddress/{address}", "/getPersonsByAddress/{address}/" })
	public List<PersonDto> getPersonsByAddress(@PathVariable("address") String address){
		List<PersonDto> personDtos = new ArrayList<>();
	    for (Person person : personService.getAllPerson()) {
	    	if(person.getAddress().equals(address)) {
	    		personDtos.add(convertToDto(person));
	    	}
	    }
	    return personDtos;
	}
	
	//UTILITIES
	private PersonDto convertToDto(Person p) {
		if (p == null) {
			throw new IllegalArgumentException("There is no such Person!");
		}
		PersonDto personDto = new PersonDto(p.getEmail(),p.getFirstName(),p.getLastName(),p.getPhoneNumber(),
				p.getAddress());
		return personDto;
	}
}