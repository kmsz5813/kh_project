package com.myweb.home.community.findStu.model;

import java.sql.Date;

public class CommunityFindStuStaticsDTO {

	private int findStu_Id;
	private String user_Name;
	private int findStu_bId;
	private boolean findStu_viewed;
	private Date findStu_latestViewDate;
	private boolean findStu_liked;
	public int getFindStu_Id() {
		return findStu_Id;
	}
	public void setFindStu_Id(int findStu_Id) {
		this.findStu_Id = findStu_Id;
	}
	public String getUser_Name() {
		return user_Name;
	}
	public void setUser_Name(String user_Name) {
		this.user_Name = user_Name;
	}
	public int getFindStu_bId() {
		return findStu_bId;
	}
	public void setFindStu_bId(int findStu_bId) {
		this.findStu_bId = findStu_bId;
	}
	public boolean isFindStu_viewed() {
		return findStu_viewed;
	}
	public void setFindStu_viewed(boolean findStu_viewed) {
		this.findStu_viewed = findStu_viewed;
	}
	public Date getFindStu_latestViewDate() {
		return findStu_latestViewDate;
	}
	public void setFindStu_latestViewDate(Date findStu_latestViewDate) {
		this.findStu_latestViewDate = findStu_latestViewDate;
	}
	public boolean isFindStu_liked() {
		return findStu_liked;
	}
	public void setFindStu_liked(boolean findStu_liked) {
		this.findStu_liked = findStu_liked;
	}
	@Override
	public String toString() {
		return "CommunityFindStuStaticsDTO [findStu_Id=" + findStu_Id + ", user_Name=" + user_Name + ", findStu_bId="
				+ findStu_bId + ", findStu_viewed=" + findStu_viewed + ", findStu_latestViewDate="
				+ findStu_latestViewDate + ", findStu_liked=" + findStu_liked + "]";
	}
	
	
	
	
}
