package com.DataManager.Constants;

public class Constants {
	public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DATABASE_NAME = "Employee";
	static final String IP = "localhost";
	static final String PORT_NO = "8889";
	public static final String DB_URL = "jdbc:mysql://" + IP + ":" + PORT_NO + "/" + DATABASE_NAME
			+ "?autoReconnect=true&useSSL=false";
	public static final String USER = "root";
	public static final String PASS = "root";
}
