package se.lingonskogen.em2012.controller;

import java.security.Principal;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import se.lingonskogen.em2012.domain.User;
import se.lingonskogen.em2012.form.ForgottenPasswordForm;
import se.lingonskogen.em2012.services.impl.MailService;
import se.lingonskogen.em2012.services.impl.MailService.Substitution;
import se.lingonskogen.em2012.services.impl.MailService.Template;

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
	public String processForm(@ModelAttribute(value="form") @Valid ForgottenPasswordForm form, BindingResult result, ModelMap model, Principal principals) throws Exception {

		setParameters(model, principals);
		
		if (result.hasErrors()) {
			// command object
			model.addAttribute("form", form);			
			return PAGE_NAME;
		}
		String userName = form.getUserName();
		User user = getUserService().getUser(userName);
		if (user == null) {
            model.addAttribute("form", form);
            
            ObjectError err = new FieldError("form", "userName", 
            		getMessageSource().getMessage("pwdPage.noSuchUser", null, new Locale(DEFAULT_LANG)));
            result.addError(err);
		    
            return PAGE_NAME;
		}
		
		String password = Long.toHexString(System.currentTimeMillis()).toLowerCase();
        user.setPassword(password);
        getUserService().updateUser(user);

        MailService mailService = new MailService();
        mailService.setSubstitution(Substitution.PASSWORD, password);
        mailService.sendMail(user, Template.NEW_PWD, true);
        
		model.addAttribute("newPwdSent", getMessageSource().getMessage("pwdPage.pwdSent", null, new Locale(DEFAULT_LANG)));
		model.addAttribute("form", form);
		return PAGE_NAME;
	}
	
	public String getCurrentPageId() {
		return "newpwd";
	}
}
