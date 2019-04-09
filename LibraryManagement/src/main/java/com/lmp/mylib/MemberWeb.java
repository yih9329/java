package com.lmp.mylib;

import java.io.UnsupportedEncodingException;

public class MemberWeb {
	private String memName;
	private String memSex;
	private int memAge;
	private MemberPhone memPhone;
	private String memAddress;
	private String memPassword;
	
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		try {
			this.memName = new String(memName.getBytes("8859_1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	public String getMemSex() {
		return memSex;
	}
	public void setMemSex(String memSex) {
		this.memSex = memSex;
	}
	public int getMemAge() {
		return memAge;
	}
	public void setMemAge(int memAge) {
		this.memAge = memAge;
	}
	public MemberPhone getMemPhone() {
		return memPhone;
	}
	public void setMemPhone(MemberPhone memPhone) {
		this.memPhone = memPhone;
	}
	public String getMemAddress() {
		return memAddress;
	}
	public void setMemAddress(String memAddress) {
		try {
			this.memAddress = new String(memAddress.getBytes("8859_1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	public String getMemPassword() {
		return memPassword;
	}
	public void setMemPassword(String memPassword) {
		this.memPassword = memPassword;
	}
}
