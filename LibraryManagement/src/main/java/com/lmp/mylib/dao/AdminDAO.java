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
import org.springframework.stereotype.Service;

import com.lmp.mylib.MemberDB;
import com.lmp.mylib.MemberPhone;
import com.lmp.mylib.MemberWeb;
import com.lmp.mylib.RTime;
import com.lmp.mylib.Ride;
import com.lmp.mylib.RideDB;
import com.lmp.mylib.RideInfo;
import com.lmp.mylib.Seat;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Service
public class AdminDAO implements IAdminDAO {
	private JdbcTemplate template;
	
	@Autowired
	public AdminDAO(ComboPooledDataSource dataSource) {
		template = new JdbcTemplate(dataSource);
	}
	
	@Override
	public boolean getAdminId(final String reqId, final String reqPw) {
		String sql = "SELECT * FROM admin WHERE admin_id=? and admin_pw=?";
		List<String> list = null;
		list = template.query(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, reqId);
				pstmt.setString(2, reqPw);
			}
		}, new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet res, int rowNum) throws SQLException {
				return res.getString("admin_id");
			}
		});
		
		if(list.isEmpty())
			return false;
		return true;
	}

	@Override
	public int insertMember(final MemberWeb member) throws DataAccessException {
		String sql = "INSERT INTO member VALUES (?, ?, ?, ?, ?, ?)";
		int res = 0;
		res = template.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, member.getMemName());
				pstmt.setString(2, member.getMemSex());
				pstmt.setInt(3, member.getMemAge());
				pstmt.setString(4, member.getMemPhone().toString());
				pstmt.setString(5, member.getMemAddress());
				pstmt.setString(6, member.getMemPassword());
			}
		});
		return res;
	}

	@Override
	public int registerSeat(final MemberWeb member, final int seatNum) throws DataAccessException {
		String sql = "UPDATE seat SET mem_name=?, mem_password=? WHERE s_num=?";
		int res = 0;
		res = template.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, member.getMemName());
				pstmt.setString(2, member.getMemPassword());
				pstmt.setInt(3, seatNum);
			}
		});
		return res;
	}
	
	@Override
	public int moveSeat(final int curSeatNum, final int newSeatNum) throws DataAccessException {
		String sql = "SELECT mem_name, mem_password FROM seat WHERE s_num=?";
		List<Seat> mem = null;
		mem = template.query(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, curSeatNum);
			}
		}, new RowMapper<Seat>() {
			@Override
			public Seat mapRow(ResultSet res, int rowNum) throws SQLException {
				Seat s = new Seat();
				s.setMemName(res.getString("mem_name"));
				s.setMemPassword(res.getString("mem_password"));
				return s;
			}
		});
		
		final Seat seatInfo = mem.get(0);
		sql = "UPDATE seat SET mem_name=null, mem_password=null WHERE s_num=?";
		int res = 0;
		res = template.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, curSeatNum);
			}
		});
		
		
		sql = "UPDATE seat SET mem_name=?, mem_password=? WHERE s_num=?";
		res += template.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, seatInfo.getMemName());
				pstmt.setString(2, seatInfo.getMemPassword());
				pstmt.setInt(3, newSeatNum);
			}
		});
		
		sql = "UPDATE ride SET s_num=? WHERE s_num=?";
		template.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, newSeatNum);
				pstmt.setInt(2, curSeatNum);
			}
		});
	
		return res;
	}
	
	@Override
	public int deleteMember(final int seatNum) throws DataAccessException {
		String sql = "DELETE FROM member WHERE mem_name=(SELECT mem_name FROM seat WHERE s_num=?) AND mem_password=(SELECT mem_password FROM seat WHERE s_num=?)";
		int res = 0;
		res = template.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, seatNum);
				pstmt.setInt(2, seatNum);
			}
		});
		return res;
	}

	@Override
	public List<Seat> getSeatInfo() {
		String sql = "SELECT * FROM seat";
		List<Seat> seat = null;
		seat = template.query(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {}
		}, new RowMapper<Seat>() {
			@Override
			public Seat mapRow(ResultSet res, int rowNum) throws SQLException {
				Seat s = new Seat();
				s.setSeatNum(res.getInt("s_num"));
				s.setMemName(res.getString("mem_name"));
				s.setMemPassword(res.getString("mem_password"));
				return s;
			}
		});
		return seat;
	}
	
	@Override
	public MemberDB getMemInfo(final int seatNum) {
		String sql = "SELECT * FROM member WHERE mem_name=(SELECT mem_name FROM seat WHERE s_num=?) AND mem_password=(SELECT mem_password FROM seat WHERE s_num=?)";
		List<MemberDB> list = null;
		list = template.query(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, seatNum);
				pstmt.setInt(2, seatNum);
			}
		}, new RowMapper<MemberDB>() {
			@Override
			public MemberDB mapRow(ResultSet res, int rowNum) throws SQLException {
				MemberDB mem = new MemberDB();
				mem.setMemName(res.getString("mem_name"));
				mem.setMemSex(res.getString("mem_sex"));
				mem.setMemAge(res.getInt("mem_age"));
				mem.setMemPassword(res.getString("mem_password"));
				mem.setMemAddress(res.getString("mem_address"));
				String[] phone = res.getString("mem_phone").split("-");
				MemberPhone mp = new MemberPhone();
				mp.setPh_1(phone[0]);
				mp.setPh_2(phone[1]);
				mp.setPh_3(phone[2]);
				mem.setMemPhone(mp);
				return mem;
			}
		});
		return list.get(0);
	}
	
	@Override
	public int updateMemInfo(final MemberWeb member, final String curMemPw) throws DataAccessException {
		String sql = "UPDATE member SET mem_sex=?, mem_age=?, mem_address=?, mem_phone=?, mem_password=? WHERE mem_name=? AND mem_password=?";
		int res = 0;
		res = template.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, member.getMemSex());
				pstmt.setInt(2, member.getMemAge());
				pstmt.setString(3, member.getMemAddress());
				pstmt.setString(4, member.getMemPhone().toString());
				pstmt.setString(5, member.getMemPassword());
				pstmt.setString(6, member.getMemName());
				pstmt.setString(7, curMemPw);
			}
		});
		return res;
	}
	
	@Override
	public List<Ride> getRideInfo() {
		String sql = "SELECT * FROM ride";
		List<RideDB> list = null;
		list = template.query(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement arg0) throws SQLException {}
		}, new RowMapper<RideDB>() {
			@Override
			public RideDB mapRow(ResultSet res, int rowNum) throws SQLException {
				RideDB r = new RideDB();
				r.setSeatNum(res.getInt("s_num"));
				r.setPosTime(res.getString("pos_time"));
				return r;
			}
		});
		sql = "SELECT mem_name, mem_address FROM member WHERE mem_name=(SELECT mem_name FROM seat WHERE s_num=?) AND mem_password=(SELECT mem_password FROM seat WHERE s_num=?)";
		List<Ride> ride = new ArrayList<>();
		for(RideDB r: list) {
			final int seatNum = r.getSeatNum();
			String posTime = r.getPosTime();
			List<RideInfo> rideInfo = null; 
			rideInfo = template.query(sql, new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement pstmt) throws SQLException {
					pstmt.setInt(1, seatNum);
					pstmt.setInt(2, seatNum);
				}
			}, new RowMapper<RideInfo>() {
				@Override
				public RideInfo mapRow(ResultSet res, int rowNum) throws SQLException {
					RideInfo ri = new RideInfo();
					ri.setMemName(res.getString("mem_name"));
					ri.setMemAddress(res.getString("mem_address"));
					return ri;
				}
			});
			Ride rd = new Ride();
			rd.setPosTime(posTime);
			rd.setMemName(rideInfo.get(0).getMemName());
			rd.setMemAddress(rideInfo.get(0).getMemAddress());
			ride.add(rd);
		}
		return ride;
	}

	@Override
	public List<RTime> getRTimeInfo() {
		String sql = "SELECT * FROM rtime";
		List<RTime> rtimeList = null;
		rtimeList = template.query(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement arg0) throws SQLException {}
		}, new RowMapper<RTime>() {
			@Override
			public RTime mapRow(ResultSet res, int rowNum) throws SQLException {
				RTime rt = new RTime();
				rt.setPosTime(res.getString("pos_time"));
				rt.setMaxNum(res.getInt("max_num"));
				return rt;
			}
		});
		return rtimeList;
	}
	
	@Override
	public int deleteRTime() throws DataAccessException{
		String sql = "DELETE FROM rtime";
		int res = 0;
		res = template.update(sql);
		return res;
	}

	@Override
	public int setRTime(String[] rtime, final int maxNum) throws DataAccessException{
		String sql = "INSERT INTO rtime VALUES(?, ?)";
		int res = 0;
		for(int i=0; i<rtime.length; i++) {
			final String time = rtime[i];
			res += template.update(sql, new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement pstmt) throws SQLException {
					pstmt.setString(1, time);
					pstmt.setInt(2, maxNum);
				}
			});	
		}
		return res;
	}
}
