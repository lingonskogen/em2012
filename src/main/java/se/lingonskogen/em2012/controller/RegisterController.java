package se.lingonskogen.em2012.controller;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import se.lingonskogen.em2012.domain.Group;
import se.lingonskogen.em2012.domain.User;
import se.lingonskogen.em2012.form.RegisterForm;
import se.lingonskogen.em2012.services.RegisterService;
import se.lingonskogen.em2012.validator.RegistrationValidator;

@Controller
@RequestMapping("/register.html")
public class RegisterController extends AbstractController {

	private RegistrationValidator validator;
    private RegisterService registerService;

    private static final String PAGE_NAME = "register";
    
	@RequestMapping(method = RequestMethod.GET)
	public String initForm(final ModelMap model, final Principal principals) {

		RegisterForm register = new RegisterForm();

		Map<String, String> groups = getGroups();
		
		setParameters(model, principals);
		
		// command object
		model.addAttribute("register", register);
		model.addAttribute("groupList", groups);

		// return form view
		return PAGE_NAME;
	}

	private Map<String,String> getGroups() {
		Map<String, String> groups = new LinkedHashMap<String, String>();
		
		for (Group group : registerService.getAvailableGroups()) {
			groups.put(group.getId(), group.getName());
		}

		return groups;
	}
	
	// Process the form.
	@RequestMapping(method = RequestMethod.POST)
	public String processForm(@ModelAttribute(value="register") @Valid RegisterForm register, BindingResult result, 
			ModelMap model, Principal principals) {
		// set custom Validation by user
		validator.validate(register, result);
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
		}
	
		model.addAttribute("message","Välkommen till EM-tipset 2012");
		return "start";
	}
	
	public void setValidator(final RegistrationValidator validator) {
		this.validator = validator;
	}
	
	public RegistrationValidator getValidator() {
		return validator;
	}
	
	public void setRegisterService(final RegisterService registerService) {
		this.registerService = registerService;
	}

	@Override
	public String getCurrentPageId() {
		return PAGE_NAME;
	}
}