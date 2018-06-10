package com.unitbv.mi.utils;

public class ApplicantUtils {

	private String applicant, city, experience, skills, language;
	private String domain;
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ApplicantUtils(String id, String applicant, String city, String experience, String skills, String language) {
		super();
		this.applicant = applicant;
		this.city = city;
		this.experience = experience;
		this.skills = skills;
		this.language = language;
		this.id = id;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public ApplicantUtils(String applicant, String city, String experience, String skills, String language) {
		super();
		this.applicant = applicant;
		this.city = city;
		this.experience = experience;
		this.skills = skills;
		this.language = language;
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
