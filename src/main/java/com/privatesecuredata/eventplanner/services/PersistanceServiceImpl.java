package com.privatesecuredata.eventplanner.services;

import java.util.List;

import org.apache.tapestry5.hibernate.HibernateSessionManager;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Criteria;
import org.hibernate.Session;

import com.privatesecuredata.eventplanner.entities.Room;
import com.privatesecuredata.eventplanner.entities.Tenant;

import antlr.debug.Event;

public class PersistanceServiceImpl implements PersistanceService {

	@Inject
	private Session session;
	
	@Inject
	private HibernateSessionManager manager;
	
	@Override
	public <T> List<T> load(Class<T> type) {
		Criteria crit = session.createCriteria(type);
		return crit.list();
	}

	@Override
	public <T> void save(T obj) {
		try {
			session.save(obj);
			manager.commit();
		}
		catch (Exception e) {
			manager.abort();
		}
	}
	
	@Override
	public <T> void delete(T obj) {
		try {
			session.delete(obj);
			manager.commit();
		}
		catch (Exception e) {
			manager.abort();
		}
	}
	
	@Override
	public List<Tenant> loadTenants() {
		return load(Tenant.class);
	}

	@Override
	public List<Room> loadRooms() {
		return load(Room.class);
	}
	
	@Override
	public List<Event> loadEvents() {
		return load(Event.class);
	}
}
