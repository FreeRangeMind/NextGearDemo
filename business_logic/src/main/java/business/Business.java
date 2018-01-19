package business;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This is just some POJO place holder class for what ever the "businessNumber" on the contract 
 * is referencing.
 * 
 * @author Tony Boarman
 * @date Jan 5, 2018
 */
@Entity
@Table(name = "BUSINESS", schema = "NEXTGEAR_DEMO")
public class Business {
	
	@Id
	@Column(name="BUSINESS_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long businessNumber;
	
	@Column(name="NAME")
	private String name;

	public long getBusinessNumber() {
		return businessNumber;
	}

	public void setBusinessNumber(long businessNumber) {
		this.businessNumber = businessNumber;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (businessNumber ^ (businessNumber >>> 32));
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
		if (!(obj instanceof Business)) {
			return false;
		}
		Business other = (Business) obj;
		if (businessNumber != other.businessNumber) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Business [businessNumber=" + businessNumber + "]";
	}
}
