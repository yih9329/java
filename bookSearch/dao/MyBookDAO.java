package bookSearch.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class MyBookDAO implements BookConnectionMaker{
	
	@Override
	public Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String jdbcURL = "jdbc:mysql://localhost:3306/library?useUnicode=true&characterEncoding=UTF-8";
			String id = "java";
			String pw = "java";
			con = DriverManager.getConnection(jdbcURL, id, pw);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;

	}
}
