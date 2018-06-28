package com.unitbv.mi.beans;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.context.RequestContext;

import com.unitbv.mi.dao.CVDAO;
import com.unitbv.mi.dao.UsersDAO;

@ManagedBean(name = "cvBeanEdit")
@SessionScoped
public class CVEditBean implements Serializable {

	private static final long serialVersionUID = 2693204137924254766L;

	private List<SelectItem> domainList;
	private String selectedDomain, experience, skills, languages, city;
	private String message;

	private static final String DOMAINS = "C:\\Users\\IuliaIlie\\Desktop\\USEFUL\\licenta\\project\\jobs\\src\\main\\resources\\domains_EN.txt";

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCity() {
		String username = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("username");
		String usernameID = UsersDAO.getIdByUsername(username);
		return CVDAO.getCity(usernameID);
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getSelectedDomain() {
		String username = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("username");
		String usernameID = UsersDAO.getIdByUsername(username);
		return CVDAO.getDomain(usernameID);
	}

	public void setSelectedDomain(String selectedDomain) {
		this.selectedDomain = selectedDomain;
	}

	public String getExperience() {
		String username = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("username");
		String usernameID = UsersDAO.getIdByUsername(username);
		return CVDAO.getExperience(usernameID);
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getSkills() {
		String username = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("username");
		String usernameID = UsersDAO.getIdByUsername(username);
		return CVDAO.getSkills(usernameID);
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public String getLanguages() {
		String username = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("username");
		String usernameID = UsersDAO.getIdByUsername(username);
		return CVDAO.getLanguage(usernameID);
	}

	public void setLanguages(String languages) {
		this.languages = languages;
	}

	public List<SelectItem> getDomainList() {
		domainList = new ArrayList<>();

		try (FileReader fr = new FileReader(new File(DOMAINS)); BufferedReader br = new BufferedReader(fr);) {
			String line;
			line = br.readLine();
			while (line != null) {
				domainList.add(new SelectItem(line));
				line = br.readLine();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return domainList;
	}

	public void setDomainList(List<SelectItem> domainList) {
		this.domainList = domainList;
	}

	private boolean hasCV() {
		String username = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("username");
		if (CVDAO.hasCV(username)) {

			return true;
		}

		return false;
	}

	@SuppressWarnings("deprecation")
	public void save() {
		String username = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("username");
		if (!experience.matches("\\d+")) {
			message = "Please enter a number in experience field.";
		} else {
			String usernameID = UsersDAO.getIdByUsername(username);
			try {
				double exp = Double.parseDouble(experience);
				if (hasCV()) {
					if (CVDAO.updateCV(usernameID, languages, selectedDomain, skills, exp, city)) {
						message = "CV updated!";
					} else {
						message = "An error occured, please try again!";
					}
				} else {
					if (CVDAO.sendCV(usernameID, languages, selectedDomain, skills, exp, city)) {
						message = "CV updated!";
					} else {
						message = "An error occured, please try again!";
					}
				}
			} catch (NumberFormatException e) {
				message = "Please enter a number in experience field.";
			}

		}

		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('dlg2').show();");
	}

	@SuppressWarnings("deprecation")
	public void delete() {
		String username = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("username");
		String usernameID = UsersDAO.getIdByUsername(username);
		if (CVDAO.deleteCV(usernameID)) {
			message = "CV deleted!";
		} else {
			message = "An error occured, please try again!";
		}
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('dlg2').show();");
	}

}
