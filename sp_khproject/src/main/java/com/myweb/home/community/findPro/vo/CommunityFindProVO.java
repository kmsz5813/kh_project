package com.myweb.home.community.findPro.vo;

public class CommunityFindProVO {
	private int findPro_id;
	private String findPro_title;
	private String findPro_content;
	public int getFindPro_id() {
		return findPro_id;
	}
	public void setFindPro_id(int findPro_id) {
		this.findPro_id = findPro_id;
	}
	public String getFindPro_title() {
		return findPro_title;
	}
	public void setFindPro_title(String findPro_title) {
		this.findPro_title = findPro_title;
	}
	public String getFindPro_content() {
		return findPro_content;
	}
	public void setFindPro_content(String findPro_content) {
		this.findPro_content = findPro_content;
	}
	@Override
	public String toString() {
		return "CommunityFindProVO [findPro_id=" + findPro_id + ", findPro_title=" + findPro_title
				+ ", findPro_content=" + findPro_content + "]";
	}
	
	
}
