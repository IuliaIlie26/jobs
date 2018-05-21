package com.unitbv.mi.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import com.unitbv.mi.dao.SearchDAO;
import com.unitbv.mi.utils.SearchResultsUtils;

@ManagedBean(name = "resultsBean")
@SessionScoped
public class ResultsBean {

	private List<SearchResultsUtils> resultsDataTable = new ArrayList<>();
	private String job;
	private String selectedCity;
	private String company;
	private String selectedDomain;

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getSelectedCity() {
		return selectedCity;
	}

	public void setSelectedCity(String selectedCity) {
		this.selectedCity = selectedCity;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getSelectedDomain() {
		return selectedDomain;
	}

	public void setSelectedDomain(String selectedDomain) {
		this.selectedDomain = selectedDomain;
	}

	public void setResultsDataTable(List<SearchResultsUtils> resultsDataTable) {
		this.resultsDataTable = resultsDataTable;
	}

	public List<SearchResultsUtils> getResultsDataTable() {
		RequestContext.getCurrentInstance().execute("toggle();");
		selectedDomain =  (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("domain");
		selectedCity =  (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("city");
		job =  (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("job");
		company =  (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("company");
		resultsDataTable.add(new SearchResultsUtils("test", "test", "test", "test"));
		return resultsDataTable;
		//return SearchDAO.getResults(selectedDomain, selectedCity, company, job);
	}

}
