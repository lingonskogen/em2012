package se.lingonskogen.em2012.domain;

import java.util.List;
import java.util.logging.Logger;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.KeyFactory.Builder;

public class TeamDao extends AbstractDao<Team> {
	
	private Logger LOG = Logger.getLogger(TeamDao.class.getName());
	
	@Override
	protected void populateEntity(Entity entity, Team bean) {
		entity.setProperty(Team.NAME, bean.getName());
		entity.setProperty(Team.CODE, bean.getCode());
	}

	@Override
	protected Team createBean(Entity entity) {
		Team team = new Team();
		team.setId(entity.getKey().getName());
		team.setTournamentId(entity.getParent().getName());
		team.setName((String) entity.getProperty(Team.NAME));
		team.setCode((String) entity.getProperty(Team.CODE));
		return team;
	}

	public String create(Team team) throws DaoException {
		String teamId = createId(team);
		Key key = createKey(team.getTournamentId(), teamId);
		super.create(key, team);
		return teamId;
	}

	public void update(Team team) throws DaoException {
		String teamId = createId(team);
		Key key = createKey(team.getTournamentId(), teamId);
		super.update(key, team);
	}

	public void delete(Team team) throws DaoException {
		String teamId = createId(team);
		Key key = createKey(team.getTournamentId(), teamId);
		super.delete(key);
	}

	public Team find(String tournamentId, String teamId) throws DaoException {
		Key key = createKey(tournamentId, teamId);
		Team team = super.find(key);
		return team;
	}

	public List<Team> findAll() {
		List<Team> list = super.findAll(Team.class.getSimpleName(), null);
		return list;
	}

	public List<Team> findAll(String tournamentId) {
		Key key = createKey(tournamentId);
		List<Team> list = super.findAll(Team.class.getSimpleName(), key);
		return list;
	}

	private String createId(Team team) {
		return urlify(team.getName());
	}

	private Key createKey(String tournamentId) {
		Builder builder = new KeyFactory.Builder(
				Tournament.class.getSimpleName(), tournamentId);
		return builder.getKey();
	}

	private Key createKey(String tournamentId, String teamId) {
		Builder builder = new KeyFactory.Builder(
				Tournament.class.getSimpleName(), tournamentId);
		builder.addChild(Team.class.getSimpleName(), teamId);
		return builder.getKey();
	}
	@Override
	protected Logger getLogger() {
		return LOG;
	}

	@Override
	protected String getType() {
		return "Team";
	}

}
