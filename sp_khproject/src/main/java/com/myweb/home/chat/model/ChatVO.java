package com.myweb.home.chat.model;


public class ChatVO {
	
	private int id;
	private String sender;
	private String message;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
//	public Date getRegdate() {
//		return regdate;
//	}
//
//	public void setRegdate(Date regdate) {
//		this.regdate = regdate;
//	}
	
	
	
	@Override
	public String toString() {
		return "ChatVO [id=" + id + ", sender=" + sender + ", message=" + message + "]";
	}
	
//	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="Asia/Seoul")
//	private Date regdate;
}
