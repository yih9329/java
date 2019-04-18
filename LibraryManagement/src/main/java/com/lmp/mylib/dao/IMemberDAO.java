package com.lmp.mylib.dao;

import java.util.List;

import com.lmp.mylib.RTime;

public interface IMemberDAO {
	boolean getMemberId(String memName, String memPw);
	int getSeatNum(String memName, String memPw);
	List<RTime> getRTime();
	List<Integer> getRideNum(List<RTime> rtime); 
	int rideInsert(int seatNum, String rtime);
	String getMyRideTime(int seatNum);
	int rideDelete(int seatNum);
}
