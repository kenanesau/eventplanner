package com.privatesecuredata.eventplanner.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.tapestry5.beaneditor.NonVisual;
import org.apache.tapestry5.beaneditor.Validate;

@Entity
public class Tenant {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonVisual
	public long id;

//	@Validate("required")
	private String name;
	
//	@Validate("required, regexp")
	private String email;
	
//	@Validate("required, regexp")
	private String phone;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
