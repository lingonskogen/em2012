package se.lingonskogen.em2012.controller.admin;

import java.util.Map;

import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import se.lingonskogen.em2012.domain.Team;
import se.lingonskogen.em2012.domain.Tournament;
import se.lingonskogen.em2012.form.admin.TournamentForm;

@Controller
@RequestMapping("/admin/tournamentupdatepage.html")
public class TournamentUpdateController extends AbstractAdminController {

//    final private static String TOURNAMENT_PAGE = "/admin/tournamentpage";
    final private static String TOURNAMENT_UPDATE_PAGE = "/admin/tournamentupdatepage";

	@RequestMapping(method = RequestMethod.GET)
	public String initForm(@RequestParam(value = "tournamentId", defaultValue="") String tournamentId, 
				   ModelMap model) {

		if (tournamentId == null || tournamentId.equals("")) {
			model.addAttribute("errorMessage", "Tournament ID inte giltig");
			return TOURNAMENT_UPDATE_PAGE;
		}
		
		Tournament tournament = getTournamentService().getTournament(tournamentId);

		if (tournament == null) {
			model.addAttribute("errorMessage", "Ogiltigt tournament id");
			return TOURNAMENT_UPDATE_PAGE;
		}

		setParameters(model, tournament);
				
		// return update tournament form view
		return TOURNAMENT_UPDATE_PAGE;
	}

	private void setParameters(final ModelMap model, final Tournament tournament) {
		TournamentForm form = new TournamentForm();
		form.setId(tournament.getId());
		form.setName(tournament.getName());
		form.setWinnerTeamId(tournament.getWinnerTeamId());
		Map<String, Team> availableTeams = getTeamsData();
		
		model.addAttribute("availableTeams", availableTeams);
		model.addAttribute("form", form);
	}
	
	// Process the form.
	@RequestMapping(method = RequestMethod.POST)
	public String processForm(@ModelAttribute(value="form") @Valid TournamentForm form, BindingResult result, ModelMap model) {

		// Register new Tournament
		Tournament tournament = getTournamentService().getTournament(form.getId()); 
		String formWinnerTeam = form.getWinnerTeamId();
		
		if (formWinnerTeam != null && !formWinnerTeam.equals("")) {
			tournament.setWinnerTeamId(form.getWinnerTeamId());

			try{
				getTournamentService().updateTournament(tournament);
			} catch (Exception e) {
			
				// TODO: Get error message from message file
				String errorMessage = "Det gick inte att uppdatera";
			
				System.out.println("Could not update");
				//	setParameters(model, tournamentForm);			
				model.addAttribute("errorMessage", errorMessage);
				setParameters(model, tournament);
				
				return TOURNAMENT_UPDATE_PAGE;
			}
		}
		
		model.addAttribute("successMessage","Tournament blev uppdaterad");
		setParameters(model, tournament);
		return TOURNAMENT_UPDATE_PAGE;
	}
	
	public String getPageId() {
		return "tournament";
	}
}