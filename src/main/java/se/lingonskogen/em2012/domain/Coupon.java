package se.lingonskogen.em2012.domain;

public class Coupon extends Bean {
	public static final String TOURNAMENT = "tournament";

    public static final String WINNER = "winner";

    private String groupId;

	private String userId;

	private String tournamentId;

	private String winnerTeamId;
	
	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTournamentId() {
		return tournamentId;
	}

	public void setTournamentId(String tournamentId) {
		this.tournamentId = tournamentId;
	}

    public void setWinnerTeamId(String winnerTeamId)
    {
        this.winnerTeamId = winnerTeamId;
    }

    public String getWinnerTeamId()
    {
        return winnerTeamId;
    }

}
