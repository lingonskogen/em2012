package se.lingonskogen.em2012.controller;

import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/rules.html")

public class RulesPageController extends AbstractController {

	private static final String PAGE_NAME = "rulespage";
	
	@RequestMapping(method = RequestMethod.GET)
	public String showPage(final ModelMap model, final Principal principal) {

		setParameters(model, principal);
		return PAGE_NAME;
	}

	@Override
	public String getCurrentPageId() {
		return "rules";
	}
}