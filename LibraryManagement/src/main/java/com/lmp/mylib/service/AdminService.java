package com.lmp.mylib.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lmp.mylib.MemberDB;
import com.lmp.mylib.MemberWeb;
import com.lmp.mylib.RTime;
import com.lmp.mylib.Ride;
import com.lmp.mylib.Seat;
import com.lmp.mylib.dao.AdminDAO;

@Service
public class AdminService implements IAdminService{

	@Autowired
	AdminDAO adminDAO;
	
	@Override
	public boolean isAdmin(String adminId, String adminPw) {
		return adminDAO.getAdminId(adminId, adminPw);
	}

	@Override
	public int registerMember(MemberWeb member, int seatNum) {
		int res = adminDAO.insertMember(member);
		if(res == 1) 
			res = adminDAO.registerSeat(member, seatNum);
		return res;
	}

	@Override
	public int deleteMember(int seatNum) {
		return adminDAO.deleteMember(seatNum);
	}

	@Override
	public List<Seat> showSeatInfo() {
		return adminDAO.getSeatInfo();
	}

	@Override
	public MemberDB getMemInfo(int seatNum) {
		return adminDAO.getMemInfo(seatNum);
	}

	@Override
	public int modifyMemInfo(MemberWeb member, String curMemPw) {
		return adminDAO.updateMemInfo(member, curMemPw);
	}

	@Override
	public List<Ride> showRideInfo() {
		return adminDAO.getRideInfo();
	}

	@Override
	public List<RTime> showRTimeInfo() {
		return adminDAO.getRTimeInfo();
	}

	@Override
	public int deleteRTime() {
		return adminDAO.deleteRTime();
	}

	@Override
	public int setRTime(String[] rtime) {
		return adminDAO.setRTime(rtime);
	}
}
