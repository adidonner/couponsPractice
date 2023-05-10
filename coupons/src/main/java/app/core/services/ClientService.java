package app.core.services;

import javax.security.auth.login.LoginException;

public abstract class ClientService {

	public boolean login(String email, String password) throws LoginException {
		return false;
	}
}
