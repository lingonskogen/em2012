package se.lingonskogen.em2012.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.springframework.ui.ModelMap;

import se.lingonskogen.em2012.domain.Coupon;
import se.lingonskogen.em2012.domain.Game;
import se.lingonskogen.em2012.domain.Prediction;
import se.lingonskogen.em2012.domain.User;
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

	public int getPosition(final User user) {
		List<User> users = getTotalUsers(user.getGroupId());
		List<Game> games = getGameService().getAvailableGames();
		SortedMap<Integer, String> userScoreMap = new TreeMap<Integer, String>();
		List<Game> playedGames = new ArrayList<Game>();

		// Remove all games that doesnt have a result yet
		for (Game game : games) {
			if (game.getAwayScore() != null && game.getHomeScore() != null) {
				playedGames.add(game);
			}
		}

		for (User tmpUser : users) {
			int score = getScore(tmpUser, playedGames);

			// String key = String.valueOf(score) + tmpUser.getId();

			// userScoreMap.put(tmpUser.getId(), score);
		}

		System.out.print(userScoreMap);

		// for(String key)

		return 3;
	}

	public int getScore(final User user, final List<Game> games) {
		int score = 0;

		for (Game game : games) {
			
			if(game.getAwayScore() == null || game.getHomeScore() == null) {
				continue;
			}
			
			long homeScore = game.getHomeScore();
			long awayScore = game.getAwayScore();

			Prediction prediction = getPredictionService().getPrediction(
					user.getId(), user.getGroupId(), game.getId());

			if (prediction == null) {
				continue;
			}

			if (prediction.getAwayScore() == awayScore) {
				score++;
			}
			if (prediction.getHomeScore() == homeScore) {
				score++;
			}

			if (isCorrectWinner(game, prediction)) {
				score++;
			}
		}
		return score;
	}

	public String getCouponUrl(final String userId) {
		String url = "coupon.html";

		Coupon coupon = getCouponService().getCoupon(userId);
		if (coupon != null) {
			url += "?couponId=" + coupon.getId();
		}

		return url;
	}

	private boolean isCorrectWinner(final Game game, final Prediction prediction) {
		long awayScore = game.getAwayScore();
		long homeScore = game.getHomeScore();
		long predAwayScore = prediction.getAwayScore();
		long predHomeScore = prediction.getHomeScore();

		if (awayScore == homeScore) {
			return predAwayScore == predHomeScore ? true : false;
		}

		if (awayScore > homeScore) {
			return predAwayScore > predHomeScore ? true : false;
		}

		if (awayScore < homeScore) {
			return predAwayScore < predHomeScore ? true : false;
		}
		return false;
	}

	public boolean hasCoupon(final User user) {
		return couponService.getCoupon(user.getId()) == null ? false : true;
	}

	/*
	 * Total users is users that have a registrered coupon.
	 */
	public List<User> getTotalUsers(final String groupId) {
		List<User> users = userService.getUsers(groupId);

		// Remove all ursers that does not have a coupon
		for (User user : users) {
			if (getCouponService().getCoupon(user.getId()) == null) {
				users.remove(user);
			}
		}
		return users;
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

	private TeamService teamService;

	public abstract String getCurrentPageId();

	public void setParameters(final ModelMap model, final Principal principal) {
		if (principal == null) {
			model.addAttribute("loggedIn", false);
			model.addAttribute("userName", "");
		} else {
		    User user = getUserService().getUser(principal.getName());
			if(user!=null) {
		    if (principal.getName() != null) {
				model.addAttribute("loggedIn", true);
				model.addAttribute("userName", user.getRealName());
			}}
		}

		model.addAttribute("registrationOpen", isRegistrationOpen());
		model.addAttribute("currentPage", getCurrentPageId());
	}

	public boolean isRegistrationOpen() {
		return true;
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
