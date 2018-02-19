package com.privatesecuredata.eventplanner.services;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.tapestry5.hibernate.HibernateSessionManager;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import com.privatesecuredata.eventplanner.entities.Event;
import com.privatesecuredata.eventplanner.entities.Room;
import com.privatesecuredata.eventplanner.entities.Tenant;

public class PersistanceServiceImpl implements PersistanceService {

	@Inject
	private Session session;
	
	@Inject
	private HibernateSessionManager manager;
	
	@Override
	public <T> List<T> load(Class<T> type) {
		Criteria crit = session.createCriteria(type);
		return load(type, crit);
	}
	
	@Override
	public <T> List<T> load(Class<T> type, Criteria crit) {
		return crit.list();
	}

	@Override
	public <T> boolean save(T obj) {
		boolean result = true;
		try {
			session.save(obj);
			manager.commit();
		}
		catch (Exception e) {
			manager.abort();
			result = false;
		}
		
		return result;
	}
	
	@Override
	public <T> boolean delete(T obj) {
		boolean result = true;
		try {
			session.delete(obj);
			manager.commit();
		}
		catch (Exception e) {
			manager.abort();
			result = false;
		}
		
		return result;
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
	
	@Override
	public List<Event> loadEvents(Date date) {
		Criteria crit = session.createCriteria(Event.class);
		crit.addOrder(Order.asc("start"));
		List<Event> lst = load(Event.class, crit);
		return lst.stream()
			.filter( ev -> ev.getEnd().after(date)  ) 
			.collect(Collectors.toList());
	}
}
