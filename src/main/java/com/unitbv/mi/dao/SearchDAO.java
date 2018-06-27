package com.unitbv.mi.dao;

import java.util.List;
import javax.faces.model.SelectItem;
import com.unitbv.mi.utils.SearchResultsUtils;

public class SearchDAO {

	public static List<SelectItem> getCities() {
		return DataConnect.getCities();
	}

	public static List<SearchResultsUtils> getResults(String domain, String city, String company, String position) {

		return DataConnect.getResults(domain, city, company, position);
	}

	public static List<SelectItem> selectDomains() {
		return DataConnect.selectDomains();
	}

}
