package se.lingonskogen.em2012.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import se.lingonskogen.em2012.domain.Coupon;
import se.lingonskogen.em2012.domain.DaoException;
import se.lingonskogen.em2012.domain.Game;
import se.lingonskogen.em2012.domain.Prediction;
import se.lingonskogen.em2012.domain.User;
import se.lingonskogen.em2012.form.CouponForm;
import se.lingonskogen.em2012.form.PredictionFormData;

@Controller
@RequestMapping("/coupon.html")
public class UserCouponPageController extends AbstractController {

	//private CouponValidator validator;
    
    private static final String PAGE_NAME = "usercouponpage";
    
	@RequestMapping(method = RequestMethod.GET) 
	public String initForm(@RequestParam(value="couponId", defaultValue = "") String couponId, 
														ModelMap model, Principal principals) {
		User user = null;
		if(principals != null) {
			String name = principals.getName();
			user = getUserService().getUser(name);
		}
		
		if(user == null) {
			model.put("errorMessage", "Du är ingen giltig användare");
			setParameters(model, principals);
			return PAGE_NAME;
		}
		
		System.out.println("CouponId: " + couponId);
		
		String action = "";
		
		CouponForm form = new CouponForm();
		// Get user coupon - if any
		Coupon coupon = getCouponService().getCoupon(user.getId());
		List<PredictionFormData> predictions = new ArrayList<PredictionFormData>();
		String tournamentId = getTournamentService().getAvailableTournaments().get(0).getId();
		
		if(coupon != null) {
			String c = coupon.getId();
			List<Prediction> preds = getPredictionService().getPredictions(user.getGroupId(), user.getId(), c);
			predictions = getFormData(tournamentId, preds);		
			// Get text from messages	
			action = "Uppdatera";
		} else {
			coupon = getCouponService().newInstance(getTournamentService().getAvailableTournaments().get(0).getId(), 
					user.getId(), user.getGroupId());
			
			List<Game> games = getGameService().getAvailableGames();
			
			for(Game game : games) {
				
				String homeTeamName = getTeamService().getTeamName(tournamentId, game.getHomeTeamId());
				String awayTeamName = getTeamService().getTeamName(tournamentId, game.getAwayTeamId());
				PredictionFormData data = getPredictionService().newFormInstance(game.getId(), homeTeamName, 
									awayTeamName, null, null);
				predictions.add(data);
			}
			// TODO: Get text from messages
			action = "Skapa";
		}
		
		form.setPredictions(predictions);
		
		//setParameters(model, principals);
		
		// command object
		model.addAttribute("form", form);
		model.addAttribute("submitAction", action);

		// return form view
		return PAGE_NAME;
	}
	
	private List<PredictionFormData> getFormData(final String tournamentId, final List<Prediction> predictions) {
		List<PredictionFormData> data = new ArrayList<PredictionFormData>();

		for(Prediction prediction : predictions) {
			Game game;
			try {
				game = getGameService().getGame(tournamentId, prediction.getGameId());
			} catch (DaoException e) {
				continue;
			}
			
			String gameId = prediction.getGameId();
			String homeTeamName = getTeamService().getTeamName(tournamentId, game.getHomeTeamId());
			String awayTeamName = getTeamService().getTeamName(tournamentId, game.getAwayTeamId());
			
			if(gameId==null || homeTeamName == null || awayTeamName == null) {
				continue;
			}
			
			PredictionFormData d = getPredictionService().newFormInstance(gameId, homeTeamName, 
					awayTeamName, prediction.getHomeScore(), prediction.getAwayScore());
			
			data.add(d);
		}
		
		return data;	
	}
	
	// Process the form.
	@RequestMapping(method = RequestMethod.POST)
	public String processForm(@ModelAttribute(value="form") @Valid List<PredictionFormData> form, BindingResult result, 
			ModelMap model, Principal principals) {
		
		System.out.println("Har klickat update/Create");
		
		
		// set custom Validation by user
	/*	validator.validate(register, result);
		if (result.hasErrors()) {
			Map<String, String> groups = getGroups();			
			
			setParameters(model, principals);
			
			// command object
			model.addAttribute("register", register);			
			model.addAttribute("groupList", groups);
			
			return PAGE_NAME;
		}
		
		// Register new User
		User user = registerService.newInstance(register.getDisplayName(), register.getUserName(), 
																		register.getPassword(), register.getGroup());
		try {
			registerService.registerUser(user);
		} catch (Exception e) {
			// Error when creating user
			Map<String, String> groups = getGroups();
			model.addAttribute("register", register);			
			model.addAttribute("groupList", groups);
			return PAGE_NAME;
		}*/
	
		//model.addAttribute("message","Välkommen till EM-tipset 2012");
		setParameters(model, principals);
		return PAGE_NAME;
	}

	@Override
	public String getCurrentPageId() {
		return PAGE_NAME;
	}
}