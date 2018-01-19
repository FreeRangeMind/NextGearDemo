package endpoints.dto;

import contract.ContractStatus;

public enum ContractStatusDTO {
	
	APPROVED( ContractStatus.APPROVED ),
	DENIED( ContractStatus.DENIED );
	
	private ContractStatus contractStatus;
	
	private ContractStatusDTO( ContractStatus contractStatus ) {
		
		this.contractStatus = contractStatus;
	}

	public String getStatusDescription() {
		
		return getContractStatus().getStatusDescription();
	}
	
	public ContractStatus getContractStatus() {
		
		return contractStatus;
	}
}
