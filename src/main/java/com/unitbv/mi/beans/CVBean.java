package com.unitbv.mi.beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.unitbv.mi.dao.CVDAO;

@ManagedBean(name = "cvBean")
@SessionScoped
public class CVBean {

	public String apply(String position, String company, String city) {

		String username = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username");
		if (username == null) {
			return "login";
		} else if (hasCV(username)) {
			sendCV(position, company, city);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Your application was successfully sent!"));
			return "index";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Incorrect Username and Passowrd", "Please enter correct username and Password"));
			return "createCV";
		}
	}

	private void sendCV(String position, String company, String city) {
		String username = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username");
		CVDAO.sendApplication(username, position, company, city);
	}

	private boolean hasCV(String username) {
		if(CVDAO.hasCV(username))
			return true;
		return false;
	}

}
