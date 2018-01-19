package endpoints;

import java.math.BigDecimal;
import java.util.Collection;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import endpoints.dto.ContractDTO;
import endpoints.dto.ContractStatusDTO;
import endpoints.dto.ContractTypeDTO;

/**
 * This WS end-point provides the various contract RESTful endpoints that are (will be) 
 * invoked by client software application, such as the Salesforce U.I.
 * 
 * @author Tony Boarman
 * @date Jan 5, 2018
 */
@RestController
public interface ContractService {
	
	/* XXX note authentication and authorization protecting the services not implemented,
	 * but will be inherently assumed for illustration purposes (sorry folks coding those tomorrow) */

    /* XXX for additional realistic flare a User ID or Session Token can be provided for the 
     * CUD methods for logging and authentication and authorization support. */

    /* TODO add checked exception handling for methods  */
	/* TODO create JSON error output renderer */
	
	/**
	 * Get a (one) contract 
	 * @param contractId
	 * @return
	 */
    @RequestMapping(path="/contract", method=RequestMethod.GET)
    public ContractDTO findContractByContractId( 
    		@RequestParam(name=QueryParams.Name.CONTRACT_ID) Long contractId );
    
    /**
     * Get ALL contracts with the given status; defaults the status to Approved if not provided.
     * 
     * @param status
     * @return
     */
    @RequestMapping(path="/contracts", method=RequestMethod.GET)
    public Collection<ContractDTO> findAllContractsByStatus( 
    		@RequestParam(name=QueryParams.Name.CONTRACT_STATUS, required=false) ContractStatusDTO status );
    
    /**
     * Create a contract
     * 
     * @param type
     * @param name
     * @param businessNumber
     * @param amountRequested
     * @return
     */
    @RequestMapping(path="/contract/create", method=RequestMethod.GET)
    public ContractDTO createContract( 
    		@RequestParam(name=QueryParams.Name.CONTRACT_TYPE) ContractTypeDTO type, 
    		@RequestParam(name=QueryParams.Name.CONTRACT_NAME) String name, 
    		@RequestParam(name=QueryParams.Name.BUSINESS_NUMBER) Long businessNumber, 
    		@RequestParam(name=QueryParams.Name.CONTRACT_AMOUNT) BigDecimal amountRequested );
    
    /**
     * Update a contract 
     * 
     * @param contractId
     * @param name
     * @param businessNumber
     * @param amountRequested
     * @return
     */
    @RequestMapping(path="/contract/update", method=RequestMethod.GET)
    public ContractDTO updateContract( 
    		@RequestParam(name=QueryParams.Name.CONTRACT_ID) Long contractId, 
    		@RequestParam(name=QueryParams.Name.CONTRACT_NAME, required=false) String name, 
    		@RequestParam(name=QueryParams.Name.BUSINESS_NUMBER, required=false) Long businessNumber, 
    		@RequestParam(name=QueryParams.Name.CONTRACT_AMOUNT, required=false) BigDecimal amountRequested );
    
    /**
     * Delete a contract
     * 
     * @param contractId
     */
    @RequestMapping(path="/contract/delete", method=RequestMethod.GET)
    public void deleteContract( 
    		@RequestParam(name=QueryParams.Name.CONTRACT_ID) Long contractId );
	
}
