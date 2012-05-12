package se.lingonskogen.em2012.services.impl;

import java.util.Date;
import java.util.List;

import se.lingonskogen.em2012.domain.DaoException;
import se.lingonskogen.em2012.domain.Game;
import se.lingonskogen.em2012.domain.GameDao;
import se.lingonskogen.em2012.services.GameService;

public class GameServiceImpl implements GameService {

	private GameDao gameDao;
	
	@Override
	public String createGame(final Game game) throws DaoException {
		return gameDao.create(game);
	}

	@Override
	public Game newInstance(final String tournamentId, final String homeTeamId, 
			final String awayTeamId, final Date kickoff, final Integer homeScore, final Integer awayScore) {
		Game game = new Game();
		game.setAwayScore(awayScore);
		game.setAwayTeamId(awayTeamId);
		game.setHomeScore(homeScore);
		game.setHomeTeamId(homeTeamId);
		game.setKickoff(kickoff);
		game.setTournamentId(tournamentId);
		return game;
	}

	@Override
	public List<Game> getAvailableGames() {
		return gameDao.findAll();
	}

	public void setGameDao(final GameDao gameDao) {
		this.gameDao = gameDao;
	}
}
