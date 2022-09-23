package com.myweb.home.purchase.model;

public class UsePointVO {
	private String ac_name;
	private int use_point;

	public String getAc_name() {
		return ac_name;
	}
	public void setAc_name(String ac_name) {
		this.ac_name = ac_name;
	}
	public int getUse_point() {
		return use_point;
	}
	public void setUse_point(int use_point) {
		this.use_point = use_point;
	}
	
	@Override
	public String toString() {
		return "UsePointVO [ac_name=" + ac_name + ", use_point=" + use_point + "]";
	}
}
