package se.lingonskogen.em2012.services;

import java.util.List;

import org.springframework.stereotype.Service;

import se.lingonskogen.em2012.domain.Group;

@Service
public interface GroupService {
	
	String createGroup(final Group group);
	Group newInstance(final String name);
	List<Group> getAvailableGroups();
}
