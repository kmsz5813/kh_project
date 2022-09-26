package com.myweb.home.community.life.vo;

public class CommunityLifeVO {
	private int Life_id;
	private String Life_title;
	private String Life_content;
	public int getLife_id() {
		return Life_id;
	}
	public void setLife_id(int life_id) {
		Life_id = life_id;
	}
	public String getLife_title() {
		return Life_title;
	}
	public void setLife_title(String life_title) {
		Life_title = life_title;
	}
	public String getLife_content() {
		return Life_content;
	}
	public void setLife_content(String life_content) {
		Life_content = life_content;
	}
	@Override
	public String toString() {
		return "CommunityLifeVO [Life_id=" + Life_id + ", Life_title=" + Life_title + ", Life_content=" + Life_content
				+ "]";
	}
	
}
