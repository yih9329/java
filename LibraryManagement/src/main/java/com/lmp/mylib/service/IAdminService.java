package com.lmp.mylib.service;

import com.lmp.mylib.Member;

public interface IAdminService {
	boolean isAdmin(String adminId, String adminPw);
	int registerMember(Member member);
}
