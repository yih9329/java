package com.lmp.mylib.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lmp.mylib.Member;
import com.lmp.mylib.dao.AdminDAO;

@Service
public class AdminService implements IAdminService{

	@Autowired
	AdminDAO adminDAO;
	
	@Override
	public boolean isAdmin(String adminId, String adminPw) {
		return adminDAO.select(adminId, adminPw);
	}

	@Override
	public int registerMember(Member member) {
		return adminDAO.insert(member);
	}

	@Override
	public int deleteMember(String memName, String memPw) {
		return adminDAO.delete(memName, memPw);
	}
}
