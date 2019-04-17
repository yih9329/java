package com.lmp.mylib;

import java.io.UnsupportedEncodingException;

public class SeatWeb {
	private int seatNum;
	private String memName;
	private String memPassword;
	
	public int getSeatNum() {
		return seatNum;
	}
	public void setSeatNum(int seatNum) {
		this.seatNum = seatNum;
	}
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
	public String getMemPassword() {
		return memPassword;
	}
	public void setMemPassword(String memPassword) {
		this.memPassword = memPassword;
	}
}
