package se.lingonskogen.em2012.controller.admin;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import se.lingonskogen.em2012.domain.Team;
import se.lingonskogen.em2012.domain.Tournament;
import se.lingonskogen.em2012.form.admin.TeamForm;

@Controller
@RequestMapping("/admin/teampage.html")
public class TeamController extends AbstractAdminController {
	
	final private static String TEAM_PAGE = "/admin/teampage";
    
	@RequestMapping(method = RequestMethod.GET)
	public String initForm(ModelMap model) {

		TeamForm tForm = new TeamForm();

		setParameters(model, tForm);

		// return form view
		return TEAM_PAGE;
	}

	private Map<String,String> getTeams() {
		Map<String, String> t = new LinkedHashMap<String, String>();
		
		for (Team team : getTeamService().getAvailableTeams()) {
			Tournament tournament = getTournamentService().getTournament(team.getTournamentId());
			String tName = null;
			if(tournament!=null) {
				tName = tournament.getName();
			}
			t.put(team.getName(), tName);
		}

		return t;
	}
	
	// Process the form.
	@RequestMapping(method = RequestMethod.POST)
	public String processForm(@ModelAttribute(value="teamForm") @Valid TeamForm teamForm, BindingResult result, ModelMap model) {

		if (result.hasErrors()) {			
			setParameters(model, teamForm);
			
			return TEAM_PAGE;
		}
		
		// Register new Tournament
		Team team = getTeamService().newInstance(teamForm.getName(), teamForm.getTournamentId());
		
		try{
			getTeamService().createTeam(team);
		} catch (Exception e) {
			
			// TODO: Get error message from message file
			String errorMessage = "Det gick inte att skapa en Team";
			
			// Error when creating tournament
			setParameters(model, teamForm);			
			model.addAttribute("errorMessage", errorMessage);
			
			return TEAM_PAGE;
		}
	
		setParameters(model, teamForm);
		
		// TODO: Get message from message file
		model.addAttribute("successMessage","Team blev skapad");
		return TEAM_PAGE;
	}
	
	private void setParameters(final ModelMap model, final TeamForm teamForm) {
		super.setParameters(model);
		
		Map<String, String> availableTeams = getTeams();
		Map<String, String> availableTournaments = getTournaments();
		// command object
		model.addAttribute("teamForm", teamForm);
		model.addAttribute("availableTeams", availableTeams);
		model.addAttribute("availableTournaments", availableTournaments);
	}
	
	private Map<String,String> getTournaments() {
		Map<String, String> t = new LinkedHashMap<String, String>();
		
		for (Tournament tournament : getTournamentService().getAvailableTournaments()) {
			t.put(tournament.getId(), tournament.getName());
		}

		return t;
	}
	public String getPageId() {
		return "team";
	}
}