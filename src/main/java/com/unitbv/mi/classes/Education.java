package com.unitbv.mi.classes;

import java.util.List;

public class Education {

	private List<String> university, degree;

	public Education(List<String> university, List<String> degree) {
		super();
		this.university = university;
		this.degree = degree;
	}

	public List<String> getUniversity() {
		return university;
	}

	public void setUniversity(List<String> university) {
		this.university = university;
	}

	public List<String> getDegree() {
		return degree;
	}

	public void setDegree(List<String> degree) {
		this.degree = degree;
	}
	
}
