package com.unitbv.mi.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import com.unitbv.mi.dao.SearchDAO;

@ManagedBean(name = "resultsBean")
@SessionScoped
public class ResultsBean {

	private static List<ResultSet> resultsDataTable = new ArrayList<>();
	private String job, selectedCity, company, selectedDomain;

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

	public void setResultsDataTable(List<ResultSet> resultsDataTable) {
		this.resultsDataTable = resultsDataTable;
	}

	public List<ResultSet> getResultsDataTable() {
		// resultsDataTable = SearchDAO.getResults(selectedDomain, selectedCity,
		// company, job);

		resultsDataTable.add(new ResultSet("test", "test", "test", "test"));
		resultsDataTable.add(new ResultSet("test2", "test2", "test2", "test2"));
		resultsDataTable.add(new ResultSet("test3", "test3", "test3", "test3"));

		return resultsDataTable;
	}

}
