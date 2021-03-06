package se.lingonskogen.em2012.validator.admin;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import se.lingonskogen.em2012.form.admin.GameForm;


@Component
public class GameValidator implements Validator {

	@Override
	public boolean supports(Class<?> klass) {
		return GameForm.class.isAssignableFrom(klass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		GameForm gameForm = (GameForm) target;

		// Is date valid date?
		
		if(gameForm.getAwayTeamId().equals(gameForm.getHomeTeamId())) {
			errors.reject("awayTeamId", "matching.newgame.teams");
		}
		
	}
}