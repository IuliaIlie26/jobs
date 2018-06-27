package com.unitbv.mi.dao;

import java.util.List;

import com.unitbv.mi.utils.ApplicantUtils;

public class CVDAO {

	public static boolean sendApplication(String position, String username) {

		return DataConnect.sendApplication(position, username);
	}

	public static boolean hasCV(String username) {
		return DataConnect.hasCV(username);
	}

	public static boolean sendCV(String usernameID, String languages, String selectedDomain, String skills,
			double experience, String city) {
		return DataConnect.sendCV(usernameID, languages, selectedDomain, skills, experience, city);
	}

	public static List<ApplicantUtils> searchApplicants(String selectedDomain, String city) {
		return DataConnect.searchApplicants(selectedDomain, city);
	}
}
