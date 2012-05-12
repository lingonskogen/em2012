package se.lingonskogen.em2012.services;

import java.util.Date;
import java.util.List;

import se.lingonskogen.em2012.domain.DaoException;
import se.lingonskogen.em2012.domain.Game;

public interface GameService {
	String createGame(final Game game) throws DaoException;
	Game newInstance(final String tournamentId, final String homeTeamId, final String awayTeamId, 
					final Date kickoff, final Integer homeScore, final Integer awayScore);
	List<Game> getAvailableGames();
}
