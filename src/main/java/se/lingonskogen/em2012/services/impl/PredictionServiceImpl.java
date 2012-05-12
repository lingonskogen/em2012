package se.lingonskogen.em2012.services.impl;

import java.util.List;

import se.lingonskogen.em2012.domain.DaoException;
import se.lingonskogen.em2012.domain.Prediction;
import se.lingonskogen.em2012.domain.PredictionDao;
import se.lingonskogen.em2012.services.PredictionService;

public class PredictionServiceImpl implements PredictionService {

	private PredictionDao predictionDao;
	
	@Override
	public String createPrediction(Prediction prediction) throws DaoException {
		return predictionDao.create(prediction);
	}

	@Override
	public Prediction newInstance(String groupId, String userId,
			String couponId, String tournamentId, String gameId,
			Integer homeScore, Integer awayScore) {
		Prediction prediction = new Prediction();
		prediction.setAwayScore(awayScore);
		prediction.setCouponId(couponId);
		prediction.setGameId(gameId);
		prediction.setGroupId(groupId);
		prediction.setHomeScore(homeScore);
		prediction.setTournamentId(tournamentId);
		prediction.setUserId(userId);
		return prediction;
	}

	public List<Prediction> findPredictions(final String groupId, final String userId, final String couponId) {
		return predictionDao.findAll(groupId, userId, couponId);
	}
	public void deletePrediction(final Prediction prediction) throws DaoException {
		predictionDao.delete(prediction);
	}
	@Override
	public List<Prediction> getAvailablePredictions() {
		return predictionDao.findAll();
	}

	public void setPredictionDao(final PredictionDao predictionDao) {
		this.predictionDao = predictionDao;
	}
}
