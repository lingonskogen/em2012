package se.lingonskogen.em2012.services.impl;

import java.util.Collections;
import java.util.Comparator;
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

	public Game getGame(final String tournamentId, final String gameId) throws DaoException {
		return gameDao.find(tournamentId, gameId);
	}
	
	@Override
	public Game newInstance(final String tournamentId, final String homeTeamId, 
			final String awayTeamId, final Date kickoff, final Long homeScore, final Long awayScore) {
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

    @Override
    public List<Game> getAvailableGames(String tournamentId) {
        return gameDao.findAll(tournamentId);
    }

    public List<Game> getSortedAvailableGames() {
    	List<Game> games = getAvailableGames();
    	Collections.<Game>sort(games, new Comparator<Game>(){
            @Override
            public int compare(Game game0, Game game1)
            {
                return game0.getKickoff().compareTo(game1.getKickoff());
            }
        });
    	
    	return games;
    }
    
    public List<Game> getSortedAvailableGames(final String tournamentId) {
    	List<Game> games = getAvailableGames(tournamentId);
    	Collections.<Game>sort(games, new Comparator<Game>(){
            @Override
            public int compare(Game game0, Game game1)
            {
                return game0.getKickoff().compareTo(game1.getKickoff());
            }
        });
    	
    	return games;
    }    
	public void setGameDao(final GameDao gameDao) {
		this.gameDao = gameDao;
	}
}
