package com.unitbv.mi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersDAO {

	public static boolean validate(String user, String password) {
	/**	Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataConnect.getConnection();
			ps = con.prepareStatement("Select username, password from User where username = ? and password = ?");
			ps.setString(1, user);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				// result found, means valid inputs
				return true;
			}
		} catch (SQLException ex) {
			System.out.println("Login error -->" + ex.getMessage());
			ex.printStackTrace();
			return false;
		} finally {
			DataConnect.close(con);
		}*/

		if (user.equals("iulia.ilie") && password.equals("iulia123"))
			return true;
		return false;
	}

	public String getNameAndLastname(String username) {

		Connection con = null;
		PreparedStatement ps = null;
		String nameAndLastname = null;
		try {
			con = DataConnect.getConnection();
			ps = con.prepareStatement("Select name, lastname from User where username = ?");
			ps.setString(1, username);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				nameAndLastname = rs.getString("name") + " " + rs.getString("lastname");
			}
		} catch (SQLException ex) {
			System.out.println("Login error -->" + ex.getMessage());
			ex.printStackTrace();

		} finally {
			DataConnect.close(con);
		}
		return nameAndLastname;
	}

	public static String getIdByUsername(String username) {
		String id = null;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DataConnect.getConnection();
			ps = con.prepareStatement("Select iduser from User where username = ?");
			ps.setString(1, username);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				id = rs.getString("iduser");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();

		} finally {
			DataConnect.close(con);
		}
		return id;
	}
}
