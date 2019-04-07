package com.lmp.mylib.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lmp.mylib.dao.AdminDAO;

@Service
public class AdminService implements IAdminService{

	@Autowired
	AdminDAO adminDAO;
	
	@Override
	public boolean isAdmin(String adminId, String adminPw) {
		return adminDAO.select(adminId, adminPw);
	}
}
