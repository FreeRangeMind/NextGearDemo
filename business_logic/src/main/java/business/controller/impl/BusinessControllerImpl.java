package business.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import business.Business;
import business.controller.BusinessController;
import business.dao.BusinessDAO;

@Component("businessController")
public class BusinessControllerImpl implements BusinessController {

	@Autowired
	private BusinessDAO businessDAO;
	
	@Override
	public Business findById( Long businessNumber ) {
		
		return businessDAO.findOne( businessNumber );
	}

	public BusinessDAO getBusinessDAO() {
		return businessDAO;
	}

	public void setBusinessDAO(BusinessDAO businessDAO) {
		this.businessDAO = businessDAO;
	}
}
