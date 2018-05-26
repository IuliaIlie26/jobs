package com.unitbv.mi.classes;

public class CurriculumVitae {

	private Experience experience;
	public Experience getExperience() {
		return experience;
	}
	public void setExperience(Experience experience) {
		this.experience = experience;
	}
	public PersonalData getPersonalData() {
		return personalData;
	}
	public void setPersonalData(PersonalData personalData) {
		this.personalData = personalData;
	}
	private PersonalData personalData;
}
