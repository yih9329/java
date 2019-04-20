package com.lmp.mylib.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.lmp.mylib.MemberDB;
import com.lmp.mylib.RTime;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Component
public class MemberDAO implements IMemberDAO {
	//private DriverManagerDataSource dataSource;
	private JdbcTemplate template;
	
	@Autowired
	public MemberDAO(ComboPooledDataSource dataSource) {				//객체가 생성될 때 드라이버 로딩, 연결이 자동으로 된다.
		template = new JdbcTemplate(dataSource);
	}
	
	@Override
	public boolean getMemberId(final String memName, final String memPw) {
		List<MemberDB> member = null; 
		String sql = "SELECT * FROM member WHERE mem_name=? AND mem_password=?";
		member = template.query(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, memName);
				pstmt.setString(2, memPw);
			}
		}, new RowMapper<MemberDB>() {
			@Override
			public MemberDB mapRow(ResultSet res, int rowNum) throws SQLException {
				MemberDB mem = new MemberDB();
				mem.setMemName(res.getString("mem_name"));
				mem.setMemPassword(res.getString("mem_password"));
				return mem;
			}
		});
		
		if(member.isEmpty())
			return false;
		return true;
	}
		
	public int getSeatNum(final String memName, final String memPw) {
		String sql = "SELECT s_num FROM seat WHERE mem_name=? AND mem_password=?";
		List<Integer> seatNum = null;
		seatNum = template.query(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, memName);
				pstmt.setString(2, memPw);
			}
		}, new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet res, int rowNum) throws SQLException {
				return res.getInt("s_num");
			}
		});
		
		if(seatNum.isEmpty())
			return -1;
		return seatNum.get(0);
	}

	@Override
	public List<RTime> getRTime() {
		String sql = "SELECT * FROM rtime";
		List<RTime> rtime = null;
		rtime = template.query(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {}
		}, new RowMapper<RTime>() {
			@Override
			public RTime mapRow(ResultSet res, int rowNum) throws SQLException {
				RTime rt = new RTime();
				rt.setPosTime(res.getString("pos_time"));
				rt.setMaxNum(res.getInt("max_num"));
				return rt;
			}
		});
		return rtime;
	}

	@Override
	public List<Integer> getRideNum(List<RTime> rtime) {
		String sql = "SELECT count(*) AS cnt FROM ride WHERE pos_time=?";
		List<Integer> rideNum = new ArrayList<>();
		for(int i=0; i<rtime.size(); i++) {
			final String time = rtime.get(i).getPosTime();
			List<Integer> list = null;
			list = template.query(sql, new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement pstmt) throws SQLException {
					pstmt.setString(1, time);
				}
			}, new RowMapper<Integer>() {
				@Override
				public Integer mapRow(ResultSet res, int rowNum) throws SQLException {
					return res.getInt("cnt");
				}
			});
			rideNum.add(list.get(0));
		}
		return rideNum;
	}
	
	@Override
	public int rideInsert(final int seatNum, final String rtime) throws DataAccessException {
		String sql = "INSERT INTO ride VALUES(?, ?)";
		int res = 0;
		res = template.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, seatNum);
				pstmt.setString(2, rtime);
			}
		});
		return res;
	}

	@Override
	public String getMyRideTime(final int seatNum) {
		String sql = "SELECT * FROM ride WHERE s_num=?";
		List<String> rtime = null;
		rtime = template.query(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, seatNum);
			}
		}, new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet res, int rowNum) throws SQLException {
				return res.getString("pos_time");
			}
		});
		
		if(rtime.isEmpty())
			return null;
		return rtime.get(0);
	}

	@Override
	public int rideDelete(final int seatNum) throws DataAccessException {
		String sql = "DELETE FROM ride WHERE s_num=?";
		int res = 0;
		res = template.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, seatNum);
			}
		});
		return res;
	}
}
