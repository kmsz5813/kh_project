package com.myweb.home.sel.model;

import java.sql.Date;

public class SelItemDTO {

	private int sel_id;
	private String sel_cusname;
	private String sel_selname;
	private String sel_interest;
	private String sel_location;
	private String sel_title;
	private String sel_content;
	private Date sel_writeday;
	private int sel_view;
	private int sel_like;
	private int sel_report;

	public int getSel_id() {
		return sel_id;
	}

	public void setSel_id(int sel_id) {
		this.sel_id = sel_id;
	}

	public String getSel_cusname() {
		return sel_cusname;
	}

	public void setSel_cusname(String sel_cusname) {
		this.sel_cusname = sel_cusname;
	}

	public String getSel_selname() {
		return sel_selname;
	}

	public void setSel_selname(String sel_selname) {
		this.sel_selname = sel_selname;
	}

	public String getSel_interest() {
		return sel_interest;
	}

	public void setSel_interest(String sel_interest) {
		this.sel_interest = sel_interest;
	}

	public String getSel_location() {
		return sel_location;
	}

	public void setSel_location(String sel_location) {
		this.sel_location = sel_location;
	}

	public String getSel_title() {
		return sel_title;
	}

	public void setSel_title(String sel_title) {
		this.sel_title = sel_title;
	}

	public String getSel_content() {
		return sel_content;
	}

	public void setSel_content(String sel_content) {
		this.sel_content = sel_content;
	}

	public Date getSel_writeday() {
		return sel_writeday;
	}

	public void setSel_writeday(Date sel_writeday) {
		this.sel_writeday = sel_writeday;
	}

	public int getSel_view() {
		return sel_view;
	}

	public void setSel_view(int sel_view) {
		this.sel_view = sel_view;
	}

	public int getSel_like() {
		return sel_like;
	}

	public void setSel_like(int sel_like) {
		this.sel_like = sel_like;
	}

	public int getSel_report() {
		return sel_report;
	}

	public void setSel_report(int sel_report) {
		this.sel_report = sel_report;
	}

	@Override
	public String toString() {
		return "SelItemDTO [sel_id=" + sel_id + ", sel_cusname=" + sel_cusname + ", sel_selname=" + sel_selname
				+ ", sel_interest=" + sel_interest + ", sel_location=" + sel_location + ", sel_title=" + sel_title
				+ ", sel_content=" + sel_content + ", sel_writeday=" + sel_writeday + ", sel_view=" + sel_view
				+ ", sel_like=" + sel_like + ", sel_report=" + sel_report + "]";
	}
	
	
}
