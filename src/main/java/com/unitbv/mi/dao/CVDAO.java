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
	
	public static boolean deleteCV(String username) {
		return DataConnect.deleteCV(username);
	}

	public static boolean sendCV(String usernameID, String languages, String selectedDomain, String skills,
			double experience, String city) {
		return DataConnect.sendCV(usernameID, languages, selectedDomain, skills, experience, city);
	}

	public static List<ApplicantUtils> searchApplicants(String selectedDomain, String city) {
		return DataConnect.searchApplicants(selectedDomain, city);
	}

	public static boolean updateCV(String usernameID, String languages, String selectedDomain, String skills,
			double exp, String city) {
		return DataConnect.updateCV(usernameID, languages, selectedDomain, skills, exp, city);
	}

	public static String getExperience(String usernameID) {
		return DataConnect.selectFromCV(usernameID, "experience");
	}
	
	public static String getDomain(String usernameID) {
		return DataConnect.selectFromCV(usernameID, "domain");
	}
	
	public static String getSkills(String usernameID) {
		return DataConnect.selectFromCV(usernameID, "skills");
	}
	
	public static String getLanguage(String usernameID) {
		return DataConnect.selectFromCV(usernameID, "languages");
	}
	
	public static String getCity(String usernameID) {
		return DataConnect.selectFromCV(usernameID, "city");
	}
}
