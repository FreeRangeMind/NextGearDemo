package business.controller;

import business.Business;

/**
 * Business logic controller contain the Business entity logic.
 *  
 * @author Tony Boarman
 * @date Jan 5, 2018
 */
public interface BusinessController {
	
	public Business findById( Long businessNumber );
	
}
