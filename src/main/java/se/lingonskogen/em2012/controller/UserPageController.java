package se.lingonskogen.em2012.controller;

import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import se.lingonskogen.em2012.domain.User;

@Controller
@RequestMapping("/mypage.html")
public class UserPageController extends AbstractController {

	private static final String PAGE_NAME = "mypage";
		
	@RequestMapping(method = RequestMethod.GET)
	public String showPage(final ModelMap model, final Principal principal) {
		User user = null;
		if(principal != null) {
			String name = principal.getName();
			user = getUserService().getUser(name);
		}
		
		String groupName = getGroupService().getGroupName(user.getGroupId());
		
		model.addAttribute("user", user);
		model.addAttribute("groupName", groupName);
		model.addAttribute("position", getPosition(user));
		model.addAttribute("totalUsers", getTotalNumUsers(user.getGroupId()));
		model.addAttribute("hasCoupon", hasCoupon(user));
		model.addAttribute("couponUrl", getCouponUrl(user.getId()));
		
		setParameters(model, principal);
		return PAGE_NAME;
	}	

	@Override
	public String getCurrentPageId() {
		return PAGE_NAME;
	}
}