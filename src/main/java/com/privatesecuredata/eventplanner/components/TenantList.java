package com.privatesecuredata.eventplanner.components;

import java.util.List;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Log;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.ActionLink;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;
import org.slf4j.Logger;

import com.privatesecuredata.eventplanner.entities.Tenant;
import com.privatesecuredata.eventplanner.services.PersistanceService;

public class TenantList {
	@Inject
	private Logger logger;

	@Inject
	private PersistanceService persistance;
	
	@Property
	private Tenant tenant;
	
	@InjectComponent
    private Zone listzone;
	
	@Inject
    private Request request;
	
	@Inject
    private AjaxResponseRenderer ajaxResponseRenderer;
	
	@Component
	private Zone listZone;
	
	public List<Tenant> getTenants() {
		return persistance.loadTenants();
	}
	
	@Log
	public void onDel(Tenant t) {
		persistance.delete(t);
		logger.info("deleted tenant!");
		
		if (request.isXHR()) {
            ajaxResponseRenderer.addRender(listzone);
        }
	}
}