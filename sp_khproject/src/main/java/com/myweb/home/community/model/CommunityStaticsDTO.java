package com.myweb.home.community.model;

import java.sql.Date;

public class CommunityStaticsDTO {
	private int cum_id;
	private String cum_name;
	private int cum_bId;
	private boolean cum_viewed;
	private Date cum_latestViewDate;
	private boolean cum_liked;
	
	public int getCum_id() {
		return cum_id;
	}
	
	public void setCum_id(int cum_id) {
		this.cum_id = cum_id;
	}
	
	public String getCum_name() {
		return cum_name;
	}
	
	public void setCum_name(String cum_name) {
		this.cum_name = cum_name;
	}
	
	public int getCum_bId() {
		return cum_bId;
	}
	
	public void setCum_bId(int cum_bId) {
		this.cum_bId = cum_bId;
	}
	
	public boolean isCum_viewed() {
		return cum_viewed;
	}
	
	public void setCum_viewed(boolean cum_viewed) {
		this.cum_viewed = cum_viewed;
	}
	
	public Date getCum_latestViewDate() {
		return cum_latestViewDate;
	}
	
	public void setCum_latestViewDate(Date cum_latestViewDate) {
		this.cum_latestViewDate = cum_latestViewDate;
	}
	
	public boolean isCum_liked() {
		return cum_liked;
	}
	
	public void setCum_liked(boolean cum_liked) {
		this.cum_liked = cum_liked;
	}

	@Override
	public String toString() {
		return "CommunityStaticsDTO [cum_id=" + cum_id + ", cum_name=" + cum_name
				+ ", cum_bId=" + cum_bId + ", cum_viewed=" + cum_viewed + ", cum_latestViewDate=" + cum_latestViewDate
				+ ", cum_liked=" + cum_liked + "]";
	}
	
}
