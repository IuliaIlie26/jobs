package com.unitbv.mi.dao;

import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.log4j.Logger;

import com.unitbv.mi.exceptions.CustomException;

public class DataConnect {

	private final static Logger logger = Logger.getLogger(DataConnect.class);

	public static synchronized Connection getConnection() throws CustomException {
		Connection con = null;
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbc:postgresql://localhost/postgres?user=postgres&password=iulia");
			logger.info("Connected to Postgres!");

		} catch (Exception ex) {
			logger.error("Database.getConnection() Error --> " );
			ex.printStackTrace();
			throw new CustomException();

		}
		return con;
	}

	public static synchronized void close(Connection con) {
		try {
			if (con != null) {
				con.close();
				logger.info("Connection to Postgres closed!");
			}
		} catch (Exception ex) {
			logger.error("Error closing Postgres connection: " + ex.getMessage());
		}
	}
}