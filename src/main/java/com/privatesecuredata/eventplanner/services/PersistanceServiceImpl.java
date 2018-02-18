package com.privatesecuredata.eventplanner.services;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.privatesecuredata.eventplanner.entities.Room;
import com.privatesecuredata.eventplanner.entities.Tenant;

import antlr.debug.Event;

public class PersistanceServiceImpl implements PersistanceService {

	private Session session;
	
	private EntityManager em = null;
	
	public PersistanceServiceImpl() {
		Configuration configuration = new Configuration();
    	configuration.configure("hibernate.cfg.xml");
    	
    	try {
	    	registerAnnotatedEntities(configuration, "com.privateseucredata.eventplanner.entities");
	    	System.out.println("Hibernate Annotation Configuration loaded");
	    	
	    	ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
	    	System.out.println("Hibernate Annotation serviceRegistry created");
	    	
	    	SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	    	session = sessionFactory.openSession();
    	}
    	catch (Exception e) {
    		throw new RuntimeException("Error setting up hibernate", e);
    	}
    	
	}
	
	private void registerAnnotatedEntities(Configuration cfg, String packageName) throws IOException, Exception {
		/* FIXME: do this in a more general way ... */
		
		cfg.addAnnotatedClass(Room.class);
		cfg.addAnnotatedClass(Tenant.class);
		cfg.addAnnotatedClass(Event.class);
	}

	protected EntityManager getEm() {
		if (null == this.em)
			this.em = session.getEntityManagerFactory().createEntityManager();
		
		return this.em;
	}
	
	@Override
	public <T> List<T> load(Class<T> type) {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<T> query = builder.createQuery(type);
		Root<T> root = query.from(type);
		query.select(root);
		TypedQuery<T> q = getEm().createQuery(query);
		List<T> lst =  q.getResultList();
		return lst;
	}

	@Override
	public <T> void save(T obj) {
		Transaction trans = null;
		try {
			trans = session.beginTransaction();
			session.save(obj);
			trans.commit();
		}
		catch (RuntimeException e) {
			if (null != trans) trans.rollback();
			throw e;
		}
	}
	
	@Override
	public <T> void delete(T obj) {
		Transaction trans = null;
		try {
			trans = session.beginTransaction();
			session.delete(obj);
			trans.commit();
		}
		catch (RuntimeException e) {
			if (null != trans) trans.rollback();
			throw e;
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
