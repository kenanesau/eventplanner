package com.privatesecuredata.eventplanner.services;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.Session;

public class AuthenticationServiceImpl implements AuthenticationService {
	
	private static final String IS_AUTHENTICATED = "is_authenticated";

	@Inject
	private Request request;
	
	protected Boolean getAuthenticated() {
		Session httpSession = request.getSession(true);
		Boolean authenticated = (Boolean)httpSession.getAttribute(IS_AUTHENTICATED);
		if (null == authenticated) {
			authenticated = false;
			httpSession.setAttribute(IS_AUTHENTICATED, authenticated);
		}
		
		return authenticated;
	}
	
	protected void setAuthenticated(Boolean authenticated) {
		Session httpSession = request.getSession(true);
		httpSession.setAttribute(IS_AUTHENTICATED, authenticated);
	}
	
	@Override
	public boolean isAuthenticated() {
		return getAuthenticated();
	}

	@Override
	public boolean authenticate(String username, String password) {
		Boolean isAuthenticated = (username.equals("kenan.esau@conan.de") &&
									password.equals("givemeajob"));
		setAuthenticated(isAuthenticated);

		return isAuthenticated;
	}
	
	public void logout() {
		if (getAuthenticated())
			setAuthenticated(false);
	}
}
