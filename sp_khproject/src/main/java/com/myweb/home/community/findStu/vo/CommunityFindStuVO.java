package com.myweb.home.community.findStu.vo;

public class CommunityFindStuVO {
	private int findStu_id;
	private String findStu_title;
	private String findStu_content;
	public int getFindStu_id() {
		return findStu_id;
	}
	public void setFindStu_id(int findStu_id) {
		this.findStu_id = findStu_id;
	}
	public String getFindStu_title() {
		return findStu_title;
	}
	public void setFindStu_title(String findStu_title) {
		this.findStu_title = findStu_title;
	}
	public String getFindStu_content() {
		return findStu_content;
	}
	public void setFindStu_content(String findStu_content) {
		this.findStu_content = findStu_content;
	}
	@Override
	public String toString() {
		return "CommunityFindStuVO [findStu_id=" + findStu_id + ", findStu_title=" + findStu_title
				+ ", findStu_content=" + findStu_content + "]";
	}
	
	
	
}
