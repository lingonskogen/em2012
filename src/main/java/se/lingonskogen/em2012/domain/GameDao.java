package se.lingonskogen.em2012.domain;

import java.util.Date;
import java.util.List;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.KeyFactory.Builder;

public class GameDao extends AbstractDao<Game> {
	@Override
	protected void populateEntity(Entity entity, Game game) {
		entity.setProperty(Game.HOMETEAM, game.getHomeTeamId());
		entity.setProperty(Game.AWAYTEAM, game.getAwayTeamId());
		entity.setProperty(Game.KICKOFF, game.getKickoff());
		entity.setProperty(Game.HOMESCORE, game.getHomeScore());
		entity.setProperty(Game.AWAYSCORE, game.getAwayScore());
	}

	@Override
	protected Game createBean(Entity entity) {
		Game game = new Game();
		game.setId(entity.getKey().getName());
		game.setTournamentId(entity.getParent().getName());
		game.setHomeTeamId((String) entity.getProperty(Game.HOMETEAM));
		game.setAwayTeamId((String) entity.getProperty(Game.AWAYTEAM));
		game.setKickoff((Date) entity.getProperty(Game.KICKOFF));
		game.setHomeScore((Integer) entity.getProperty(Game.HOMESCORE));
		game.setAwayScore((Integer) entity.getProperty(Game.AWAYSCORE));
		return game;
	}

	public String create(Game game) throws DaoException {
		String gameId = createId(game);
		Key key = createKey(game.getTournamentId(), gameId);
		super.create(key, game);
		return gameId;
	}

	public void update(Game game) throws DaoException {
		String gameId = createId(game);
		Key key = createKey(game.getTournamentId(), gameId);
		super.update(key, game);
	}

	public void delete(Game game) throws DaoException {
		String gameId = createId(game);
		Key key = createKey(game.getTournamentId(), gameId);
		super.delete(key);
	}

	public Game find(String tournamentId, String gameId) throws DaoException {
		Key key = createKey(tournamentId, gameId);
		Game game = super.find(key);
		return game;
	}

	public List<Game> findAll() {
		List<Game> list = super.findAll(Game.class.getSimpleName(), null);
		return list;
	}

	public List<Game> findAll(String tournamentId) {
		Key key = createKey(tournamentId);
		List<Game> list = super.findAll(Game.class.getSimpleName(), key);
		return list;
	}

	private String createId(Game game) {
		return urlify(game.getHomeTeamId() + " - " + game.getAwayTeamId());
	}

	private Key createKey(String tournamentId) {
		Builder builder = new KeyFactory.Builder(
				Tournament.class.getSimpleName(), tournamentId);
		return builder.getKey();
	}

	private Key createKey(String tournamentId, String gameId) {
		Builder builder = new KeyFactory.Builder(
				Tournament.class.getSimpleName(), tournamentId);
		builder.addChild(Game.class.getSimpleName(), gameId);
		return builder.getKey();
	}

}
