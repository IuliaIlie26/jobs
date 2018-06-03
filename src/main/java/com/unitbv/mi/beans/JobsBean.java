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

import com.unitbv.mi.dao.JobsDAO;
import com.unitbv.mi.dao.RecruiterDAO;
import com.unitbv.mi.utils.JobResultsUtils;

@ManagedBean(name = "jobs")
@SessionScoped
public class JobsBean implements Serializable {

	private static final long serialVersionUID = -8410585020831478502L;
	private String company, position, description, city, website, selectedDomain;
	private static String id;

	public String getId() {
		return id;
	}

	private List<SelectItem> domainList;
	private String editPosition, editDescription, editCity, editWebsite, editSelectedDomain;
	private List<JobResultsUtils> resultsDataTable = new ArrayList<>();

	public String getEditPosition() {
		if (id != null)
			editPosition = JobsDAO.select("position", id);
		return editPosition;
	}

	public String getEditDescription() {
		if (id != null)
			editDescription = JobsDAO.select("description", id);
		return editDescription;
	}

	public String getEditCity() {
		if (id != null)
			editCity = JobsDAO.select("city", id);
		return editCity;
	}

	public String getEditWebsite() {
		if (id != null)
			editWebsite = JobsDAO.select("website", id);
		return editWebsite;
	}

	public void setEditPosition(String editPosition) {
		this.editPosition = editPosition;
	}

	public void setEditDescription(String editDescription) {
		this.editDescription = editDescription;
	}

	public void setEditCity(String editCity) {
		this.editCity = editCity;
	}

	public void setEditWebsite(String editWebsite) {
		this.editWebsite = editWebsite;
	}

	public void setEditSelectedDomain(String editSelectedDomain) {
		this.editSelectedDomain = editSelectedDomain;
	}

	public String getEditSelectedDomain() {
		if (id != null)
			editSelectedDomain = JobsDAO.select("domain", id);
		return editSelectedDomain;
	}

	public String getSelectedDomain() {
		return selectedDomain;
	}

	public void setSelectedDomain(String selectedDomain) {
		this.selectedDomain = selectedDomain;
	}

	public List<SelectItem> getDomainList() {
		domainList = new ArrayList<>();
		try (FileReader fr = new FileReader(new File(
				"C:\\Users\\IuliaIlie\\Desktop\\USEFUL\\licenta\\project\\jobs\\src\\main\\resources\\domains_EN.txt"));
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

	public String getCompany() {
		String recruiter = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("username");
		return RecruiterDAO.getCompany(recruiter);
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String submit() {
		String recruiter = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("username");
		company = RecruiterDAO.getCompany(recruiter);
		JobsDAO.publish(position, selectedDomain, company, description, city, recruiter, website);
		position = "";
		selectedDomain = "";
		company = "";
		description = "";
		city = "";
		website = "";
		return "publishJobs";
	}

	public List<JobResultsUtils> getResultsDataTable() {
		resultsDataTable = JobsDAO.getAll(this.getCompany());
		return resultsDataTable;
	}

	public void setResultsDataTable(List<JobResultsUtils> resultsDataTable) {
		this.resultsDataTable = resultsDataTable;
	}

	public String edit() {

		id = ((String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id")).trim();
		return "editJobs";
	}

	public String saveChanges() {

		JobsDAO.update(id, editPosition, editSelectedDomain, editDescription, editCity, editWebsite);
		return "publishJobs";
	}

}
