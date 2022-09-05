package com.myweb.home.community.model;

import java.sql.Date;

public class CommunityDTO {

	private int cum_id;
	private int ac_number;
	private String cum_main;
	private String cum_title;
	private String cum_content;
	private Date cum_writeday;
	private int cum_like;
	private int cum_view;
	private int cum_report;
	
	public int getCum_id() {
		return cum_id;
	}
	
	public void setCum_id(int cum_id) {
		this.cum_id = cum_id;
	}
	
	public int getAc_number() {
		return ac_number;
	}
	
	public void setAc_number(int ac_number) {
		this.ac_number = ac_number;
	}
	
	public String getCum_main() {
		return cum_main;
	}
	
	public void setCum_main(String cum_main) {
		this.cum_main = cum_main;
	}
	
	public String getCum_title() {
		return cum_title;
	}
	
	public void setCum_title(String cum_title) {
		this.cum_title = cum_title;
	}
	
	public String getCum_content() {
		return cum_content;
	}
	
	public void setCum_content(String cum_content) {
		this.cum_content = cum_content;
	}
	
	public Date getCum_writeday() {
		return cum_writeday;
	}
	
	public void setCum_writeday(Date cum_writeday) {
		this.cum_writeday = cum_writeday;
	}
	
	public int getCum_like() {
		return cum_like;
	}
	
	public void setCum_like(int cum_like) {
		this.cum_like = cum_like;
	}
	
	public int getCum_view() {
		return cum_view;
	}
	
	public void setCum_view(int cum_view) {
		this.cum_view = cum_view;
	}
	
	public int getCum_report() {
		return cum_report;
	}
	
	public void setCum_report(int cum_report) {
		this.cum_report = cum_report;
	}

	@Override
	public String toString() {
		return "CommunityDTO [cum_id=" + cum_id + ", ac_number=" + ac_number
				+ ", cum_main=" + cum_main + ", cum_title=" + cum_title + ", cum_content=" + cum_content
				+ ", cum_writeday=" + cum_writeday + ", cum_like=" + cum_like + ", cum_view=" + cum_view
				+ ", cum_report=" + cum_report + "]";
	}	
	
}
