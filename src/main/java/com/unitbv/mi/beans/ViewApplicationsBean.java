package com.unitbv.mi.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import com.unitbv.mi.dao.ApplicationsDAO;
import com.unitbv.mi.dao.JobsDAO;
import com.unitbv.mi.dao.RecruiterDAO;
import com.unitbv.mi.utils.ApplicantUtils;

@ManagedBean(name = "viewApplicationBean")
@SessionScoped
public class ViewApplicationsBean implements Serializable {

	private static final long serialVersionUID = 6610914869438024359L;
	private List<SelectItem> positions;
	private List<SelectItem> city;
	private String company;
	private String selectedCity, selectedPosition;
	private String displayPosition = "none";
	private List<ApplicantUtils> application;

	public List<ApplicantUtils> getApplication() {
		application = ApplicationsDAO.getApplication(selectedPosition, selectedCity, company);
		return application;
	}

	public void setApplication(List<ApplicantUtils> application) {
		this.application = application;
	}

	public String getDisplayPosition() {
		return displayPosition;
	}

	public void setDisplayPosition(String displayPosition) {
		this.displayPosition = displayPosition;
	}

	public String getSelectedCity() {
		return selectedCity;
	}

	public void setSelectedCity(String selectedCity) {
		displayPosition = "block";
		this.selectedCity = selectedCity;
	}

	public String getSelectedPosition() {
		
		return selectedPosition;
	}

	public void setSelectedPosition(String selectedPosition) {

		this.selectedPosition = selectedPosition;
	}

	public List<SelectItem> getPositions() {
		String username = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("username");
		company = RecruiterDAO.getCompany(username);
		positions = JobsDAO.getPositions(company);
		return positions;
	}

	public void setPositions(List<SelectItem> positions) {
		this.positions = positions;
	}

	public List<SelectItem> getCity() {
		String username = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("username");
		String company = RecruiterDAO.getCompany(username);
		city = JobsDAO.getCitiesByCompany(company);
		return city;
	}

	public void setCity(List<SelectItem> city) {
		this.city = city;
	}

	public String search() {
		return "viewApplicants_results";
	}
}
