package com.unitbv.mi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.servlet.jsp.jstl.sql.Result;
import javax.servlet.jsp.jstl.sql.ResultSupport;

public class SearchDAO {

	private static Connection con = null;
	static PreparedStatement ps = null;

	public static List<SelectItem> getCities() {
		List<SelectItem> results = new ArrayList<>();

		try {
			con = DataConnect.getConnection();
			ps = con.prepareStatement("select city from jobs order by city");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				results.add(new SelectItem(rs.getString("city")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataConnect.close(con);
		}
		return results;
	}

	public static List<com.unitbv.mi.beans.ResultSet> getResults(String domain, String city, String company, String position) {

		List<com.unitbv.mi.beans.ResultSet> results = new ArrayList<>();
		
		try {
			con = DataConnect.getConnection();

			String sql = "select position, company, city, description from jobs where domain= ? and city= ? ";
			if (company != null) {
				if (position != null) {
					sql += " and company = ?  and position = ?";
					ps = con.prepareStatement(sql);
					ps.setString(3, "%" + company + "%");
					ps.setString(4, "%" + position + "%");
				} else {
					sql += " and company = ?";
					ps = con.prepareStatement(sql);
					ps.setString(3, "%" + company + "%");
				}
			} else if (position != null) {
				sql += " and position = ?";
				ps = con.prepareStatement(sql);
				ps.setString(3, "%" + position + "%");
			} else {
				ps = con.prepareStatement(sql);
			}
			ps.setString(1, domain);
			ps.setString(2, city);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				
				com.unitbv.mi.beans.ResultSet row = new com.unitbv.mi.beans.ResultSet(rs.getString("position"),rs.getString("company"),rs.getString("city"),rs.getString("description"));	
				results.add(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataConnect.close(con);
		}
		return results;
	}

}
