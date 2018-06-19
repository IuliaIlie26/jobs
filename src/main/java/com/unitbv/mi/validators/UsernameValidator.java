package com.unitbv.mi.validators;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import com.unitbv.mi.dao.UsersDAO;

@FacesValidator("username")
public class UsernameValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String username = (String) value;
		if (username == null || username.equals("")) {
			final ResourceBundle bundle = ResourceBundle.getBundle("ApplicationResources");

			final FacesMessage msg = new FacesMessage(bundle.getString("enterUsername"));
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
			if (UsersDAO.getIdByUsername(username) != null) {
				final ResourceBundle bundle = ResourceBundle.getBundle("ApplicationResources");

				final FacesMessage msg = new FacesMessage(bundle.getString("userTaken"));
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(msg);
			}

	}

}
