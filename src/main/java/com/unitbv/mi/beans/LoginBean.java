package com.unitbv.mi.beans;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;
import com.unitbv.mi.dao.UsersDAO;
import com.unitbv.mi.utils.SessionUtils;

@ManagedBean(name = "login")
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1094801825228386363L;

	private String password;
	private String username;
	private static String isLoggedIn = " none";
	private static String isLoggedOut = " block";
	private String nameAndLastname = null;

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

	public static void setIsLoggedIn(String log) {
		isLoggedIn = log;
	}

	public String getIsLoggedOut() {
		return isLoggedOut;
	}

	public static void setIsLoggedOut(String log) {
		isLoggedOut = log;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String user) {
		this.username = user;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public void validateUsernamePassword() {

		HttpSession session = SessionUtils.getSession();
		session.setAttribute("username", username);
		isLoggedIn = " block";
		isLoggedOut = " none";

	}

	public void logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		isLoggedIn = " none";
		isLoggedOut = " block";
	}
}