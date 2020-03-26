package com.bridgelabz.config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionConfig {

	private static String driver;
	private static String url;
	private static String userName;
	private static String password;
	private static Connection con;
	private static Properties properties = new Properties();
	private static ConnectionConfig instance;

	private ConnectionConfig() {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input = classLoader.getResourceAsStream("config.properties");
		System.out.println("Properties load");
		try {
			properties.load(input);
			driver = properties.getProperty("driver");
			System.out.println("Driver: " + driver);
			url = properties.getProperty("url");
			userName = properties.getProperty("userName");
			password = properties.getProperty("password");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static final ConnectionConfig getInstance() {
		if (instance == null) {
			instance = new ConnectionConfig();
		}
		return instance;
	}

	public Connection getConnection() {
		if (con == null) {
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url, userName, password);

			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		return con;
	}
}
