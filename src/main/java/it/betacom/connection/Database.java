package it.betacom.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

public class Database {
	private static String url;
	private static String user;
	private static String password;

	static {
		try (InputStream input = Database.class.getClassLoader().getResourceAsStream("db_conf.properties")) {
			Properties prop = new Properties();

			if (input == null) {
				System.out.println("Sorry, unable to find db_conf.properties");
			}

			// load a properties file from class path
			prop.load(input);

			// get the property value
			url = prop.getProperty("db.url");
			user = prop.getProperty("db.user");
			password = prop.getProperty("db.password");

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private Database() {
	}

	public static Connection getConnection() throws SQLException {
		Connection connection = null;
		connection = DriverManager.getConnection(url, user, password);

		return connection;
	}
}