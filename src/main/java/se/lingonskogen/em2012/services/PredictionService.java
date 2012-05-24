package se.lingonskogen.em2012.services;

import java.util.List;

import se.lingonskogen.em2012.domain.DaoException;
import se.lingonskogen.em2012.domain.Prediction;
import se.lingonskogen.em2012.form.PredictionFormData;

public interface PredictionService {
	String createPrediction(final Prediction prediction) throws DaoException;
	Prediction newInstance(final String groupId, final String userId, final String couponId, 
			final String tournamentId, final String gameId, final Long homeScore, final Long awayScore);
	
	Prediction getPrediction(final String groupId, final String userId, final String couponId, final String predictionId) throws DaoException;
	void deletePrediction(final Prediction prediction) throws DaoException;
	Prediction getPrediction(final String userId, final String groupId, final String gameId);
	
	List<Prediction> getPredictions(final String groupId, final String userId, final String couponId);
	List<Prediction> getPredictions(final String groupId, final String userId);
	List<Prediction> getPredictions(final String groupId);
	List<Prediction> getPredictions();
}
