package com.unitbv.mi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.unitbv.mi.utils.UUIDGeneratorUtils;

public class UsersDAO {

	public static boolean validate(String user, String password) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataConnect.getConnection();

			ps = con.prepareStatement("Select username, password from User where username = ? and password = ?");
			ps.setString(1, user);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return true;
			}

			ps = con.prepareStatement("Select email, password from User where email = ? and password = ?");
			ps.setString(1, user);
			ps.setString(2, password);

			rs = ps.executeQuery();

			if (rs.next()) {
				return true;
			}
		} catch (SQLException ex) {
			System.out.println("Login error -->" + ex.getMessage());
			ex.printStackTrace();
		} finally {
			DataConnect.close(con);
		}
		return false;
	}

	public static String getNameAndLastname(String username) {

		Connection con = null;
		PreparedStatement ps = null;
		String nameAndLastname = null;
		try {
			con = DataConnect.getConnection();
			ps = con.prepareStatement("Select lastname, name from User where username = ? or email = ?");
			ps.setString(1, username);
			ps.setString(2, username);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				nameAndLastname = rs.getString("lastname") + " " + rs.getString("name");
			}
		} catch (SQLException ex) {
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

	public static boolean validateRegistration(String name, String lastname, String username, String password, String phone, String email) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DataConnect.getConnection();
			ps = con.prepareStatement("insert into user values (?,?,?,?,?,?,?)");
			ps.setString(1, UUIDGeneratorUtils.generate());
			ps.setString(2, name);
			ps.setString(3, lastname);
			ps.setString(7, username);
			ps.setString(6, password);
			ps.setString(5, phone);
			ps.setString(4, email);
			ps.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;

		} finally {
			DataConnect.close(con);
		}

		return true;
	}

	public static boolean update(String value, String username, String column) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DataConnect.getConnection();
			ps = con.prepareStatement("update user set "+ column +" = ? where username = ?");
			ps.setString(1, value);
			ps.setString(2, username);
			ps.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;

		} finally {
			DataConnect.close(con);
		}
		return true;
	}
}
