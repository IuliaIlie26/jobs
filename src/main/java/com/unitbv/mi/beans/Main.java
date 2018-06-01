package com.unitbv.mi.beans;


import com.unitbv.mi.dao.UsersDAO;
import com.unitbv.mi.utils.MD5EncryptionUtils;


public class Main {

	public static void main(String[] args) {
		
		String password = "TestDell";
		String name = "Popescu", lastname ="Vlad", company ="Dell", username ="popescu.vlad", email ="popescu.vlad@dell.ro";
		try {
			password = MD5EncryptionUtils.encrypt(password);
			boolean valid = UsersDAO.validateRecruiterRegistration( name, lastname, username, password, email, company);
		} catch (Exception e) {
		
		}
	}
}
