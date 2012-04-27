package se.lingonskogen.em2012.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import se.lingonskogen.em2012.form.Register;
import se.lingonskogen.em2012.services.RegisterService;

@Controller
@SessionAttributes
public class RegisterController {

	private RegisterService registerService;
		
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addContact(@ModelAttribute("register")
                            Register register, BindingResult result) {
 
        //System.out.println("First Name:" + contact.getFirstname() +
          //          "Last Name:" + contact.getLastname());
 
        return "redirect:registers.html";
    }
 
    @RequestMapping("/registers")
    public ModelAndView showContacts() {
 
        return new ModelAndView("register", "command", new Register());
    }
	
	
	

	/*public ModelAndView handleRequest(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		
		registerService.registerUser();
		
		return new ModelAndView("register", "register",  null);
	}

	public void setRegisterService(final RegisterService registerService) {
		this.registerService = registerService;
	}

	public Class<? extends Annotation> annotationType() {
		// TODO Auto-generated method stub
		return null;
	}

	public String value() {
		// TODO Auto-generated method stub
		return null;
	}
*/

}
