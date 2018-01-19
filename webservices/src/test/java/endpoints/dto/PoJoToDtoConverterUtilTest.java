package endpoints.dto;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Assert;

import org.junit.Test;

import business.Business;
import contract.ContractStatus;
import contract.SalesContract;

public class PoJoToDtoConverterUtilTest {
	
    @Test
    public void convertContractTest1() throws Exception {
    	
    	SalesContract contract = createFullyPopulatedContract();
    	
    	ContractDTO contractDTO = PoJoToDtoConverterUtil.CONTRACT_CONVERTER.apply(contract);
    	
    	Assert.assertEquals(contract.getContractId().longValue(), contractDTO.getContractId());
    	Assert.assertEquals(contract.getName(), contractDTO.getName());
    	Assert.assertEquals(contract.getAmountRequested(), contractDTO.getAmountRequested());
    	Assert.assertEquals(Long.valueOf(contract.getBusiness().getBusinessNumber()), contractDTO.getBusinessNumber());
    	Assert.assertEquals(contract.getContractActivationDate(), contractDTO.getContractActivationDate());
    	Assert.assertEquals(ContractStatusDTO.APPROVED, contractDTO.getStatus());
    	Assert.assertEquals(ContractTypeDTO.SALES, contractDTO.getContractType());

    }

    @Test
    public void convertContractTest2() throws Exception {

    	SalesContract contract = new SalesContract();
    	
    	ContractDTO contractDTO = PoJoToDtoConverterUtil.CONTRACT_CONVERTER.apply(contract);
    	
    	Assert.assertNull(contractDTO.getStatus());
    	Assert.assertEquals(ContractTypeDTO.SALES, contractDTO.getContractType());

    }
    
    private SalesContract createFullyPopulatedContract() {
    	
    	SalesContract contract = new SalesContract();
    	
    	contract.setAmountRequested( BigDecimal.valueOf(55005.33d) );
    	contract.setBusiness( createBusiness() );
    	contract.setContractActivationDate(parseStringAsDate( "2018-01-05" ));
    	contract.setContractId( 99L );
    	contract.setContractStatus( ContractStatus.APPROVED );
    	contract.setName( "Happy Little Contract" );
    	
    	return contract;
    }
    
    private Business createBusiness() {
    	
    	Business business = new Business();
    	business.setBusinessNumber( 123L );
    	
    	return business;
    }

	/**
	 * Convenient method to parse a String date and return a java.util.Date object
	 *
	 * @param dateAsString
	 * @return Date object or null
	 */
	private static Date parseStringAsDate( String dateAsString ) {
		
		DateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd" );
		Date dateValue = null;
		
		try {
			
			dateValue = dateFormat.parse( dateAsString );
			
		} catch (ParseException e) {
			
			throw new IllegalArgumentException("Cannot resolve  Date: " + dateAsString + " with pattern: yyyy-MM-dd", e);
		}
		
		return dateValue;
	}
}
