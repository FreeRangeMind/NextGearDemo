package endpoints.impl;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import business.Business;
import business.controller.BusinessController;
import contract.Contract;
import contract.ContractStatus;
import contract.controller.ContractController;
import endpoints.ContractService;
import endpoints.QueryParams;
import endpoints.dto.ContractDTO;
import endpoints.dto.ContractStatusDTO;
import endpoints.dto.ContractTypeDTO;
import endpoints.dto.PoJoToDtoConverterUtil;

@Component("contractService")
public class ContractServiceImpl implements ContractService {

	@Autowired
	@Qualifier("contractController")
	private ContractController contractController;

	@Autowired
	@Qualifier("businessController")
	private BusinessController businessController;
	
    public ContractDTO findContractByContractId( Long contractId ) {
    	
    	Contract contract = contractController.findById(contractId);
    	
    	ContractDTO contractDTO = PoJoToDtoConverterUtil.CONTRACT_CONVERTER.apply(contract);
    	
		return contractDTO;
    }

	@Override
	public Collection<ContractDTO> findAllContractsByStatus( ContractStatusDTO status ) {
		
		ContractStatus contractStatus = 
				(status == null ? QueryParams.DefaultValue.CONTRACT_STATUS : status).getContractStatus();
		
		Collection<Contract> contracts = contractController.findAllContractsByStatus( contractStatus );
		
		Collection<ContractDTO> contractDTOs = contracts.stream()
				.map(PoJoToDtoConverterUtil.CONTRACT_CONVERTER)
				.collect( Collectors.<ContractDTO>toList() );
		
		return contractDTOs;
	}

	@Override
	public ContractDTO createContract(
			ContractTypeDTO type, String name, Long businessNumber, BigDecimal amountRequested ) {
		
		Business business = businessController.findById( businessNumber );
		
		Contract contract = createContract( type, name, business, amountRequested );

    	ContractDTO contractDTO = PoJoToDtoConverterUtil.CONTRACT_CONVERTER.apply(contract);
    	
		return contractDTO;
	}
	
	private Contract createContract( ContractTypeDTO contractType, String name, Business business, BigDecimal amountRequested ) {
		
		switch(contractType) {
		
			case SALES:
				return contractController.createSalesContract(name, business, amountRequested);
			case EXPRESS:
				return contractController.createExpressContract(name, business, amountRequested);
			default:
				throw new IllegalArgumentException( 
						"Cannot convert the " + contractType.getContractTypeDescription() 
						+ " contract type (" + contractType + ") into a known internal value." );
		}
	}
	
	@Override
	public ContractDTO updateContract(
			Long contractId, String name, Long businessNumber, BigDecimal amountRequested ) {
		
    	Contract contract = contractController.findById( contractId );
		Business business = (businessNumber == null ? null : businessController.findById( businessNumber ));
		
		contract = contractController.updateContract( contract, name, business, amountRequested );

    	ContractDTO contractDTO = PoJoToDtoConverterUtil.CONTRACT_CONVERTER.apply(contract);
    	
		return contractDTO;
	}

	@Override
	public void deleteContract( Long contractId ) {

    	Contract contract = contractController.findById( contractId );
		contractController.deleteContract( contract );
	}
	
	public ContractController getContractController() {
		return contractController;
	}

	public void setContractController( ContractController contractController ) {
		this.contractController = contractController;
	}

	public BusinessController getBusinessController() {
		return businessController;
	}

	public void setBusinessController( BusinessController businessController ) {
		this.businessController = businessController;
	}
}
