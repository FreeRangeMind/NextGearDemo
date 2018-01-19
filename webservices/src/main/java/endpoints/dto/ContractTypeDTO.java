package endpoints.dto;

/**
 * Contract Sub-Type indicator 
 * 
 * @author Tony Boarman
 * @date Jan 5, 2018
 */
public enum ContractTypeDTO {
	
	SALES("Sales Contract"),
	EXPRESS("Express Contract");
	
	private String contractTypeDescription;
	
	private ContractTypeDTO( String contractTypeDescription ) {
		
		this.contractTypeDescription = contractTypeDescription;
	}

	public String getContractTypeDescription() {
		
		return contractTypeDescription;
	}
}
