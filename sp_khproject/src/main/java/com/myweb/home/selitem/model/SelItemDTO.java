package com.myweb.home.selitem.model;

import java.sql.Date;

public class SelItemDTO {

	private int sel_id;
	private String sel_name;
	private String sel_field;	// 분야
	private String sel_location;
	private String sel_title;
	private String sel_content;
	private int sel_price;
	private Date sel_writeday;
	private int sel_number;	// 판매횟수
	private int sel_view;
	private int sel_like;
	private int sel_reviewCount;
	private double sel_starScore;
	private int sel_report;
	
	private String uuidName;
	private String location;
	public String getLocation() {
		return location;
	}



	public void setLocation(String location) {
		this.location = location;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	private String url;

	public String getUuidName() {
		return uuidName;
	}

	public void setUuidName(String uuidName) {
		this.uuidName = uuidName;
	}

	public int getSel_id() {
		return sel_id;
	}

	public void setSel_id(int sel_id) {
		this.sel_id = sel_id;
	}

	public String getSel_name() {
		return sel_name;
	}

	public void setSel_name(String sel_name) {
		this.sel_name = sel_name;
	}

	public String getSel_field() {
		return sel_field;
	}

	public void setSel_field(String sel_field) {
		this.sel_field = sel_field;
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
	
	public int getSel_price() {
		return sel_price;
	}
	public void setSel_price(int sel_price) {
		this.sel_price = sel_price;
	}
	
	public Date getSel_writeday() {
		return sel_writeday;
	}

	public void setSel_writeday(Date sel_writeday) {
		this.sel_writeday = sel_writeday;
	}
	
	public int getSel_number() {
		return sel_number;
	}

	public void setSel_number(int sel_number) {
		this.sel_view = sel_number;
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
	
	public int getSel_reviewCount() {
		return sel_reviewCount;
	}

	public void setSel_reviewCount(int sel_reviewCount) {
		this.sel_reviewCount = sel_reviewCount;
	}

	public double getSel_starScore() {
		return sel_starScore;
	}

	public void setSel_starScore(double sel_starScore) {
		this.sel_starScore = sel_starScore;
	}
	
	
	@Override
	public String toString() {
		return "SelItemDTO [sel_id=" + sel_id + ", sel_name=" + sel_name + ", sel_field=" + sel_field
				+ ", sel_location=" + sel_location + ", sel_title=" + sel_title + ", sel_content=" + sel_content
				+ ", sel_writeday=" + sel_writeday + ", sel_view=" + sel_view + ", sel_like=" + sel_like
				+ ", sel_report=" + sel_report + ", uuidName=" + uuidName + ", location=" + location + ", url=" + url
				+ "]";
	}


}