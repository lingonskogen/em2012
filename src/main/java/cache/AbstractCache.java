package cache;

import se.lingonskogen.em2012.services.CouponService;
import se.lingonskogen.em2012.services.GameService;
import se.lingonskogen.em2012.services.GroupService;
import se.lingonskogen.em2012.services.PredictionService;
import se.lingonskogen.em2012.services.TeamService;
import se.lingonskogen.em2012.services.TournamentService;
import se.lingonskogen.em2012.services.UserService;

public abstract class AbstractCache {

	private UserService userService;
	private GroupService groupService;
	private CouponService couponService;
	private PredictionService predictionService;
	private GameService gameService;
	private TournamentService tournamentService;
	private TeamService teamService;

	public void clearCache() {
		
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

}
