package com.unitbv.mi.validators;

import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("password")
public class PasswordValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		UIInput uiInputConfirmPassword = (UIInput) component.getAttributes().get("confirmationPassword");
		final String confirmationPassword = uiInputConfirmPassword.getSubmittedValue().toString();
		final String password = (String) value;
		final ResourceBundle bundle = ResourceBundle.getBundle("ApplicationResources");
		final FacesMessage msg;
		if (!password.matches("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$")) {
			msg = new FacesMessage(bundle.getString("passwordPolicy"));
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		} else if (!password.equals(confirmationPassword)) {
			msg = new FacesMessage(bundle.getString("match"));
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}

	}

}
