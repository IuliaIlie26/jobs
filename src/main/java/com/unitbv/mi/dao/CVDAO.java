package com.unitbv.mi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CVDAO {

	public static void sendApplication(String username, String job, String company, String city) {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataConnect.getConnection();
			ps = con.prepareStatement("insert into applications values(?,?,?,?)");

			ps.setString(1, username);
			ps.setString(2, job);
			ps.setString(3, company);
			ps.setString(4, city);
			ps.executeUpdate();

		} catch (SQLException ex) {
			System.out.println("Login error -->" + ex.getMessage());
			ex.printStackTrace();
		} finally {
			DataConnect.close(con);
		}
	}

	public static boolean hasCV(String username) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataConnect.getConnection();
			ps = con.prepareStatement("select username from cv where username =?");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
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

}
