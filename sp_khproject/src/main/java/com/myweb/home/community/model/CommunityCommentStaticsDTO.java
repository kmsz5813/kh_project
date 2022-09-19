package com.myweb.home.community.model;

public class CommunityCommentStaticsDTO {
	private int cum_com_id;
	private String cum_com_name;
	private int cum_com_bId;
	private boolean cum_com_liked;
	
	public int getCum_com_id() {
		return cum_com_id;
	}
	
	public void setCum_com_id(int cum_com_id) {
		this.cum_com_id = cum_com_id;
	}
	
	public String getCum_com_name() {
		return cum_com_name;
	}
	
	public void setCum_com_name(String cum_com_name) {
		this.cum_com_name = cum_com_name;
	}
	
	public int getCum_com_bId() {
		return cum_com_bId;
	}
	
	public void setCum_com_bId(int cum_com_bId) {
		this.cum_com_bId = cum_com_bId;
	}
	
	public boolean isCum_com_liked() {
		return cum_com_liked;
	}
	
	public void setCum_com_liked(boolean cum_com_liked) {
		this.cum_com_liked = cum_com_liked;
	}

	@Override
	public String toString() {
		return "CommunityCommentStaticsDTO [cum_com_id=" + cum_com_id + ", cum_com_name=" + cum_com_name
				+ ", cum_com_bId=" + cum_com_bId + ", cum_com_liked=" + cum_com_liked + "]";
	}
	
}
