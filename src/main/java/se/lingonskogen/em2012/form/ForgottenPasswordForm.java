package se.lingonskogen.em2012.form;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class ForgottenPasswordForm {

	@NotEmpty
	@Email
	private String userName = null;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}