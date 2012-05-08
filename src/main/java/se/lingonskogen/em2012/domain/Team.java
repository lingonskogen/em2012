package se.lingonskogen.em2012.domain;

public class Team extends Bean {
	public static final String NAME = "name";

	private String tournamentId;

	private String name;

	public String getTournamentId() {
		return tournamentId;
	}

	public void setTournamentId(String tournamentId) {
		this.tournamentId = tournamentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
