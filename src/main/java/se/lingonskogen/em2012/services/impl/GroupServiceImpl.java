package se.lingonskogen.em2012.services.impl;

import java.util.List;

import se.lingonskogen.em2012.domain.DaoException;
import se.lingonskogen.em2012.domain.Group;
import se.lingonskogen.em2012.domain.GroupDao;
import se.lingonskogen.em2012.services.GroupService;

public class GroupServiceImpl implements GroupService {

	private GroupDao groupDao;
	
	@Override
	public String createGroup(Group group) {
		String id = null;
		try {
			id = groupDao.create(group);
			
		} catch (DaoException e) {
			return null;
		}
		return id;
	}

	public Group newInstance(final String name) {
		Group group = new Group();
		group.setName(name);
		
		return group;
	}
	
	public List<Group> getAvailableGroups() {
		return groupDao.findAll();
	}
	
	public void setGroupDao(final GroupDao groupDao) {
		this.groupDao = groupDao;
	}
}
