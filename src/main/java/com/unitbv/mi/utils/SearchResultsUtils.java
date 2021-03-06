package com.unitbv.mi.utils;

public class SearchResultsUtils {

	private String position, company, description, city, website, id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public SearchResultsUtils(String id, String position, String company, String city, String description, String website) {
		super();
		this.position = position;
		this.company = company;
		this.description = description;
		this.city = city;
		this.website = website;
		this.id=id;
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
