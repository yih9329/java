package com.lmp.mylib.dao;

public interface IMemberDAO {
	int memberInsert(String memName, String memSex, int memAge, String memPhone, String memAddress, String memPassword);
	boolean isMember(String reqId, String reqPw);
	void memberSelect();
	
	void memberUpdate();
	void memberDelete();
}
