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
	
	/**
	 * Create a PersonDto object
	 * @param email email of person 
	 * @param firstName first name of person
	 * @param lastName last name of person
	 * @param phoneNumber phone number of person
	 * @param address address of person
	 * @return PersonDto
	 * @throws IllegalArgumentException
	 */
	@PostMapping(value = { "/createPerson/{email}", "/createPerson/{email}/" })
	public PersonDto createPerson(@PathVariable("email") String email, @RequestParam String firstName,
			@RequestParam String lastName,@RequestParam String phoneNumber,
			@RequestParam String address) throws IllegalArgumentException {
		
		Person person = personService.createPerson(email, firstName, lastName, phoneNumber, address);
		return PersonDto.convertToDto(person);
	}
	
	/**
	 * Updates a PersonDto object
	 * @param email email of person to be updated 
	 * @param firstName new first name of person
	 * @param lastName new last name of person
	 * @param phoneNumber new phone number of person
	 * @param address new address of person
	 * @return PersonDto
	 * @throws IllegalArgumentException
	 */
	@PutMapping(value = { "/updatePerson/{email}", "/updatePerson/{email}/" })
	public PersonDto updatePerson(@PathVariable("email") String email,
			@RequestParam String firstName, @RequestParam String lastName,
			@RequestParam String phoneNumber, @RequestParam String address) throws IllegalArgumentException {
		
		Person person = personService.updatePerson(email, firstName, lastName, phoneNumber, address);
		return PersonDto.convertToDto(person);
	}
	
	/**
	 * Gets the list of PersonDtos
	 * @return List<PErsonDto>
	 */
	@GetMapping(value = { "/getAllPersons", "/getAllPersons/" })
	public List<PersonDto> getAllPersons(){
		List<PersonDto> personDtos = new ArrayList<>();
	    for (Person person : personService.getAllPerson()) {
	    	personDtos.add(PersonDto.convertToDto(person));
	    }
	    return personDtos;
	}
	
	/**
	 * Gets PersonDto with this email
	 * @param email
	 * @return PersonDto
	 */
	@GetMapping(value = { "/getPersonByEmail/{email}", "/getPersonByEmail/{email}/" })
	public PersonDto getPersonByEmail(@PathVariable("email") String email){
		return PersonDto.convertToDto(personService.findPersonByEmail(email));
	}
	
	/**
	 * Gets list of PersonDto having this first name
	 * @param firstName first name of person
	 * @return List<PersonDto>
	 */
	@GetMapping(value = { "/getPersonsByFirstName/{firstName}", "/getPersonsByFirstName/{firstName}/" })
	public List<PersonDto> getPersonsByFirstName(@PathVariable("firstName") String firstName){
		List<PersonDto> personDtos = new ArrayList<>();
	    for (Person person : personService.getAllPerson()) {
	    	if(person.getFirstName().equalsIgnoreCase(firstName)) {
	    		personDtos.add(PersonDto.convertToDto(person));
	    	}
	    }
	    return personDtos;
	}
	
	/**
	 * Gets list of PersonDto having this last name
	 * @param lastName last name of person
	 * @return List<PersonDto>
	 */
	@GetMapping(value = { "/getPersonsByLastName/{lastName}", "/getPersonsByLastName/{lastName}/" })
	public List<PersonDto> getPersonsByLastName(@PathVariable("lastName") String lastName){
		List<PersonDto> personDtos = new ArrayList<>();
	    for (Person person : personService.getAllPerson()) {
	    	if(person.getLastName().equalsIgnoreCase(lastName)) {
	    		personDtos.add(PersonDto.convertToDto(person));
	    	}
	    }
	    return personDtos;
	}
	
	/**
	 *  Gets list of PersonDto having this last name
	 * @param address address of person
	 * @return List<PersonDto>
	 */
	@GetMapping(value = { "/getPersonsByAddress/{address}", "/getPersonsByAddress/{address}/" })
	public List<PersonDto> getPersonsByAddress(@PathVariable("address") String address){
		List<PersonDto> personDtos = new ArrayList<>();
	    for (Person person : personService.getAllPerson()) {
	    	if(person.getAddress().equals(address)) {
	    		personDtos.add(PersonDto.convertToDto(person));
	    	}
	    }
	    return personDtos;
	}
	
	/**
	 * Delete Person with this email
	 * @param email
	 * @return PersonDto
	 */
	@DeleteMapping(value = { "/deletePerson/{email}", "/deletePerson/{email}/" })
	public PersonDto deletePerson(@PathVariable("email") String email) {
		
		Person person = personService.deletePersonByEmail(email);
		return PersonDto.convertToDto(person);
	}
	
	/**
	 * Delete all Person in system
	 * @return List<PersonDto>
	 */
	@DeleteMapping(value = { "/deleteAllPersons", "/deleteAllPersons/" })
	public List<PersonDto> deleteAllPersons() {
		List <Person> personList = personService.getAllPerson();
		List <PersonDto> personDtos = new ArrayList<PersonDto>();
		
		for(Person person: personList) {
			personService.deletePerson(person);
			personDtos.add(PersonDto.convertToDto(person));
		}
		return personDtos;
	}
}