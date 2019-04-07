package com.lmp.mylib.dao;

import com.lmp.mylib.Member;

public interface IAdminDAO {
	boolean select(String reqId, String reqPw);
	int insert(Member member);
}
