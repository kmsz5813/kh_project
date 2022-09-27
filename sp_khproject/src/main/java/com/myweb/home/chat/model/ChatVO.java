package com.myweb.home.chat.model;

import java.util.Date;

public class ChatVO {
	
	private int id;
	private String item_id;
	private String sender;
	private String receiver;
	private String message;
	private Date writeday;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getItem_id() {
		return item_id;
	}
	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getWriteday() {
		return writeday;
	}
	public void setWriteday(Date writeday) {
		this.writeday = writeday;
	}
	@Override
	public String toString() {
		return "ChatVO [id=" + id + ", item_id=" + item_id + ", sender=" + sender + ", receiver=" + receiver
				+ ", message=" + message + ", writeday=" + writeday + "]";
	}
	
	
	

	
	
	
}
