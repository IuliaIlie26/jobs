package com.unitbv.mi.validators;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import com.unitbv.mi.dao.UsersDAO;
import com.unitbv.mi.utils.MD5EncryptionUtils;

@FacesValidator("login")
public class LoginValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String username = (String) value;
		UIInput uiInputConfirmPassword = (UIInput) component.getAttributes().get("password");
		String password="";
		final ResourceBundle bundle;
		final FacesMessage msg;
		try {
			password = MD5EncryptionUtils.encrypt(uiInputConfirmPassword.getSubmittedValue().toString());
		} catch (NoSuchAlgorithmException | NoSuchProviderException e) {
			bundle = ResourceBundle.getBundle("ApplicationResources");
			msg = new FacesMessage(bundle.getString("DBerror"));
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
		boolean valid = UsersDAO.validate(username, password);
		if (!valid) {
			bundle = ResourceBundle.getBundle("ApplicationResources");
			msg = new FacesMessage(bundle.getString("failed"));
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
	}
}