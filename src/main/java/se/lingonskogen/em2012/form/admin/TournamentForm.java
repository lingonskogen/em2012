package se.lingonskogen.em2012.form.admin;

import org.hibernate.validator.constraints.NotEmpty;

public class TournamentForm {
	@NotEmpty
	private String name = null;
	
	private String id;
	private String winnerTeamId;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWinnerTeamId() {
		return winnerTeamId;
	}

	public void setWinnerTeamId(String winnerTeamId) {
		this.winnerTeamId = winnerTeamId;
	}

	public void setName(final String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}