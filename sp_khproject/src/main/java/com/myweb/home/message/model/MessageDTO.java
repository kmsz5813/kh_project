package com.myweb.home.message.model;

public class MessageDTO {

	private int msg_id;
	private String msg_cus_sender;
	private String msg_sel_sender;
	private String msg_cus_receiver;
	private String msg_sel_receiver;
	private String msg_title;
	private String msg_content;

	public int getMsg_id() {
		return msg_id;
	}

	public String getMsg_cus_sender() {
		return msg_cus_sender;
	}

	public void setMsg_cus_sender(String msg_cus_sender) {
		this.msg_cus_sender = msg_cus_sender;
	}

	public String getMsg_sel_sender() {
		return msg_sel_sender;
	}

	public void setMsg_sel_sender(String msg_sel_sender) {
		this.msg_sel_sender = msg_sel_sender;
	}

	public String getMsg_cus_receiver() {
		return msg_cus_receiver;
	}

	public void setMsg_cus_receiver(String msg_cus_receiver) {
		this.msg_cus_receiver = msg_cus_receiver;
	}

	public String getMsg_sel_receiver() {
		return msg_sel_receiver;
	}

	public void setMsg_sel_receiver(String msg_sel_receiver) {
		this.msg_sel_receiver = msg_sel_receiver;
	}

	public String getMsg_title() {
		return msg_title;
	}

	public void setMsg_title(String msg_title) {
		this.msg_title = msg_title;
	}

	public String getMsg_content() {
		return msg_content;
	}

	public void setMsg_content(String msg_content) {
		this.msg_content = msg_content;
	}

	public void setMsg_id(int msg_id) {
		this.msg_id = msg_id;
	}

	@Override
	public String toString() {
		return "MessageDTO [msg_id=" + msg_id + ", msg_cus_sender=" + msg_cus_sender + ", msg_sel_sender="
				+ msg_sel_sender + ", msg_cus_receiver=" + msg_cus_receiver + ", msg_sel_receiver=" + msg_sel_receiver
				+ ", msg_title=" + msg_title + ", msg_content=" + msg_content + "]";
	}

}
