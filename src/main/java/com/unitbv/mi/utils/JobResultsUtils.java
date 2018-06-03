package com.unitbv.mi.utils;

public class JobResultsUtils {

	private String position, city, recruiter, id;
	
	public String getId() {
		return id;
	}

	public JobResultsUtils(String id, String position, String city, String recruiter) {
		super();
		this.position = position;
		this.city = city;
		this.recruiter = recruiter;
		this.id=id;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRecruiter() {
		return recruiter;
	}

	public void setRecruiter(String recruiter) {
		this.recruiter = recruiter;
	}
	
	
}
