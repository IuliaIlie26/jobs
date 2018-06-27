package com.unitbv.mi.dao;

import java.util.List;
import javax.faces.model.SelectItem;

import com.unitbv.mi.utils.JobResultsUtils;

public class JobsDAO {

	public static boolean publish(String position, String domain, String company, String description, String city,
			String recruiter, String website) {

		return DataConnect.publish(position, domain, company, description, city, recruiter, website);
	}

	public static List<JobResultsUtils> getAll(String company) {

		return DataConnect.getAll(company);
	}

	public static String select(String column, String id) {

		return DataConnect.selectFromJobs(column, id);
	}

	public static String getPositionById(String id) {

		return DataConnect.getPositionById(id);
	}

	public static boolean update(String id, String position, String domain, String description, String city,
			String website) {
		return DataConnect.update(id, position, domain, description, city, website);

	}

	public static String getCityById(String id) {
		return DataConnect.getCityById(id);
	}

	public static String getCompany(String id) {
		return DataConnect.getCompany(id);
	}

	public static List<SelectItem> getCitiesByCompany(String company) {
		return DataConnect.getCitiesByCompany(company);
	}

	public static List<SelectItem> getPositions(String company) {
		return DataConnect.getPositions(company);
	}

	public static String getIDByPosition(String selectedPosition, String company, String selectedCity) {
		return DataConnect.getIDByPosition(selectedPosition, company, selectedCity);
	}

}
