package com.myweb.home.community.question.model;

import java.sql.Date;

public class CommunityQuestionStaticsDTO {

	private int question_Id;
	private String user_Name;
	private int question_bId;
	private boolean question_viewed;
	private Date question_latestViewDate;
	private boolean question_liked;
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
	public int getQuestion_bId() {
		return question_bId;
	}
	public void setQuestion_bId(int question_bId) {
		this.question_bId = question_bId;
	}
	public boolean isQuestion_viewed() {
		return question_viewed;
	}
	public void setQuestion_viewed(boolean question_viewed) {
		this.question_viewed = question_viewed;
	}
	public Date getQuestion_latestViewDate() {
		return question_latestViewDate;
	}
	public void setQuestion_latestViewDate(Date question_latestViewDate) {
		this.question_latestViewDate = question_latestViewDate;
	}
	public boolean isQuestion_liked() {
		return question_liked;
	}
	public void setQuestion_liked(boolean question_liked) {
		this.question_liked = question_liked;
	}
	@Override
	public String toString() {
		return "CommunityQuestionStaticsDTO [question_Id=" + question_Id + ", user_Name=" + user_Name
				+ ", question_bId=" + question_bId + ", question_viewed=" + question_viewed
				+ ", question_latestViewDate=" + question_latestViewDate + ", question_liked=" + question_liked + "]";
	}
	
	
	
}
