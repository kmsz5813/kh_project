package com.myweb.home.community.vo;

public class CommunityVO {
	private int cum_id;
	private String cum_title;
	private String cum_content;
	
	public int getCum_id() {
		return cum_id;
	}
	
	public void setCum_id(int cum_id) {
		this.cum_id = cum_id;
	}
	
	public String getCum_title() {
		return cum_title;
	}
	
	public void setCum_title(String cum_title) {
		this.cum_title = cum_title;
	}
	
	public String getCum_content() {
		return cum_content;
	}
	
	public void setCum_content(String cum_content) {
		this.cum_content = cum_content;
	}
	
	@Override
	public String toString() {
		return "CommunityVO [cum_id=" + cum_id + ", cum_title=" + cum_title + ", cum_content=" + cum_content + "]";
	}
	
	
}
