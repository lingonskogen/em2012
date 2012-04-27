package se.lingonskogen.em2012.services.impl;

import se.lingonskogen.em2012.services.RegisterService;
import se.lingonskogen.em2012.services.UserService;

public class RegisterServiceImpl implements RegisterService {

	private UserService userService;

	public void registerUser() throws Exception {
	
		
	}	
	
	public void setUserService(final UserService userService) {
		this.userService = userService;
	}
}
