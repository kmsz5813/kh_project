package com.myweb.home.community.vo;

public class CommunityQuestionVO {
	private int question_id;
	private String question_title;
	private String question_content;
	public int getQuestion_id() {
		return question_id;
	}
	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}
	public String getQuestion_title() {
		return question_title;
	}
	public void setQuestion_title(String question_title) {
		this.question_title = question_title;
	}
	public String getQuestion_content() {
		return question_content;
	}
	public void setQuestion_content(String question_content) {
		this.question_content = question_content;
	}
	@Override
	public String toString() {
		return "CommunityQuestionVO [question_id=" + question_id + ", question_title=" + question_title
				+ ", question_content=" + question_content + "]";
	}
	
	
	
	
}
