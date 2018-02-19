package com.privatesecuredata.eventplanner.services;

public class AuthenticationServiceImpl implements AuthenticationService {

	private boolean isAuthenticated = false;
	
	@Override
	public boolean isAuthenticated() {
		return isAuthenticated;
	}

	@Override
	public boolean authenticate(String username, String password) {
		isAuthenticated = true; //(username.equals("kenan.esau@conan.de") &&
							//password.equals("givemeajob"));

		return isAuthenticated;
	}
}
