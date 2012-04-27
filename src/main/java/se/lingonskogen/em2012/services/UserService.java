package se.lingonskogen.em2012.services;

import org.springframework.stereotype.Service;

@Service
public interface UserService {
	
	public String fetchUser(final String userName);

}
