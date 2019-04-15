package com.lmp.mylib.service;

import java.util.List;

public interface IMemberService {
	boolean memberLogin(String memName, String memPw);
	List<String> showRideTime();
}
