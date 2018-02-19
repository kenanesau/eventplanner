package com.privatesecuredata.eventplanner.components;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Log;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.DateField;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.Select;
import org.apache.tapestry5.corelib.components.TextField;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.SelectModelFactory;
import org.slf4j.Logger;

import com.privatesecuredata.eventplanner.entities.Event;
import com.privatesecuredata.eventplanner.entities.Room;
import com.privatesecuredata.eventplanner.entities.Tenant;
import com.privatesecuredata.eventplanner.services.PersistanceService;

public class CreateEvent {
	@Inject
    @Property
    private Locale currentLocale;
	
	@Inject
	private Logger logger;

	@Inject
	private PersistanceService persistance;
	
	@Property
	private SelectModel selectTenantModel;
	
	@Property 
	private Room selectedRoom;
	
	@Property 
	private SelectModel selectRoomModel;
	
	@Property 
	private Tenant selectedTenant;
	
	@Inject
	SelectModelFactory selectModelFactory;
	
	@Inject
	ComponentResources componentResources;
	
	@InjectComponent
	DateField startTime;
	
	@InjectComponent
	DateField endTime;
	
	@InjectComponent 
	TextField name;
	
	@InjectComponent
	Select tenant;
	
	@InjectComponent
	Select room;
	
	@Component
	Form form;
	
	@Property
	@Persist
    private Event newEvent;
	
	void setupRender() {
		List<Tenant> tenants = persistance.loadTenants();
		selectTenantModel = selectModelFactory.create(tenants, "name");
		
		List<Room> rooms = persistance.loadRooms();
		selectRoomModel = selectModelFactory.create(rooms, "name");
		
		Calendar cal = Calendar.getInstance(currentLocale);
		
		Date now = cal.getTime();
		cal.add(Calendar.HOUR, 1);
		newEvent = new Event("", now, cal.getTime()); 
	}
	
	@Log
	public void onValidateFromForm() {
		if (newEvent.getName() == null || newEvent.getName().isEmpty()) {
			form.recordError(name, "Name must not be empty!");
		}
		if (newEvent.getEnd().before(newEvent.getStart())) {
			form.recordError(endTime, "End of event is before start");
		}
		Calendar now = Calendar.getInstance(currentLocale);
		if (newEvent.getStart().before(now.getTime())) {
			form.recordError(startTime, "Start-time has to be in the future");
		}
		if (newEvent.getRoom() == null) {
			form.recordError(room, "You have to select a room!");
		}
		if (newEvent.getTenant() == null) {
			form.recordError(tenant, "You have to select a tenant!");
		}
	}
	
	@Log
	public void onSuccess() {
		persistance.save(newEvent);
		logger.info("Saved event!");
		componentResources.discardPersistentFieldChanges();
	}
}
