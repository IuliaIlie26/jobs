package com.unitbv.mi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RecruiterDAO {

	public static String getCompany(String recruiter) {
		String company = null;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DataConnect.getConnection();
			ps = con.prepareStatement("Select company from recruiter where username = ? or email = ?");
			ps.setString(1, recruiter);
			ps.setString(2, recruiter);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				company = rs.getString("company");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();

		} finally {
			DataConnect.close(con);
		}
		return company;
	}

}
