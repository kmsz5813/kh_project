package com.myweb.home.purchase.model;

import java.sql.Date;

public class EventCouponDTO {
	private int evtcou_seq;
	private String evtcou_name;
	private Date evtcou_startDate;
	private Date evtcou_endDate;
	private int evtcou_salePercent;
	public int getEvtcou_seq() {
		return evtcou_seq;
	}
	public void setEvtcou_seq(int evtcou_seq) {
		this.evtcou_seq = evtcou_seq;
	}
	public String getEvtcou_name() {
		return evtcou_name;
	}
	public void setEvtcou_name(String evtcou_name) {
		this.evtcou_name = evtcou_name;
	}
	public Date getEvtcou_startDate() {
		return evtcou_startDate;
	}
	public void setEvtcou_startDate(Date evtcou_startDate) {
		this.evtcou_startDate = evtcou_startDate;
	}
	public Date getEvtcou_endDate() {
		return evtcou_endDate;
	}
	public void setEvtcou_endDate(Date evtcou_endDate) {
		this.evtcou_endDate = evtcou_endDate;
	}
	public int getEvtcou_salePercent() {
		return evtcou_salePercent;
	}
	public void setEvtcou_salePercent(int evtcou_salePercent) {
		this.evtcou_salePercent = evtcou_salePercent;
	}
	@Override
	public String toString() {
		return "EventCouponDTO [evtcou_seq=" + evtcou_seq + ", evtcou_name=" + evtcou_name + ", evtcou_startDate="
				+ evtcou_startDate + ", evtcou_endDate=" + evtcou_endDate + ", evtcou_salePercent=" + evtcou_salePercent
				+ "]";
	}
	
	
	
	
}
