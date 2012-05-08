package se.lingonskogen.em2012.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import se.lingonskogen.em2012.form.RegisterForm;

@Component
public class RegistrationValidator implements Validator {
	@Override
	public boolean supports(Class<?> klass) {
		return RegisterForm.class.isAssignableFrom(klass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		RegisterForm registration = (RegisterForm) target;

		if (!(registration.getPassword()).equals(registration.getConfirmPassword())) {
			errors.rejectValue("password", "matchingPassword.registration.password");
		}
	}
}