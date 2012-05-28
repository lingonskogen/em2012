package se.lingonskogen.em2012.domain;

import java.util.List;
import java.util.logging.Logger;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.KeyFactory.Builder;

public class GroupDao extends AbstractDao<Group> {
	
	private Logger LOG = Logger.getLogger(GroupDao.class.getName());
	
	@Override
	protected void populateEntity(Entity entity, Group bean) {
		entity.setProperty(Group.NAME, bean.getName());
	}

	@Override
	protected Group createBean(Entity entity) {
		Group group = new Group();
		group.setId(entity.getKey().getName());
		group.setName((String) entity.getProperty(Group.NAME));
		return group;
	}

	public String create(Group group) throws DaoException {
		String groupId = createId(group);
		Key key = createKey(groupId);
		super.create(key, group);
		return groupId;
	}

	public void update(Group group) throws DaoException {
		Key key = createKey(group.getId());
		super.update(key, group);
	}

	public void delete(Group group) throws DaoException {
		Key key = createKey(group.getId());
		super.delete(key);
	}

	public Group find(String groupId) throws DaoException {
		Key key = createKey(groupId);
		Group group = super.find(key);
		return group;
	}

	public List<Group> findAll() {
		List<Group> list = super.findAll(Group.class.getSimpleName(), null);
		return list;
	}

	private String createId(Group group) {
		return urlify(group.getName());
	}

	private Key createKey(String groupId) {
		Builder builder = new KeyFactory.Builder(Group.class.getSimpleName(),
				groupId);
		return builder.getKey();
	}
	@Override
	protected Logger getLogger() {
		return LOG;
	}

	@Override
	protected String getType() {
		return "Group";
	}

}
