package se.lingonskogen.em2012.services.impl;

import java.util.List;

import se.lingonskogen.em2012.domain.DaoException;
import se.lingonskogen.em2012.domain.Tournament;
import se.lingonskogen.em2012.domain.TournamentDao;
import se.lingonskogen.em2012.services.TournamentService;

public class TournamentServiceImpl implements TournamentService {

	private TournamentDao tournamentDao;
	

	public void setTournamentDao(final TournamentDao tournamentDao) {
		this.tournamentDao = tournamentDao;
	}


	@Override
	public String createTournament(Tournament tournament) throws DaoException {
		return tournamentDao.create(tournament);
	}


	@Override
	public Tournament newInstance(String name) {
		Tournament tournament = new Tournament();
		tournament.setName(name);
		return tournament;
	}

	@Override
	public Tournament getTournament(final String tournamentId) {
		try {
			return tournamentDao.find(tournamentId);
		} catch (DaoException e) {
			return null;
		}
	}

	public String getTournamentName(final String tournamentId) {
		Tournament tournament;
		try {
			tournament = tournamentDao.find(tournamentId);
		} catch (DaoException e) {
			return null;
		}
		return tournament.getName();
	}
	@Override
	public List<Tournament> getAvailableTournaments() {
		return tournamentDao.findAll();
	}
}
