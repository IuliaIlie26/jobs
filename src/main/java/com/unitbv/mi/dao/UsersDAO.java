package com.unitbv.mi.dao;

public class UsersDAO {

	public static int validate(String user, String password) {
		return DataConnect.validate(user, password);
	}

	public static String getNameAndLastname(String username) {

		return DataConnect.getNameAndLastname(username);
	}

	public static String getIdByUsername(String username) {
		return DataConnect.getIdByUsername(username);
	}

	public static boolean validateRegistration(String name, String lastname, String username, String password,
			String phone, String email) {
		return DataConnect.validateRegistration(name, lastname, username, password, phone, email);
	}

	public static boolean update(String value, String username, String column) {
		return DataConnect.update(value, username, column);
	}

	public static String select(String value, String username) {
		return DataConnect.selectFromUsers(value, username);
	}

	public static boolean name(String name, String lastname, String username, String password, String email,
			String company) {
		return DataConnect.name(name, lastname, username, password, email, company);
	}

	public static String getCity(String user) {
		return DataConnect.getCity(user);
	}
}
