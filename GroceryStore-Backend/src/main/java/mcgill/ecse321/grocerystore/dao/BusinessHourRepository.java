package mcgill.ecse321.grocerystore.dao;

import org.springframework.data.repository.CrudRepository;

import mcgill.ecse321.grocerystore.model.BusinessHour;

public interface BusinessHourRepository extends CrudRepository<BusinessHour, Integer>{

	BusinessHour findBusinessHourById(int id);
}