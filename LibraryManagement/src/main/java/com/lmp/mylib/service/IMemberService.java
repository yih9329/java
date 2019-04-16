package com.lmp.mylib.service;

import java.util.List;

import com.lmp.mylib.RTime;

public interface IMemberService {
	boolean memberLogin(String memName, String memPw);
	List<RTime> showRideTime();
}

