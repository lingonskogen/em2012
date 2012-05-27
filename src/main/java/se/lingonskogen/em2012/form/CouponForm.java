package se.lingonskogen.em2012.form;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import se.lingonskogen.em2012.domain.Team;


public class CouponForm {
	
    private Map<String, PredictionFormData> predictionMap = new NonEmptyHashMap<String, PredictionFormData>(){
        @Override
        public PredictionFormData build(String key)
        {
            PredictionFormData formData = new PredictionFormData();
            formData.setGameId(key);
            return formData;
        }
    };

	private List<Team> teams;
	
	private String winnerTeamId;
	
    public List<PredictionFormData> getPredictions() {
        List<PredictionFormData> list = new ArrayList<PredictionFormData>(predictionMap.values());
        Collections.sort(list, new Comparator<PredictionFormData>()
        {
            @Override
            public int compare(PredictionFormData o1, PredictionFormData o2)
            {
                return -1;
//                return o1.getKickoff().compareTo(o2.getKickoff());
            }
        });
		return list;
	}

    public Map<String, PredictionFormData> getPredictionMap()
    {
        return predictionMap;
    }
    
    public void putPredictionFormData(String gameId, PredictionFormData formData)
    {
        predictionMap.put(gameId, formData);
    }
    public PredictionFormData getPrediction(String gameId)
    {
        return predictionMap.get(gameId);
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
