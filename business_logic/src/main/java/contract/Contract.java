package contract;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import business.Business;


@Entity(name = "Contract")
@Table(name = "CONTRACT", schema = "NEXTGEAR_DEMO")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
	    discriminatorType = DiscriminatorType.STRING,
	    name = "TYPE"
	)
public class Contract {
	
	/* Note: object is used here vs. primative to support find by example with null/unknown ID */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long contractId;
	
	@Column(name="NAME")
	private String name;

    @ManyToOne
    @JoinColumn(name = "BUSINESS_ID")
	private Business business;
	
	@Column(name="ACTIVATION_DATE")
	private Date contractActivationDate;
	
	@Column(name="AMOUNT", precision=20, scale=2)
	private BigDecimal amountRequested;   //TODO note you can change this to use Currency too, for example @see contract.ContractAmountRequested
	
	@Enumerated(EnumType.STRING)
	@Column(name="STATUS")
	private ContractStatus contractStatus;

	public Long getContractId() {
		return contractId;
	}

	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Business getBusiness() {
		return business;
	}

	public void setBusiness(Business business) {
		this.business = business;
	}

	public Date getContractActivationDate() {
		return contractActivationDate;
	}

	public void setContractActivationDate(Date contractActivationDate) {
		this.contractActivationDate = contractActivationDate;
	}

	public BigDecimal getAmountRequested() {
		return amountRequested;
	}

	public void setAmountRequested(BigDecimal amountRequested) {
		this.amountRequested = amountRequested;
	}
	
	public ContractStatus getContractStatus() {
		return contractStatus;
	}

	public void setContractStatus(ContractStatus contractStatus) {
		this.contractStatus = contractStatus;
	}
	
	public boolean isActiveAndApproved() {
		
		return (getContractActivationDate() != null && getContractStatus().equals(ContractStatus.APPROVED));
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
		if (!(obj instanceof Contract)) {
			return false;
		}
		Contract other = (Contract) obj;
		if (contractId != other.contractId) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Contract [contractId=" + contractId + ", name=" + name + ", business=" + business
				+ ", contractActivationDate=" + contractActivationDate + ", amountRequested=" + amountRequested + "]";
	}
}
