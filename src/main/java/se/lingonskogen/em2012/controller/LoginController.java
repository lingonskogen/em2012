package se.lingonskogen.em2012.controller;

import java.security.Principal;

import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import se.lingonskogen.em2012.form.LoginForm;

@Controller
@RequestMapping("/login.html")
public class LoginController extends AbstractController {

	@RequestMapping(method = RequestMethod.GET)
	public String initForm(final ModelMap model, final Principal principals) {

		setParameters(model, principals);
		// return form view
		return "login";
	}
	
	// Process the login form.
	@RequestMapping(method = RequestMethod.POST)
	public String processForm(@ModelAttribute(value="login") @Valid LoginForm login, BindingResult result, ModelMap model, Principal principals) {
		setParameters(model, principals);
		return "start";
	}

	public String getCurrentPageId() {
		return "login";
	}
}