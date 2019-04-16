package com.lmp.mylib.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.lmp.mylib.RTime;

@Component
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
	public boolean getMemberId(String memName, String memPw) {
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
			String sql = "SELECT * FROM member WHERE mem_name=? AND mem_password=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memName);
			pstmt.setString(2, memPw);
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
	
	@Override
	public List<RTime> getRTime() {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "scott";
		String pw = "tiger";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		List<RTime> list = new LinkedList<>();
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, id, pw);
			String sql = "SELECT * FROM rtime";
			pstmt = con.prepareStatement(sql);
			res = pstmt.executeQuery();
			
			while(res.next()) {
				RTime rt = new RTime();
				String time = res.getString(1);
				int maxNum = res.getInt(2);
				rt.setPosTime(time);
				rt.setMaxNum(maxNum);
				list.add(rt);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(res != null)		res.close();
				if(pstmt != null) 	pstmt.close();
				if(con != null)		con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return list;
	}
}
