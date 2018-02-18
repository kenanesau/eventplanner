package com.privatesecuredata.eventplanner.services;

import java.util.List;

import com.privatesecuredata.eventplanner.entities.Room;
import com.privatesecuredata.eventplanner.entities.Tenant;

import antlr.debug.Event;

public interface PersistanceService {
	<T> List<T> load(Class<T> type);
    <T> void save(T obj);
    <T> void delete(T obj);
    List<Tenant> loadTenants();
    List<Room> loadRooms();
    List<Event> loadEvents();
}
