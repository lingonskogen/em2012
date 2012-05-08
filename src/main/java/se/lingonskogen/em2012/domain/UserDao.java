package se.lingonskogen.em2012.domain;

import java.util.List;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.KeyFactory.Builder;

public class UserDao extends AbstractDao<User> {
	@Override
	protected void populateEntity(Entity entity, User user) {
		entity.setProperty(User.REALNAME, user.getRealName());
		entity.setProperty(User.USERNAME, user.getUserName());
		entity.setProperty(User.PASSWORD, user.getPassword());
	}

	@Override
	protected User createBean(Entity entity) {
		User user = new User();
		user.setId(entity.getKey().getName());
		user.setGroupId(entity.getParent().getName());
		user.setRealName((String) entity.getProperty(User.REALNAME));
		user.setUserName((String) entity.getProperty(User.USERNAME));
		user.setPassword((String) entity.getProperty(User.PASSWORD));
		return user;
	}

	public String create(User user) throws DaoException {
		String userId = createId(user);
		Key key = createKey(user.getGroupId(), userId);
		super.create(key, user);
		return userId;
	}

	public void update(User user) throws DaoException {
		String userId = createId(user);
		Key key = createKey(user.getGroupId(), userId);
		super.update(key, user);
	}

	public void delete(User user) throws DaoException {
		String userId = createId(user);
		Key key = createKey(user.getGroupId(), userId);
		super.delete(key);
	}

	public User find(String groupId, String userId) throws DaoException {
		Key key = createKey(groupId, userId);
		User user = super.find(key);
		return user;
	}

	public List<User> findAll() {
		List<User> list = super.findAll(User.class.getSimpleName(), null);
		return list;
	}

	public List<User> findAll(String groupId) {
		Key key = createKey(groupId);
		List<User> list = super.findAll(User.class.getSimpleName(), key);
		return list;
	}

	private String createId(User user) {
		return urlify(user.getUserName());
	}

	private Key createKey(String groupId) {
		Builder builder = new KeyFactory.Builder(Group.class.getSimpleName(),
				groupId);
		return builder.getKey();
	}

	private Key createKey(String groupId, String userId) {
		Builder builder = new KeyFactory.Builder(Group.class.getSimpleName(),
				groupId);
		builder.addChild(User.class.getSimpleName(), userId);
		return builder.getKey();
	}

}
