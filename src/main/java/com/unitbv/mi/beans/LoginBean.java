package com.unitbv.mi.beans;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

import com.unitbv.mi.dao.UsersDAO;
import com.unitbv.mi.utils.SessionUtils;

@ManagedBean(name = "login")
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1094801825228386363L;

	private String password;
	private String msg;
	private String username;
	private static String isLoggedIn = " none";
	private String nameAndLastname = "";

	public String getNameAndLastname() {
		nameAndLastname = UsersDAO.getNameAndLastname(username);
		return nameAndLastname;
	}

	public void setNameAndLastname(String nameAndLastname) {
		this.nameAndLastname = nameAndLastname;
	}

	public String getIsLoggedIn() {
		return isLoggedIn;
	}

	public void setIsLoggedIn(String isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	public String getIsLoggedOut() {
		return isLoggedOut;
	}

	public void setIsLoggedOut(String isLoggedOut) {
		this.isLoggedOut = isLoggedOut;
	}

	private static String isLoggedOut = " block";

	public void setPassword(String password) {
		this.password = password;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getPassword() {
		return password;
	}

	public String getMsg() {
		return msg;
	}

	public String getUsername() {
		return username;
	}

	// validate login
	public String validateUsernamePassword() {
		boolean valid = UsersDAO.validate(username, password);
		if (valid) {
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("username", username);
			isLoggedIn = " block";
			isLoggedOut = " none";
			return "index";
		} else {
			// TODO internationalizare!
			RequestContext.getCurrentInstance().execute("alert('Incorrect username or password!');");
			return "index";
		}
	}

	public String logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		isLoggedIn = " none";
		isLoggedOut = " block";
		return "index";
	}
}