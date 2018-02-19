package com.privatesecuredata.eventplanner.components;

import java.util.List;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Log;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.Grid;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;
import org.slf4j.Logger;

import com.privatesecuredata.eventplanner.entities.Room;
import com.privatesecuredata.eventplanner.pages.Rooms;
import com.privatesecuredata.eventplanner.services.PersistanceService;

public class RoomList
{
	@Inject
	private Logger logger;

	@Inject
	private PersistanceService persistance;
	
	@Property
	private Room room;
	
	@Persist
	@Property 
	private Boolean deleteSucceeded;
	
	@InjectComponent
    private Zone listzone;
	
	@Inject
    private Request request;
	
	@Inject
    private AjaxResponseRenderer ajaxResponseRenderer;
	
	public void setupRender() {
		if (null == deleteSucceeded)
			deleteSucceeded = true;
		
	}
	
	public List<Room> getRooms() {
		return persistance.loadRooms();
	}
	
	@Log
	public Class onDel(Room r) {
		deleteSucceeded = persistance.delete(r);
		if (deleteSucceeded) logger.info("deleted room!");
		
		if (deleteSucceeded && request.isXHR()) {
            ajaxResponseRenderer.addRender(listzone);
            return null;
        }
		
		return Rooms.class;
	}
}