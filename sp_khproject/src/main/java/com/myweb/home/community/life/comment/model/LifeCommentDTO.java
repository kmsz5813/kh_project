package com.myweb.home.community.life.comment.model;

import java.sql.Date;

public class LifeCommentDTO {

	private int comment_Id;
	private int comment_bId;
	private String user_Name;
	private String comment_Content;
	private Date comment_Date;
	private boolean comment_deleted;
	private int comment_like;
	public int getComment_Id() {
		return comment_Id;
	}
	public void setComment_Id(int comment_Id) {
		this.comment_Id = comment_Id;
	}
	public int getComment_bId() {
		return comment_bId;
	}
	public void setComment_bId(int comment_bId) {
		this.comment_bId = comment_bId;
	}
	public String getUser_Name() {
		return user_Name;
	}
	public void setUser_Name(String user_Name) {
		this.user_Name = user_Name;
	}
	public String getComment_Content() {
		return comment_Content;
	}
	public void setComment_Content(String comment_Content) {
		this.comment_Content = comment_Content;
	}
	public Date getComment_Date() {
		return comment_Date;
	}
	public void setComment_Date(Date comment_Date) {
		this.comment_Date = comment_Date;
	}
	public boolean isComment_deleted() {
		return comment_deleted;
	}
	public void setComment_deleted(boolean comment_deleted) {
		this.comment_deleted = comment_deleted;
	}
	public int getComment_like() {
		return comment_like;
	}
	public void setComment_like(int comment_like) {
		this.comment_like = comment_like;
	}
	@Override
	public String toString() {
		return "questionCommentDTO [comment_Id=" + comment_Id + ", comment_bId=" + comment_bId + ", user_Name="
				+ user_Name + ", comment_Content=" + comment_Content + ", comment_Date=" + comment_Date
				+ ", comment_deleted=" + comment_deleted + ", comment_like=" + comment_like + "]";
	}
	
	
	
}
