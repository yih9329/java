package com.lmp.mylib.dao;

import java.util.List;

public interface IMemberDAO {
	boolean getMemberId(String memName, String memPw);
	int memberInsert(String memName, String memSex, int memAge, String memPhone, String memAddress, String memPassword);
	List<String> getRTime();
}
