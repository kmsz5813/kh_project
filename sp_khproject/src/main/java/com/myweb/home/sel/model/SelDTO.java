package com.myweb.home.sel.model;

public class SelDTO {
	
	private String sel_email;
	private String sel_name;
	private String sel_pw;
	private String sel_job;
	private String sel_field;
	private String sel_interest;
	private int sel_index;
	private String sel_sendemail;
	
	@Override
	public String toString() {
		return "SelDTO [sel_email=" + sel_email + ", sel_name=" + sel_name + ", sel_pw=" + sel_pw + ", sel_job="
				+ sel_job + ", sel_field=" + sel_field + ", sel_interest=" + sel_interest + ", sel_index=" + sel_index
				+ ", sel_sendemail=" + sel_sendemail + "]";
	}
	public String getSel_email() {
		return sel_email;
	}
	public void setSel_email(String sel_email) {
		this.sel_email = sel_email;
	}
	public String getSel_name() {
		return sel_name;
	}
	public void setSel_name(String sel_name) {
		this.sel_name = sel_name;
	}
	public String getSel_pw() {
		return sel_pw;
	}
	public void setSel_pw(String sel_pw) {
		this.sel_pw = sel_pw;
	}
	public String getSel_job() {
		return sel_job;
	}
	public void setSel_job(String sel_job) {
		this.sel_job = sel_job;
	}
	public String getSel_field() {
		return sel_field;
	}
	public void setSel_field(String sel_field) {
		this.sel_field = sel_field;
	}
	public String getSel_interest() {
		return sel_interest;
	}
	public void setSel_interest(String sel_interest) {
		this.sel_interest = sel_interest;
	}
	public int getSel_index() {
		return sel_index;
	}
	public void setSel_index(int sel_index) {
		this.sel_index = sel_index;
	}
	public String getSel_sendemail() {
		return sel_sendemail;
	}
	public void setSel_sendemail(String sel_sendemail) {
		this.sel_sendemail = sel_sendemail;
	}

}
