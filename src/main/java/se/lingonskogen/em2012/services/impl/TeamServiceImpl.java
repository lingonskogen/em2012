package se.lingonskogen.em2012.services.impl;

import java.util.List;

import se.lingonskogen.em2012.domain.DaoException;
import se.lingonskogen.em2012.domain.Team;
import se.lingonskogen.em2012.domain.TeamDao;
import se.lingonskogen.em2012.services.TeamService;

public class TeamServiceImpl implements TeamService {

	TeamDao teamDao;
	
	@Override
	public String createTeam(Team team) throws DaoException {
		return teamDao.create(team);
	}

	public String getTeamName(final String tournamentId, final String teamId) {
		Team team;
		try {
			team = teamDao.find(tournamentId, teamId);
		} catch (DaoException e) {
			return null;
		}
		return team.getName();
	}
	
	@Override
	public Team newInstance(final String name, final String tournamentId) {
		Team team = new Team();		
		team.setName(name);
		team.setTournamentId(tournamentId);
		return team;
	}

	@Override
	public List<Team> getAvailableTeams() {
		return teamDao.findAll();
	}

	public void setTeamDao(final TeamDao teamDao) {
		this.teamDao = teamDao;
	}
}
