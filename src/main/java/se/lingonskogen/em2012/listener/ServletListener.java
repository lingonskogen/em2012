package se.lingonskogen.em2012.listener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

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

public class ServletListener implements ServletContextListener
{

    private static String TOURNAMENT_EM2012 = "EM2012";

    @Override
    public void contextDestroyed(ServletContextEvent arg0)
    {
        // Do nothing
    }

    @Override
	public void contextInitialized(ServletContextEvent event) {

		try {
	        // Create a tournament
	        String tourId = createTournaments();
	        Random random = new Random(System.currentTimeMillis());
	        GameDao gameDao = new GameDao();
            List<Game> games = gameDao.findAll(tourId);
            
    		// Create groups
    		GroupDao groupDao = new GroupDao();
            UserDao userdao = new UserDao();
            CouponDao couponDao = new CouponDao();
            
            PredictionDao predictionDao = new PredictionDao();
            
    		for (GroupType type : GroupType.values()) {
    			Group group = new Group();
    			group.setName(type.toString());
    			String groupId = groupDao.create(group);
    			
    		     List<String> names = new ArrayList<String>();
    		     names.add("Tobbe");
    		     names.add("Susen");
    		     names.add("Kalle");
    		        
    		     for (String name : names)
    		     {
    		         // Create users
    		         User user = new User();
    		         user.setGroupId(groupId);
    		         user.setPassword(name.toLowerCase());
    		         user.setRealName(name);
    		         user.setUserName(name.toLowerCase());
    		         String userId = userdao.create(user);
    		         
    		         Coupon coupon = new Coupon();
    		         coupon.setTournamentId(tourId);
    		         coupon.setGroupId(groupId);
    		         coupon.setUserId(userId);
                     String couponId = couponDao.create(coupon);
                     
                     for (Game game : games)
                    {
                         Prediction prediction = new Prediction();
                         prediction.setGroupId(groupId);
                         prediction.setUserId(userId);
                         prediction.setCouponId(couponId);
                         prediction.setTournamentId(game.getTournamentId());
                         prediction.setGameId(game.getId());
                         prediction.setHomeScore(Math.abs(random.nextLong()) % 5);
                         prediction.setAwayScore(Math.abs(random.nextLong()) % 5);
                         predictionDao.create(prediction);
                    }
    		     }
    		}
		}
		catch (DaoException e)
		{
		    // jaha
		}
	}

    private String createTournaments() throws DaoException
    {
        TournamentDao tourDao = new TournamentDao();
        Tournament tour = new Tournament();
        tour.setName(TOURNAMENT_EM2012);
        String tourId = tourDao.create(tour);
        createTeams(tourId);
        return tourId;
    }

    private void createTeams(String tourId) throws DaoException
    {
        List<String> teamIds = new ArrayList<String>();
        TeamDao teamDao = new TeamDao();
        Team team = new Team();
        team.setName("Sverige");
        team.setTournamentId(tourId);
        teamIds.add(teamDao.create(team));

        team = new Team();
        team.setName("Ukraina");
        team.setTournamentId(tourId);
        teamIds.add(teamDao.create(team));

        team = new Team();
        team.setName("Frankrike");
        team.setTournamentId(tourId);
        teamIds.add(teamDao.create(team));

        team = new Team();
        team.setName("England");
        team.setTournamentId(tourId);
        teamIds.add(teamDao.create(team));

        for (String homeTeamId : teamIds)
        {
            for (String awayTeamId : teamIds)
            {
                if (!homeTeamId.equals(awayTeamId))
                {
                    createGame(tourId, homeTeamId, awayTeamId);
                }
            }
        }
    }

    private void createGame(String tourId, String homeTeamId, String awayTeamId)
            throws DaoException
    {
        GameDao gameDao = new GameDao();
        Game game = new Game();
        game.setTournamentId(tourId);
        game.setHomeTeamId(homeTeamId);
        game.setAwayTeamId(awayTeamId);
        game.setKickoff(new Date(System.currentTimeMillis()));
        gameDao.create(game);
    }
}