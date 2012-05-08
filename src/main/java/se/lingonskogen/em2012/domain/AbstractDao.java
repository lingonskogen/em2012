package se.lingonskogen.em2012.domain;

import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public abstract class AbstractDao<T extends Bean> {
	protected abstract void populateEntity(Entity entity, T bean);

	protected abstract T createBean(Entity entity);

	protected void create(Key key, T bean) throws DaoException {
		DatastoreService store = DatastoreServiceFactory.getDatastoreService();
		try {
			store.get(key);
			throw new DaoException("Key is not unique");
		} catch (EntityNotFoundException e) {
			// ok
		}
		Entity entity = new Entity(key);
		populateEntity(entity, bean);
		store.put(entity);
	}

	protected void update(Key key, T bean) throws DaoException {
		DatastoreService store = DatastoreServiceFactory.getDatastoreService();
		Entity entity = null;
		try {
			entity = store.get(key);
		} catch (EntityNotFoundException e) {
			throw new DaoException("Key is not found");
		}
		populateEntity(entity, bean);
		store.put(entity);

	}

	protected void delete(Key key) throws DaoException {
		DatastoreService store = DatastoreServiceFactory.getDatastoreService();
		try {
			store.get(key);
		} catch (EntityNotFoundException e) {
			throw new DaoException("Key is not found");
		}
		store.delete(key);
	}

	protected T find(Key key) throws DaoException {
		DatastoreService store = DatastoreServiceFactory.getDatastoreService();
		Entity entity = null;
		try {
			entity = store.get(key);
		} catch (EntityNotFoundException e) {
			throw new DaoException("Key is not found");
		}
		return createBean(entity);
	}

	protected List<T> findAll(String kind, Key parentKey) {
		Query query = parentKey != null ? new Query(kind, parentKey)
				: new Query(kind);
		DatastoreService store = DatastoreServiceFactory.getDatastoreService();
		PreparedQuery preparedQuery = store.prepare(query);
		List<T> list = new ArrayList<T>();
		for (Entity entity : preparedQuery.asIterable()) {
			T bean = createBean(entity);
			list.add(bean);
		}
		return list;
	}

	protected String urlify(String text) {
		// TODO urlify
		return text;
	}
}
