package del.res.utilities;


import java.sql.*;

public class db_interact {
	static Connection conn = null;
	
	//For setting up a connection to the database
	public static Connection newDatabase(){
		try{
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String forname = "oracle.jdbc.driver.OracleDriver";
			String username = "c##mike";
			String password = "ziggy";
			String dbURL = url;
			//Get the driver running...
			Class.forName(forname);
			//Open connection to DB
			conn = DriverManager.getConnection(dbURL,username,password);
			System.out.println("Connection Established!");
			return conn;
		}
		catch (Exception e) {
			System.out.println(e);
			return null;
		}
		
	}
	
}