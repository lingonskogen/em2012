package se.lingonskogen.em2012.listener;

import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import se.lingonskogen.em2012.domain.Coupon;
import se.lingonskogen.em2012.domain.CouponDao;
import se.lingonskogen.em2012.domain.DaoException;
import se.lingonskogen.em2012.domain.Game;
import se.lingonskogen.em2012.domain.GameDao;
import se.lingonskogen.em2012.domain.Group;
import se.lingonskogen.em2012.domain.GroupDao;
import se.lingonskogen.em2012.domain.GroupType;
import se.lingonskogen.em2012.domain.Prediction;
import se.lingonskogen.em2012.domain.PredictionDao;
import se.lingonskogen.em2012.domain.Team;
import se.lingonskogen.em2012.domain.TeamDao;
import se.lingonskogen.em2012.domain.Tournament;
import se.lingonskogen.em2012.domain.TournamentDao;
import se.lingonskogen.em2012.domain.User;
import se.lingonskogen.em2012.domain.UserDao;

public class ServletListener implements ServletContextListener {

	private static String TOURNAMENT_ID = "EM2012";
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// Do nothing
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {

		GroupDao groupDao = new GroupDao();
		try {		
		// Create groups
		for (GroupType type : GroupType.values()) {
			Group group = new Group();
			group.setName(type.toString());
			groupDao.create(group);
		}

		// Create users
		UserDao userdao = new UserDao();
		User user = new User();
		user.setGroupId(GroupType.VÄNNER.toString());
		user.setPassword("PWD");
		user.setRealName("Kalle");
		user.setUserName("kalle@katten.se");
		userdao.create(user);
		
		user = new User();
		user.setGroupId(GroupType.SIGMA.toString());
		user.setPassword("PWD");
		user.setRealName("Tobbbe");
		user.setUserName("tobbe@tobbe.se");
		userdao.create(user);

		user = new User();
		user.setGroupId(GroupType.ATELES.toString());
		user.setPassword("PWD");
		user.setRealName("Susen");
		user.setUserName("susen@susen.se");
		userdao.create(user);
		
		// Create a tournament
		createTournaments();
		
		// Create Teams
		createTeams();
		
		// Create Coupons
		createCoupons();
		
		// Create Games
		createGames();
		
		// Create Predictions
		createPredictions();
		
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void createPredictions() throws DaoException {
		PredictionDao pDao = new PredictionDao();
		
		Prediction p = new Prediction();
		p.setAwayScore(0);
		p.setCouponId(TOURNAMENT_ID);
		p.setGameId(createId("Sverige","Ukraina"));
		p.setGroupId(GroupType.ATELES.toString());
		p.setHomeScore(2);
		p.setTournamentId(TOURNAMENT_ID);
		p.setUserId("susen@susen.se");
		pDao.create(p);
		
		p = new Prediction();
		p.setAwayScore(2);
		p.setCouponId(TOURNAMENT_ID);
		p.setGameId(createId("Frankrike","England"));
		p.setGroupId(GroupType.ATELES.toString());
		p.setHomeScore(2);
		p.setTournamentId(TOURNAMENT_ID);
		p.setUserId("susen@susen.se");
		pDao.create(p);
		
		p = new Prediction();
		p.setAwayScore(8);
		p.setCouponId(TOURNAMENT_ID);
		p.setGameId(createId("Frankrike","England"));
		p.setGroupId(GroupType.SIGMA.toString());
		p.setHomeScore(8);
		p.setTournamentId(TOURNAMENT_ID);
		p.setUserId("tobbe@tobbe.se");
		pDao.create(p);
		
		p = new Prediction();
		p.setAwayScore(9);
		p.setCouponId(TOURNAMENT_ID);
		p.setGameId(createId("Sverige","Ukraina"));
		p.setGroupId(GroupType.SIGMA.toString());
		p.setHomeScore(9);
		p.setTournamentId(TOURNAMENT_ID);
		p.setUserId("tobbe@tobbe.se");
		pDao.create(p);
		
		p = new Prediction();
		p.setAwayScore(10);
		p.setCouponId(TOURNAMENT_ID);
		p.setGameId(createId("Frankrike","England"));
		p.setGroupId(GroupType.VÄNNER.toString());
		p.setHomeScore(0);
		p.setTournamentId(TOURNAMENT_ID);
		p.setUserId("kalle@katten.se");
		pDao.create(p);
		
		p = new Prediction();
		p.setAwayScore(11);
		p.setCouponId(TOURNAMENT_ID);
		p.setGameId(createId("Sverige","Ukraina"));
		p.setGroupId(GroupType.VÄNNER.toString());
		p.setHomeScore(0);
		p.setTournamentId(TOURNAMENT_ID);
		p.setUserId("kalle@katten.se");
		pDao.create(p);
		
	}
	
	private String createId(String h, String a) {
		return h + " - " + a;
	}
	
	private void createGames() throws DaoException {
		GameDao gDao = new GameDao();
		
		Game g = new Game();
		g.setAwayScore(null);
		g.setAwayTeamId("Sverige");
		g.setHomeScore(null);
		g.setHomeTeamId("Ukraina");
		g.setKickoff(new Date());
		g.setTournamentId(TOURNAMENT_ID);
		gDao.create(g);
		
		g = new Game();
		g.setAwayScore(null);
		g.setAwayTeamId("Frankrike");
		g.setHomeScore(null);
		g.setHomeTeamId("England");
		g.setKickoff(new Date());
		g.setTournamentId(TOURNAMENT_ID);
		gDao.create(g);
	}
	
	private void createCoupons() throws DaoException {
		CouponDao cDao = new CouponDao();
		Coupon c = new Coupon();
		c.setGroupId(GroupType.VÄNNER.toString());
		c.setTournamentId(TOURNAMENT_ID);
		c.setUserId("kalle@katten.se");
		cDao.create(c);
		
		c = new Coupon();
		c.setGroupId(GroupType.ATELES.toString());
		c.setTournamentId(TOURNAMENT_ID);
		c.setUserId("susen@susen.se");
		cDao.create(c);
		
		c = new Coupon();
		c.setGroupId(GroupType.SIGMA.toString());
		c.setTournamentId(TOURNAMENT_ID);
		c.setUserId("tobbe@tobbe.se");
		cDao.create(c);
	}
	
	private void createTournaments() throws DaoException {
		TournamentDao tourDao = new TournamentDao();
		Tournament tour = new Tournament();
		tour.setName(TOURNAMENT_ID);
		tourDao.create(tour);
	}
	private void createTeams() throws DaoException {
		TeamDao teamDao = new TeamDao();
		Team team = new Team();
		team.setName("Sverige");
		team.setTournamentId(TOURNAMENT_ID);
		teamDao.create(team);
		
		team = new Team();
		team.setName("Ukraina");
		team.setTournamentId(TOURNAMENT_ID);
		teamDao.create(team);
		
		team = new Team();
		team.setName("Frankrike");
		team.setTournamentId(TOURNAMENT_ID);
		teamDao.create(team);
		
		team = new Team();
		team.setName("England");
		team.setTournamentId(TOURNAMENT_ID);
		teamDao.create(team);

	}
}