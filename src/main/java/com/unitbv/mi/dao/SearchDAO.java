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

import com.unitbv.mi.utils.SearchResultsUtils;

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

	public static List<SearchResultsUtils> getResults(String domain, String city, String company, String position) {

		List<SearchResultsUtils> results = null;
		
		try {
			con = DataConnect.getConnection();

			String sql = "select position, company, city, description from jobs where domain= ? and city= ? ";
			if (company != null && (!company.equals(""))) {
				if (position != null && (!position.equals(""))) {
					sql += " and company like ?  and position like ?";
					ps = con.prepareStatement(sql);
					ps.setString(3, "%" + company + "%");
					ps.setString(4, "%" + position + "%");
				} else {
					sql += " and company like ?";
					ps = con.prepareStatement(sql);
					ps.setString(3, "%" + company + "%");
				}
			} else if (position != null && (!position.equals(""))) {
				sql += " and position like ?";
				ps = con.prepareStatement(sql);
				ps.setString(3, "%" + position + "%");
			} else {
				ps = con.prepareStatement(sql);
			}
			ps.setString(1, domain);
			ps.setString(2, city);

			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				results = new ArrayList<>();
				SearchResultsUtils row = new SearchResultsUtils(rs.getString("position"),rs.getString("company"),rs.getString("city"),rs.getString("description"));	
				results.add(row);
			}
			while (rs.next()) {
				
				SearchResultsUtils row = new SearchResultsUtils(rs.getString("position"),rs.getString("company"),rs.getString("city"),rs.getString("description"));	
				results.add(row);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			DataConnect.close(con);
		}
		return results;
	}

	public static List<SelectItem> selectDomains() {
		List<SelectItem> list = new ArrayList<>();
		String sql = "SELECT DOMAIN FROM JOBS ORDER BY DOMAIN	";
		con = DataConnect.getConnection();
		try {
		ps= con.prepareStatement(sql);
		ResultSet rs= ps.executeQuery();
		while(rs.next()) {
			list.add(new SelectItem(rs.getString("domain")));
		}}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
