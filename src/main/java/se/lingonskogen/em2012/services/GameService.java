package se.lingonskogen.em2012.services;

import java.util.Date;
import java.util.List;

import se.lingonskogen.em2012.domain.DaoException;
import se.lingonskogen.em2012.domain.Game;

public interface GameService {
	String createGame(final Game game) throws DaoException;
	Game newInstance(final String tournamentId, final String homeTeamId, final String awayTeamId, 
					final Date kickoff, final Long homeScore, final Long awayScore);
	List<Game> getAvailableGames();
	Game getGame(final String tournamentId, final String gameId) throws DaoException;
}
