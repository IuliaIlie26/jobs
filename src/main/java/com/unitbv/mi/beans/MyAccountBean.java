package com.unitbv.mi.beans;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.primefaces.context.RequestContext;

import com.unitbv.mi.dao.UsersDAO;
import com.unitbv.mi.utils.MD5EncryptionUtils;

@ManagedBean(name = "myAccount")
@SessionScoped
public class MyAccountBean implements Serializable {

	private static final long serialVersionUID = -224231964742009233L;
	private String name, lastname, email, password, passwordRepeat, phone;
	private String selectedMyAccount;
	private String oldPassword;
	private boolean showPopup;

	public void show() {
		showPopup = true;
	}

	public void hide() {
		showPopup = false;
	}

	public boolean isShowPopup() {
		return showPopup;
	}

	public void setShowPopup(boolean showPopup) {
		this.showPopup = showPopup;
	}

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

	public String getName() {
		String username = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username");
		return UsersDAO.select("name", username);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		String username = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username");
		return UsersDAO.select("lastname", username);
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		String username = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username");
		return UsersDAO.select("email", username);
	}

	public void setEmail(String email) {
		this.email = email;
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
		String username = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username");
		return UsersDAO.select("phone", username);
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void saveChanges() {
		boolean result = true;

		try {
			InternetAddress emailAddr = new InternetAddress(email);
			emailAddr.validate();
		} catch (AddressException ex) {
			result = false;
		}

		if (!(phone.length() == 10 && phone.matches("\\d+") && phone.charAt(0) == '0')) {
			result = false;
		}

		if (result) {
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
				if (ok) {

					RequestContext.getCurrentInstance().execute("alert('ok')");
				}
			}
		} else {

			RequestContext.getCurrentInstance().execute("alert('nu')");
		}
	}

	public void changePassword() {
		try {
			oldPassword = MD5EncryptionUtils.encrypt(oldPassword);
		} catch (NoSuchAlgorithmException | NoSuchProviderException e1) {
			e1.printStackTrace();
		}
		String username = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username");
		if (UsersDAO.validate(username, oldPassword)==1) {
			try {
				password = MD5EncryptionUtils.encrypt(password);
				boolean ok = UsersDAO.update(password, username, "password");
				if (!ok)
					throw new Exception();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			final ResourceBundle bundle = ResourceBundle.getBundle("ApplicationResources");
			final FacesMessage msg = new FacesMessage(bundle.getString("loginError"));
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		}

	}
}
