package com.privatesecuredata.eventplanner.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.tapestry5.beaneditor.NonVisual;

@Entity
@Table(name="tbl_evnt")
public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonVisual
	public long id;
	
	private String name;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "startTime")
	private Date start;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "endTime")
	private Date end;

	@OneToOne
	private Tenant tenant;
	
	@OneToOne
	private Room room;
	
	public Event() {}
	
	public Event(String name, Date start, Date end) {
		super();
	    this.start = start;
		this.end = end;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	public Tenant getTenant() {
		return tenant;
	}
	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}

	
}
