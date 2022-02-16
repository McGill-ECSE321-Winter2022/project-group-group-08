package mcgill.ecse321.grocerystore.dao;

import org.springframework.data.repository.CrudRepository;

import mcgill.ecse321.grocerystore.model.Account;

public interface AccountRepository extends CrudRepository<Account, String>{

	Account findAccountByUsername(String username);
}
