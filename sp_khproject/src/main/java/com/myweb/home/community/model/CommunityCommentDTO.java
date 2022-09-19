package com.myweb.home.community.model;

import java.sql.Date;

public class CommunityCommentDTO {
	private int cum_com_id;
	private int cum_com_bId;
	private String cum_com_Name;
	private String cum_com_content;
	private Date cum_com_writeday;
	//private boolean deleted;
	//private boolean hidden;
	private int cum_com_like;
	
	public int getCum_com_id() {
		return cum_com_id;
	}
	
	public void setCum_com_id(int cum_com_id) {
		this.cum_com_id = cum_com_id;
	}
	
	public int getCum_com_bId() {
		return cum_com_bId;
	}
	
	public void setCum_com_bId(int cum_com_bId) {
		this.cum_com_bId = cum_com_bId;
	}
	
	public String getCum_com_Name() {
		return cum_com_Name;
	}
	
	public void setCum_com_Name(String cum_com_Name) {
		this.cum_com_Name = cum_com_Name;
	}
	
	public String getCum_com_content() {
		return cum_com_content;
	}
	
	public void setCum_com_content(String cum_com_content) {
		this.cum_com_content = cum_com_content;
	}
	
	public Date getCum_com_writeday() {
		return cum_com_writeday;
	}
	
	public void setCum_com_writeday(Date cum_com_writeday) {
		this.cum_com_writeday = cum_com_writeday;
	}
	
	public int getCum_com_like() {
		return cum_com_like;
	}
	
	public void setCum_com_like(int cum_com_like) {
		this.cum_com_like = cum_com_like;
	}

	@Override
	public String toString() {
		return "CommunityCommentDTO [cum_com_id=" + cum_com_id + ", cum_com_bId=" + cum_com_bId + ", cum_com_Name="
				+ cum_com_Name + ", cum_com_content=" + cum_com_content + ", cum_com_writeday=" + cum_com_writeday
				+ ", cum_com_like=" + cum_com_like + "]";
	}
	
	
}
