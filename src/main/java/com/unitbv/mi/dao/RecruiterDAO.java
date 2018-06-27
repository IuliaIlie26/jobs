package com.unitbv.mi.dao;

public class RecruiterDAO {

	public static String getCompany(String recruiter) {
		return DataConnect.getCompanyForRecruiter(recruiter);
	}

}
