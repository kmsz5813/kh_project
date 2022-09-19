package com.myweb.home.community.model;

import java.sql.Date;

public class CommunityFindProDTO {

	private int findPro_Id;
	private String user_Name;
	private String findPro_Title;
	private String findPro_Content;
	private Date findPro_Date;
	private int findPro_like;
	private int findPro_view;
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
	public String getFindPro_Title() {
		return findPro_Title;
	}
	public void setFindPro_Title(String findPro_Title) {
		this.findPro_Title = findPro_Title;
	}
	public String getFindPro_Content() {
		return findPro_Content;
	}
	public void setFindPro_Content(String findPro_Content) {
		this.findPro_Content = findPro_Content;
	}
	public Date getFindPro_Date() {
		return findPro_Date;
	}
	public void setFindPro_Date(Date findPro_Date) {
		this.findPro_Date = findPro_Date;
	}
	public int getFindPro_like() {
		return findPro_like;
	}
	public void setFindPro_like(int findPro_like) {
		this.findPro_like = findPro_like;
	}
	public int getFindPro_view() {
		return findPro_view;
	}
	public void setFindPro_view(int findPro_view) {
		this.findPro_view = findPro_view;
	}
	@Override
	public String toString() {
		return "CommunityFindProDTO [findPro_Id=" + findPro_Id + ", user_Name=" + user_Name + ", findPro_Title="
				+ findPro_Title + ", findPro_Content=" + findPro_Content + ", findPro_Date=" + findPro_Date
				+ ", findPro_like=" + findPro_like + ", findPro_view=" + findPro_view + "]";
	}
	
}
