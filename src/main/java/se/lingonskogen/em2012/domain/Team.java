package se.lingonskogen.em2012.domain;

public class Team extends Bean {
    public static final String NAME = "name";
    
    public static final String CODE = "code";

    public static final String GROUP = "group";
    
	private String tournamentId;

	private String name;
	
	private String code;

	private String group;
	
	public String getTournamentId() {
		return tournamentId;
	}

	public void setTournamentId(String tournamentId) {
		this.tournamentId = tournamentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getCode()
    {
        return code;
    }

    public String getGroup()
    {
        return group;
    }

    public void setGroup(String group)
    {
        this.group = group;
    }

}
