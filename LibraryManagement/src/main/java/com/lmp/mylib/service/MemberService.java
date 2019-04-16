package com.lmp.mylib.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lmp.mylib.RTime;
import com.lmp.mylib.dao.MemberDAO;

@Service
public class MemberService implements IMemberService {
	@Autowired
	MemberDAO memberDAO;
	
	@Override
	public boolean memberLogin(String memName, String memPw) {
		return memberDAO.getMemberId(memName, memPw);
	}

	@Override
	public List<RTime> showRideTime() {
		return memberDAO.getRTime();
	}
}
