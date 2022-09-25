package com.myweb.home.message.model;

public class MessageDTO {

	private String ch_content;
	
	
	@Override
	public String toString() {
		return "MessageDTO [ch_content=" + ch_content + "]";
	}
	public String getCh_content() {
		return ch_content;
	}
	public void setCh_content(String ch_content) {
		this.ch_content = ch_content;
	}
	
}
