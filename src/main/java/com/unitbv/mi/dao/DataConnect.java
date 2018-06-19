package com.unitbv.mi.dao;

import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.log4j.Logger;

import com.unitbv.mi.exceptions.CustomException;

public class DataConnect {

	private final static Logger logger = Logger.getLogger(DataConnect.class);

	public static Connection getConnection() throws CustomException {
		Connection con = null;
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres",
					"iulia");
			logger.info("Connected to Postgres!");

		} catch (Exception ex) {
			logger.error("Database.getConnection() Error --> " + ex.getMessage());
			throw new CustomException();

		}
		return con;
	}

	public static void close(Connection con) {
		try {
			con.close();
			logger.info("Connection to Postgres closed!");

		} catch (Exception ex) {
			logger.error("Error closing Postgres connection: " + ex.getMessage());
		}
	}
}