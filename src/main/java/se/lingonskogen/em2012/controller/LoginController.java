package se.lingonskogen.em2012.controller;

import java.util.LinkedHashMap;
import java.util.Map;

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
public class LoginController {

	@RequestMapping(method = RequestMethod.GET)
	public String initForm(ModelMap model) {

		LoginForm login = new LoginForm();

		// command object
		model.addAttribute("loginForm", login);

		// return form view
		return "login";
	}
	
	// Process the login form.
	@RequestMapping(method = RequestMethod.POST)
	public String processForm(@ModelAttribute(value="login") @Valid LoginForm login, BindingResult result, ModelMap model) {
		// Login user.
		// If not logged in - show login page
		// If logger in - show start page
		return "start";
	}
	
/*	public void setValidator(final LoginValidator validator) {
	
		this.validator = validator;
	}
	
	public RegistrationValidator getValidator() {
		return validator;
	}*/
}