package com.myweb.home.purchase.model;

public class UsePointVO {
	private String ac_name;	// 구매계정 닉네임
	private int use_point;	// 사용포인트
	private int earn_point;	// 적립 포인트
		
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
	public int getEarn_point() {
		return earn_point;
	}
	public void setEarn_point(int earn_point) {
		this.earn_point = earn_point;
	}
	@Override
	public String toString() {
		return "UsePointVO [ac_name=" + ac_name + ", use_point=" + use_point + ", earn_point=" + earn_point + "]";
	}
	
}
