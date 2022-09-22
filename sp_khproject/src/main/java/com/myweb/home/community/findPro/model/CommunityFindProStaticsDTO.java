package com.myweb.home.community.model;

import java.sql.Date;

public class CommunityFindProStaticsDTO {

	private int findPro_Id;
	private String user_Name;
	private int findPro_bId;
	private boolean findPro_viewed;
	private Date findPro_latestViewDate;
	private boolean findPro_liked;
	public int getFindPro_Id() {
		return findPro_Id;
	}
	public void setFindPro_Id(int findPro_Id) {
		this.findPro_Id = findPro_Id;
	}
	public String getUser_Name() {
		return user_Name;
	}
	public void setUser_Name(String user_Name) {
		this.user_Name = user_Name;
	}
	public int getFindPro_bId() {
		return findPro_bId;
	}
	public void setFindPro_bId(int findPro_bId) {
		this.findPro_bId = findPro_bId;
	}
	public boolean isFindPro_viewed() {
		return findPro_viewed;
	}
	public void setFindPro_viewed(boolean findPro_viewed) {
		this.findPro_viewed = findPro_viewed;
	}
	public Date getFindPro_latestViewDate() {
		return findPro_latestViewDate;
	}
	public void setFindPro_latestViewDate(Date findPro_latestViewDate) {
		this.findPro_latestViewDate = findPro_latestViewDate;
	}
	public boolean isFindPro_liked() {
		return findPro_liked;
	}
	public void setFindPro_liked(boolean findPro_liked) {
		this.findPro_liked = findPro_liked;
	}
	@Override
	public String toString() {
		return "CommunityFindProStaticsDTO [findPro_Id=" + findPro_Id + ", user_Name=" + user_Name + ", findPro_bId="
				+ findPro_bId + ", findPro_viewed=" + findPro_viewed + ", findPro_latestViewDate="
				+ findPro_latestViewDate + ", findPro_liked=" + findPro_liked + "]";
	}
	
	
}
