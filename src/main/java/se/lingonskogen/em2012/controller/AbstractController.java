package se.lingonskogen.em2012.controller;

import java.security.Principal;

import org.springframework.ui.ModelMap;

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
		if(principal == null) {
			model.addAttribute("loggedIn", false);
			model.addAttribute("userName", "");
		} else {
			System.out.println("Logged in: " + principal.getName());
			if(principal.getName() != null) {
				model.addAttribute("loggedIn", true);
				model.addAttribute("userName", principal.getName());
			}
		}	
		
		model.addAttribute("currentPage", getCurrentPageId());
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

