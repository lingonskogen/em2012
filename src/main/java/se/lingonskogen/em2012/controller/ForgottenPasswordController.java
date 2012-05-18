package se.lingonskogen.em2012.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import se.lingonskogen.em2012.form.ForgottenPasswordForm;

@Controller
@RequestMapping("/forgotten-password.html")
public class ForgottenPasswordController extends AbstractController {

	private static final String PAGE_NAME = "forgottenpwdpage";
	
	@RequestMapping(method = RequestMethod.GET)
	public String loadPage(final ModelMap model, final Principal principals) throws Exception {
		
		ForgottenPasswordForm form = new ForgottenPasswordForm();

		// command object
		model.addAttribute("form", form);
		
		setParameters(model, principals);
		
        return PAGE_NAME;
	}
	
	// Process the form.
	@RequestMapping(method = RequestMethod.POST)
	public String processForm(@ModelAttribute(value="form") @Valid ForgottenPasswordForm form, BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			// command object
			model.addAttribute("form", form);			

			return PAGE_NAME;
		}
		
		// TODO: Fix
		// Generate new password
		System.out.println("GENERAGE NEW PASSWORD");
		
		// Email new password to user
		System.out.println("Email new pwd to user");
		
		// Set new password on user
		System.out.println("Persist new user password");

		// TODO: Msg from file
		model.addAttribute("newPwdSent","Nytt lösenord skickat");
		model.addAttribute("form", form);
		return PAGE_NAME;
	}
	
	public String getCurrentPageId() {
		return "newpwd";
	}
}
