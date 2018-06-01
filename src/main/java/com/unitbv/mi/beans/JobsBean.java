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

@ManagedBean(name = "jobs")
@SessionScoped
public class JobsBean implements Serializable {

	private static final long serialVersionUID = -8410585020831478502L;
	private String company, position, description, city, website, selectedDomain;
	private List<SelectItem> domainList;

	public String getSelectedDomain() {
		return selectedDomain;
	}

	public void setSelectedDomain(String selectedDomain) {
		this.selectedDomain = selectedDomain;
	}

	public List<SelectItem> getDomainList() {
		domainList = new ArrayList<>();
		try (FileReader fr = new FileReader(new File("D:\\USEFUL\\licenta\\project\\jobs\\src\\main\\resources\\domains_EN.txt"));
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
		String recruiter = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username");
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

	public void submit() {
		String recruiter = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username");
		company = RecruiterDAO.getCompany(recruiter);
		JobsDAO.publish(position, selectedDomain, company, description, city, recruiter, website);
	}

}
