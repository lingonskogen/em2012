package se.lingonskogen.em2012.services.impl;

import java.util.List;

import se.lingonskogen.em2012.domain.Group;
import se.lingonskogen.em2012.domain.User;
import se.lingonskogen.em2012.services.GroupService;
import se.lingonskogen.em2012.services.RegisterService;
import se.lingonskogen.em2012.services.UserService;

public class RegisterServiceImpl implements RegisterService {

	private UserService userService;
	private GroupService groupService;
	
	public void registerUser(final User user) throws Exception {
		userService.createUser(user);
		
	}	
	
	public User newInstance(final String realName, final String userName, final String password, final String groupId) {
		User user = new User();
		user.setGroupId(groupId);
		user.setPassword(password);
		user.setRealName(realName);
		user.setUserName(userName);

		return user;
	}
	
	public List<Group> getAvailableGroups() {
		return groupService.getAvailableGroups();
	}

	
	public void setUserService(final UserService userService) {
		this.userService = userService;
	}
	
	public void setGroupService(final GroupService groupService) {
		this.groupService = groupService;
	}
}

