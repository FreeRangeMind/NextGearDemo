package contract;

public enum ContractStatus {

	APPROVED("Approved"),
	DENIED("Denied");
	
	private String statusDescription;
	
	private ContractStatus( String statusDescription ) {
		
		this.statusDescription = statusDescription;
	}

	public String getStatusDescription() {
		
		return statusDescription;
	}
	
}
