package com.unitbv.mi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import com.unitbv.mi.utils.JobResultsUtils;
import com.unitbv.mi.utils.UUIDGeneratorUtils;

public class JobsDAO {

	public static boolean publish(String position, String domain, String company, String description, String city,
			String recruiter, String website) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DataConnect.getConnection();
			ps = con.prepareStatement("insert into jobs values (?,?,?,?,?,?,?,?)");
			ps.setString(1, UUIDGeneratorUtils.generate());
			ps.setString(2, position);
			ps.setString(8, company);
			ps.setString(3, city);
			ps.setString(6, website);
			ps.setString(5, description);
			ps.setString(4, domain);
			ps.setString(7, recruiter);
			ps.executeUpdate();

		} catch (SQLException ex) {

			ex.printStackTrace();
			return false;

		} finally {
			DataConnect.close(con);
		}

		return true;
	}

	public static List<JobResultsUtils> getAll(String company) {

		List<JobResultsUtils> results = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DataConnect.getConnection();
			ps = con.prepareStatement("select id, position, city, recruiter from jobs where company = ?");
			ps.setString(1, company);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				JobResultsUtils job = new JobResultsUtils(rs.getString("id"), rs.getString("position"),
						rs.getString("city"), rs.getString("recruiter"));
				results.add(job);
			}

		} catch (SQLException ex) {

			ex.printStackTrace();

		} finally {
			DataConnect.close(con);
		}
		return results;
	}

	public static String select(String column, String id) {
		String result = null;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DataConnect.getConnection();
			ps = con.prepareStatement("select " + column + " from jobs where id = ?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				result = rs.getString(column);
			}

		} catch (SQLException ex) {

			ex.printStackTrace();

		} finally {
			DataConnect.close(con);
		}
		return result;
	}

	public static String getPositionById(String id) {
		Connection con = null;
		PreparedStatement ps = null;
		String position = null;
		try {
			con = DataConnect.getConnection();
			ps = con.prepareStatement("select position from jobs where id = ?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				position = rs.getString("position");
			}
		} catch (SQLException ex) {

			ex.printStackTrace();

		} finally {
			DataConnect.close(con);
		}
		return position;
	}

	public static boolean update(String id, String position, String domain, String description, String city,
			String website) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DataConnect.getConnection();
			ps = con.prepareStatement(
					"update jobs set position = ? , city = ?, website = ?, description =?, domain =? where id = ?");

			ps.setString(1, position);
			ps.setString(2, city);
			ps.setString(3, website);
			ps.setString(4, description);
			ps.setString(5, domain);
			ps.setString(6, id);
			ps.executeUpdate();

		} catch (SQLException ex) {

			ex.printStackTrace();
			return false;

		} finally {
			DataConnect.close(con);
		}

		return true;

	}

	public static String getCityById(String id) {
		Connection con = null;
		PreparedStatement ps = null;
		String city = null;
		try {
			con = DataConnect.getConnection();
			ps = con.prepareStatement("select city from jobs where id = ?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				city = rs.getString("city");
			}
		} catch (SQLException ex) {

			ex.printStackTrace();

		} finally {
			DataConnect.close(con);
		}
		return city;
	}

	public static String getCompany(String id) {
		Connection con = null;
		PreparedStatement ps = null;
		String company = null;
		try {
			con = DataConnect.getConnection();
			ps = con.prepareStatement("select company from jobs where id = ?");
			ps.setString(1, id);
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

	public static List<SelectItem> getCitiesByCompany(String company) {
		Connection con = null;
		PreparedStatement ps = null;
		List<SelectItem> cities = new ArrayList<>();
		try {
			con = DataConnect.getConnection();
			ps = con.prepareStatement("select city from jobs where company = ?");
			ps.setString(1, company);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				cities.add(new SelectItem(rs.getString("city")));
			}
		} catch (SQLException ex) {

			ex.printStackTrace();

		} finally {
			DataConnect.close(con);
		}
		return cities;
	}

	public static List<SelectItem> getPositions(String company) {
		Connection con = null;
		PreparedStatement ps = null;
		List<SelectItem> positions = new ArrayList<>();
		try {
			con = DataConnect.getConnection();
			ps = con.prepareStatement("select position from jobs where company = ?");
			ps.setString(1, company);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				positions.add(new SelectItem(rs.getString("position")));
			}
		} catch (SQLException ex) {

			ex.printStackTrace();

		} finally {
			DataConnect.close(con);
		}
		return positions;
	}

	public static String getIDByPosition(String selectedPosition, String company, String selectedCity) {
		Connection con = null;
		PreparedStatement ps = null;
		String position = null;
		try {
			con = DataConnect.getConnection();
			ps = con.prepareStatement("select id from jobs where company = ? and city = ? and position = ?");
			ps.setString(1, company);
			ps.setString(2, selectedCity);
			ps.setString(3, selectedPosition);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				position = rs.getString("id");
			}
		} catch (SQLException ex) {

			ex.printStackTrace();

		} finally {
			DataConnect.close(con);
		}
		return position;
	}

}
