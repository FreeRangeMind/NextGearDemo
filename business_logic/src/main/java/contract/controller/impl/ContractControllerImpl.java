package contract.controller.impl;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import business.Business;
import business.controller.BusinessController;
import business.dao.ContractDAO;
import contract.Contract;
import contract.ContractStatus;
import contract.ExpressContract;
import contract.SalesContract;
import contract.controller.ContractController;
import util.StringUtil;

@Component("contractController")
public class ContractControllerImpl implements ContractController {
	
	private static final BigDecimal FIFTY_THOUSAND = new BigDecimal(50000);

	@Autowired
	@Qualifier("businessController")
	private BusinessController businessController;

	@Autowired
	@Qualifier("contractDAO")
	private ContractDAO contractDAO;
	
	@Override
	public Contract findById( Long contractId ) {
		
		return contractDAO.findOne( contractId );
	}

	@Override
	public Collection<Contract> findAllContractsByStatus( ContractStatus contractStatus ) {
		
		Contract contract = new Contract();
		contract.setContractStatus(contractStatus);
		Example<Contract> example = Example.of(contract);
		
		return contractDAO.findAll(example);
	}

	//XXX note I would make this a transactional method if this where real code 
	@Override
	public void deleteContract( Contract contract ) {
		
		if (contract != null) {
			contractDAO.delete(contract);
		}
	}
	
	//XXX note I would make this a transactional method if this where real code
	@Override
	public SalesContract createSalesContract( String name, Business business, BigDecimal amountRequested ) {
		
		SalesContract contract = new SalesContract();
		
		contract.setAmountRequested(amountRequested);
		contract.setBusiness(business);
		contract.setName(name);
		setActiveAndApproved( contract );
		
		return contractDAO.saveAndFlush(contract);
	}

	//XXX note I would make this a transactional method if this where real code
	@Override
	public ExpressContract createExpressContract( String name, Business business, BigDecimal amountRequested ) {
		
		ExpressContract contract = new ExpressContract();
		
		contract.setAmountRequested(amountRequested);
		contract.setBusiness(business);
		contract.setName(name);
		
		if ( isLessThanFiftyThousand( amountRequested ) ) {
			
			setActiveAndApproved( contract );
			
		} else {
			
			contract.setContractStatus( ContractStatus.DENIED );
		}
		
		return contractDAO.saveAndFlush(contract);
	}
	
	//XXX note I would make this a transactional method if this where real code
	@Override
	public Contract updateContract( Contract persistedContract, String newNameValue, 
			Business newBusinessValue, BigDecimal newAmountRequestedValue ) {
		
		if ( !(StringUtil.isNullOrEmpty(newNameValue)) ) {
			
			persistedContract.setName(newNameValue);
		}

		if ( newBusinessValue != null ) {
			
			persistedContract.setBusiness(newBusinessValue);
		}

		if ( newAmountRequestedValue != null ) {
			
			validateAmountRequestedUpdateValue( persistedContract, newAmountRequestedValue );
			
			persistedContract.setAmountRequested( newAmountRequestedValue );

			if (isLessThanFiftyThousand( newAmountRequestedValue ) 
					|| persistedContract instanceof SalesContract ) {

				setActiveAndApproved( persistedContract );
			}
		}
		
		return contractDAO.saveAndFlush( persistedContract );
	}

	/**
	 * Contains biz-logic for approved contracts.
	 * 
	 * @param contract
	 */
	private void setActiveAndApproved( Contract contract ) {
		
		contract.setContractStatus( ContractStatus.APPROVED );
		contract.setContractActivationDate( new Date() );
	}
	
	private void validateAmountRequestedUpdateValue( Contract contract, BigDecimal newAmountRequestedValue ) {
		
		if (isLessThanZero( newAmountRequestedValue )) {
			
			throw new IllegalArgumentException(
					"Cannot update contract with value less than zero");
		}
		
		if (contract.isActiveAndApproved() ) {

			BigDecimal contractAmountRequested = contract.getAmountRequested();

			if (contractAmountRequested != null 
					&& (newAmountRequestedValue.compareTo(contractAmountRequested) != 0)) {

				throw new IllegalArgumentException(
						"Cannot change the contract amount after it has been approved and made active.");
			}

			if (contract instanceof ExpressContract && !(isLessThanFiftyThousand( newAmountRequestedValue )) ) {
				
				throw new IllegalArgumentException(
						"Cannot update express contract with value less than $50,000 USD");
			}	
		}
	}
	
	private boolean isLessThanZero( BigDecimal amountRequested ) {

		return (amountRequested.compareTo(BigDecimal.ZERO) < 0);
	}
	
	private boolean isLessThanFiftyThousand( BigDecimal amountRequested ) {
		
		return (amountRequested.compareTo(FIFTY_THOUSAND) < 0);
	}
	
	public BusinessController getBusinessController() {
		return businessController;
	}

	public void setBusinessController(BusinessController businessController) {
		this.businessController = businessController;
	}

	public ContractDAO getContractDAO() {
		return contractDAO;
	}

	public void setContractDAO(ContractDAO contractDAO) {
		this.contractDAO = contractDAO;
	}
}
