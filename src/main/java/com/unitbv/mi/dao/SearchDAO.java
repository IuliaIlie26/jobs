package com.unitbv.mi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SearchDAO {

	private Connection con=null;
	PreparedStatement ps = null;
	
	public List<String> getCities(){
		List<String> results= new ArrayList<>();
		
		try {
			con=DataConnect.getConnection();
			ps = con.prepareStatement("select city from jobs");
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return results;
	}
}
