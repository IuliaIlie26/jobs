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
import com.unitbv.mi.dao.CVDAO;

@ManagedBean(name = "cvBean")
@SessionScoped
public class CVBean {

	private String name, lastname, email, phone, city, country, gender;
	private String selectedLevel;
	private List<SelectItem> countryList;
	private String university, prevCompany, selectedDomainCurrent, currentPosition, position, currentCompany, selectedDomainPrev,
			selectedDegree, selectedLast;
	private String sinceDate, toDate, sinceDateCurrent;
	private List<SelectItem> degree;
	private String language;
	private List<SelectItem> levelList;
	private List<String> skills;
	private String mLanguage;
	private List<SelectItem> last;

	
	public String getSelectedLast() {
		return selectedLast;
	}

	public void setSelectedLast(String selectedLast) {
		this.selectedLast = selectedLast;
	}

	public List<SelectItem> getLast() {
		last= new ArrayList<>();
		last.add(new SelectItem("None"));
		last.add(new SelectItem("Highschool"));
		last.add(new SelectItem("Professional school"));
		last.add(new SelectItem("University - Bachelor"));
		last.add(new SelectItem("University - Master"));
		last.add(new SelectItem("University - PhD"));
		return last;
	}

	public void setLast(List<SelectItem> last) {
		this.last = last;
	}

	public String getmLanguage() {
		return mLanguage;
	}

	public void setmLanguage(String mLanguage) {
		this.mLanguage = mLanguage;
	}

	public String getSelectedDomainCurrent() {
		return selectedDomainCurrent;
	}

	public void setSelectedDomainCurrent(String selectedDomainCurrent) {
		this.selectedDomainCurrent = selectedDomainCurrent;
	}

	public List<String> getSkills() {
		return skills;
	}

	public void setSkills(List<String> skills) {
		this.skills = skills;
	}

	public String getSelectedLevel() {
		return selectedLevel;
	}

	public void setSelectedLevel(String selectedLevel) {
		this.selectedLevel = selectedLevel;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public List<SelectItem> getLevelList() {
		levelList = new ArrayList<>();
		levelList.add(new SelectItem("A1"));
		levelList.add(new SelectItem("A2"));
		levelList.add(new SelectItem("B1"));
		levelList.add(new SelectItem("B2"));
		levelList.add(new SelectItem("C1"));
		levelList.add(new SelectItem("C2"));
		return levelList;
	}

	public void setLevelList(List<SelectItem> levelList) {
		this.levelList = levelList;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getSelectedDegree() {
		return selectedDegree;
	}

	public void setSelectedDegree(String selectedDegree) {
		this.selectedDegree = selectedDegree;
	}

	public String getPrevCompany() {
		return prevCompany;
	}

	public void setPrevCompany(String prevCompany) {
		this.prevCompany = prevCompany;
	}

	private List<SelectItem> domainList;

	public String getSelectedDomain() {
		return selectedDomainCurrent;
	}

	public void setSelectedDomain(String selectedDomain) {
		this.selectedDomainCurrent = selectedDomain;
	}

	public List<SelectItem> getDegree() {
		degree = new ArrayList<>();
		degree.add(new SelectItem("Bachelor"));
		degree.add(new SelectItem("Master"));
		degree.add(new SelectItem("PhD"));
		return degree;
	}

	public void setDegree(List<SelectItem> degree) {
		this.degree = degree;
	}

	public String getSinceDate() {
		return sinceDate;
	}

	public void setSinceDate(String sinceDate) {
		this.sinceDate = sinceDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getSinceDateCurrent() {
		return sinceDateCurrent;
	}

	public void setSinceDateCurrent(String sinceDateCurrent) {
		this.sinceDateCurrent = sinceDateCurrent;
	}

	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

	public String getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(String currentPositions) {
		this.currentPosition = currentPositions;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getCurrentCompany() {
		return currentCompany;
	}

	public void setCurrentCompany(String currentCompany) {
		this.currentCompany = currentCompany;
	}

	public String getSelectedDomainPrev() {
		return selectedDomainPrev;
	}

	public void setSelectedDomainPrev(String selectedDomainPrev) {
		this.selectedDomainPrev = selectedDomainPrev;
	}

	public List<SelectItem> getDomainList() {
		domainList = new ArrayList<>();

		try (FileReader fr = new FileReader(new File("C:\\Users\\IuliaIlie\\Desktop\\USEFUL\\licenta\\project\\jobs\\src\\main\\resources\\domains_EN.txt"));
				BufferedReader br = new BufferedReader(fr);) {
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

	public List<SelectItem> getCountryList() {
		countryList = new ArrayList<>();

		try (FileReader fr = new FileReader(new File("D:\\USEFUL\\licenta\\project\\jobs\\src\\main\\resources\\countries_EN.txt"));
				BufferedReader br = new BufferedReader(fr);) {
			String line;
			line = br.readLine();
			while (line != null) {
				countryList.add(new SelectItem(line));
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

	public void setCountryList(List<SelectItem> countryList) {
		this.countryList = countryList;
	}

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

	public void publishCV() {

	}

	public void saveCV() {

	}
}
