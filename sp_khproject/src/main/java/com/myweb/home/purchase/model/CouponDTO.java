package com.myweb.home.purchase.model;

import java.sql.Date;

public class CouponDTO {
	private int coupon_number;			// 쿠폰 고유번호
	private String coupon_name;			// 쿠폰명(설명_
	private Date coupon_startDate;		// 쿠폰 다운로드일(시작일)
	private Date coupon_endDate;		// 쿠폰 마감일
	private String coupon_userName;		// 쿠폰 다운받은 회원닉네임
	private String coupon_used;			// 사용됐으면 'Y' 아니면 null
	private int coupon_salePercent;		// 쿠폰 할인 %
	
	public int getCoupon_number() {
		return coupon_number;
	}
	public void setCoupon_number(int coupon_number) {
		this.coupon_number = coupon_number;
	}
	public String getCoupon_name() {
		return coupon_name;
	}
	public void setCoupon_name(String coupon_name) {
		this.coupon_name = coupon_name;
	}
	public Date getCoupon_startDate() {
		return coupon_startDate;
	}
	public void setCoupon_startDate(Date coupon_startDate) {
		this.coupon_startDate = coupon_startDate;
	}
	public Date getCoupon_endDate() {
		return coupon_endDate;
	}
	public void setCoupon_endDate(Date coupon_endDate) {
		this.coupon_endDate = coupon_endDate;
	}
	public String getCoupon_userName() {
		return coupon_userName;
	}
	public void setCoupon_userName(String coupon_userName) {
		this.coupon_userName = coupon_userName;
	}
	public String getCoupon_used() {
		return coupon_used;
	}
	public void setCoupon_used(String coupon_used) {
		this.coupon_used = coupon_used;
	}
	public int getCoupon_salePercent() {
		return coupon_salePercent;
	}
	public void setCoupon_salePercent(int coupon_salePercent) {
		this.coupon_salePercent = coupon_salePercent;
	}
	
	@Override
	public String toString() {
		return "CouponDTO [coupon_number=" + coupon_number + ", coupon_name=" + coupon_name + ", coupon_startdate="
				+ coupon_startDate + ", coupon_enddate=" + coupon_endDate + ", coupon_userName=" + coupon_userName
				+ ", coupon_used=" + coupon_used + ", coupon_salePercent=" + coupon_salePercent + "]";
	}
	
	
}
