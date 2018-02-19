package com.privatesecuredata.eventplanner.components;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Log;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;
import org.slf4j.Logger;

import com.privatesecuredata.eventplanner.entities.Event;
import com.privatesecuredata.eventplanner.pages.Upcoming;
import com.privatesecuredata.eventplanner.services.PersistanceService;

public class UpcomingEventList
{
	@Inject
    @Property
    private Locale currentLocale;
	
	@Inject
	private Logger logger;

	@Inject
	private PersistanceService persistance;
	
	@Property
	private Event event;
	
	@InjectComponent
    private Zone listzone;
	
	@Inject
    private Request request;
	
	@Inject
    private AjaxResponseRenderer ajaxResponseRenderer;
	
	@Component
	private Zone listZone;
	
	public List<Event> getUpcomingEvents() {
		Calendar cal = Calendar.getInstance(currentLocale);
		return persistance.loadEvents(cal.getTime());
	}
	
	@Log
	public Class onDel(Event t) {
		boolean deleteSucceeded = persistance.delete(t);
		if (deleteSucceeded) logger.info("deleted event!");

		if (deleteSucceeded && request.isXHR()) {
            ajaxResponseRenderer.addRender(listzone);
            return null;
        }
		
		return Upcoming.class;
	}

}