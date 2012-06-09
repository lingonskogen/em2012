package se.lingonskogen.em2012.controller;

import java.security.Principal;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import se.lingonskogen.em2012.domain.Game;
import se.lingonskogen.em2012.domain.Team;
import se.lingonskogen.em2012.form.GroupsForm;
import se.lingonskogen.em2012.form.GroupsForm.TeamForm;
import se.lingonskogen.em2012.form.LoginForm;

@Controller
@RequestMapping("/login.html")
public class LoginController extends AbstractController {

	private static final String PAGE_NAME = "login";
	
	@RequestMapping(method = RequestMethod.GET)
	public String initForm(final ModelMap model, final Principal principals) {

		setParameters(model, principals);
		List<Game> games = getGameService().getAvailableGames();
        Map<String, Integer> sMap = new HashMap<String, Integer>();
        Map<String, Integer> vMap = new HashMap<String, Integer>();
        Map<String, Integer> oMap = new HashMap<String, Integer>();
        Map<String, Integer> fMap = new HashMap<String, Integer>();
		for (Game game : games)
        {
		    String homeTeamId = game.getHomeTeamId();
		    String awayTeamId = game.getAwayTeamId();
            Long homeScore = game.getHomeScore();
            Long awayScore = game.getAwayScore();
            if (homeScore != null && awayScore != null)
            {
                initMaps(homeTeamId, sMap, vMap, oMap, fMap);
                initMaps(awayTeamId, sMap, vMap, oMap, fMap);
                sMap.put(homeTeamId, sMap.get(homeTeamId)+1);
                sMap.put(awayTeamId, sMap.get(awayTeamId)+1);
                if (homeScore > awayScore)
                {
                    vMap.put(homeTeamId, vMap.get(homeTeamId)+1);
                    fMap.put(awayTeamId, vMap.get(awayTeamId)+1);
                }
                else if (homeScore < awayScore)
                {
                    vMap.put(awayTeamId, vMap.get(awayTeamId)+1);
                    fMap.put(homeTeamId, vMap.get(homeTeamId)+1);
                }
                else if (homeScore == awayScore)
                {
                    oMap.put(homeTeamId, vMap.get(homeTeamId)+1);
                    oMap.put(awayTeamId, vMap.get(awayTeamId)+1);
                }
            }
        }
		GroupsForm groupsForm = new GroupsForm();
		List<Team> teams = getTeamService().getAvailableTeams();
		for (Team team : teams)
        {
            TeamForm teamForm = new TeamForm(team.getName());
            Integer s = sMap.get(team.getId());
            Integer v = vMap.get(team.getId());
            Integer o = oMap.get(team.getId());
            Integer f = fMap.get(team.getId());
            teamForm.setS(s == null ? 0 : s);
            teamForm.setV(v == null ? 0 : v);
            teamForm.setO(o == null ? 0 : o);
            teamForm.setF(f == null ? 0 : f);
            if ("A".equals(team.getGroup()))
            {
                groupsForm.getGroupA().getTeams().add(teamForm);
            }
            else if ("B".equals(team.getGroup()))
            {
                groupsForm.getGroupB().getTeams().add(teamForm);
            }
            else if ("C".equals(team.getGroup()))
            {
                groupsForm.getGroupC().getTeams().add(teamForm);
            }
            else if ("D".equals(team.getGroup()))
            {
                groupsForm.getGroupD().getTeams().add(teamForm);
            }
        }
		Comparator<TeamForm> comparator = new Comparator<TeamForm>()
        {
            @Override
            public int compare(TeamForm o1, TeamForm o2)
            {
                return o2.getP() - o1.getP();
            }
        };
        Collections.sort(groupsForm.getGroupA().getTeams(), comparator);
        Collections.sort(groupsForm.getGroupB().getTeams(), comparator);
        Collections.sort(groupsForm.getGroupC().getTeams(), comparator);
        Collections.sort(groupsForm.getGroupD().getTeams(), comparator);
		model.addAttribute("groupsFrom", groupsForm);
		// return form view
		return PAGE_NAME;
	}
	
	private void initMaps(String id, Map<String, Integer>... maps)
    {
	    for (Map<String, Integer> map : maps)
        {
            if (!map.containsKey(id))
            {
                map.put(id, 0);
            }
        }
    }

    // Process the login form.
	@RequestMapping(method = RequestMethod.POST)
	public String processForm(@ModelAttribute(value="login") @Valid LoginForm login, BindingResult result, ModelMap model, Principal principals) {
		setParameters(model, principals);
		
		return "start";
	}

	public String getCurrentPageId() {
		return PAGE_NAME;
	}
}