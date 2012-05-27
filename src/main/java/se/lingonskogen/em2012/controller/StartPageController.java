package se.lingonskogen.em2012.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/start.html")
public class StartPageController extends AbstractController {

	@RequestMapping(method = RequestMethod.GET)
	public String start(final ModelMap model, final Principal principal) throws Exception {   
	    setParameters(model, principal);
	    
	    if(isRegistrationOpen()) {
	    	return "prestartpage";
	    }    
	    
	    // Setup toplist elements
	    model.addAttribute("groupToplist", getToplist("ateles", 5));
	    model.addAttribute("globalToplist", getToplist(5));
	    
	    model.addAttribute("nextGames", getNextGames(2));
	    return "startpage";
	}

	@Override
	public String getCurrentPageId() {
		return "start";
	}
}
