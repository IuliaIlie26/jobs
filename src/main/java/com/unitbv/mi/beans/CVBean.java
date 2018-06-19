package com.unitbv.mi.beans;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.unitbv.mi.dao.ApplicationsDAO;
import com.unitbv.mi.dao.CVDAO;
import com.unitbv.mi.dao.UsersDAO;

@ManagedBean(name = "cvBean")
@SessionScoped
public class CVBean implements Serializable {

	private static final long serialVersionUID = 2693204137924254766L;

	private List<SelectItem> domainList;
	private String selectedDomain, experience, skills, languages, city;
	private String message;
	private static final String PATH = "C:\\Users\\IuliaIlie\\Desktop\\USEFUL\\98. Servers\\payara-4.1.2.174\\payara41\\glassfish\\domains\\licenta\\docroot\\htdocs\\cv";
	private static final String DOMAINS = "C:\\Users\\IuliaIlie\\Desktop\\USEFUL\\licenta\\project\\jobs\\src\\main\\resources\\domains_EN.txt";

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	private UploadedFile file;

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public String getSelectedDomain() {
		return selectedDomain;
	}

	public void setSelectedDomain(String selectedDomain) {
		this.selectedDomain = selectedDomain;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public String getLanguages() {
		return languages;
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

	@SuppressWarnings("deprecation")
	public void apply() {
		String id = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");

		String username = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username");
		if (username == null) {
			message = "Please log in!";
		} else if (alreadyApplied(id, username)) {
			message = "You already applied for this job!";
		} else if (hasCV(username)) {
			CVDAO.sendApplication(id, username);
			message = "Application sent!";
		} else {
			message = "Please upload a CV first! Please use the CV Tool";
		}

		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('dlg2').show();");
	}

	private boolean alreadyApplied(String position, String username) {
		return ApplicationsDAO.hasApplied(username, position);
	}

	private boolean hasCV(String username) {
		if (CVDAO.hasCV(username))
			return true;
		return false;
	}

	private void upload(String username) {

		try (InputStream input = file.getInputstream()) {
			File newFile = new File(PATH, username + ".pdf");
			if (newFile.exists()) {
				newFile.delete();
			}
			Files.copy(input, newFile.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	public void save() {
		String username = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username");
		if(hasCV(username)) {
			message = "You seem to already have a CV. To edit it, go to My Account - View or edit CV.";
		}
		else if (!experience.matches("\\d+")) {
			message = "Please enter a number in experience field.";
		} else {
			String usernameID = UsersDAO.getIdByUsername(username);
			try {
				double exp = Double.parseDouble(experience);
				if (CVDAO.sendCV(usernameID, languages, selectedDomain, skills, exp, city)) {
					message = "CV sent!";
				} else {
					message = "An error occured, please try again!";
				}
			} catch (NumberFormatException e) {
				message = "Please enter a number in experience field.";
			}

		}

		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('dlg2').show();");
	}

	public void fileUploadListener(FileUploadEvent event) {
		file = event.getFile();
		String username = UsersDAO
				.getIdByUsername((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username"));
		upload(username);
		
	}
}
