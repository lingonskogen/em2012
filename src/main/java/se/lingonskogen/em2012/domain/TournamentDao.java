package se.lingonskogen.em2012.domain;

import java.util.List;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.KeyFactory.Builder;

public class TournamentDao extends AbstractDao<Tournament> {
	@Override
	protected void populateEntity(Entity entity, Tournament bean) {
		entity.setProperty(Tournament.NAME, bean.getName());
	}

	@Override
	protected Tournament createBean(Entity entity) {
		Tournament tournament = new Tournament();
		tournament.setId(entity.getKey().getName());
		tournament.setName((String) entity.getProperty(Tournament.NAME));
		return tournament;
	}

	public String create(Tournament tournament) throws DaoException {
		String tournamentId = createId(tournament);
		Key key = createKey(tournamentId);
		super.create(key, tournament);
		return tournamentId;
	}

	public void update(Tournament tournament) throws DaoException {
		String tournamentId = createId(tournament);
		Key key = createKey(tournamentId);
		super.update(key, tournament);
	}

	public void delete(Tournament tournament) throws DaoException {
		String tournamentId = createId(tournament);
		Key key = createKey(tournamentId);
		super.delete(key);
	}

	public Tournament find(String tournamentId) throws DaoException {
		Key key = createKey(tournamentId);
		Tournament tournament = super.find(key);
		return tournament;
	}

	public List<Tournament> findAll() {
		List<Tournament> list = super.findAll(Tournament.class.getSimpleName(),
				null);
		return list;
	}

	private String createId(Tournament tournament) {
		return urlify(tournament.getName());
	}

	private Key createKey(String tournamentId) {
		Builder builder = new KeyFactory.Builder(
				Tournament.class.getSimpleName(), tournamentId);
		return builder.getKey();
	}

}
