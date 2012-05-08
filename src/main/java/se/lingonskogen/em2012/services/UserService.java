package se.lingonskogen.em2012.services;

import org.springframework.stereotype.Service;

import se.lingonskogen.em2012.domain.User;

@Service
public interface UserService {
	
	User fetchUser(final String group, final String userName);
	User createUser(final User user);
}
