package com.myweb.home.selitem.model;

import java.sql.Date;

public class ReviewDetailVO {
	
	private int sel_id;
	private int star;
	
	public int getSel_id() {
		return sel_id;
	}
	public void setSel_id(int sel_id) {
		this.sel_id = sel_id;
	}
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}
	
	@Override
	public String toString() {
		return "ReviewDetailVO [sel_id=" + sel_id + ", star=" + star + "]";
	}

	
}