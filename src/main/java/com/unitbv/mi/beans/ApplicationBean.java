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
import javax.faces.model.SelectItem;

import com.unitbv.mi.utils.ApplicantUtils;

@ManagedBean(name = "applicationBean")
@SessionScoped
public class ApplicationBean implements Serializable {

	private static final long serialVersionUID = -6281353658450441225L;
	private String language, experience, city, selectedDomain;
	private List<SelectItem> domains;
	private String clicked = "none";
	private List<ApplicantUtils> resultsDataTable;
	
	public List<ApplicantUtils> getResultsDataTable() {
		return resultsDataTable;
	}
	public void setResultsDataTable(List<ApplicantUtils> resultsDataTable) {
		this.resultsDataTable = resultsDataTable;
	}
	public String getClicked() {
		return clicked;
	}
	public void setClicked(String clicked) {
		this.clicked = clicked;
	}
	public String getSelectedDomain() {
		return selectedDomain;
	}
	public void setSelectedDomain(String selectedDomain) {
		this.selectedDomain = selectedDomain;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	public List<SelectItem> getDomains() {
		domains= new ArrayList<>();
		try (FileReader fr = new FileReader(new File(
				"C:\\Users\\IuliaIlie\\Desktop\\USEFUL\\licenta\\project\\jobs\\src\\main\\resources\\domains_EN.txt"));
				BufferedReader br = new BufferedReader(fr);) {
			String line;
			line = br.readLine();
			while (line != null) {
				domains.add(new SelectItem(line));
				line = br.readLine();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return domains;
	}
	public void setDomains(List<SelectItem> domain) {
		this.domains = domain;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	} 
	
	public void search() {
		if(!experience.matches("\\d+")) {
			// pop up validator!
			return;
		}
		clicked = "block";
	}
	
	public void view() {
		
	}

}
