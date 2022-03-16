package mcgill.ecse321.grocerystore.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mcgill.ecse321.grocerystore.dao.ManagerRepository;
import mcgill.ecse321.grocerystore.dao.PersonRepository;
import mcgill.ecse321.grocerystore.dao.UserRoleRepository;
import mcgill.ecse321.grocerystore.model.Manager;
import mcgill.ecse321.grocerystore.model.Person;

@Service
public class ManagerService {
	
	@Autowired
	ManagerRepository managerRepository;
	@Autowired 
	PersonRepository personRepository;
	@Autowired
	UserRoleRepository userRoleRepository;
	
	
	
	@Transactional 
	public Manager createManager(Person person) {
		if(person == null || !personRepository.existsById(person.getEmail())) {
			throw new IllegalArgumentException("Invalid person");
		}
		if(userRoleRepository.findUserRoleByPerson(person) != null){
			throw new InvalidInputException("Person has already been assigned a role");
		}
		Manager manager = new Manager();
		manager.setPerson(person);
		managerRepository.save(manager);
		return manager;
	}
	@Transactional
	public List<Manager> getAllManagers() {
		return toList(managerRepository.findAll());
	}
	
	@Transactional
	public Manager getManager(int id) {
		if(id < 0) {
			throw new IllegalArgumentException ("The id cannot be a negative number");
		}
		Manager manager = managerRepository.findManagerById(id);
		if(manager == null) {
			throw new IllegalArgumentException("No manager with id " + id + " exists");
		}
		return manager;
	}
	
	@Transactional
	public Manager updateManager(int id, Person person) {
		if(id < 0) {
			throw new IllegalArgumentException ("The id cannot be a negative number");
		}
		if(person == null) {
			throw new IllegalArgumentException ("The person cannot be null");
		}
		Manager manager = managerRepository.findManagerById(id);
		if(manager == null) {
			throw new IllegalArgumentException("No manager with id " + id + " exists");
		}
		manager.setPerson(person);
		managerRepository.save(manager);
		return manager;
	}
	
	@Transactional
	public boolean deleteManager(Manager manager) {
		if (manager == null || !managerRepository.existsById(manager.getId())) {
			throw new IllegalArgumentException("Manager does not exist");
		}else {
			managerRepository.delete(manager);
			return true;
		}
	}
	
	@Transactional
	public Manager deleteManagerById(int id) {
		if (id < 0) {
			throw new IllegalArgumentException("Negative Id");
		}
		else {
			Manager manager = managerRepository.findManagerById(id);
			if(manager == null) {
				throw new IllegalArgumentException("No manager with that id");
			}
			managerRepository.delete(manager);
			return manager;
		}
	}
	
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
	
	 	

}