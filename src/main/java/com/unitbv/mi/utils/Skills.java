package com.unitbv.mi.utils;

public class Skills {

	private String experience, field, languages, skills;

	public Skills(String experience, String field, String languages, String skills) {
		super();
		this.experience = experience;
		this.field = field;
		this.languages = languages;
		this.skills = skills;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getLanguages() {
		return languages;
	}

	public void setLanguages(String languages) {
		this.languages = languages;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}
}
