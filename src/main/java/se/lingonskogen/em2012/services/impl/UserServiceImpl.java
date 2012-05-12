package se.lingonskogen.em2012.services.impl;

import java.util.List;

import se.lingonskogen.em2012.domain.DaoException;
import se.lingonskogen.em2012.domain.GroupType;
import se.lingonskogen.em2012.domain.User;
import se.lingonskogen.em2012.domain.UserDao;
import se.lingonskogen.em2012.services.UserService;

public class UserServiceImpl implements UserService {

	private UserDao userDao;

	public User getUser(final String groupId, final String userId) {
		try {
			return userDao.find(groupId, userId);
		} catch (DaoException e) {
			return null;
		}
	}
	
	public User newInstance(final GroupType groupType, final String password, final String realName, final String userName) {
		User user = new User();
		user.setGroupId(groupType.toString());
		user.setPassword(password);
		user.setRealName(realName);
		user.setUserName(userName);
		return user;
	}
	
	public String createUser(final User user) throws DaoException {
			return userDao.create(user);
	}

	public String getUserName(final String userId) {
		String name = "";
		
		for(User user : userDao.findAll()) {
			if(user.getId().equals(userId)) {
				name = user.getRealName();
			}
		}
		return name;
	}
	

	public void delete(final String groupId, final String userId) throws DaoException {
		User user = getUser(groupId, userId);
		userDao.delete(user);
	}
	
	public void setUserDao(final UserDao userDao) {
		this.userDao = userDao;
	}

	public List<User> getUsers(final String groupId) {
		return userDao.findAll(groupId);
	}
	
	public List<User> getUsers() {
		return userDao.findAll();
	}	
}
