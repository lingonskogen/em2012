package se.lingonskogen.em2012.services;

import java.util.List;

import org.springframework.stereotype.Service;

import se.lingonskogen.em2012.domain.Group;
import se.lingonskogen.em2012.domain.User;

@Service
public interface RegisterService {
	
	void registerUser(final User user) throws Exception;
	
	User newInstance(final String realName, final String userName, final String password, final String groupId);
	
	List<Group> getAvailableGroups();
}
