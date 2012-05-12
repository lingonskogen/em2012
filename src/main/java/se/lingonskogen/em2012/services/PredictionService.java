package se.lingonskogen.em2012.services;

import java.util.List;

import se.lingonskogen.em2012.domain.DaoException;
import se.lingonskogen.em2012.domain.Prediction;

public interface PredictionService {
	String createPrediction(final Prediction prediction) throws DaoException;
	Prediction newInstance(final String groupId, final String userId, final String couponId, 
			final String tournamentId, final String gameId, final Long homeScore, final Long awayScore);
	List<Prediction> getAvailablePredictions();

	List<Prediction> findPredictions(final String groupId, final String userId, final String couponId);
	Prediction findPrediction(final String groupId, final String userId, final String couponId, final String predictionId) throws DaoException;
	void deletePrediction(final Prediction prediction) throws DaoException;
}
