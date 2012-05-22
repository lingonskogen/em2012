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

import se.lingonskogen.em2012.domain.Tournament;
import se.lingonskogen.em2012.form.admin.TournamentForm;

@Controller
@RequestMapping("/admin/tournamentpage.html")
public class TournamentController extends AbstractAdminController {

    final private static String TOURNAMENT_PAGE = "/admin/tournamentpage";

	@RequestMapping(method = RequestMethod.GET)
	public String initForm(ModelMap model) {

		TournamentForm tForm = new TournamentForm();

		setParameters(model, tForm);

		// return form view
		return TOURNAMENT_PAGE;
	}

	private Map<String,String> getTournaments() {
		Map<String, String> t = new LinkedHashMap<String, String>();
		
		for (Tournament tournament : getTournamentService().getAvailableTournaments()) {
			t.put(tournament.getId(), tournament.getName());
		}

		return t;
	}
	
	// Process the form.
	@RequestMapping(method = RequestMethod.POST)
	public String processForm(@ModelAttribute(value="tournamentForm") @Valid TournamentForm tournamentForm, BindingResult result, ModelMap model) {

		if (result.hasErrors()) {			
			setParameters(model, tournamentForm);
			
			return TOURNAMENT_PAGE;
		}
		
		// Register new Tournament
		Tournament tournament = getTournamentService().newInstance(tournamentForm.getName());
		
		try{
			getTournamentService().createTournament(tournament);
		} catch (Exception e) {
			
			// TODO: Get error message from message file
			String errorMessage = "Det gick inte att skapa en Tournament";
			
			// Error when creating tournament
			setParameters(model, tournamentForm);			
			model.addAttribute("errorMessage", errorMessage);
			
			return TOURNAMENT_PAGE;
		}
	
		setParameters(model, tournamentForm);
		
		// TODO: Get message from message file
		model.addAttribute("successMessage","Tournament blev skapad");
		return TOURNAMENT_PAGE;
	}
	
	private void setParameters(final ModelMap model, final TournamentForm tournamentForm) {
		super.setParameters(model);
		Map<String, String> availableTournaments = getTournaments();
		
		// command object
		model.addAttribute("tournamentForm", tournamentForm);
		model.addAttribute("availableTournaments", availableTournaments);
	}
	
	public String getPageId() {
		return "tournament";
	}
}