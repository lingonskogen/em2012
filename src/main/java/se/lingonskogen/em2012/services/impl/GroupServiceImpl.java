package se.lingonskogen.em2012.services.impl;

import java.util.List;

import se.lingonskogen.em2012.domain.DaoException;
import se.lingonskogen.em2012.domain.Group;
import se.lingonskogen.em2012.domain.GroupDao;
import se.lingonskogen.em2012.services.GroupService;

public class GroupServiceImpl implements GroupService {

	private GroupDao groupDao;
	
	@Override
	public String createGroup(Group group) throws DaoException {
		return groupDao.create(group);
	}

	public Group newInstance(final String name) {
		Group group = new Group();
		group.setName(name);
		
		return group;
	}
	
	public void delete(final String groupId) throws DaoException {
		Group group = groupDao.find(groupId);
		groupDao.delete(group);
	}
	
	public List<Group> getAvailableGroups() {
		return groupDao.findAll();
	}
	
	public String getGroupName(final String groupId) {
		Group group;
		try {
			group = groupDao.find(groupId);
		} catch (DaoException e) {
			return null;
		}
		return group.getName();
	}
	public void setGroupDao(final GroupDao groupDao) {
		this.groupDao = groupDao;
	}
}
