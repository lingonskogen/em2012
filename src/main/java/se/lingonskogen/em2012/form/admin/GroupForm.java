package se.lingonskogen.em2012.form.admin;

import org.hibernate.validator.constraints.NotEmpty;

public class GroupForm {
	@NotEmpty
	private String groupName = null;

	public void setGroupName(final String groupName) {
		this.groupName = groupName;
	}
	
	public String getGroupName() {
		return groupName;
	}
}