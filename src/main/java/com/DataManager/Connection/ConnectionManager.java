package com.DataManager.Connection;

import java.sql.*;

import com.DataManager.Constants.Constants;

public class ConnectionManager {

	public static Connection createConnection() {

		Connection conn = null;
		try {
			// System.out.println("Connecting to database");

			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(Constants.DB_URL, Constants.USER, Constants.PASS);

			// System.out.println("Creating statement");
			return conn;
		} catch (Exception e) {

			e.printStackTrace();
			return conn;
		}
	}
}
