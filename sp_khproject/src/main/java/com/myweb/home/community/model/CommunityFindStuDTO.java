package com.myweb.home.community.model;

import java.sql.Date;

public class CommunityFindStuDTO {

	private int findStu_Id;
	private String user_Name;
	private String findStu_Title;
	private String findStu_Content;
	private Date findStu_Date;
	private int findStu_like;
	private int findStu_view;
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
	public String getFindStu_Title() {
		return findStu_Title;
	}
	public void setFindStu_Title(String findStu_Title) {
		this.findStu_Title = findStu_Title;
	}
	public String getFindStu_Content() {
		return findStu_Content;
	}
	public void setFindStu_Content(String findStu_Content) {
		this.findStu_Content = findStu_Content;
	}
	public Date getFindStu_Date() {
		return findStu_Date;
	}
	public void setFindStu_Date(Date findStu_Date) {
		this.findStu_Date = findStu_Date;
	}
	public int getFindStu_like() {
		return findStu_like;
	}
	public void setFindStu_like(int findStu_like) {
		this.findStu_like = findStu_like;
	}
	public int getFindStu_view() {
		return findStu_view;
	}
	public void setFindStu_view(int findStu_view) {
		this.findStu_view = findStu_view;
	}
	@Override
	public String toString() {
		return "CommunityFindStuDTO [findStu_Id=" + findStu_Id + ", user_Name=" + user_Name + ", findStu_Title="
				+ findStu_Title + ", findStu_Content=" + findStu_Content + ", findStu_Date=" + findStu_Date
				+ ", findStu_like=" + findStu_like + ", findStu_view=" + findStu_view + "]";
	}
	
	
	
}
