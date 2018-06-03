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
	private static boolean isRecruiter = false;
	private String userDisplay, recruiterDisplay;

	public String getRecruiterDisplay() {
		if (isRecruiter == true)
			recruiterDisplay = " block";
		else
			recruiterDisplay = " none";
		return recruiterDisplay;
	}

	public void setRecruiterDisplay(String recruiterDisplay) {
		this.recruiterDisplay = recruiterDisplay;
	}

	public String getUserDisplay() {
		if (isRecruiter == false)
			userDisplay = " block";
		else
			userDisplay = " none";
		return userDisplay;
	}

	public void setUserDisplay(String recruiterDisplay) {
		this.userDisplay = recruiterDisplay;
	}

	public static boolean isRecruiter() {
		return isRecruiter;
	}

	public static void setRecruiter(boolean isRec) {
		isRecruiter = isRec;
	}

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

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public String validateUsernamePassword() {

		HttpSession session = SessionUtils.getSession();
		session.setAttribute("username", username);
		isLoggedIn = " block";
		isLoggedOut = " none";
		if (isRecruiter) {
			return "publishJobs";
		} else {
			return "index";
		}

	}

	public String logout() {

		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		isLoggedIn = " none";
		isLoggedOut = " block";
		isRecruiter = false;
		return "index";
	}
}