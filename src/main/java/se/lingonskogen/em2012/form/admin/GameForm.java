package se.lingonskogen.em2012.form.admin;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;

public class GameForm {
	@NotEmpty
	private String tournamentId;
	@NotEmpty
	private String homeTeamId;

	@NotEmpty
	private String awayTeamId;
	@NotNull
	private Date kickoff;
	@NumberFormat
	private Long homeScore;
	@NumberFormat
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
