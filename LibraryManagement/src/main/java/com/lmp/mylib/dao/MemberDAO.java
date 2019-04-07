package com.lmp.mylib.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Service;

@Service
public class MemberDAO implements IMemberDAO {
	
	@Override
	public int memberInsert(String memName, String memSex, int memAge, String memPhone, String memAddress, String memPassword) {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "scott";
		String pw = "tiger";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		int res = 0;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, id, pw);
			String sql = "INSERT INTO member VALUES (?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memName);
			pstmt.setString(2, memSex);
			pstmt.setInt(3, memAge);
			pstmt.setString(4, memPhone);
			pstmt.setString(5, memAddress);
			pstmt.setString(6, memPassword);
			res = pstmt.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null)	pstmt.close();
				if(con != null)		con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return res;
	}

	@Override
	public void memberSelect() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void memberUpdate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void memberDelete() {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public boolean isMember(String reqId, String reqPw) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
