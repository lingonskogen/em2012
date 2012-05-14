package se.lingonskogen.em2012.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StartPageController {

	@RequestMapping("/start")
	public String start(ModelMap model, Principal principal) throws Exception {
	    if (principal == null)
	    {
	        model.addAttribute("username", "Guest");
	    }
	    else
	    {
            model.addAttribute("username", principal.getName());
	    }
        return "start";
	}
}
