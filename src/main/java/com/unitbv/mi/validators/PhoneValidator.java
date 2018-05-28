package com.unitbv.mi.validators;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("phone")
public class PhoneValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String number = value.toString();
		final ResourceBundle bundle;
		final FacesMessage msg;
		if (!(number.length() == 10 && number.matches("\\d+") && number.charAt(0)=='0')) {
			bundle = ResourceBundle.getBundle("ApplicationResources");
			msg = new FacesMessage(bundle.getString("phoneLength"));
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
	}
}
