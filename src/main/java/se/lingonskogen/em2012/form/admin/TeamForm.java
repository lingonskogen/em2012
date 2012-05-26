package se.lingonskogen.em2012.form.admin;

import org.hibernate.validator.constraints.NotEmpty;

public class TeamForm {
	@NotEmpty
	private String tournamentId;
	@NotEmpty
	private String name;
	@NotEmpty
	private String code;
	
	public String getTournamentId() {
		return tournamentId;
	}

	public void setTournamentId(final String tournamentId) {
		this.tournamentId = tournamentId;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}
	
	public void setCode(final String code) {
		this.code = code;
	}
	public String getCode() {
		return code;
	}
}
