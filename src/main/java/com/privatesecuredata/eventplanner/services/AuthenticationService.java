package com.privatesecuredata.eventplanner.services;

public interface AuthenticationService {
	boolean isAuthenticated();
	boolean authenticate(String username, String password);
	void logout();
}
