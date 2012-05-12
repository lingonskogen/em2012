package se.lingonskogen.em2012.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/admin/userpage.html")
public class UserController extends AbstractController {

	final private static String USER_PAGE = "userpage";
	
	@RequestMapping(method = RequestMethod.GET)
	public String initForm(ModelMap model) {

		
		// return form view
		return USER_PAGE;
	}

}
