package com.lmp.mylib.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lmp.mylib.dao.MemberDAO;

@Service
public class MemberService implements IMemberService {
	@Autowired
	MemberDAO memberDAO;
	
	@Override
	public boolean memberLogin(String memName, String memPw) {
		return memberDAO.getMemberId(memName, memPw);
	}
	
}
