package se.lingonskogen.em2012.domain;

public class Tournament extends Bean {
	public static final String NAME = "name";

	public static final String WINNERTEAM = "winnerteam";
	
	private String name;
	
	private String winnerTeamId;

	public String getWinnerTeamId() {
		return winnerTeamId;
	}

	public void setWinnerTeamId(String winnerTeamId) {
		this.winnerTeamId = winnerTeamId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
