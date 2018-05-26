package com.unitbv.mi.beans;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import com.unitbv.mi.classes.PersonalData;
import com.unitbv.mi.dao.CVDAO;
import com.unitbv.mi.utils.SessionUtils;

@ManagedBean(name = "cvBean")
@SessionScoped
public class CVBean {

	private String name, lastname, email, phone, city, country;
	private List<String>	university, degree, prevCompany, sinceDate, toDate, selectedDomain, currentPosition, position, currentCompany, domain, sinceDateCurrent, selectedDomainPrev;
	
	public List<String> getPrevCompany() {
		return prevCompany;
	}

	public void setPrevCompany(List<String> prevCompany) {
		this.prevCompany = prevCompany;
	}

	private List<SelectItem> domainList;
	
	public List<String> getSelectedDomain() {
		return selectedDomain;
	}

	public void setSelectedDomain(List<String> selectedDomain) {
		this.selectedDomain = selectedDomain;
	}
	
	public List<String> getDegree() {
		return degree;
	}

	public void setDegree(List<String> degree) {
		this.degree = degree;
	}

	public List<String> getSinceDate() {
		return sinceDate;
	}

	public void setSinceDate(List<String> sinceDate) {
		this.sinceDate = sinceDate;
	}

	public List<String> getToDate() {
		return toDate;
	}

	public void setToDate(List<String> toDate) {
		this.toDate = toDate;
	}

	public List<String> getSinceDateCurrent() {
		return sinceDateCurrent;
	}

	public void setSinceDateCurrent(List<String> sinceDateCurrent) {
		this.sinceDateCurrent = sinceDateCurrent;
	}

	public List<String> getUniversity() {
		return university;
	}

	public void setUniversity(List<String> university) {
		this.university = university;
	}

	public List<String> getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(List<String> currentPositions) {
		this.currentPosition = currentPositions;
	}

	public List<String> getPosition() {
		return position;
	}

	public void setPosition(List<String> position) {
		this.position = position;
	}

	public List<String> getCurrentCompany() {
		return currentCompany;
	}

	public void setCurrentCompany(List<String> currentCompany) {
		this.currentCompany = currentCompany;
	}

	public List<String> getDomain() {
		return domain;
	}

	public void setDomain(List<String> domain) {
		this.domain = domain;
	}

	public List<String> getSelectedDomainPrev() {
		return selectedDomainPrev;
	}

	public void setSelectedDomainPrev(List<String> selectedDomainPrev) {
		this.selectedDomainPrev = selectedDomainPrev;
	}

	public List<SelectItem> getDomainList() {
		return domainList;
	}

	public void setDomainList(List<SelectItem> domainList) {
		this.domainList = domainList;
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

		try (FileReader fr = new FileReader(new File("D:\\USEFUL\\licenta\\project\\jobs\\src\\main\\resources\\countries_EN.txt"));
				BufferedReader br = new BufferedReader(fr);) {
			String line;
			line = br.readLine();
			while (line != null) {
				countryList.add(line);
				line = br.readLine();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return countryList;
	}

	public void setCountryList(List<String> countryList) {
		this.countryList = countryList;
	}

	private List<String> countryList;

	public String apply() {

		String position = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("position");
		String company = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("company");
		String city = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("city");
		String username = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username");
		if (username == null) {
			return "login";
		} else if (hasCV(username)) {
			sendCV(position, company, city);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Your application was successfully sent!"));
			return "index";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "No CV available",
					"You seem to have no CV uploaded. Please add your CV using CV Tool."));
			return "createCV";
		}
	}

	private void sendCV(String position, String company, String city) {
		String username = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username");
		CVDAO.sendApplication(username, position, company, city);
	}

	private boolean hasCV(String username) {
		if (CVDAO.hasCV(username))
			return true;
		return false;
	}

	public void finishCV() {
		PersonalData personalData = new PersonalData(name, lastname, email, phone, city, country);
		HttpSession session = SessionUtils.getSession();
		session.setAttribute("personalData", personalData);
	}
}
