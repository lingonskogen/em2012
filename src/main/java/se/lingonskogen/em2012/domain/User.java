package se.lingonskogen.em2012.domain;

public class User extends Bean {
	public static final String REALNAME = "realName";

	// Username is email
	public static final String USERNAME = "userName"; 

	public static final String PASSWORD = "password";

	public static final String PAID = "paid";

	private String groupId;

	private String realName;

	private String userName;

	private String password;

	private Boolean paid = Boolean.FALSE;

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPaid(Boolean paid)
	{
	    this.paid = paid;
    }

    public Boolean getPaid()
    {
        return paid;
    }

}
