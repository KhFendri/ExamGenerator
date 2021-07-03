package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySingleton
{
	Connection conn;
	private static MySingleton m = null;
	public String testbd = "null";

	public Connection getConn()
	{
		return conn;
	}

	private MySingleton()
	{
		// load and register JDBC driver for MySQL

		String serveurBD = "jdbc:mysql://localhost:3306/qcm_bd";
		String login = "root";
		String motPasse = "";
		try
		{
			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection(serveurBD, login, motPasse);

		} catch (SQLException e)
		{
			e.printStackTrace();
			System.out.println("Execption");
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	public static MySingleton getInstance()
	{
		if (m == null)
			m = new MySingleton();
		return m;
	}

}
