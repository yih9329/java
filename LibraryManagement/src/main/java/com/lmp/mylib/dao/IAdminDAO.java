package com.lmp.mylib.dao;

import java.util.List;

import com.lmp.mylib.MemberDB;
import com.lmp.mylib.MemberWeb;
import com.lmp.mylib.Seat;

public interface IAdminDAO {
	boolean getAdminId(String reqId, String reqPw);
	int insert(MemberWeb member);
	int delete(String memName, String memPw);
	List<Seat> getSeatInfo();
	MemberDB getMemInfo(String memName, String memPw);
}
