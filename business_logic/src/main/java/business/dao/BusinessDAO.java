package business.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import business.Business;

/**
 * This interface is an abstraction layer that acts as the gate-keeper to all
 * DB operations with regards to a Business object.
 * 
 * @author Tony Boarman
 * @date Jan 5, 2018
 */
@Repository
public interface BusinessDAO extends CrudRepository<Business, Long> {
	
}
