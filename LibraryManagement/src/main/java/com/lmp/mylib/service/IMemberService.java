package com.lmp.mylib.service;

import java.util.List;

import com.lmp.mylib.RTime;

public interface IMemberService {
	boolean memberLogin(String memName, String memPw);
	int getSeatNum(String memName, String memPw);
	List<RTime> showRideTime();
	List<Integer> showRideNum(List<RTime> rtime);
	int applyRide(int seatNum, String rtime);
	String showMyRideTime(int seatNum);
}

