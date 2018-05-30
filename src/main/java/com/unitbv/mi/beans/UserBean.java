package com.unitbv.mi.beans;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpSession;
import com.unitbv.mi.dao.UsersDAO;
import com.unitbv.mi.utils.MD5EncryptionUtils;
import com.unitbv.mi.utils.SessionUtils;

@ManagedBean(name = "userBean")
@SessionScoped
public class UserBean implements Serializable {

	private static final long serialVersionUID = 2608783053609184582L;
	private String name, lastname, email, username, password, passwordRepeat, phone;
	private String nameAndLastname = "";
	private String selectedMyAccount;
	private String oldPassword;

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getSelectedMyAccount() {
		return selectedMyAccount;
	}

	public void setSelectedMyAccount(String selectedMyAccount) {
		this.selectedMyAccount = selectedMyAccount;
	}

	public String getNameAndLastname() {
		nameAndLastname = UsersDAO.getNameAndLastname(username);
		return nameAndLastname;
	}

	public void setNameAndLastname(String nameAndLastname) {
		this.nameAndLastname = nameAndLastname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordRepeat() {
		return passwordRepeat;
	}

	public void setPasswordRepeat(String passwordRepeat) {
		this.passwordRepeat = passwordRepeat;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String register() {
		System.out.println("intra");
		try {
			password = MD5EncryptionUtils.encrypt(password);
			boolean valid = UsersDAO.validateRegistration(name, lastname, username, password, phone, email);
			if (valid) {
				HttpSession session = SessionUtils.getSession();
				session.setAttribute("username", username);
				LoginBean.setIsLoggedIn("block");
				LoginBean.setIsLoggedOut("none");
				return "index";
			} else
				throw new Exception("Not valid");
		} catch (Exception e) {
			final ResourceBundle bundle = ResourceBundle.getBundle("ApplicationResources");
			final FacesMessage msg = new FacesMessage(bundle.getString("DBerror"));
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
	}

	public void saveChanges() {

		String username = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username");
		boolean ok = true;
		if (username != null) {
			if (name != null) {
				ok = ok && UsersDAO.update(name, username, "name");
			}
			if (lastname != null) {
				ok = ok && UsersDAO.update(lastname, username, "lastname");
			}

			if (email != null) {
				ok = ok && UsersDAO.update(email, username, "email");
			}

			if (phone != null) {
				ok = ok && UsersDAO.update(phone, username, "phone");
			}

		}

		if (!ok) {
			final ResourceBundle bundle = ResourceBundle.getBundle("ApplicationResources");
			final FacesMessage msg = new FacesMessage(bundle.getString("DBerror"));
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		}
		if (!ok) {
			final ResourceBundle bundle = ResourceBundle.getBundle("ApplicationResources");
			final FacesMessage msg = new FacesMessage(bundle.getString("updateOK"));
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		}

	}

	public void changePassword() {
		String username = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username");
		if (UsersDAO.validate(username, oldPassword)) {
			try {
				password = MD5EncryptionUtils.encrypt(password);
				boolean ok = UsersDAO.update(password, username, "password");
				if (!ok)
					throw new Exception();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else {
			final ResourceBundle bundle = ResourceBundle.getBundle("ApplicationResources");
			final FacesMessage msg = new FacesMessage(bundle.getString("loginError"));
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		}

	}
}
