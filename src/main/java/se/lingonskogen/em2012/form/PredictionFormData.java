package se.lingonskogen.em2012.form;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

public class PredictionFormData {

	private String gameId;
	private Date kickoff;
	private String homeTeamName;
	private String awayTeamName;

	@NotEmpty
	@NumberFormat(style = Style.NUMBER)
	@Min(0)
	private Long homeScore;

    @NotEmpty
    @NumberFormat(style = Style.NUMBER)
    @Min(0)
	private Long awayScore;

	public PredictionFormData() {}
	
	public PredictionFormData(final Long homeScore, final Long awayScore) {
		this.homeScore = homeScore;
		this.awayScore = awayScore;
	}

	public PredictionFormData(final String gameId, final Date kickoff,
			final String homeTeamName, final String awayTeamName,
			final Long homeScore, final Long awayScore) {
		this.gameId = gameId;
		this.homeScore = homeScore;
		this.awayScore = awayScore;
		this.kickoff = kickoff;
		this.homeTeamName = homeTeamName;
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

	public String getKickoff() {
	    if (kickoff == null)
	    {
	        return null;
	    }
		SimpleDateFormat format = new SimpleDateFormat("d/M HH:mm");
		return format.format(kickoff);
	}

	public Date getKickoffDate() {
		return kickoff;
	}

	public void setKickoff(Date kickoff) {
		this.kickoff = kickoff;
	}

	public String getHomeTeamName() {
		return homeTeamName;
	}

	public void setHomeTeamName(String homeTeamName) {
		this.homeTeamName = homeTeamName;
	}

	public String getAwayTeamName() {
		return awayTeamName;
	}

	public void setAwayTeamName(String awayTeamName) {
		this.awayTeamName = awayTeamName;
	}

	@Override
	public String toString() {
		return homeScore + "-" + awayScore;
	}
}
