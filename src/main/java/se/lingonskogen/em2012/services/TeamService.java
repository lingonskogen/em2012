package se.lingonskogen.em2012.services;

import java.util.List;

import se.lingonskogen.em2012.domain.DaoException;
import se.lingonskogen.em2012.domain.Team;

public interface TeamService {
	String createTeam(final Team team) throws DaoException;
	Team newInstance(final String name, final String tournamentId);
	List<Team> getAvailableTeams();
}
