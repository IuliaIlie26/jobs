package com.unitbv.mi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.unitbv.mi.exceptions.CustomException;
import com.unitbv.mi.utils.ApplicantUtils;
import com.unitbv.mi.utils.UUIDGeneratorUtils;

public class CVDAO {

	public static synchronized boolean sendApplication(String position, String username) {

		String userID = UsersDAO.getIdByUsername(username);
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataConnect.getConnection();
			ps = con.prepareStatement("insert into applications values(?,?,?,?)");
			String uuid = UUIDGeneratorUtils.generate();
			ps.setString(1, uuid);
			ps.setString(2, userID);
			ps.setString(3, position);
			String company = JobsDAO.getCompany(position);
			ps.setString(4, company);
			ps.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		} catch (CustomException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DataConnect.close(con);
		}
		return true;
	}

	public static synchronized boolean hasCV(String username) {
		username = UsersDAO.getIdByUsername(username);
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataConnect.getConnection();
			ps = con.prepareStatement("select username from cv where username = ?");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (CustomException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	public static synchronized boolean sendCV(String usernameID, String languages, String selectedDomain, String skills, double experience,
			String city) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataConnect.getConnection();
			ps = con.prepareStatement("insert into cv values(?,?,?,?,?,?,?)");
			String uuid = UUIDGeneratorUtils.generate();
			ps.setString(1, uuid);
			ps.setString(2, usernameID);
			ps.setString(3, skills);
			ps.setDouble(4, experience);
			ps.setString(5, languages);
			ps.setString(6, selectedDomain);
			ps.setString(7, city);
			ps.executeUpdate();

		} catch (SQLException | CustomException ex) {
			ex.printStackTrace();
			return false;
		} finally {
			DataConnect.close(con);
		}
		return true;
	}

	public static synchronized List<ApplicantUtils> searchApplicants(String selectedDomain, String city) {
		List<ApplicantUtils> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DataConnect.getConnection();
			ps = con.prepareStatement("select id, username, city, experience, skills, languages from cv where domain = ? and city = ?");
			ps.setString(1, selectedDomain);
			ps.setString(2, city);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new ApplicantUtils(rs.getString("id"), UsersDAO.getNameAndLastname(rs.getString("username")), rs.getString("city"),
						rs.getString("experience"), rs.getString("skills"), rs.getString("languages")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (CustomException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DataConnect.close(con);
		}
		return list;
	}
}
