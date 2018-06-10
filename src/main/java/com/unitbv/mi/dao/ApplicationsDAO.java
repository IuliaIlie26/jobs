package com.unitbv.mi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.unitbv.mi.utils.ApplicantUtils;

public class ApplicationsDAO {

	public static List<ApplicantUtils> getApplication(String selectedPosition, String selectedCity, String company) {
		List<ApplicantUtils> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DataConnect.getConnection();
			ps = con.prepareStatement(
					"select cv.id, username, city, experience, skills, languages, domain from cv join applications on username = applicant where position = ?");
			ps.setString(1, JobsDAO.getIDByPosition(selectedPosition, company, selectedCity));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new ApplicantUtils(rs.getString("id"), UsersDAO.getNameAndLastname(rs.getString("username")),
						rs.getString("city"), rs.getString("experience"), rs.getString("skills"),
						rs.getString("languages")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}

	public static String getApplicant(String id) {
		String username = null;

		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DataConnect.getConnection();
			ps = con.prepareStatement("select username from cv where id = ?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				username = rs.getString("username");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return username;

	}

}
