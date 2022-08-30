package com.myweb.home.cus.model;

public class CusDTO {

	private String cus_email;
	private String cus_name;
	private String cus_pw;
	private String cus_job;
	private String cus_field;
	private String cus_interest;
	private int cus_index;
	private String cus_sendemail;
	public String getCus_email() {
		return cus_email;
	}
	public void setCus_email(String cus_email) {
		this.cus_email = cus_email;
	}
	public String getCus_name() {
		return cus_name;
	}
	public void setCus_name(String cus_name) {
		this.cus_name = cus_name;
	}
	public String getCus_pw() {
		return cus_pw;
	}
	public void setCus_pw(String cus_pw) {
		this.cus_pw = cus_pw;
	}
	public String getCus_job() {
		return cus_job;
	}
	public void setCus_job(String cus_job) {
		this.cus_job = cus_job;
	}
	public String getCus_field() {
		return cus_field;
	}
	public void setCus_field(String cus_field) {
		this.cus_field = cus_field;
	}
	public String getCus_interest() {
		return cus_interest;
	}
	public void setCus_interest(String cus_interest) {
		this.cus_interest = cus_interest;
	}
	public int getCus_index() {
		return cus_index;
	}
	public void setCus_index(int cus_index) {
		this.cus_index = cus_index;
	}
	public String getCus_sendemail() {
		return cus_sendemail;
	}
	public void setCus_sendemail(String cus_sendemail) {
		this.cus_sendemail = cus_sendemail;
	}
	@Override
	public String toString() {
		return "CustomerDTO [cus_email=" + cus_email + ", cus_name=" + cus_name + ", cus_pw=" + cus_pw + ", cus_job="
				+ cus_job + ", cus_field=" + cus_field + ", cus_interest=" + cus_interest + ", cus_index=" + cus_index
				+ ", cus_sendemail=" + cus_sendemail + "]";
	}
}
