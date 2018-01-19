package business.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import contract.Contract;

/**
 * This interface is an abstraction layer that acts as the gate-keeper/handler
 * for all database/object-persisting operations with regards to a Contract object(s)
 * instance.
 * 
 * Note:  This should only ever be used by an instance of the ContractController or
 * similar.
 * 
 * @see contract.controller.ContractController
 * 
 * @author Tony Boarman
 * @date Jan 5, 2018
 */
@Repository
public interface ContractDAO extends JpaRepository<Contract, Long> {
}
