package se.lingonskogen.em2012.form;

import java.util.List;

import se.lingonskogen.em2012.domain.Team;


public class CouponForm {
	
	List<PredictionFormData> predictions;

	private List<Team> teams;
	
	private String winnerTeamId;
	
	public List<PredictionFormData> getPredictions() {
		return predictions;
	}

	public void setPredictions(List<PredictionFormData> predictions) {
		this.predictions = predictions;
	}

    public void setTeams(List<Team> teams)
    {
        this.teams = teams;
    }

    public List<Team> getTeams()
    {
        return teams;
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
