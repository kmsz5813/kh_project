package com.myweb.home.Accounts.model;

import java.sql.Date;

public class AccountsDTO {
	
	private int ac_number;
	private String ac_email;
	private String ac_name;
	private String ac_pw;
	private String ac_job;
	private String ac_field;
	private String ac_interest;
	private int ac_index;
	private Date ac_signday;
	private String ac_sendemail;
	private String ac_ip;
	
	@Override
	public String toString() {
		return "AccountsDTO [ac_number=" + ac_number + ", ac_email=" + ac_email + ", ac_name=" + ac_name + ", ac_pw="
				+ ac_pw + ", ac_job=" + ac_job + ", ac_field=" + ac_field + ", ac_interest=" + ac_interest
				+ ", ac_index=" + ac_index + ", ac_signday=" + ac_signday + ", ac_sendemail=" + ac_sendemail
				+ ", ac_ip=" + ac_ip + "]";
	}
	
	public int getAc_number() {
		return ac_number;
	}
	public void setAc_number(int ac_number) {
		this.ac_number = ac_number;
	}
	public String getAc_email() {
		return ac_email;
	}
	public void setAc_email(String ac_email) {
		this.ac_email = ac_email;
	}
	public String getAc_name() {
		return ac_name;
	}
	public void setAc_name(String ac_name) {
		this.ac_name = ac_name;
	}
	public String getAc_pw() {
		return ac_pw;
	}
	public void setAc_pw(String ac_pw) {
		this.ac_pw = ac_pw;
	}
	public String getAc_job() {
		return ac_job;
	}
	public void setAc_job(String ac_job) {
		this.ac_job = ac_job;
	}
	public String getAc_field() {
		return ac_field;
	}
	public void setAc_field(String ac_field) {
		this.ac_field = ac_field;
	}
	public String getAc_interest() {
		return ac_interest;
	}
	public void setAc_interest(String ac_interest) {
		this.ac_interest = ac_interest;
	}
	public int getAc_index() {
		return ac_index;
	}
	public void setAc_index(int ac_index) {
		this.ac_index = ac_index;
	}
	public Date getAc_signday() {
		return ac_signday;
	}
	public void setAc_signday(Date ac_signday) {
		this.ac_signday = ac_signday;
	}
	public String getAc_sendemail() {
		return ac_sendemail;
	}
	public void setAc_sendemail(String ac_sendemail) {
		this.ac_sendemail = ac_sendemail;
	}
	public String getAc_ip() {
		return ac_ip;
	}
	public void setAc_ip(String ac_ip) {
		this.ac_ip = ac_ip;
	}

}
