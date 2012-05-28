package cache;

import java.util.List;

import se.lingonskogen.em2012.domain.Game;

public class GameCache extends AbstractCache {

	List<Game> gamesWithResults;
	
	
	public List<Game> getGamesWithResults() {
		if(gamesWithResults == null || gamesWithResults.isEmpty()) {
			gamesWithResults = getGamesWithResults(getTournamentService().getAvailableTournaments().get(0).getId());
		}
		return gamesWithResults;
	}
	
	public void clearCache() {
		super.clearCache();
		gamesWithResults = null;		
	}
	
	private List<Game> getGamesWithResults(final String tournamentId) {
		List<Game> games = getGameService().getAvailableGames(tournamentId);

		for (Game game : games) {
			if(game.getAwayScore() != null && game.getHomeScore() != null) {
				gamesWithResults.add(game);
			}
		}
		
		return games;
	}
	
}
