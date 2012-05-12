package se.lingonskogen.em2012.services;

import java.util.List;

import se.lingonskogen.em2012.domain.DaoException;
import se.lingonskogen.em2012.domain.Prediction;

public interface PredictionService {
	String createPrediction(final Prediction prediction) throws DaoException;
	Prediction newInstance(final String groupId, final String userId, final String couponId, 
			final String tournamentId, final String gameId, final Integer homeScore, final Integer awayScore);
	List<Prediction> getAvailablePredictions();

	List<Prediction> findPredictions(final String groupId, final String userId, final String couponId);
	
	void deletePrediction(final Prediction prediction) throws DaoException;
}
