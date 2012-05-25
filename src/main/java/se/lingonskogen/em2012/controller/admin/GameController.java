package se.lingonskogen.em2012.controller.admin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import se.lingonskogen.em2012.domain.Game;
import se.lingonskogen.em2012.domain.Team;
import se.lingonskogen.em2012.domain.Tournament;
import se.lingonskogen.em2012.form.admin.GameForm;
import se.lingonskogen.em2012.validator.admin.GameValidator;

@Controller
@RequestMapping("/admin/gamepage.html")
public class GameController extends AbstractAdminController {

	private GameValidator gameValidator;
	
	final private static String GAME_PAGE = "/admin/gamepage";
	
	@RequestMapping(method = RequestMethod.GET)
	public String initForm(ModelMap model) {

		GameForm gForm = new GameForm();

		setParameters(model, gForm);

		// return form view
		return GAME_PAGE;
	}

	private Map<String,Game> getGames() {
		Map<String, Game> t = new LinkedHashMap<String, Game>();
		
		for (Game game : getGameService().getSortedAvailableGames()) {
			t.put(game.getId(), game);
		}

		return t;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
	    dateFormat.setLenient(false);

	    // true passed to CustomDateEditor constructor means convert empty String to null
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	
	// Process the form.
	@RequestMapping(method = RequestMethod.POST)
	public String processForm(@ModelAttribute(value="gameForm") @Valid GameForm gameForm, BindingResult result, ModelMap model) {

		gameValidator.validate(gameForm, result);
		
		if (result.hasErrors()) {			
			setParameters(model, gameForm);
			
			return GAME_PAGE;
		}
		
		// Register new Tournament
		Game game = getGameService().newInstance(gameForm.getTournamentId(), gameForm.getHomeTeamId(), gameForm.getAwayTeamId(), 
				gameForm.getKickoff(), gameForm.getHomeScore(), gameForm.getAwayScore());
		
		try{
			getGameService().createGame(game);
		} catch (Exception e) {
			
			// TODO: Get error message from message file
			String errorMessage = "Det gick inte att skapa en Game";
			
			// Error when creating game
			setParameters(model, gameForm);			
			model.addAttribute("errorMessage", errorMessage);
			
			return GAME_PAGE;
		}
	
		setParameters(model, gameForm);
		
		// TODO: Get message from message file
		model.addAttribute("successMessage","Game blev skapad");
		return GAME_PAGE;
	}
	
	private void setParameters(final ModelMap model, final GameForm gameForm) {
		super.setParameters(model);
		
		Map<String, Game> availableGames = getGames();
		Map<String, String> availableTournaments = getTournaments();
		Map<String, String> availableTeams = getTeams();
		
		// command object
		model.addAttribute("gameForm", gameForm);
		model.addAttribute("availableGames", availableGames);
		model.addAttribute("availableTournaments", availableTournaments);
		model.addAttribute("availableTeams", availableTeams);
	}

	private Map<String, String> getTeams() {
		Map<String, String> teams = new HashMap<String, String>();
		
		for(Team team : getTeamService().getAvailableTeams()) {
			teams.put(team.getId(), team.getName());
		}
		
		return teams;
	}
	private Map<String, String> getTournaments() {
		Map<String, String> t = new LinkedHashMap<String, String>();
		
		for (Tournament tournament : getTournamentService().getAvailableTournaments()) {
			t.put(tournament.getId(), tournament.getName());
		}

		return t;
	}

	public void setGameValidator(final GameValidator gameValidator) {
		this.gameValidator = gameValidator;
	}
	
	public String getPageId() {
		return "game";
	}
}