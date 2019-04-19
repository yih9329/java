package com.lmp.mylib.dao;

import java.util.List;

import com.lmp.mylib.MemberDB;
import com.lmp.mylib.MemberWeb;
import com.lmp.mylib.RTime;
import com.lmp.mylib.Ride;
import com.lmp.mylib.Seat;

public interface IAdminDAO {
	boolean getAdminId(String reqId, String reqPw);
	int insertMember(MemberWeb member);
	int registerSeat(MemberWeb member, int seatNum);
	int moveSeat(int curSeatNum, int newSeatNum);
	int deleteMember(int seatNum);
	int updateMemInfo(MemberWeb member, String curMemPw);
	List<Seat> getSeatInfo();
	MemberDB getMemInfo(int seatNum);
	List<Ride> getRideInfo();
	List<RTime> getRTimeInfo();
	int deleteRTime();
	int setRTime(String[] rtime, int maxNum);
}
