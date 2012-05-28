package se.lingonskogen.em2012.listener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;

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

    private Random random = new Random(System.currentTimeMillis());

    @Override
    public void contextDestroyed(ServletContextEvent arg0)
    {
        // Do nothing
    }

    private void createGroups(){
    	GroupDao groupDao = new GroupDao();
    	
    	try {
    		/*Group group = new Group();
    		
    		group.setName("Ateles");        
			groupDao.create(group);
			
			group = new Group();
    		group.setName("Sigma");        
			groupDao.create(group);
*/
			Group group = new Group();
    		group.setName("Friend");        
			groupDao.create(group);
			
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
    
    @Override
    public void contextInitialized(ServletContextEvent event)
    {
    	
    	/*createGroups();
        try
        {
        	
        	// Create a tournament
            String tourId = createTournaments();
            createGroups();
            GameDao gameDao = new GameDao();
            List<Game> games = gameDao.findAll(tourId);

            // Create groups
            GroupDao groupDao = new GroupDao();
            UserDao userdao = new UserDao();
            CouponDao couponDao = new CouponDao();

            PredictionDao predictionDao = new PredictionDao();

            MemcacheService cache = MemcacheServiceFactory.getMemcacheService();
            cache.clearAll();
            for (GroupType type : GroupType.values())
            {
                Group group = new Group();
                group.setName(type.toString());
                String groupId = groupDao.create(group);

                User foobar = new User();
                foobar.setGroupId(groupId);
                foobar.setPassword("123".toLowerCase());
                foobar.setRealName("Foo Bar");
                foobar.setUserName("foo@bar.com");
                userdao.create(foobar);

                List<String> names = new ArrayList<String>();
                names.add("Tobbe");
                names.add("Susen");
                names.add("Kalle");
                names.add("Adam");

                for (String firstname : names)
                {
                    for (String lastname : names)
                    {
                        lastname = lastname + "sson";
                        // Create users
                        User user = new User();
                        user.setGroupId(groupId);
                        user.setPassword("123".toLowerCase());
                        user.setRealName(firstname + " " + lastname);
                        user.setUserName(firstname.toLowerCase() + "." + lastname.toLowerCase() + "@home.se");
                        String userId = userdao.create(user);

                        Coupon coupon = new Coupon();
                        coupon.setTournamentId(tourId);
                        coupon.setGroupId(groupId);
                        coupon.setUserId(userId);
                        coupon.setWinnerTeamId(teamIds.get(Math.abs(random.nextInt(teamIds.size()))));
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
        }
        catch (DaoException e)
        {
            // jaha
        }*/
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

    List<String> teamIds = new ArrayList<String>();
    
    private void createTeams(String tourId) throws DaoException
    {
        TeamDao teamDao = new TeamDao();
        String croId = teamDao.create(makeTeam(tourId, "Kroatien", "CRO"));
        teamIds.add(croId);
        String czeId = teamDao.create(makeTeam(tourId, "Tjeckien", "CZE"));
        teamIds.add(czeId);
        String denId = teamDao.create(makeTeam(tourId, "Danmark", "DEN"));
        teamIds.add(denId);
        String engId = teamDao.create(makeTeam(tourId, "England", "ENG"));
        teamIds.add(engId);
        String fraId = teamDao.create(makeTeam(tourId, "Frankrike", "FRA"));
        teamIds.add(fraId);
        String gerId = teamDao.create(makeTeam(tourId, "Tyskland", "GER"));
        teamIds.add(gerId);
        String greId = teamDao.create(makeTeam(tourId, "Grekland", "GRE"));
        teamIds.add(gerId);
        String itaId = teamDao.create(makeTeam(tourId, "Italien", "ITA"));
        teamIds.add(itaId);
        String nedId = teamDao.create(makeTeam(tourId, "Holland", "NED"));
        teamIds.add(nedId);
        String polId = teamDao.create(makeTeam(tourId, "Polen", "POL"));
        teamIds.add(polId);
        String porId = teamDao.create(makeTeam(tourId, "Portugal", "POR"));
        teamIds.add(polId);
        String irlId = teamDao.create(makeTeam(tourId, "Irland", "IRL"));
        teamIds.add(irlId);
        String rusId = teamDao.create(makeTeam(tourId, "Ryssland", "RUS"));
        teamIds.add(rusId);
        String espId = teamDao.create(makeTeam(tourId, "Spanien", "ESP"));
        teamIds.add(engId);
        String sweId = teamDao.create(makeTeam(tourId, "Sverige", "SWE"));
        teamIds.add(sweId);
        String ukrId = teamDao.create(makeTeam(tourId, "Ukraina", "UKR"));
        teamIds.add(ukrId);

        createGame(tourId, polId, greId, "8 jun 18:00");
        createGame(tourId, rusId, czeId, "8 jun 20:45");
        createGame(tourId, nedId, denId, "9 jun 19:00");
        createGame(tourId, gerId, porId, "9 jun 21:45");
        createGame(tourId, espId, itaId, "10 jun 18:00");
        createGame(tourId, irlId, croId, "10 jun 20:45");
        createGame(tourId, ukrId, sweId, "11 jun 21:45");
        createGame(tourId, fraId, engId, "11 jun 19:00");
        createGame(tourId, polId, rusId, "12 jun 20:45");
        createGame(tourId, greId, czeId, "12 jun 18:00");
        createGame(tourId, nedId, gerId, "13 jun 21:45");
        createGame(tourId, denId, porId, "13 jun 19:00");
        createGame(tourId, espId, irlId, "14 jun 20:45");
        createGame(tourId, itaId, croId, "14 jun 18:00");
        createGame(tourId, sweId, engId, "15 jun 21:45");
        createGame(tourId, ukrId, fraId, "15 jun 19:00");
        createGame(tourId, greId, rusId, "16 jun 20:45");
        createGame(tourId, czeId, polId, "16 jun 20:45");
        createGame(tourId, porId, nedId, "17 jun 21:45");
        createGame(tourId, denId, gerId, "17 jun 21:45");
        createGame(tourId, croId, espId, "18 jun 20:45");
        createGame(tourId, itaId, irlId, "18 jun 20:45");
        createGame(tourId, sweId, fraId, "19 jun 21:45");
        createGame(tourId, engId, ukrId, "19 jun 21:45");
    }

    private Team makeTeam(String tourId, String name, String code)
    {
        Team team = new Team();
        team.setTournamentId(tourId);
        team.setCode(code);
        team.setName(name);
        return team;
    }

    private void createGame(String tourId, String homeTeamId, String awayTeamId, String dateStr)
            throws DaoException
    {
        GameDao gameDao = new GameDao();
        Game game = new Game();
        game.setTournamentId(tourId);
        game.setHomeTeamId(homeTeamId);
        game.setAwayTeamId(awayTeamId);
        SimpleDateFormat formatter = new SimpleDateFormat("d MMM HH:mm");
        try
        {
            Date date = formatter.parse(dateStr);
            game.setKickoff(date);
            game.setHomeScore(Math.abs(random.nextLong()) % 5);
            game.setAwayScore(Math.abs(random.nextLong()) % 5);
            gameDao.create(game);
        }
        catch (ParseException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
//        game.setKickoff(new Date(System.currentTimeMillis() + (homeTeamId.hashCode() * 2)
//                + awayTeamId.hashCode()));
    }
}