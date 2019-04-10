package com.lmp.mylib.service;

import java.util.List;

import com.lmp.mylib.MemberDB;
import com.lmp.mylib.MemberWeb;
import com.lmp.mylib.Seat;

public interface IAdminService {
	boolean isAdmin(String adminId, String adminPw);
	int registerMember(MemberWeb member, int seatNum);
	int deleteMember(int seatNum);
	int modifyMemInfo(MemberWeb member, String curMemPw);
	List<Seat> showSeatInfo();
	MemberDB getMemInfo(int seatNum);
	
}
