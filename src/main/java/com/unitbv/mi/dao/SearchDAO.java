package com.unitbv.mi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.servlet.jsp.jstl.sql.Result;

public class SearchDAO {

	private static Connection con=null;
	static PreparedStatement ps = null;
	
	public static List<SelectItem> getCities(){
		List<SelectItem> results= new ArrayList<>();
		
		try {
			con=DataConnect.getConnection();
			ps = con.prepareStatement("select city from jobs order by city");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				results.add(new SelectItem(rs.getString("city")));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			DataConnect.close(con);
		}
		return results;
	}

	public static Result getResults() {
		// TODO Auto-generated method stub
		return null;
	}
}
