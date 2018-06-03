package com.unitbv.mi.utils;

public class ApplicantUtils {

	private String applicant, city, experience;

	public ApplicantUtils(String applicant, String city, String experience) {
		super();
		this.applicant = applicant;
		this.city = city;
		this.experience = experience;
	}

	public String getApplicant() {
		return applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}
}
