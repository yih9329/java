package com.lmp.mylib.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lmp.mylib.MemberDB;
import com.lmp.mylib.MemberWeb;
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
	public int registerMember(MemberWeb member) {
		return adminDAO.insert(member);
	}

	@Override
	public int deleteMember(String memName, String memPw) {
		return adminDAO.delete(memName, memPw);
	}

	@Override
	public List<Seat> showSeatInfo() {
		return adminDAO.getSeatInfo();
	}

	@Override
	public MemberDB getMemInfo(String memName, String memPw) {
		return adminDAO.getMemInfo(memName, memPw);
	}
}
