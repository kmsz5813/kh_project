package com.myweb.home.selitem.model;

import java.sql.Date;

public class SelItemStaticsDTO {

	private int id;
	private String ac_name;
	private int sel_id;
	private boolean liked;
	
	public String getAc_name() {
		return ac_name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSel_id() {
		return sel_id;
	}
	public void setSel_id(int sel_id) {
		this.sel_id = sel_id;
	}
	public boolean isLiked() {
		return liked;
	}
	public void setLiked(boolean liked) {
		this.liked = liked;
	}
	public void setAc_name(String ac_name) {
		this.ac_name = ac_name;
	}
	@Override
	public String toString() {
		return "SelItemStaticsDTO [id=" + id + ", ac_name=" + ac_name + ", sel_id=" + sel_id + ", liked=" + liked + "]";
	}

	






	
}
