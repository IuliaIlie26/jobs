package com.unitbv.mi.utils;

public class SearchResultsUtils {

	private String position, company, description, city;

	public SearchResultsUtils(String position, String company, String description, String city) {
		super();
		this.position = position;
		this.company = company;
		this.description = description;
		this.city = city;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String domain) {
		this.city = domain;
	}
	
}
