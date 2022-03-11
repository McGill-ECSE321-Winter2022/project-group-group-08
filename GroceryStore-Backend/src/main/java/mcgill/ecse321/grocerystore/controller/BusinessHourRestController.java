package mcgill.ecse321.grocerystore.controller;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mcgill.ecse321.grocerystore.model.BusinessHour;
import mcgill.ecse321.grocerystore.model.Employee;
import mcgill.ecse321.grocerystore.model.GroceryStoreSystem;
import mcgill.ecse321.grocerystore.model.UserRole;
import mcgill.ecse321.grocerystore.model.BusinessHour.WeekDay;
import mcgill.ecse321.grocerystore.dto.BusinessHourDto;
import mcgill.ecse321.grocerystore.service.BusinessHourService;

@CrossOrigin(origins = "*")
@RestController
public class BusinessHourRestController {

    @Autowired
	private BusinessHourService businesshourservice;
	
    @GetMapping(value = { "/businesshour/all", "/businesshour/all/" })
    public List<BusinessHourDto> getAllBusinessHours(){
        return businesshourservice.getAllBusinessHours().stream().map(q->convertToDto(q).collect(Collectors.toList()));
    }

	@GetMapping(value = { "/businesshour/{id}", "/businesshour/{id}/" })
    public BusinessHourDto getBusinessHour(@PathVariable("id") int id){
        BusinessHour businessHour = businesshourservice.getBusinessHoursbyID(id);
        return convertToDto(businessHour);
    }

    @PostMapping(value = { "/businesshour/{id}", "/businesshour/{id}/" })
	public BusinessHourDto creatBusinessHour(@RequestParam (name = "id") int id, @RequestParam (name = "day") WeekDay day, @RequestParam (name = "startTime") Time startTime,
			@RequestParam (name = "endTime") Time endTime, @RequestParam (name = "working") Boolean working) throws IllegalArgumentException {
        BusinessHour b = businesshourservice.createBusinessHour(id, day, startTime, endTime, working);
		return convertToDto(b);
	}
	
    @PatchMapping(value = {"/businesshour/update/{id}", "/businesshour/update/{id}/"})
    public BusinessHourDto updateBusinessHour(@RequestParam (name = "id") int id, @RequestParam (name = "day") WeekDay day, @RequestParam (name = "startTime") Time startTime,
    @RequestParam (name = "endTime") Time endTime, @RequestParam (name = "working") Boolean working, @RequestParam (name = "storeName") String storeName) {
        GroceryStoreSystem system=GroceryStoreSystemService.getGroceryStoreSystembyStoreName(storeName);
        if(system == null) {
            throw new IllegalArgumentException("This system does not exists");
        }
        BusinessHour b = businesshourservice.updateBusinessHour(system,id, day, startTime, endTime, working);
		return convertToDto(b);
 }

	private BusinessHourDto convertToDto(BusinessHour businesshour) {
		if (businesshour == null) {
			throw new IllegalArgumentException("There is no such BusinessHour!");
		}
		BusinessHourDto businessHourDto = new BusinessHourDto(businesshour.getId(),businesshour.getDay(),businesshour.getStartTime(),businesshour.getEndTime(),businesshour.getWorking());
		return businessHourDto;
	}
	
}
