package se.lingonskogen.em2012.controller.admin;

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

	
	public void deleteGroup() {
		
	}
	public void deleteUser() {
		
	}
	public void deleteCoupon() {
		
	}
	
	public void deletePrediction() {
		
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