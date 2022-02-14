package mcgill.ecse321.grocerystore.dao;

import org.springframework.data.repository.CrudRepository;

import mcgill.ecse321.model.BusinessHour;

public interface BusinessHourRepository extends CrudRepository<BusinessHour, Integer>{

	BusinessHour findByid(Integer id);
}
