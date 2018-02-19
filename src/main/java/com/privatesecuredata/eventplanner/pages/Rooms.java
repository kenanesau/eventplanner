package com.privatesecuredata.eventplanner.pages;

import org.apache.tapestry5.annotations.Log;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.slf4j.Logger;

import com.privatesecuredata.eventplanner.entities.Room;
import com.privatesecuredata.eventplanner.services.PersistanceService;

public class Rooms
{
	@Inject
	private Logger logger;

	@Inject
	PersistanceService persistance;

	@Property
	private Room newRoom;
	
	@Log
	void onSuccess()
	{
		persistance.save(newRoom);
		logger.info("saved new room!");
	}

}
