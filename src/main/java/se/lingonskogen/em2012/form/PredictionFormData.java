package se.lingonskogen.em2012.form;


public class PredictionFormData {

	private String gameId;
	private String homeTeamName;
	private String awayTeamName;
	private Long homeScore;
	private Long awayScore;

	
	public String getHomeTeamName() {
		return homeTeamName;
	}

	public void setHomeTeamName(String homeTeamName) {
		this.homeTeamName = homeTeamName;
	}

	public String getAwayTeam() {
		return awayTeamName;
	}

	public void setAwayTeamName(String awayTeamName) {
		this.awayTeamName = awayTeamName;
	}

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public Long getHomeScore() {
		return homeScore;
	}

	public void setHomeScore(Long homeScore) {
		this.homeScore = homeScore;
	}

	public Long getAwayScore() {
		return awayScore;
	}

	public void setAwayScore(Long awayScore) {
		this.awayScore = awayScore;
	}

	@Override
	public String toString() {
		return homeTeamName + " - " + awayTeamName + " : " + homeScore + "-" + awayScore;
	}
}
