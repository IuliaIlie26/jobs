package com.unitbv.mi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.unitbv.mi.utils.UUIDGeneratorUtils;

public class CVDAO {

	public static void sendApplication(String username, String job, String company, String city) {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataConnect.getConnection();
			ps = con.prepareStatement("insert into applications values(?,?,?,?,?)");
			String uuid = UUIDGeneratorUtils.generate();
			ps.setString(1, uuid);
			ps.setString(1, UsersDAO.getIdByUsername(username));
			ps.setString(2, job);
			ps.setString(3, company);
			ps.setString(4, city);
			ps.executeUpdate();

		} catch (SQLException ex) {
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
			ps = con.prepareStatement("select applicant from cv where applicant =?");
			ps.setString(1, UsersDAO.getIdByUsername(username));
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			DataConnect.close(con);
		}
		return false;
	}

}
