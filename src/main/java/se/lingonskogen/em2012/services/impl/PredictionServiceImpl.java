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
    public void createPredictions(List<Prediction> predictions) throws DaoException
    {
        predictionDao.create(predictions);
    }
    
    @Override
    public void updatePrediction(Prediction prediction) throws DaoException {
        predictionDao.update(prediction);
    }

    @Override
    public void updatePredictions(List<Prediction> predictions) throws DaoException
    {
        predictionDao.update(predictions);
    }

	@Override
	public Prediction newInstance(String groupId, String userId,
			String couponId, String tournamentId, String gameId,
			Long homeScore, Long awayScore) {
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

	public Prediction getPrediction(final String groupId, final String userId, final String couponId, final String predictionId) throws DaoException {
		return predictionDao.find(groupId, userId, couponId, predictionId);
	}
	
	public List<Prediction> getPredictions(final String groupId) {
		if(groupId == null) {
			return getPredictions();
		}
		return predictionDao.findAll(groupId);
	}
	public List<Prediction> getPredictions(final String groupId, final String userId) {
		if(userId == null) {
			return getPredictions(groupId); 
		}
		
		return predictionDao.findAll(groupId, userId);
	}
	public List<Prediction> getPredictions(final String groupId, final String userId, final String couponId) {		
		if(couponId == null) {
			return getPredictions(groupId, userId);
		}
		
		return predictionDao.findAll(groupId, userId, couponId);
	}
	
	public Prediction getPrediction(final String userId, final String groupId, final String gameId) {
		List<Prediction> predictions = getPredictions(groupId, userId);
		
		for (Prediction prediction : predictions) {
			if(prediction.getGameId().equals(gameId)) {
				return prediction;
			}
		}
		return null;
	}
	
	public List<Prediction> getPredictions() {
		return predictionDao.findAll();
	}

	public void deletePrediction(final Prediction prediction) throws DaoException {
		predictionDao.delete(prediction);
	}

	public void setPredictionDao(final PredictionDao predictionDao) {
		this.predictionDao = predictionDao;
	}

}
