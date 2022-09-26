package com.myweb.home.community.life.model;

import java.sql.Date;

public class CommunityLifeDTO {

	private int life_Id;
	private String user_Name;
	private String life_Title;
	private String life_Content;
	private Date life_Date;
	private int life_like;
	private int life_view;
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
	public String getLife_Title() {
		return life_Title;
	}
	public void setLife_Title(String life_Title) {
		this.life_Title = life_Title;
	}
	public String getLife_Content() {
		return life_Content;
	}
	public void setLife_Content(String life_Content) {
		this.life_Content = life_Content;
	}
	public Date getLife_Date() {
		return life_Date;
	}
	public void setLife_Date(Date life_Date) {
		this.life_Date = life_Date;
	}
	public int getLife_like() {
		return life_like;
	}
	public void setLife_like(int life_like) {
		this.life_like = life_like;
	}
	public int getLife_view() {
		return life_view;
	}
	public void setLife_view(int life_view) {
		this.life_view = life_view;
	}
	@Override
	public String toString() {
		return "CommunityLifeDTO [life_Id=" + life_Id + ", user_Name=" + user_Name + ", life_Title=" + life_Title
				+ ", life_Content=" + life_Content + ", life_Date=" + life_Date + ", life_like=" + life_like
				+ ", life_view=" + life_view + "]";
	}
	
}
