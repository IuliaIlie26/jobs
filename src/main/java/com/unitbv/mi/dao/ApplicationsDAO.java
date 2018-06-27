package com.unitbv.mi.dao;

import java.util.List;
import com.unitbv.mi.utils.ApplicantUtils;

public class ApplicationsDAO {

	public static List<ApplicantUtils> getApplication(String selectedPosition, String selectedCity, String company) {
		return DataConnect.getApplication(selectedPosition, selectedCity, company);

	}

	public static String getApplicant(String id) {
		return DataConnect.getApplicant(id);

	}

	public static boolean hasApplied(String username, String position) {
		return DataConnect.hasApplied(username, position);
	}

}
