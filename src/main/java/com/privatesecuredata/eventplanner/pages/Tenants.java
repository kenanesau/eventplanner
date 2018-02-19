package com.privatesecuredata.eventplanner.pages;

import org.apache.tapestry5.annotations.Log;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.slf4j.Logger;

import com.privatesecuredata.eventplanner.entities.Tenant;
import com.privatesecuredata.eventplanner.services.PersistanceService;

public class Tenants
{
	@Inject
	private Logger logger;

	@Inject
	PersistanceService persistance;

	@Property
	private Tenant newTenant;
	
	@Log
	void onSuccess()
	{
		persistance.save(newTenant);
		logger.info("saved new tenant!");
	}
}
