package se.lingonskogen.em2012.domain;

public class User extends Bean
{
    public static final String REALNAME = "realname";
    
    public static final String USERNAME = "username";
    
    public static final String PASSWORD = "password";
    
    private String groupId;
    
    private String realname;
    
    private String username;
    
    private String password;

    public String getGroupId()
    {
        return groupId;
    }

    public void setGroupId(String groupId)
    {
        this.groupId = groupId;
    }

    public String getRealname()
    {
        return realname;
    }

    public void setRealname(String realname)
    {
        this.realname = realname;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
    
}
