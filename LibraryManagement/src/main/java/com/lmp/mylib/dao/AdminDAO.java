package com.lmp.mylib.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lmp.mylib.MemberDB;
import com.lmp.mylib.MemberPhone;
import com.lmp.mylib.MemberWeb;
import com.lmp.mylib.RTime;
import com.lmp.mylib.Ride;
import com.lmp.mylib.Seat;

@Service
public class AdminDAO implements IAdminDAO {
	@Override
	public boolean getAdminId(String reqId, String reqPw) {
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

	@Override
	public int insertMember(MemberWeb member) {
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
			pstmt.setString(1, member.getMemName());
			pstmt.setString(2, member.getMemSex());
			pstmt.setInt(3, member.getMemAge());
			pstmt.setString(4, member.getMemPhone().toString());
			pstmt.setString(5, member.getMemAddress());
			pstmt.setString(6, member.getMemPassword());
			res = pstmt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) 	pstmt.close();
				if(con != null)		con.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return res;
	}

	@Override
	public int registerSeat(MemberWeb member, int seatNum) {
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
			String sql = "UPDATE seat SET mem_name=?, mem_password=? WHERE s_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getMemName());
			pstmt.setString(2, member.getMemPassword());
			pstmt.setInt(3, seatNum);
			res = pstmt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) 	pstmt.close();
				if(con != null) 	con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return res;
	}
	
	@Override
	public int deleteMember(int seatNum) {
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
			String sql = "DELETE FROM member WHERE mem_name=(SELECT mem_name FROM seat WHERE s_num=?) AND mem_password=(SELECT mem_password FROM seat WHERE s_num=?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, seatNum);
			pstmt.setInt(2, seatNum);
			res = pstmt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null)	 pstmt.close();
				if(con != null)		 con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return res;
	}

	@Override
	public List<Seat> getSeatInfo() {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "scott";
		String pw = "tiger";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		List<Seat> seat = new ArrayList<>();
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, id, pw);
			String sql = "SELECT * FROM seat";
			pstmt = con.prepareStatement(sql);
			res = pstmt.executeQuery();
			
			while(res.next()) {
				int seatNum = res.getInt(1);
				String memName = res.getString(2);
				String memPassword = res.getString(3);
				Seat s = new Seat();
				s.setSeatNum(seatNum);
				s.setMemName(memName);
				s.setMemPassword(memPassword);
				seat.add(s);
			}
			
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
		return seat;
	}

	@Override
	public MemberDB getMemInfo(int seatNum) {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "scott";
		String pw = "tiger";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		MemberDB m = new MemberDB();
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, id, pw);
			String sql = "SELECT * FROM member WHERE mem_name=(SELECT mem_name FROM seat WHERE s_num=?) AND mem_password=(SELECT mem_password FROM seat WHERE s_num=?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, seatNum);
			pstmt.setInt(2, seatNum);
			res = pstmt.executeQuery();
			
			while(res.next()) {
				m.setMemName(res.getString(1));
				m.setMemSex(res.getString(2));
				m.setMemAge(res.getInt(3));
				m.setMemAddress(res.getString(5));
				m.setMemPassword(res.getString(6));
				
				String[] phone = res.getString(4).split("-");
				MemberPhone mp = new MemberPhone();
				mp.setPh_1(phone[0]);
				mp.setPh_2(phone[1]);
				mp.setPh_3(phone[2]);
				m.setMemPhone(mp);
			}
			
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
		return m;
	}
	
	@Override
	public int updateMemInfo(MemberWeb member, String curMemPw) {
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
			String sql = "UPDATE member SET mem_sex=?, mem_age=?, mem_address=?, mem_phone=?, mem_password=? WHERE mem_name=? AND mem_password=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getMemSex());
			pstmt.setInt(2, member.getMemAge());
			pstmt.setString(3, member.getMemAddress());
			pstmt.setString(4, member.getMemPhone().toString());
			pstmt.setString(5, member.getMemPassword());
			pstmt.setString(6, member.getMemName());
			pstmt.setString(7, curMemPw);
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
	public List<Ride> getRideInfo() {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "scott";
		String pw = "tiger";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		ResultSet rs = null;
		List<Ride> list = new ArrayList<>();
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, id, pw);
			String sql = "SELECT * FROM ride";
			pstmt = con.prepareStatement(sql);
			res = pstmt.executeQuery();
			
			while(res.next()) {
				Ride r = new Ride();
				String memName = null;
				String memAddress = null;
				int seatNum = res.getInt(1);
				String posTime = res.getString(2);
				sql = "SELECT mem_name, mem_address FROM member WHERE mem_name=(SELECT mem_name FROM seat WHERE s_num=?) AND mem_password=(SELECT mem_password FROM seat WHERE s_num=?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, seatNum);
				pstmt.setInt(2, seatNum);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					memName = rs.getString(1);
					memAddress = rs.getString(2);
				}
				r.setMemName(memName);
				r.setPosTime(posTime);
				r.setMemAddress(memAddress);
				list.add(r);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null)		rs.close();
				if(res != null)		res.close();
				if(pstmt != null)	pstmt.close();
				if(con != null) 	con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return list;
	}

	@Override
	public List<RTime> getRTimeInfo() {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "scott";
		String pw = "tiger";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		List<RTime> list = new ArrayList<>();
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, id, pw);
			String sql = "SELECT * FROM rtime";
			pstmt = con.prepareStatement(sql);
			res = pstmt.executeQuery();
			
			while(res.next()) {
				RTime rt = new RTime();
				String posTime = res.getString(1);
				int maxNum = res.getInt(2);
				rt.setPosTime(posTime);
				rt.setMaxNum(maxNum);
				list.add(rt);
			}
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
		return list;
	}
	
	@Override
	public int deleteRTime() {
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
			String sql = "DELETE FROM rtime";
			pstmt = con.prepareStatement(sql);
			res = pstmt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null)	pstmt.close();
				if(con != null) 	con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return res;
	}

	@Override
	public int setRTime(String[] rtime, int maxNum) {
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
			String sql = "INSERT INTO rtime VALUES(?, ?)";
			for(String s: rtime) {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, s);
				pstmt.setInt(2, maxNum);
				res += pstmt.executeUpdate();
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) 	pstmt.close();
				if(con != null)		con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return res;
	}

}
