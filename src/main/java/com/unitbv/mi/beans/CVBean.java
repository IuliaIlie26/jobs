package com.unitbv.mi.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.unitbv.mi.dao.CVDAO;

@ManagedBean(name = "cvBean")
@SessionScoped
public class CVBean {

	private String name, lastname, email, phone, city, country;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public List<String> getCountryList() {
		countryList = new ArrayList<>();
		countryList.add("Romania");
		return countryList;
	}

	public void setCountryList(List<String> countryList) {
		this.countryList = countryList;
	}

	private List<String> countryList;
	
	public String apply() {

		String position = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("position");
		String company = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("company");
		String city = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("city");
		String username = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("username");
		if (username == null) {
			return "login";
		} else if (hasCV(username)) {
			sendCV(position, company, city);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Your application was successfully sent!"));
			return "index";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"No CV available", "You seem to have no CV uploaded. Please add your CV using CV Tool."));
			return "createCV";
		}
	}

	private void sendCV(String position, String company, String city) {
		String username = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("username");
		CVDAO.sendApplication(username, position, company, city);
	}

	private boolean hasCV(String username) {
		if (CVDAO.hasCV(username))
			return true;
		return false;
	}

	public void next() {
		
	}
}
