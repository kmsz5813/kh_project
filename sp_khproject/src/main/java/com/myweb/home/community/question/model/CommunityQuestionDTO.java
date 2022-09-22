package com.myweb.home.community.question.model;

import java.sql.Date;

public class CommunityQuestionDTO {

	private int question_Id;
	private String user_Name;
	private String question_Title;
	private String question_Content;
	private Date question_Date;
	private int question_like;
	private int question_view;
	
	public int getQuestion_Id() {
		return question_Id;
	}
	public void setQuestion_Id(int question_Id) {
		this.question_Id = question_Id;
	}
	public String getUser_Name() {
		return user_Name;
	}
	public void setUser_Name(String user_Name) {
		this.user_Name = user_Name;
	}
	public String getQuestion_Title() {
		return question_Title;
	}
	public void setQuestion_Title(String question_Title) {
		this.question_Title = question_Title;
	}
	public String getQuestion_Content() {
		return question_Content;
	}
	public void setQuestion_Content(String question_Content) {
		this.question_Content = question_Content;
	}
	public Date getQuestion_Date() {
		return question_Date;
	}
	public void setQuestion_Date(Date question_Date) {
		this.question_Date = question_Date;
	}
	public int getQuestion_like() {
		return question_like;
	}
	public void setQuestion_like(int question_like) {
		this.question_like = question_like;
	}
	public int getQuestion_view() {
		return question_view;
	}
	public void setQuestion_view(int question_view) {
		this.question_view = question_view;
	}
	@Override
	public String toString() {
		return "CommunityQuestionDTO [question_Id=" + question_Id + ", user_Name=" + user_Name + ", question_Title="
				+ question_Title + ", question_Content=" + question_Content + ", question_Date=" + question_Date
				+ ", question_like=" + question_like + ", question_view=" + question_view + "]";
	}
	
	
}
