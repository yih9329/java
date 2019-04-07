package com.lmp.mylib.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Service;

@Service
public class AdminDAO implements IAdminDAO {
	@Override
	public boolean select(String reqId, String reqPw) {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "scott";
		String pw = "tiger";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		boolean ret = false;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, id, pw);
			String sql = "SELECT * FROM admin WHERE admin_id=? and admin_pw=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, reqId);
			pstmt.setString(2, reqPw);
			res = pstmt.executeQuery();
			
			if(res.next())
				ret = true;
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(res != null)		res.close();
				if(pstmt != null)	pstmt.close();
				if(con != null)		con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return ret;
	}
}
