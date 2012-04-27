package se.lingonskogen.em2012.services.impl;

import se.lingonskogen.em2012.services.UserService;

public class UserServiceImpl implements UserService {

	private UserDao userDao;

	public String fetchUser(String userName) {
		String user = userDao.fetchUser(userName);
		return user;
	}
	
	public void setUserDao(final UserDao userDao) {
		this.userDao = userDao;
	}
	
}
