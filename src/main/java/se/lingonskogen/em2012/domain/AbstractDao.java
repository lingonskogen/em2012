package se.lingonskogen.em2012.domain;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;

public abstract class AbstractDao<T extends Bean> {
    
    private final static Logger LOG = Logger.getLogger(AbstractDao.class.getName());
    
	protected abstract void populateEntity(Entity entity, T bean);

    protected abstract String getType();

    protected abstract Logger getLogger();

	protected abstract T createBean(Entity entity);

	protected void create(List<Key> keys, List<T> beans) throws DaoException {
        DatastoreService store = DatastoreServiceFactory.getDatastoreService();
        Transaction trx = store.beginTransaction();
        Map<Key, Entity> map = store.get(keys);
        if (map.size() != 0)
        {
            throw new DaoException("Key is not unique");
        }
        List<Entity> entities = new ArrayList<Entity>(keys.size());
        for (int i = 0; i < keys.size(); i++)
        {
            Entity entity = new Entity(keys.get(i));
            populateEntity(entity, beans.get(i));
        }
        store.put(entities);
        trx.commit();
        clearCache(keys);
	}
	
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

		clearCache(key.getKind());
	}

	protected void update(List<Key> keys, List<T> beans) throws DaoException {
        DatastoreService store = DatastoreServiceFactory.getDatastoreService();
        Map<Key, Entity> entityMap = store.get(keys);
        LOG.info("found " + entityMap.size() + ", expected: " + keys.size());
        if (entityMap.size() != keys.size()) {
            throw new DaoException("Key is not found");
        }
        List<Entity> entities = new ArrayList<Entity>(entityMap.values());
        for (int i = 0; i < keys.size(); i++)
        {
            populateEntity(entities.get(i), beans.get(i));
        }
        store.put(entities);

        clearCache(keys);
	    
	}
	
	protected void update(Key key, T bean) throws DaoException {
		DatastoreService store = DatastoreServiceFactory.getDatastoreService();
        Transaction trx = store.beginTransaction();
        Entity entity = null;
		try {
			entity = store.get(key);
		} catch (EntityNotFoundException e) {
			throw new DaoException("Key is not found");
		}
		populateEntity(entity, bean);
		store.put(entity);
		trx.commit();
        clearCache(key.getKind());
	}

	protected void delete(List<Key> keys) throws DaoException {
        DatastoreService store = DatastoreServiceFactory.getDatastoreService();
        Map<Key, Entity> entityMap = store.get(keys);
        if (entityMap.size() != keys.size()) {
            throw new DaoException("Key is not found");
        }
	    store.delete(keys);
	}
	
	protected void delete(Key key) throws DaoException {
		DatastoreService store = DatastoreServiceFactory.getDatastoreService();
		getLogger().info("Delete " + getType());
		try {
			store.get(key);
		} catch (EntityNotFoundException e) {
			throw new DaoException("Key is not found");
		}
		store.delete(key);

        clearCache(key.getKind());
	}

    protected T find(Key key) throws DaoException {
        List<Entity> entities = getAllEntities(key.getKind());
        for (Entity entity : entities)
        {
            if (entity.getKey().equals(key))
            {
                return createBean(entity);
            }
        }
        throw new DaoException("Key is not found");
	}

	protected List<T> findAll(String kind, Key parentKey) {
        List<Entity> entities = getAllEntities(kind);
        List<T> list = new ArrayList<T>();
        for (Entity entity : entities) {
            if (isMatch(entity.getParent(), parentKey))
            {
                T bean = createBean(entity);
                list.add(bean);
            }
        }
		return list;
	}

    private List<Entity> getAllEntities(String kind)
    {
        String cacheKey = kind + "_list";
        MemcacheService cache = MemcacheServiceFactory.getMemcacheService();
        if (!cache.contains(cacheKey))
        {
            LOG.info("findAll(" + kind + ") " + cacheKey);
            Query query = new Query(kind);
            DatastoreService store = DatastoreServiceFactory.getDatastoreService();
            PreparedQuery preparedQuery = store.prepare(query);
            List<Entity> entities = preparedQuery.asList(FetchOptions.Builder.withDefaults());
            cache.put(cacheKey, entities);
        }
        List<Entity> entities = (List<Entity>) cache.get(cacheKey);
        return entities;
    }

	private boolean isMatch(Key entityKey, Key parentKey)
    {
	    if (parentKey == null)
	    {
	        return true;
	    }
	    while (entityKey != null)
	    {
	        if (entityKey.equals(parentKey))
	        {
	            return true;
	        }
	        entityKey = entityKey.getParent();
	    }
        return false;
    }

    protected String urlify(String text) {
	    text = Normalizer.normalize(text, Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+","");
		text = text.trim().toLowerCase().replaceAll("[^a-z0-9]", "-");
		return text;
	}
	
    private void clearCache(List<Key> keys)
    {
        if (keys.size() > 0)
        {
            clearCache(keys.get(0).getKind());
        }
    }

    private void clearCache(String kind)
    {
        LOG.info("clear( " + kind + ")");
        MemcacheService cache = MemcacheServiceFactory.getMemcacheService();
        cache.delete(kind + "_list");
    }

}
