package se.lingonskogen.em2012.domain;

import java.util.Date;

public class Game extends Bean {
	public static final String HOMETEAM = "hometeam";

	public static final String AWAYTEAM = "awayteam";

	public static final String KICKOFF = "kickoff";

	public static final String HOMESCORE = "homeScore";

	public static final String AWAYSCORE = "awayScore";

	private String tournamentId;

	private String homeTeamId;

	private String awayTeamId;

	private Date kickoff;

	private Long homeScore;

	private Long awayScore;

	public String getTournamentId() {
		return tournamentId;
	}

	public void setTournamentId(String tournamentId) {
		this.tournamentId = tournamentId;
	}

	public String getHomeTeamId() {
		return homeTeamId;
	}

	public void setHomeTeamId(String homeTeamId) {
		this.homeTeamId = homeTeamId;
	}

	public String getAwayTeamId() {
		return awayTeamId;
	}

	public void setAwayTeamId(String awayTeamId) {
		this.awayTeamId = awayTeamId;
	}

	public Date getKickoff() {
		return kickoff;
	}

	public void setKickoff(Date kickoff) {
		this.kickoff = kickoff;
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

}
