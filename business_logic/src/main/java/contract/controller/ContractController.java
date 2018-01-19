package contract.controller;

import java.math.BigDecimal;
import java.util.Collection;

import business.Business;
import contract.Contract;
import contract.ContractStatus;
import contract.ExpressContract;
import contract.SalesContract;

/**
 * This API acts as the gate-keeper/handler of all operations and business logic with regards
 * to a Contract.  This is intended to be the "exposed" API that would be used by any external
 * project that would include the business_logic project as a dependency.
 * 
 * @author Tony Boarman
 * @date Jan 5, 2018
 */
public interface ContractController {

	/**
	 * Get a (one) persisted contract, if any, having the given Contract ID.
	 *  
	 * @param contractId - Contract ID for which to perform the record lookup.
	 * 
	 * @return the found contract or null if not found for the ID
	 */
    public Contract findById( Long contractId );
    
    /**
     * Get ALL contracts with the given status;
     * 
     * @param contractStatus
     * @return
     */
    public Collection<Contract> findAllContractsByStatus( ContractStatus contractStatus );
    

    /**
     * Create a sales contract
     * 
     * @param name
     * @param business
     * @param amountRequested
     * @return
     */
    public SalesContract createSalesContract( String name, Business business, BigDecimal amountRequested );
    
    
    /**
     * Create an express contract
     * 
     * @param name
     * @param business
     * @param amountRequested
     * @return
     */
    public ExpressContract createExpressContract( String name, Business business, BigDecimal amountRequested );
    
    /**
     * Update a contract 
     * Long contractId, String name, Long businessNumber, BigDecimal amountRequested 
     * @param contract
     * @param name 
     * @param business 
     * @param amountRequested 
     * @return
     */
    public Contract updateContract( Contract contract, String name, Business business, BigDecimal amountRequested );
    
    /**
     * Delete a contract
     * 
     * @param contract
     * @return
     */
    public void deleteContract( Contract contract );
	
}
