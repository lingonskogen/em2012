package se.lingonskogen.em2012.services.impl;

import se.lingonskogen.em2012.domain.DaoException;
import se.lingonskogen.em2012.domain.GroupType;
import se.lingonskogen.em2012.domain.User;
import se.lingonskogen.em2012.domain.UserDao;
import se.lingonskogen.em2012.services.UserService;

public class UserServiceImpl implements UserService {

	private UserDao userDao;

	public User fetchUser(final String group, final String userName) {
		try {
			return userDao.find(group, userName);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	private User newUserInstance(final GroupType groupType, final String password, final String realName, final String userName) {
		User user = new User();
		user.setGroupId(groupType.toString());
		user.setPassword(password);
		user.setRealName(realName);
		user.setUserName(userName);
		return user;
	}
	
	public User createUser(final User user) {
		//User user = newUserInstance(GroupType.ATELES,"testpwd", "Susen", "susgl");
		try {
			userDao.create(user);
			return user;
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public void setUserDao(final UserDao userDao) {
		this.userDao = userDao;
	}
	
}
