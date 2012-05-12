package se.lingonskogen.em2012.controller.admin;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import se.lingonskogen.em2012.domain.DaoException;
import se.lingonskogen.em2012.domain.Game;
import se.lingonskogen.em2012.domain.Prediction;
import se.lingonskogen.em2012.form.admin.SearchForm;

@Controller
@RequestMapping("/admin/predictionpage.html")
public class PredictionController extends AbstractController {
	
	private final static String PREDICTION_PAGE = "predictionpage";
	
	
	@RequestMapping(method = RequestMethod.GET)
	public String initForm(ModelMap model) {

		SearchForm searchForm = new SearchForm();
		setParameters(model, searchForm);
		return PREDICTION_PAGE;
	}

	// Process the search form.
	@RequestMapping(method = RequestMethod.POST)
	public String processForm(@ModelAttribute(value="searchForm") @Valid SearchForm searchForm, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			System.out.println("searchForm-error in Prediction Controller");
			searchForm.setUserId(null);
		}
				
		setParameters(model, searchForm);
		return PREDICTION_PAGE;
	}

	private Map<String, PredictionPageInfo> getPredictions(final String groupId, final String userId, final String couponId) {
		Map<String, PredictionPageInfo> pInfo = new HashMap<String, PredictionPageInfo>();
		
		for (Prediction prediction : getPredictionService().getPredictions(groupId, userId, couponId)) {
			String tournamentId = prediction.getTournamentId();
			
			Game game;
			try {
				game = getGameService().getGame(tournamentId, prediction.getGameId());
			} catch (DaoException e) {
				continue;
			}
			String tName = getTournamentService().getTournamentName(tournamentId);
			String gName = getGroupService().getGroupName(prediction.getGroupId());
			String uName = getUserService().getUserName(prediction.getUserId());
			String cId = prediction.getCouponId();
			String hTeam = getTeamService().getTeamName(tournamentId, game.getHomeTeamId());
			String aTeam = getTeamService().getTeamName(tournamentId, game.getAwayTeamId());
			String hScore = prediction.getHomeScore().toString();
			String aScore = prediction.getAwayScore().toString();

			pInfo.put(prediction.getId()+cId+uName+gName, new PredictionPageInfo(tName, gName, uName, cId, hTeam, aTeam,
					hScore, aScore));
		}

		return pInfo;
	}
	
	private void setParameters(final ModelMap model, SearchForm searchForm) {	
		String groupId = searchForm.getGroupId();
		if(groupId == null || groupId.equals("default")) {
			searchForm.setUserId(null);
		} else {
			groupId = searchForm.getGroupId();
		}
		
		String userId = searchForm.getUserId();
		if(userId == null || userId.equals("default")) {
			userId = null;
		} else {
			userId = searchForm.getUserId();
		}
		
		String couponId = getCouponId(groupId, userId);
		Map<String, String> availableGroups = getAvailableGroups();
		Map<String, String> availableUsers = getAvailableUsers(searchForm.getGroupId());
		Map<String, PredictionPageInfo> availablePredictions = getPredictions(groupId, userId, couponId);
		
		availableGroups.put("default", "Choose group");
		availableUsers.put("default", "Choose user");
		
		// command object
		model.addAttribute("searchForm", searchForm);
		model.addAttribute("availablePredictions", availablePredictions);
		model.addAttribute("tournamentId", getTournamentId());
		model.addAttribute("couponId", couponId);
		model.addAttribute("availableUsers", availableUsers);
		model.addAttribute("availableGroups", availableGroups);
	}

	public class PredictionPageInfo {
		private String tournamentName;
		private String groupName;
		private String userName;
		private String couponId;
		private String homeTeam;
		private String awayTeam;
		private String homeScore;
		private String awayScore;		
		
		public PredictionPageInfo(final String tName, final String gName, final String uName, final String cId,
				final String hTeam, final String aTeam, final String hScore, final String aScore) {
			this.tournamentName = tName;
			this.groupName = gName;
			this.userName = uName;
			this.couponId = cId;
			this.homeTeam = hTeam;
			this.awayTeam = aTeam;
			this.homeScore = hScore;
			this.awayScore = aScore;
		}
		
		
		
		public String getCouponId() {
			return couponId;
		}

		public String getHomeTeam() {
			return homeTeam;
		}

		public String getAwayTeam() {
			return awayTeam;
		}

		public String getHomeScore() {
			return homeScore;
		}

		public String getAwayScore() {
			return awayScore;
		}
		
		public String getTournamentName() {
			return tournamentName;
		}
		public void setTournamentName(String tournamentName) {
			this.tournamentName = tournamentName;
		}
		public String getGroupName() {
			return groupName;
		}
		public void setGroupName(String groupName) {
			this.groupName = groupName;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
	}

}