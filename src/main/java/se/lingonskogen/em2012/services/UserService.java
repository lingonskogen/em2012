package se.lingonskogen.em2012.services;

import java.util.List;

import org.springframework.stereotype.Service;

import se.lingonskogen.em2012.domain.DaoException;
import se.lingonskogen.em2012.domain.GroupType;
import se.lingonskogen.em2012.domain.User;

@Service
public interface UserService {
	
	User fetchUser(final String group, final String userName);
	String createUser(final User user) throws DaoException;
	List<User> getAvailableUsers();
	User newInstance(final GroupType groupType, final String password, final String realName, final String userName);
	String getUserName(final String userId);
}
