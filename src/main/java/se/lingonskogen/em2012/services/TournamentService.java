package se.lingonskogen.em2012.services;

import java.util.List;

import org.springframework.stereotype.Service;

import se.lingonskogen.em2012.domain.DaoException;
import se.lingonskogen.em2012.domain.Tournament;

@Service
public interface TournamentService {
	
	String createTournament(final Tournament tournament) throws DaoException;
	Tournament newInstance(final String name);
	List<Tournament> getAvailableTournaments();
	Tournament getTournament(final String tournamentId);
	String getTournamentName(final String tournamentId);
}
