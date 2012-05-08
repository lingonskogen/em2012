package se.lingonskogen.em2012.domain;

public class Tournament extends Bean {
	public static final String NAME = "name";

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
