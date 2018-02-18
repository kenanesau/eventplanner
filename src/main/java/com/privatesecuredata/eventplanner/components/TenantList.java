package com.privatesecuredata.eventplanner.components;

import java.util.List;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.privatesecuredata.eventplanner.entities.Tenant;
import com.privatesecuredata.eventplanner.services.PersistanceService;

public class TenantList {
	@Property
	private Tenant tenant;
	
	@Inject
	private PersistanceService persistanceService;
	
	public List<Tenant> getTenants() {
		return persistanceService.loadTenants();
	}
	
}