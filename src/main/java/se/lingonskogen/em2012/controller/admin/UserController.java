package se.lingonskogen.em2012.controller.admin;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import se.lingonskogen.em2012.domain.User;
import se.lingonskogen.em2012.form.admin.SearchForm;

@Controller
@RequestMapping("/admin/userpage.html")
public class UserController extends AbstractController {

	final private static String USER_PAGE = "userpage";
	
	@RequestMapping(method = RequestMethod.GET)
	public String initForm(ModelMap model) {
		SearchForm searchForm = new SearchForm();
		setParameters(model, searchForm);
		return USER_PAGE;
	}

	// Process the search form.
	@RequestMapping(method = RequestMethod.POST)
	public String processForm(@ModelAttribute(value="searchForm") @Valid SearchForm searchForm, BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			System.out.println("searchForm-error in UserController");
		}
				
		setParameters(model, searchForm);
		return USER_PAGE;
	}
	
	private Map<String, UserPageInfo> getUsers(final String groupId) {
		Map<String, UserPageInfo> usersInfo = new HashMap<String, UserPageInfo>();
		
		
		List<User> users = groupId == null ? getUserService().getUsers() : getUserService().getUsers(groupId);  
		
		for (User user : users) {
			String gName = getGroupService().getGroupName(user.getGroupId());
			String rName = user.getRealName();
			String uName = user.getUserName();

			usersInfo.put(user.getId()+uName+gName, new UserPageInfo(gName, uName, rName));
		}

		return usersInfo;
	}
		
	private void setParameters(final ModelMap model, final SearchForm searchForm) {
		String groupId = searchForm.getGroupId();
		if(groupId == null || groupId.equals("default")) {
			groupId = null;
		} else {
			groupId = searchForm.getGroupId();
		}

		Map<String, String> availableGroups = getAvailableGroups();
		Map<String, UserPageInfo> availableUsers = getUsers(groupId);
		
		availableGroups.put("default", "Choose group");
		
		// command object
		model.addAttribute("availableUsers", availableUsers);
		model.addAttribute("availableGroups", availableGroups);
		model.addAttribute("searchForm", searchForm);
	}

	public class UserPageInfo {
		private String groupName;
		private String userName;
		private String realName;
		
		public UserPageInfo(final String groupName, final String userName, final String realName) {
			this.groupName = groupName;
			this.userName = userName;
			this.realName = realName;
		}
		
		public String getGroupName() {
			return groupName;
		}
		public void setGroupName(String groupName) {
			this.groupName = groupName;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getRealName() {
			return realName;
		}
		public void setRealName(String realName) {
			this.realName = realName;
		}
	}

}