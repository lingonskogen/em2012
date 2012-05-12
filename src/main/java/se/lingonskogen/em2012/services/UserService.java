package se.lingonskogen.em2012.services;

import java.util.List;

import org.springframework.stereotype.Service;

import se.lingonskogen.em2012.domain.DaoException;
import se.lingonskogen.em2012.domain.GroupType;
import se.lingonskogen.em2012.domain.User;

@Service
public interface UserService {
	
	String createUser(final User user) throws DaoException;
	User newInstance(final GroupType groupType, final String password, final String realName, final String userName);
	
	String getUserName(final String userId);
	void delete(final String groupId, final String userId) throws DaoException;
	
	User getUser(final String groupId, final String userId);
	List<User> getUsers(final String groupId);
	List<User> getUsers();
}
