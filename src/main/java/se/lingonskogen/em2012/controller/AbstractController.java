package se.lingonskogen.em2012.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.logging.Logger;

import org.springframework.context.MessageSource;
import org.springframework.ui.ModelMap;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.google.appengine.api.memcache.Stats;

import se.lingonskogen.em2012.domain.Coupon;
import se.lingonskogen.em2012.domain.Game;
import se.lingonskogen.em2012.domain.Prediction;
import se.lingonskogen.em2012.domain.Tournament;
import se.lingonskogen.em2012.domain.User;
import se.lingonskogen.em2012.form.StatisticsFormData.TournamentFormData.GameFormData;
import se.lingonskogen.em2012.form.TopListData;
import se.lingonskogen.em2012.services.CouponService;
import se.lingonskogen.em2012.services.GameService;
import se.lingonskogen.em2012.services.GroupService;
import se.lingonskogen.em2012.services.PredictionService;
import se.lingonskogen.em2012.services.TeamService;
import se.lingonskogen.em2012.services.TournamentService;
import se.lingonskogen.em2012.services.UserService;

public abstract class AbstractController {

    private static final Logger LOG = Logger.getLogger(AbstractController.class.getName());
    
	private UserService userService;
	private GroupService groupService;
	private CouponService couponService;
	private PredictionService predictionService;
	private GameService gameService;
	private TournamentService tournamentService;
	private TeamService teamService;

	private MessageSource messageSource;

	protected static final String DEFAULT_LANG = "sv";
	
	public abstract String getCurrentPageId();

	public void setParameters(final ModelMap model, final Principal principal) {
		if (principal == null) {
			model.addAttribute("loggedIn", false);
			model.addAttribute("userName", "");
		} else {
			User user = getUserService().getUser(principal.getName());
			if (user != null) {
				if (principal.getName() != null) {
					model.addAttribute("loggedIn", true);
					model.addAttribute("userName", user.getRealName());
				}
				model.addAttribute("hasCoupon", hasCoupon(user));
			}
		}

		model.addAttribute("registrationOpen", isRegistrationOpen());
		model.addAttribute("currentPage", getCurrentPageId());		
		
		MemcacheService cache = MemcacheServiceFactory.getMemcacheService();
		Stats statistics = cache.getStatistics();
		LOG.info(statistics.toString());
	}

	public Integer calcScore(Game game, Prediction prediction) {
		boolean sane = true;
		
		sane &= game.getHomeScore() != null;
		sane &= game.getAwayScore() != null;
		sane &= prediction.getHomeScore() != null;
		sane &= prediction.getAwayScore() != null;
		int score = 0;
		if (sane) {
			score += game.getHomeScore().equals(prediction.getHomeScore()) ? 1 : 0;
			score += game.getAwayScore().equals(prediction.getAwayScore()) ? 1 : 0;
			score += (game.getHomeScore() < game.getAwayScore() && prediction
					.getHomeScore() < prediction.getAwayScore()) ? 1 : 0;
			score += (game.getHomeScore() > game.getAwayScore() && prediction
					.getHomeScore() > prediction.getAwayScore()) ? 1 : 0;
			score += (game.getHomeScore() == game.getAwayScore() && prediction
					.getHomeScore() == prediction.getAwayScore()) ? 1 : 0;
		}
				
		return score;
	}

	public Integer getPosition(final User currentUser) {
		String groupId = currentUser.getGroupId();

		Tournament tournament = getTournamentService()
				.getAvailableTournaments().get(0);
		List<Game> games = getGameService().getAvailableGames(
				tournament.getId());
		List<User> users = getUserService().getUsers(groupId);

		Map<String, Integer> scores = getScores(users, tournament);
		
		Integer currentUserScore = scores.get(currentUser.getId());
		Integer position = null;
		if (currentUserScore != null) {
			position = 1;
			for (String key : scores.keySet()) {
				if (scores.get(key) == null) continue;
				
				if (scores.get(key) > currentUserScore) {					
					position++;
				}
			}
		}
		return position;
	}

	private Map<String, Integer> getScores(List<User> users, final Tournament tournament) {
		
		// Get users scores from cache - if exists
        String cacheKey = "user_scores_list";
        MemcacheService cache = MemcacheServiceFactory.getMemcacheService();
        if (!cache.contains(cacheKey)) {
            LOG.info("getScores " + cacheKey);
            Map<String, Integer> scores = new HashMap<String, Integer>();
            scores = getUserScoresList(tournament);
            cache.put(cacheKey, scores);
        }
        Map<String, Integer> userScores = (Map<String, Integer>) cache.get(cacheKey);
        Map<String, Integer> tmpList = new HashMap<String, Integer>();
        for(User user : users) {
        	tmpList.put(user.getId(), userScores.get(user.getId()));
        }
        
        return tmpList;
    }
	
	private Map<String, Integer> getUserScoresList(final Tournament tournament) {
		// Get all users
		List<User> users = getUserService().getUsers();
		List<Game> games = getGameService().getAvailableGames(tournament.getId());
		Map<String, Integer> scores = new HashMap<String, Integer>();
		
		for (Game game : games) {
			for (User user : users) {
				String userId = user.getId();

				List<Prediction> userPredictions = getPredictionService()
						.getPredictions(user.getGroupId(), userId);

				for (Prediction prediction : userPredictions) {
					if (game.getId().equals(prediction.getGameId())) {
						Integer score = calcScore(game, prediction);
						if (!scores.containsKey(userId)) {
							scores.put(userId, 0);
						}
						scores.put(userId, score + scores.get(userId));
					}
				}				
			}
		}

		// If we have a tournament winner - add points for correct winner
		if (tournament.getWinnerTeamId() != null && !tournament.getWinnerTeamId().equals("")) {
			for (String userid : scores.keySet()) {					
				Coupon userCoupon = getCouponService().getCoupon(userid);
				
				if(userCoupon.getWinnerTeamId().equals(tournament.getWinnerTeamId())) {
					scores.put(userid, 5 + scores.get(userid));	
				}
			}				
		}
		return scores;
	}
	
	public Integer getUserPoints(final User user) {
		List<User> users = new ArrayList<User>();
		Tournament tournament = getTournamentService()
				.getAvailableTournaments().get(0);
		users.add(user);
		
		Map<String, Integer> userPointsMap = getScores(users, tournament);
		
		return userPointsMap.get(user.getId());
	}
	
	public List<TopListData> getToplist(final String groupId, final int num) {
		List<User> users = groupId == null ? getUserService().getUsers() : 	getUserService().getUsers(groupId);
		List<TopListData> toplist = new LinkedList<TopListData>();
		Tournament tournament = getTournamentService()
				.getAvailableTournaments().get(0);

		Map<String, Integer> userPointsMap = getScores(users, tournament);
		
		for (String key : userPointsMap.keySet()) {
			
			if (userPointsMap.get(key) == null) continue;
			
			TopListData t = new TopListData();
			
			User user = groupId == null ? getUserService().getUserById(key) : getUserService().getUser(groupId, key);
			String groupName = getGroupService().getGroupName(user.getGroupId());
			
			t.setGroupName(groupName);
			t.setPoints(userPointsMap.get(key));
			t.setUserRealName(user.getRealName());
			
			toplist.add(t);
		}
		
		// Sort toplist
		 Collections.sort(toplist, new Comparator<TopListData>() {
			 @Override
			 public int compare(TopListData o1, TopListData o2) {
				 Integer p1 = o1.getPoints();
				 Integer p2 = o2.getPoints();
				 return -(p1.compareTo(p2));
			 }
		 });
		 
		// Return num elemenst
		int size = toplist.size() < num ? toplist.size() : num;
		
		return toplist.subList(0, size);		
	}
	
	public List<TopListData> getToplist(final int num) {
		return getToplist(null, num);
	}

	public List<GameFormData> getNextGames(final int num) {
		List<Game> games = getGameService().getSortedAvailableGames();
		List<GameFormData> nextGames = new LinkedList<GameFormData>();
		Date now = new Date();

		
		int counter = 0;
		for (Game game : games) {
		    if(game.getKickoff().compareTo(now) > 1) {
		    	String tournamentId = game.getTournamentId();
		    	counter++;
				GameFormData formData = new GameFormData(game);
				formData.setAwayTeam(getTeamService().getTeam(tournamentId, game.getAwayTeamId()));
				formData.setHomeTeam(getTeamService().getTeam(tournamentId, game.getHomeTeamId()));
				formData.setKickoff(game.getKickoff());
				
				nextGames.add(formData);
				if(counter >= num) {
					return nextGames;
				}
			}
		}
		return nextGames;
	}
	
	public boolean hasCoupon(final User user) {
		return couponService.getCoupon(user.getId()) == null ? false : true;
	}

	/*
	 * Total users is users that have a registrered coupon.
	 */
	public List<User> getTotalUsers(final String groupId) {
		List<User> users = userService.getUsers(groupId);
		List<User> tmpUsers = new ArrayList<User>(users);

		if (users != null) {
			// Remove all ursers that does not have a coupon
			for (User user : users) {
				if (getCouponService().getCoupon(user.getId()) == null) {
					tmpUsers.remove(user);
				}
			}
		}
		return tmpUsers;
	}

	public int getTotalNumUsers(final String groupId) {
		return getTotalUsers(groupId).size();
	}

    private void clearCache(String kind) {
        LOG.info("clear( " + kind + ")");
        MemcacheService cache = MemcacheServiceFactory.getMemcacheService();
        cache.delete(kind);
    }	
    
	public CouponService getCouponService() {
		return couponService;
	}

	public void setCouponService(CouponService couponService) {
		this.couponService = couponService;
	}

	public PredictionService getPredictionService() {
		return predictionService;
	}

	public void setPredictionService(PredictionService predictionService) {
		this.predictionService = predictionService;
	}

	public GameService getGameService() {
		return gameService;
	}

	public void setGameService(GameService gameService) {
		this.gameService = gameService;
	}

	public TournamentService getTournamentService() {
		return tournamentService;
	}

	public void setTournamentService(TournamentService tournamentService) {
		this.tournamentService = tournamentService;
	}

	public TeamService getTeamService() {
		return teamService;
	}

	public void setTeamService(TeamService teamService) {
		this.teamService = teamService;
	}

	public int getNumRegistreredCoupons(final String groupId) {
		List<Coupon> coupons = getCouponService().getCoupons(groupId);
		if (coupons == null) return 0;
		
		return coupons.size();
	}
	
	public boolean isRegistrationOpen() {
        TimeZone tz = TimeZone.getTimeZone("Europe/Stockholm");
        GregorianCalendar current = new GregorianCalendar();
        current.setTimeZone(tz);
        GregorianCalendar deadline = new GregorianCalendar(2012, Calendar.JUNE, 8, 12, 0);
        deadline.setTimeZone(tz);
        
        return current.before(deadline);
	}
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(final UserService userService) {
		this.userService = userService;
	}

	public GroupService getGroupService() {
		return groupService;
	}

	public void setGroupService(final GroupService groupService) {
		this.groupService = groupService;
	}

	public void setMessageSource(final MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	public MessageSource getMessageSource() {
		return messageSource;
	}
}
