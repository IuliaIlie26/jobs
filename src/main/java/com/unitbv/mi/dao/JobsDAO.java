package com.unitbv.mi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.unitbv.mi.utils.UUIDGeneratorUtils;

public class JobsDAO {

	public static boolean publish(String position, String domain, String company, String description, String city, String recruiter,
			String website) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DataConnect.getConnection();
			ps = con.prepareStatement("insert into jobs values (?,?,?,?,?,?,?,?)");
			ps.setString(1, UUIDGeneratorUtils.generate());
			ps.setString(2, position);
			ps.setString(3, company);
			ps.setString(5, city);
			ps.setString(4, website);
			ps.setString(6, description);
			ps.setString(7, domain);
			ps.setString(8, recruiter);
			ps.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;

		} finally {
			DataConnect.close(con);
		}

		return true;
	}

}
