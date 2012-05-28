package se.lingonskogen.em2012.domain;

import java.util.List;
import java.util.logging.Logger;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.KeyFactory.Builder;

public class PredictionDao extends AbstractDao<Prediction> {

	private Logger LOG = Logger.getLogger(PredictionDao.class.getName());
	
	@Override
	protected void populateEntity(Entity entity, Prediction prediction) {
		entity.setProperty(Prediction.TOURNAMENT, prediction.getTournamentId());
		entity.setProperty(Prediction.GAME, prediction.getGameId());
		entity.setProperty(Prediction.HOMESCORE, prediction.getHomeScore());
		entity.setProperty(Prediction.AWAYSCORE, prediction.getAwayScore());
	}

	@Override
	protected Prediction createBean(Entity entity) {
		Prediction prediction = new Prediction();
		prediction.setId(entity.getKey().getName());
		prediction.setGroupId(entity.getParent().getParent().getParent()
				.getName());
		prediction.setUserId(entity.getParent().getParent().getName());
		prediction.setCouponId(entity.getParent().getName());
		prediction.setTournamentId((String) entity
				.getProperty(Prediction.TOURNAMENT));
		prediction.setGameId((String) entity.getProperty(Prediction.GAME));
		prediction.setHomeScore((Long) entity
				.getProperty(Prediction.HOMESCORE));
		prediction.setAwayScore((Long) entity
				.getProperty(Prediction.AWAYSCORE));
		return prediction;
	}

	public String create(Prediction prediction) throws DaoException {
		String predictionId = createId(prediction);
		Key key = createKey(prediction.getGroupId(), prediction.getUserId(),
				prediction.getCouponId(), predictionId);
		super.create(key, prediction);
		return predictionId;
	}

	public void update(Prediction prediction) throws DaoException {
		String predictionId = createId(prediction);
		Key key = createKey(prediction.getGroupId(), prediction.getUserId(),
				prediction.getCouponId(), predictionId);
		super.update(key, prediction);
	}

	public void delete(Prediction prediction) throws DaoException {
		String predictionId = createId(prediction);
		Key key = createKey(prediction.getGroupId(), prediction.getUserId(),
				prediction.getCouponId(), predictionId);
		super.delete(key);
	}

	public Prediction find(String groupId, String userId, String couponId,
			String predictionId) throws DaoException {
		Key key = createKey(groupId, userId, couponId, predictionId);
		Prediction prediction = super.find(key);
		return prediction;
	}

	public List<Prediction> findAll() {
		List<Prediction> list = super.findAll(Prediction.class.getSimpleName(),
				null);
		return list;
	}

	public List<Prediction> findAll(String groupId) {
		Key key = createKey(groupId);
		List<Prediction> list = super.findAll(Prediction.class.getSimpleName(),
				key);
		return list;
	}

	public List<Prediction> findAll(String groupId, String userId) {
		Key key = createKey(groupId, userId);
		List<Prediction> list = super.findAll(Prediction.class.getSimpleName(),
				key);
		return list;
	}

	public List<Prediction> findAll(String groupId, String userId,
			String couponId) {
		Key key = createKey(groupId, userId, couponId);
		List<Prediction> list = super.findAll(Prediction.class.getSimpleName(),
				key);
		return list;
	}

	private String createId(Prediction prediction) {
		return prediction.getGameId();
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

	private Key createKey(String groupId, String userId, String couponId) {
		Builder builder = new KeyFactory.Builder(Group.class.getSimpleName(),
				groupId);
		builder.addChild(User.class.getSimpleName(), userId);
		builder.addChild(Coupon.class.getSimpleName(), couponId);
		return builder.getKey();
	}

	private Key createKey(String groupId, String userId, String couponId,
			String predictionId) {
		Builder builder = new KeyFactory.Builder(Group.class.getSimpleName(),
				groupId);
		builder.addChild(User.class.getSimpleName(), userId);
		builder.addChild(Coupon.class.getSimpleName(), couponId);
		builder.addChild(Prediction.class.getSimpleName(), predictionId);
		return builder.getKey();
	}
	@Override
	protected Logger getLogger() {
		return LOG;
	}

	@Override
	protected String getType() {
		return "Prediction";
	}

}
