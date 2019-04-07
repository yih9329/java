package com.lmp.mylib;

public class MemberPhone {
	private String ph_1;
	private String ph_2;
	private String ph_3;
	
	public String getPh_1() {
		return ph_1;
	}
	public void setPh_1(String ph_1) {
		this.ph_1 = ph_1;
	}
	public String getPh_2() {
		return ph_2;
	}
	public void setPh_2(String ph_2) {
		this.ph_2 = ph_2;
	}
	public String getPh_3() {
		return ph_3;
	}
	public void setPh_3(String ph_3) {
		this.ph_3 = ph_3;
	}
	
	@Override
	public String toString() {
		return ph_1 + "-" + ph_2 + "-" + ph_3;
	}
}
