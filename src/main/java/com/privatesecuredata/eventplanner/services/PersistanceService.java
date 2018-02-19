package com.privatesecuredata.eventplanner.services;

import java.util.Date;
import java.util.List;

import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.hibernate.Criteria;

import com.privatesecuredata.eventplanner.entities.Event;
import com.privatesecuredata.eventplanner.entities.Room;
import com.privatesecuredata.eventplanner.entities.Tenant;

public interface PersistanceService {
	<T> List<T> load(Class<T> type);
	<T> List<T> load(Class<T> type, Criteria crit);
	
	@CommitAfter
    <T> boolean save(T obj);
	
	@CommitAfter
    <T> boolean delete(T obj);
    List<Tenant> loadTenants();
    List<Room> loadRooms();
    List<Event> loadEvents();
	List<Event> loadEvents(Date date);

	
}
