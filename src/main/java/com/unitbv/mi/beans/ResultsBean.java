package com.unitbv.mi.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.jsp.jstl.sql.Result;

import com.unitbv.mi.dao.SearchDAO;

@ManagedBean(name = "resultsBean")
@SessionScoped
public class ResultsBean {

	private Result resultsDataTable;

	public Result getResultsDataTable()  {
		populateDataTable();
		return resultsDataTable;
	}

	private void populateDataTable() {
		resultsDataTable = SearchDAO.getResults();
		
	}

}
