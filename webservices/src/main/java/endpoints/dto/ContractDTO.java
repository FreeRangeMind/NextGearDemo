package endpoints.dto;

import java.math.BigDecimal;

/**
 * This is a RESTful service output DTO (Data Transfer Object) that is rendered into JSON
 * via Jackson and the Spring Boot magic.  
 * 
 * XXX Warning:  changes to this class will change the publicly exposed WS contract.
 * 
 * @author Tony Boarman
 * @date Jan 5, 2018
 */
public class ContractDTO {
	
	private long contractId;
	private String name;
	private Long businessNumber;
	private String contractActivationDate;
	private BigDecimal amountRequested;   //TODO note you can change this to use @see java.util.Currency or an extension thereof 
	private ContractStatusDTO status;
	private ContractTypeDTO contractType;

	public long getContractId() {
		return contractId;
	}

	public void setContractId(long contractId) {
		this.contractId = contractId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getBusinessNumber() {
		return businessNumber;
	}

	public void setBusinessNumber(Long businessNumber) {
		this.businessNumber = businessNumber;
	}

	public String getContractActivationDate() {
		return contractActivationDate;
	}

	public void setContractActivationDate(String contractActivationDate) {
		this.contractActivationDate = contractActivationDate;
	}

	public BigDecimal getAmountRequested() {
		return amountRequested;
	}

	public void setAmountRequested(BigDecimal amountRequested) {
		this.amountRequested = amountRequested;
	}

	public ContractStatusDTO getStatus() {
		return status;
	}

	public void setStatus(ContractStatusDTO status) {
		this.status = status;
	}
	
	public ContractTypeDTO getContractType() {
		return contractType;
	}

	public void setContractType(ContractTypeDTO contractType) {
		this.contractType = contractType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (contractId ^ (contractId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof ContractDTO)) {
			return false;
		}
		ContractDTO other = (ContractDTO) obj;
		if (contractId != other.contractId) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "ContractDTO [contractId=" + contractId + ", name=" + name + ", businessNumber=" + businessNumber
				+ ", contractActivationDate=" + contractActivationDate + ", amountRequested=" + amountRequested
				+ ", status=" + status + ", contractType=" + contractType + "]";
	}
}
