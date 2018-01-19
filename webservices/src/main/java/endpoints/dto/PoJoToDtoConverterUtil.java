package endpoints.dto;

import java.util.Date;
import java.util.function.Function;

import business.Business;
import contract.Contract;
import contract.ContractStatus;
import contract.ExpressContract;
import contract.SalesContract;
import util.DateUtil;

public class PoJoToDtoConverterUtil {

	private static final Function<Contract, ContractTypeDTO> CONTRACT_TYPE_CONVERTER = contractPOJO -> {

		if (contractPOJO == null) {
			return null;
		}
		
		if (contractPOJO instanceof ExpressContract) {
			return ContractTypeDTO.EXPRESS;
		}

		if (contractPOJO instanceof SalesContract) {
			return ContractTypeDTO.SALES;
		}
		
		throw new IllegalArgumentException( "Cannot convert contract to known contract-type; Contract:  " + contractPOJO );
	};

	private static final Function<ContractStatus, ContractStatusDTO> CONTRACT_STATUS_CONVERTER = contractStatus -> {
		
		if (contractStatus == null) {
			return null;
		}
		
		switch (contractStatus) {
		
		case APPROVED:
			return ContractStatusDTO.APPROVED;
			
		case DENIED:
			return ContractStatusDTO.DENIED;
			
		default:
			throw new IllegalArgumentException( 
					"Cannot convert the " + contractStatus.getStatusDescription() 
					+ " contract status (" + contractStatus + ") into a known contract-status value." );
		}
	};
	
	public static final Function<Contract, ContractDTO> CONTRACT_CONVERTER = contractPOJO -> {

		if (contractPOJO == null) {
			return null;
		}
		
		ContractDTO external = new ContractDTO();
		
		external.setAmountRequested( contractPOJO.getAmountRequested() );
		
		Business business = contractPOJO.getBusiness();
		
		if (business != null) {
			external.setBusinessNumber( business.getBusinessNumber() );
		}
		
		Date contractActivationDate = contractPOJO.getContractActivationDate();
		
		if (contractActivationDate != null) {
			external.setContractActivationDate( DateUtil.getISO8601DateAsString(contractActivationDate) );
		}
		
		external.setContractId( contractPOJO.getContractId() );
		external.setContractType( CONTRACT_TYPE_CONVERTER.apply(contractPOJO) );
		external.setName( contractPOJO.getName() );
		external.setStatus( CONTRACT_STATUS_CONVERTER.apply(contractPOJO.getContractStatus()) );
		
		return external;
	};

}
