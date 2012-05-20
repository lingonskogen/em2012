package se.lingonskogen.em2012.form;

import java.util.List;
import java.util.Map;

import se.lingonskogen.em2012.domain.Game;
import se.lingonskogen.em2012.domain.Prediction;

public class StatisticsFormData {
	List<TopListData> topList;
	List<Game> games;
	// userId -> List<PredictionData
	Map<String, List<Prediction>> userPredictions;
	
	public List<TopListData> getTopList() {
		return topList;
	}
	public void setTopList(List<TopListData> topList) {
		this.topList = topList;
	}
	public List<Game> getGames() {
		return games;
	}
	public void setGames(List<Game> games) {
		this.games = games;
	}
	public Map<String, List<Prediction>> getUserPredictions() {
		return userPredictions;
	}
	public void setUserPredictions(Map<String, List<Prediction>> userPredictions) {
		this.userPredictions = userPredictions;
	}
	
}
