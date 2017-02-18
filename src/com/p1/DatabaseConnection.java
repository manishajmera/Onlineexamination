package com.p1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
	static Connection con;
	/**
	 * gives a connection object
	 * @return the connection object
	 */
	protected static Connection getInstance() throws FileNotFoundException, IOException, ClassNotFoundException, SQLException{
		Properties p=new Properties();
		p.load(new FileInputStream("C:\\Users\\Gaurav Miglani\\Desktop\\javaprog\\ADV java\\PROJECT\\WebContent\\Connection.properties"));
		Class.forName(p.getProperty("driver"));
		Connection con=DriverManager.getConnection(p.getProperty("url"));
		return con;
	}
}
