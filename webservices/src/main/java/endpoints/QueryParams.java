package endpoints;

import endpoints.dto.ContractStatusDTO;

/**
 * This interface contains the entire static list of the reserved names and values used for 
 * the RESTful service query parameters.  This will allow for easier searching of name uses and 
 * helps to avoid naming conflicts (two different things named the same) and naming redundancies 
 * (two or more different names being used for the same thing, i.e.: id, contractId, cid, etc.).
 * 
 * Note:  Static final interface strings are used to avoid compile error when using values for
 * annotation attribute constants.
 * 
 * @author Tony Boarman
 * @date Jan 5, 2018
 */
public interface QueryParams {
	
	public interface Name {

		public static final String BUSINESS_NUMBER = "businessNumber";
		
		public static final String CONTRACT_AMOUNT = "amountRequested";
		
		public static final String CONTRACT_ID = "contractId";

		public static final String CONTRACT_NAME = "name";
		
		public static final String CONTRACT_STATUS = "status";
		
		public static final String CONTRACT_TYPE = "type";
	}

	public interface DefaultValue {
		
		public static final ContractStatusDTO CONTRACT_STATUS = ContractStatusDTO.APPROVED;
	}
}
