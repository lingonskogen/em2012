package se.lingonskogen.em2012.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import se.lingonskogen.em2012.domain.Coupon;
import se.lingonskogen.em2012.domain.DaoException;
import se.lingonskogen.em2012.domain.Group;
import se.lingonskogen.em2012.domain.Prediction;
import se.lingonskogen.em2012.domain.Tournament;
import se.lingonskogen.em2012.domain.User;
import se.lingonskogen.em2012.services.CouponService;
import se.lingonskogen.em2012.services.GameService;
import se.lingonskogen.em2012.services.GroupService;
import se.lingonskogen.em2012.services.PredictionService;
import se.lingonskogen.em2012.services.TeamService;
import se.lingonskogen.em2012.services.TournamentService;
import se.lingonskogen.em2012.services.UserService;

public class AbstractController {

	protected final static String DELETE_ACTION = "delete";
	
	private CouponService couponService;
	private TournamentService tournamentService;
	private GroupService groupService;
	private UserService userService;
	private GameService gameService;
	private PredictionService predictionService;
	private TeamService teamService;

	public Map<String,String> getAvailableGroups() {
		Map<String, String> groups = new HashMap<String, String>();
		
		for (Group group : getGroupService().getAvailableGroups()) {
			groups.put(group.getId(), group.getName());
		}

		return groups;
	}
	public String getTournamentId() {
		Tournament t = getTournamentService().getAvailableTournaments().get(0);
		return t.getId();
	}
	
	public String getCouponId(final String groupId, final String userId) {
		if(groupId == null || userId == null || groupId.equals("default") || userId.equals("default")) {
			return null;
		}
		
		Coupon c = getCouponService().getCoupons(groupId, userId).get(0);
		return c.getId();
	}
	
	public Map<String, String> getAvailableUsers(final String groupId) {
		Map<String, String> usersMap = new HashMap<String, String>();
		
		if(groupId == null) {
			return usersMap;
		}
		
		List<User> users = getUserService().getUsers(groupId);

		for(User user : users) {
			usersMap.put(user.getId(), user.getRealName());
		}
		
		return usersMap;
	}
	
	public Map<String, String> getAvailableUsers() {
		return getAvailableUsers(null);
		
	}
	
	public void deleteGroup(final String groupId) throws DaoException {
		// First delete all users
		List<User> users = getUserService().getUsers(groupId);
		
		for(User user : users) {
			getUserService().delete(groupId, user.getId());
		}
		
		// Then delete the group
		getGroupService().delete(groupId);
	}
	
	public void deleteUser(final String groupId, final String userId) throws DaoException {
		// First delete all coupons
		List<Coupon> coupons = getCouponService().getCoupons(groupId, userId);
		for(Coupon coupon : coupons) {
			deleteCoupon(groupId, userId, coupon.getId());
		}
		
		// Then delete the user
		getUserService().delete(groupId, userId);				
	}
	
	public void deleteCoupon(final String groupId, final String userId, final String couponId) throws DaoException {
		
		// First delete all predictions
		List<Prediction> predictions = getPredictionService().getPredictions(groupId, userId, couponId);
		for(Prediction prediction : predictions) {
			deletePrediction(prediction);
		}
		
		// Then delete the coupon
		getCouponService().deleteCoupon(couponId, userId, groupId);
	}
	
	public void deletePrediction(final Prediction prediction) throws DaoException {
		getPredictionService().deletePrediction(prediction);
	}
	
	
	public CouponService getCouponService() {
		return couponService;
	}

	public TournamentService getTournamentService() {
		return tournamentService;
	}

	public GroupService getGroupService() {
		return groupService;
	}

	public UserService getUserService() {
		return userService;
	}

	public GameService getGameService() {
		return gameService;
	}

	public PredictionService getPredictionService() {
		return predictionService;
	}

	public TeamService getTeamService() {
		return teamService;
	}

	public void setTeamService(final TeamService teamService) {
		this.teamService = teamService;
	}
	
	public void setPredictionService(final PredictionService predictionService) {
		this.predictionService = predictionService;
	}
	
	public void setGameService(final GameService gameService) {
		this.gameService = gameService;
	}
	
	public void setCouponService(final CouponService couponService) {
		this.couponService = couponService;
	}

	public void setTournamentService(final TournamentService tournamentService) {
		this.tournamentService = tournamentService;
	}
	public void setGroupService(final GroupService groupService) {
		this.groupService = groupService;
	}
	public void setUserService(final UserService userService) {
		this.userService = userService;
	}
}