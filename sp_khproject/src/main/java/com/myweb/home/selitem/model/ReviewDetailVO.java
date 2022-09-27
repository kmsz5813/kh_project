package com.myweb.home.selitem.model;

import java.sql.Date;

public class ReviewDetailVO {
	
	private int sel_id;
	private double star;
	private String sel_name;
	private int count;
	
	
	public int getSel_id() {
		return sel_id;
	}
	public void setSel_id(int sel_id) {
		this.sel_id = sel_id;
	}
	public double getStar() {
		return star;
	}
	public void setStar(double star) {
		this.star = star;
	}
	public String getSel_name() {
		return sel_name;
	}
	public void setSel_name(String sel_name) {
		this.sel_name = sel_name;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "ReviewDetailVO [sel_id=" + sel_id + ", star=" + star + ", sel_name=" + sel_name + ", count=" + count
				+ "]";
	}
	
	

	
}