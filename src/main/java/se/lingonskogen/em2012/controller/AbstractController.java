package se.lingonskogen.em2012.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.springframework.ui.ModelMap;

import se.lingonskogen.em2012.domain.Coupon;
import se.lingonskogen.em2012.domain.Game;
import se.lingonskogen.em2012.domain.Prediction;
import se.lingonskogen.em2012.domain.Team;
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

	private UserService userService;
	private GroupService groupService;
	private CouponService couponService;
	private PredictionService predictionService;
	private GameService gameService;
	private TournamentService tournamentService;
	private TeamService teamService;

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
			}
		}

		model.addAttribute("registrationOpen", isRegistrationOpen());
		model.addAttribute("currentPage", getCurrentPageId());
	}

	public Integer calcScore(Game game, Prediction prediction) {
		boolean sane = true;
		sane &= game.getHomeScore() != null;
		sane &= game.getAwayScore() != null;
		sane &= prediction.getHomeScore() != null;
		sane &= prediction.getAwayScore() != null;
		int score = 0;
		if (sane) {
			score += game.getHomeScore() == prediction.getHomeScore() ? 1 : 0;
			score += game.getAwayScore() == prediction.getAwayScore() ? 1 : 0;
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

		Map<String, Integer> scores = new HashMap<String, Integer>();
		for (Game game : games) {
			for (User user : users) {
				String userId = user.getId();

				List<Prediction> userPredictions = getPredictionService()
						.getPredictions(groupId, userId);

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
		
		Integer currentUserScore = scores.get(currentUser.getId());
		Integer position = null;
		if (currentUserScore != null) {
			position = 1;
			for (String key : scores.keySet()) {
				if (scores.get(key) > currentUserScore) {					
					position++;
				}
			}
		}
		return position;
	}

	public List<TopListData> getToplist(final String groupId, final int num) {
		List<User> users = groupId == null ? getUserService().getUsers() : 	getUserService().getUsers(groupId);
		List<TopListData> toplist = new LinkedList<TopListData>();

		// Get all users points and returl list with num
		TopListData t = new TopListData();
		t.setGroupName("Ateles");
		t.setPoints(21);
		t.setUserRealName("Susen Gladén");
		toplist.add(t);
		
		t = new TopListData();
		t.setGroupName("Ateles");
		t.setPoints(21);
		t.setUserRealName("Susen Gladén");
		toplist.add(t);
		t = new TopListData();
		t.setGroupName("Ateles");
		t.setPoints(21);
		t.setUserRealName("Susen Gladén");
		toplist.add(t);
		t = new TopListData();
		t.setGroupName("Ateles");
		t.setPoints(21);
		t.setUserRealName("Susen Gladén");
		toplist.add(t);
		t = new TopListData();
		t.setGroupName("Ateles");
		t.setPoints(21);
		t.setUserRealName("Susen Gladén");
		toplist.add(t);
		
		return toplist;
		
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
		    if(game.getKickoff().compareTo(now) < 1) {
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

	public boolean isRegistrationOpen() {
        /*TimeZone tz = TimeZone.getTimeZone("Europe/Stockholm");
        GregorianCalendar current = new GregorianCalendar();
        current.setTimeZone(tz);
        GregorianCalendar deadline = new GregorianCalendar(2012, Calendar.JUNE, 8, 12, 0);
        deadline.setTimeZone(tz);
        
        return current.before(deadline); */
		return false;
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
}
