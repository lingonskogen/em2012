package se.lingonskogen.em2012.form.admin;

import org.hibernate.validator.constraints.NotEmpty;

public class TournamentForm {
	@NotEmpty
	private String name = null;

	public void setName(final String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}