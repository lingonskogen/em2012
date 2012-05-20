package se.lingonskogen.em2012.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import se.lingonskogen.em2012.domain.Game;
import se.lingonskogen.em2012.domain.Prediction;
import se.lingonskogen.em2012.domain.User;
import se.lingonskogen.em2012.form.StatisticsFormData;
import se.lingonskogen.em2012.form.TopListData;


@Controller
@RequestMapping("/statistics.html")
public class StatisticsPageController extends AbstractController {	
	
	private static final String PAGE_NAME = "statpage";
	
	@RequestMapping(method = RequestMethod.GET)
	public String showPage(final ModelMap model, final Principal principal) {
		User user = null;
		if(principal != null) {
			String name = principal.getName();
			user = getUserService().getUser(name);
		}
		
		StatisticsFormData data = new StatisticsFormData(); 
		List<Game> games = getGameService().getAvailableGames();
		List<TopListData> topList = getTopList("ateles");
		
		data.setGames(games);
		data.setTopList(topList);
		data.setUserPredictions(getUserPredictions("ateles"));
		
		model.addAttribute("data", data);
		setParameters(model, principal);
		return PAGE_NAME;
	}

	private List<TopListData> getTopList(final String groupId) {
		List<TopListData> data = new ArrayList<TopListData>();
		List<Game> games = getGameService().getAvailableGames();
		List<User> users = getUserService().getUsers(groupId);
		
		for (User user : users) {
			TopListData tld = new TopListData();
			tld.setPoints(getScore(user, games));
			tld.setUserId(user.getId());
			tld.setUserRealName(user.getRealName());
		}
		
		return data;
	}
	
	private Map<String, List<Prediction>> getUserPredictions(final String groupId) {
		Map<String, List<Prediction>> data = new HashMap<String, List<Prediction>> ();
		
		List<User> users = getUserService().getUsers(groupId);
		
		for(User user : users) {
			List<Prediction> preds = getPredictionService().getPredictions(groupId, user.getId());
			data.put(user.getId(), preds);
		}
		return data;			
	}
	
	@Override
	public String getCurrentPageId() {
		return "statistics";
	}
}