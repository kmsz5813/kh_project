package com.myweb.home.community.life.model;

import java.sql.Date;

public class CommunityLifeStaticsDTO {

	private int life_Id;
	private String user_Name;
	private int life_bId;
	private boolean life_viewed;
	private Date life_latestViewDate;
	private boolean life_liked;
	public int getLife_Id() {
		return life_Id;
	}
	public void setLife_Id(int life_Id) {
		this.life_Id = life_Id;
	}
	public String getUser_Name() {
		return user_Name;
	}
	public void setUser_Name(String user_Name) {
		this.user_Name = user_Name;
	}
	public int getLife_bId() {
		return life_bId;
	}
	public void setLife_bId(int life_bId) {
		this.life_bId = life_bId;
	}
	public boolean isLife_viewed() {
		return life_viewed;
	}
	public void setLife_viewed(boolean life_viewed) {
		this.life_viewed = life_viewed;
	}
	public Date getLife_latestViewDate() {
		return life_latestViewDate;
	}
	public void setLife_latestViewDate(Date life_latestViewDate) {
		this.life_latestViewDate = life_latestViewDate;
	}
	public boolean isLife_liked() {
		return life_liked;
	}
	public void setLife_liked(boolean life_liked) {
		this.life_liked = life_liked;
	}
	@Override
	public String toString() {
		return "CommunityLifeStaticsDTO [life_Id=" + life_Id + ", user_Name=" + user_Name + ", life_bId=" + life_bId
				+ ", life_viewed=" + life_viewed + ", life_latestViewDate=" + life_latestViewDate + ", life_liked="
				+ life_liked + "]";
	}
	
}
